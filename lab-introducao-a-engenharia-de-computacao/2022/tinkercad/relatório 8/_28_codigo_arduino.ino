namespace Config {
  constexpr int TRIGGER_PIN = 3;
  constexpr int ECHO_PIN   = 2;
  constexpr int LED_1 = 13;
  constexpr int LED_2 = 12;
  constexpr int LED_3 = 11;
  constexpr int LED_4 = 10;
  constexpr long SERIAL_BAUD = 9600;

  constexpr unsigned long PULSE_PRE_US = 2;
  constexpr unsigned long PULSE_WIDTH_US = 10;
  constexpr int CM_PER_US_DIV2 = 29;
  constexpr int DIST_CM_LONG  = 150;
  constexpr int DIST_CM_MID   = 100;
  constexpr int DIST_CM_SHORT  = 70;
  constexpr unsigned long READ_INTERVAL_MS = 100;
}

static unsigned long last_read_time = 0;

long measure_cm() {
  digitalWrite(Config::TRIGGER_PIN, LOW);
  delayMicroseconds(Config::PULSE_PRE_US);
  digitalWrite(Config::TRIGGER_PIN, HIGH);
  delayMicroseconds(Config::PULSE_WIDTH_US);
  digitalWrite(Config::TRIGGER_PIN, LOW);
  long us = pulseIn(Config::ECHO_PIN, HIGH);
  return (us / 2) / Config::CM_PER_US_DIV2;
}

void set_all_leds_low() {
  digitalWrite(Config::LED_1, LOW);
  digitalWrite(Config::LED_2, LOW);
  digitalWrite(Config::LED_3, LOW);
  digitalWrite(Config::LED_4, LOW);
}

void update_leds_by_distance(long dist_cm) {
  if (dist_cm > Config::DIST_CM_LONG)
    digitalWrite(Config::LED_1, HIGH);
  else if (dist_cm > Config::DIST_CM_MID)
    digitalWrite(Config::LED_2, HIGH);
  else if (dist_cm > Config::DIST_CM_SHORT)
    digitalWrite(Config::LED_3, HIGH);
  else
    digitalWrite(Config::LED_4, HIGH);
}

void setup() {
  Serial.begin(Config::SERIAL_BAUD);
  pinMode(Config::TRIGGER_PIN, OUTPUT);
  pinMode(Config::ECHO_PIN, INPUT);
  pinMode(Config::LED_1, OUTPUT);
  pinMode(Config::LED_2, OUTPUT);
  pinMode(Config::LED_3, OUTPUT);
  pinMode(Config::LED_4, OUTPUT);
}

void loop() {
  unsigned long now = millis();
  if (now - last_read_time >= Config::READ_INTERVAL_MS) {
    last_read_time = now;
    long dist = measure_cm();
    Serial.print(dist);
    Serial.println(" cm");
    set_all_leds_low();
    update_leds_by_distance(dist);
  }
}
