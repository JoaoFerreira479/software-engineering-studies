namespace Config {
  constexpr int PIN_LDR    = A1;
  constexpr int PIN_BUZZER = 4;
  constexpr int LED_13 = 13;
  constexpr int LED_12 = 12;
  constexpr int LED_11 = 11;
  constexpr int LED_10 = 10;
  constexpr long SERIAL_BAUD = 9600;

  constexpr int THRESHOLD_HIGH  = 1000;
  constexpr int THRESHOLD_MID_H = 700;
  constexpr int THRESHOLD_MID_L = 500;
  constexpr int PWM_HIGH = 255;
  constexpr int PWM_MID_H = 180;
  constexpr int PWM_MID_L = 130;
  constexpr int PWM_LOW  = 100;
  constexpr int TONE_FREQ_HZ = 1500;
  constexpr unsigned long READ_INTERVAL_MS = 200;
}

static unsigned long last_read_time = 0;

void turn_all_off() {
  digitalWrite(Config::LED_13, LOW);
  digitalWrite(Config::LED_12, LOW);
  digitalWrite(Config::LED_11, LOW);
  digitalWrite(Config::LED_10, LOW);
  noTone(Config::PIN_BUZZER);
}

void update_indicators(int value) {
  if (value >= Config::THRESHOLD_HIGH) {
    analogWrite(Config::LED_13, Config::PWM_HIGH);
    tone(Config::PIN_BUZZER, Config::TONE_FREQ_HZ);
  } else if (value > Config::THRESHOLD_MID_H) {
    analogWrite(Config::LED_12, Config::PWM_MID_H);
  } else if (value > Config::THRESHOLD_MID_L) {
    analogWrite(Config::LED_11, Config::PWM_MID_L);
  } else {
    analogWrite(Config::LED_10, Config::PWM_LOW);
  }
}

void setup() {
  pinMode(Config::LED_13, OUTPUT);
  pinMode(Config::LED_12, OUTPUT);
  pinMode(Config::LED_11, OUTPUT);
  pinMode(Config::LED_10, OUTPUT);
  pinMode(Config::PIN_BUZZER, OUTPUT);
  Serial.begin(Config::SERIAL_BAUD);
}

void loop() {
  unsigned long now = millis();
  if (now - last_read_time >= Config::READ_INTERVAL_MS) {
    last_read_time = now;
    int value = analogRead(Config::PIN_LDR);
    Serial.println(value);
    turn_all_off();
    update_indicators(value);
  }
}
