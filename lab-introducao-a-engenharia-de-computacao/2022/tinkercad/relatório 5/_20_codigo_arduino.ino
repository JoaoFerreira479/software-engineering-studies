namespace Config {
  constexpr int LED_1 = 13;
  constexpr int LED_2 = 12;
  constexpr int LED_3 = 11;
  constexpr int LED_4 = 10;
  constexpr int BUTTON_PIN = A1;
  constexpr unsigned long STEP_MS = 1000;
}

static const int LED_PINS[] = {
  Config::LED_1, Config::LED_2, Config::LED_3, Config::LED_4
};
constexpr size_t NUM_LEDS = sizeof(LED_PINS) / sizeof(LED_PINS[0]);

void set_all_leds(int state) {
  for (size_t i = 0; i < NUM_LEDS; ++i)
    digitalWrite(LED_PINS[i], state);
}

void run_sequence() {
  for (size_t i = 0; i < NUM_LEDS; ++i) {
    digitalWrite(LED_PINS[i], HIGH);
    delay(Config::STEP_MS);
  }
  delay(Config::STEP_MS);
  set_all_leds(LOW);
  delay(Config::STEP_MS);
}

void setup() {
  for (size_t i = 0; i < NUM_LEDS; ++i)
    pinMode(LED_PINS[i], OUTPUT);
  pinMode(Config::BUTTON_PIN, INPUT);
}

void loop() {
  if (digitalRead(Config::BUTTON_PIN) == HIGH)
    run_sequence();
}
