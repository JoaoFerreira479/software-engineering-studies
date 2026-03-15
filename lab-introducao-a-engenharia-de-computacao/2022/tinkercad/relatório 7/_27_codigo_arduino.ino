namespace Config {
  constexpr int PIN_LDR    = A1;
  constexpr int PIN_BUZZER = 4;
  constexpr long SERIAL_BAUD = 9600;

  constexpr int THRESHOLD_HIGH   = 1000;
  constexpr int THRESHOLD_MID_H  = 700;
  constexpr int THRESHOLD_MID_L  = 500;
  constexpr int THRESHOLD_LOW    = 400;
  constexpr int TONE_HIGH_HZ = 1500;
  constexpr int TONE_MID_HZ  = 1200;
  constexpr int TONE_MID_L_HZ = 800;
  constexpr int TONE_LOW_HZ  = 350;
  constexpr unsigned long READ_INTERVAL_MS = 200;
}

static unsigned long last_read_time = 0;

void set_buzzer_by_light(int value) {
  if (value >= Config::THRESHOLD_HIGH)
    tone(Config::PIN_BUZZER, Config::TONE_HIGH_HZ);
  else if (value > Config::THRESHOLD_MID_H)
    tone(Config::PIN_BUZZER, Config::TONE_MID_HZ);
  else if (value > Config::THRESHOLD_MID_L)
    tone(Config::PIN_BUZZER, Config::TONE_MID_L_HZ);
  else if (value > Config::THRESHOLD_LOW)
    tone(Config::PIN_BUZZER, Config::TONE_LOW_HZ);
  else
    noTone(Config::PIN_BUZZER);
}

void setup() {
  pinMode(Config::PIN_BUZZER, OUTPUT);
  Serial.begin(Config::SERIAL_BAUD);
}

void loop() {
  unsigned long now = millis();
  if (now - last_read_time >= Config::READ_INTERVAL_MS) {
    last_read_time = now;
    int value = analogRead(Config::PIN_LDR);
    Serial.println(value);
    set_buzzer_by_light(value);
  }
}
