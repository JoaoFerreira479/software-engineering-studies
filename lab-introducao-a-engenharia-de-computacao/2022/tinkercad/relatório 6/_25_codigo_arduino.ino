namespace Config {
  constexpr int LED_1 = 13;
  constexpr int LED_2 = 12;
  constexpr int LED_3 = 11;
  constexpr int LED_4 = 10;
  constexpr long SERIAL_BAUD = 9600;
}

static const int LED_PINS[] = { Config::LED_1, Config::LED_2, Config::LED_3, Config::LED_4 };
constexpr size_t NUM_LEDS = 4;

static bool binary_mode = false;

void set_all_leds(int state) {
  for (size_t i = 0; i < NUM_LEDS; ++i)
    digitalWrite(LED_PINS[i], state);
}

void set_leds_bits(int value) {
  digitalWrite(Config::LED_1, bitRead(value, 3) ? HIGH : LOW);
  digitalWrite(Config::LED_2, bitRead(value, 2) ? HIGH : LOW);
  digitalWrite(Config::LED_3, bitRead(value, 1) ? HIGH : LOW);
  digitalWrite(Config::LED_4, bitRead(value, 0) ? HIGH : LOW);
}

void process_normal(char cmd) {
  set_all_leds(LOW);
  if (cmd == '1') digitalWrite(Config::LED_1, HIGH);
  if (cmd == '2') digitalWrite(Config::LED_2, HIGH);
  if (cmd == '3') digitalWrite(Config::LED_3, HIGH);
  if (cmd == '4') digitalWrite(Config::LED_4, HIGH);
  if (cmd == '5') set_all_leds(HIGH);
  if (cmd == '6') set_all_leds(LOW);
}

void process_binary(char cmd) {
  int num = cmd - '0';
  if (num >= 1 && num <= 9)
    set_leds_bits(num);
}

void setup() {
  Serial.begin(Config::SERIAL_BAUD);
  for (size_t i = 0; i < NUM_LEDS; ++i)
    pinMode(LED_PINS[i], OUTPUT);
  set_all_leds(LOW);
}

void loop() {
  if (Serial.available() <= 0) return;

  char cmd = static_cast<char>(Serial.read());

  if (cmd == '0') {
    binary_mode = true;
    Serial.println("Modo binario ativado!");
    return;
  }
  if (cmd == 'n') {
    binary_mode = false;
    Serial.println("Modo normal ativado!");
    return;
  }

  if (binary_mode)
    process_binary(cmd);
  else
    process_normal(cmd);
}
