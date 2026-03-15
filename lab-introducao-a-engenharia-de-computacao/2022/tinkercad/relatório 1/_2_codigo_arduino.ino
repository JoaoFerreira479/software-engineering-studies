namespace Config {
  constexpr int LED_PIN    = 7;
  constexpr int INTERVAL_MS = 150;
}

void setup() {
  pinMode(Config::LED_PIN, OUTPUT);
}

void loop() {
  digitalWrite(Config::LED_PIN, HIGH);
  delay(Config::INTERVAL_MS);
  digitalWrite(Config::LED_PIN, LOW);
  delay(Config::INTERVAL_MS);
}
