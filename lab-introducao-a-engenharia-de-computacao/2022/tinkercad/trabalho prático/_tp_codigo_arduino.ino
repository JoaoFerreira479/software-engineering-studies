#include <Keypad.h>
#include <Servo.h>
#include <LiquidCrystal.h>

namespace Config {

  constexpr int BUZZER_PIN       = 10;
  constexpr int LED_VERMELHO_PIN = 12;
  constexpr int LED_VERDE_PIN   = 11;
  constexpr int SERVO_PIN       = 13;

  constexpr size_t PASSWORD_MAX_LEN = 8;
  static const char PASSWORD[]      = "1A4B7C";
  constexpr size_t PASSWORD_LEN     = sizeof(PASSWORD) - 1;

  constexpr byte KEYPAD_ROWS = 4;
  constexpr byte KEYPAD_COLS = 4;

  constexpr unsigned long MASK_DIGIT_MS     = 500;
  constexpr unsigned long UNLOCK_DURATION_MS = 5000;
  constexpr unsigned long BEEP_DURATION_MS   = 400;
  constexpr int BEEP_FREQ_LOCKED   = 293;
  constexpr int BEEP_FREQ_UNLOCKED = 500;

  constexpr int SERVO_ANGLE_LOCKED   = 0;
  constexpr int SERVO_ANGLE_UNLOCKED = 90;
}

class InputBuffer {
public:
  static constexpr size_t CAPACITY = Config::PASSWORD_MAX_LEN;

  InputBuffer() : length_(0) {
    clear();
  }

  void clear() {
    length_ = 0;
    for (size_t i = 0; i < CAPACITY; ++i)
      data_[i] = '\0';
  }

  bool is_full() const { return length_ >= CAPACITY; }

  bool push(char c) {
    if (is_full())
      return false;
    data_[length_] = c;
    ++length_;
    return true;
  }

  size_t length() const { return length_; }

  bool matches(const char* expected, size_t expected_len) const {
    if (length_ != expected_len)
      return false;
    for (size_t i = 0; i < expected_len; ++i) {
      if (data_[i] != expected[i])
        return false;
    }
    return true;
  }

  char at(size_t index) const {
    return index < length_ ? data_[index] : '\0';
  }

private:
  char  data_[CAPACITY];
  size_t length_;
};

class LockController {
public:
  void begin() {
    pinMode(Config::LED_VERMELHO_PIN, OUTPUT);
    pinMode(Config::LED_VERDE_PIN, OUTPUT);
    pinMode(Config::BUZZER_PIN, OUTPUT);
    servo_.attach(Config::SERVO_PIN);
    set_locked(true);
  }

  void set_locked(bool locked) {
    locked_ = locked;

    tone(Config::BUZZER_PIN, locked ? Config::BEEP_FREQ_LOCKED : Config::BEEP_FREQ_UNLOCKED);
    delay(Config::BEEP_DURATION_MS);
    noTone(Config::BUZZER_PIN);

    digitalWrite(Config::LED_VERMELHO_PIN, locked ? HIGH : LOW);
    digitalWrite(Config::LED_VERDE_PIN,   locked ? LOW : HIGH);
    servo_.write(locked ? Config::SERVO_ANGLE_LOCKED : Config::SERVO_ANGLE_UNLOCKED);
  }

  bool is_locked() const { return locked_; }

private:
  Servo servo_;
  bool  locked_;
};

class LockDisplay {
public:
  void begin(LiquidCrystal& lcd) {
    lcd_ = &lcd;
    lcd_->begin(16, 2);
    show_prompt();
  }

  void show_prompt() {
    if (!lcd_) return;
    lcd_->clear();
    lcd_->setCursor(0, 0);
    lcd_->print("Digite sua Senha");
    lcd_->setCursor(0, 1);
    lcd_->print("Senha: ");
  }

  static constexpr int PASSWORD_COL = 6;

  void show_digit_at(size_t position, char c) {
    if (!lcd_) return;
    lcd_->setCursor(PASSWORD_COL + static_cast<int>(position), 1);
    lcd_->print(c);
  }

  void mask_digit_at(size_t position) {
    if (!lcd_) return;
    lcd_->setCursor(PASSWORD_COL + static_cast<int>(position), 1);
    lcd_->print('*');
  }

  void refresh_mask_line(const InputBuffer& buf) {
    if (!lcd_) return;
    lcd_->setCursor(PASSWORD_COL, 1);
    for (size_t i = 0; i < buf.length(); ++i)
      lcd_->print('*');
  }

private:
  LiquidCrystal* lcd_;
};

enum class SystemState {
  WaitingInput,
  DigitShown,
  UnlockedTimed
};

static LiquidCrystal lcd(A0, A1, A2, A3, A4, A5);
static Keypad keypad(
  makeKeymap((char[]){
    '1','2','3','A', '4','5','6','B', '7','8','9','C', '*','0','#','D'
  }),
  (byte[]){ 9, 8, 7, 6 },
  (byte[]){ 5, 4, 3, 2 },
  Config::KEYPAD_ROWS,
  Config::KEYPAD_COLS
);

static LockController lock;
static LockDisplay     display;
static InputBuffer     input_buffer;
static SystemState     system_state = SystemState::WaitingInput;
static unsigned long   state_entered_at = 0;

void reset_to_input() {
  input_buffer.clear();
  lock.set_locked(true);
  display.show_prompt();
  system_state = SystemState::WaitingInput;
}

void setup() {
  display.begin(lcd);
  lock.begin();
  reset_to_input();
}

void loop() {
  const unsigned long now = millis();
  char key = keypad.getKey();

  switch (system_state) {

    case SystemState::WaitingInput: {
      if (!key) break;

      if (key == '*' || key == '#') {
        reset_to_input();
        break;
      }

      if (input_buffer.is_full()) {
        reset_to_input();
        break;
      }

      if (input_buffer.push(key)) {
        display.show_digit_at(input_buffer.length() - 1, key);
        state_entered_at = now;
        system_state = SystemState::DigitShown;
      }
      break;
    }

    case SystemState::DigitShown: {
      if (key == '*' || key == '#') {
        reset_to_input();
        break;
      }

      if (now - state_entered_at >= Config::MASK_DIGIT_MS) {
        display.refresh_mask_line(input_buffer);
        system_state = SystemState::WaitingInput;

        const size_t len = input_buffer.length();
        if (len == Config::PASSWORD_LEN) {
          if (input_buffer.matches(Config::PASSWORD, Config::PASSWORD_LEN)) {
            lock.set_locked(false);
            state_entered_at = now;
            system_state = SystemState::UnlockedTimed;
          } else {
            reset_to_input();
          }
        } else {
          if (len > 0 && input_buffer.at(len - 1) != Config::PASSWORD[len - 1])
            reset_to_input();
        }
      }
      break;
    }

    case SystemState::UnlockedTimed: {
      if (key == '*' || key == '#') {
        reset_to_input();
        break;
      }
      if (now - state_entered_at >= Config::UNLOCK_DURATION_MS)
        reset_to_input();
      break;
    }
  }
}
