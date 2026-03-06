namespace Config {
  constexpr int TRIGGER_PIN = 3;
  constexpr int ECHO_PIN   = 2;
  constexpr int LED_FAR  = 13;
  constexpr int LED_MID  = 12;
  constexpr int LED_NEAR = 11;
  constexpr int PIN_BUZZER = 8;
  constexpr long SERIAL_BAUD = 9600;

  constexpr unsigned long PULSE_PRE_US = 2;
  constexpr unsigned long PULSE_WIDTH_US = 10;
  constexpr int CM_PER_US_DIV2 = 29;
  constexpr long DIST_CM_FAR  = 200;
  constexpr long DIST_CM_MID  = 100;
  constexpr int TONE_FAR_HZ  = 1500;
  constexpr int TONE_MID_HZ  = 1000;
  constexpr int TONE_NEAR_HZ = 100;
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

void turn_all_off() {
  digitalWrite(Config::LED_FAR,  LOW);
  digitalWrite(Config::LED_MID,  LOW);
  digitalWrite(Config::LED_NEAR, LOW);
  noTone(Config::PIN_BUZZER);
}

void update_indicators(long dist_cm) {
  if (dist_cm > Config::DIST_CM_FAR) {
    digitalWrite(Config::LED_FAR, HIGH);
    tone(Config::PIN_BUZZER, Config::TONE_FAR_HZ);
  } else if (dist_cm > Config::DIST_CM_MID) {
    digitalWrite(Config::LED_MID, HIGH);
    tone(Config::PIN_BUZZER, Config::TONE_MID_HZ);
  } else {
    digitalWrite(Config::LED_NEAR, HIGH);
    tone(Config::PIN_BUZZER, Config::TONE_NEAR_HZ);
  }
}

void setup() {
  Serial.begin(Config::SERIAL_BAUD);
  pinMode(Config::LED_FAR,  OUTPUT);
  pinMode(Config::LED_MID,  OUTPUT);
  pinMode(Config::LED_NEAR, OUTPUT);
  pinMode(Config::PIN_BUZZER, OUTPUT);
  pinMode(Config::TRIGGER_PIN, OUTPUT);
  pinMode(Config::ECHO_PIN, INPUT);
}

void loop() {
  unsigned long now = millis();
  if (now - last_read_time >= Config::READ_INTERVAL_MS) {
    last_read_time = now;
    long dist = measure_cm();
    Serial.print(dist);
    Serial.println(" cm");
    turn_all_off();
    update_indicators(dist);
  }
}
