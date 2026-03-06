namespace Config {
  constexpr int LED_PIN     = 10;
  constexpr int BUTTON_PIN  = 13;
  constexpr int BLINK_COUNT = 5;
  constexpr int BLINK_MS   = 250;
}

void blink_led(int times, int interval_ms) {
  for (int i = 0; i < times; ++i) {
    digitalWrite(Config::LED_PIN, HIGH);
    delay(static_cast<unsigned long>(interval_ms));
    digitalWrite(Config::LED_PIN, LOW);
    delay(static_cast<unsigned long>(interval_ms));
  }
}

void setup() {
  pinMode(Config::LED_PIN,    OUTPUT);
  pinMode(Config::BUTTON_PIN, INPUT);
}

void loop() {
  if (digitalRead(Config::BUTTON_PIN) == HIGH) {
    blink_led(Config::BLINK_COUNT, Config::BLINK_MS);
  } else {
    digitalWrite(Config::LED_PIN, LOW);
  }
}
