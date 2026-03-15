namespace Config {
  constexpr int LED_1_PIN = 11;
  constexpr int LED_2_PIN = 10;
  constexpr int BRIGHTNESS_HIGH = 255;
  constexpr int BRIGHTNESS_LOW  = 0;
  constexpr unsigned long ALTERNATE_MS = 3000;
  constexpr long SERIAL_BAUD = 9600;
}

static bool led1_on = true;
static unsigned long last_switch_time = 0;

void set_leds(int b1, int b2) {
  analogWrite(Config::LED_1_PIN, b1);
  analogWrite(Config::LED_2_PIN, b2);
}

void setup() {
  Serial.begin(Config::SERIAL_BAUD);
  pinMode(Config::LED_1_PIN, OUTPUT);
  pinMode(Config::LED_2_PIN, OUTPUT);
  set_leds(Config::BRIGHTNESS_HIGH, Config::BRIGHTNESS_LOW);
}

void loop() {
  unsigned long now = millis();
  if (now - last_switch_time >= Config::ALTERNATE_MS) {
    last_switch_time = now;
    led1_on = !led1_on;
    if (led1_on)
      set_leds(Config::BRIGHTNESS_HIGH, Config::BRIGHTNESS_LOW);
    else
      set_leds(Config::BRIGHTNESS_LOW, Config::BRIGHTNESS_HIGH);
  }
}
