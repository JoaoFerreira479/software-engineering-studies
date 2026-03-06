namespace Config {
  constexpr int LED_R = 13;
  constexpr int LED_Y = 12;
  constexpr int LED_G = 11;
  constexpr int LED_B = 10;
  constexpr long SERIAL_BAUD = 9600;
}

static const int LED_PINS[] = { Config::LED_R, Config::LED_Y, Config::LED_G, Config::LED_B };
constexpr size_t NUM_LEDS = 4;

void set_all_leds(int state) {
  for (size_t i = 0; i < NUM_LEDS; ++i)
    digitalWrite(LED_PINS[i], state);
}

void process_command(char cmd) {
  set_all_leds(LOW);
  switch (cmd) {
    case 'r': digitalWrite(Config::LED_R, HIGH); break;
    case 'y': digitalWrite(Config::LED_Y, HIGH); break;
    case 'g': digitalWrite(Config::LED_G, HIGH); break;
    case 'b': digitalWrite(Config::LED_B, HIGH); break;
    case 'a':
      set_all_leds(HIGH);
      break;
    case 'x':
      break;
    default:
      break;
  }
}

void setup() {
  Serial.begin(Config::SERIAL_BAUD);
  for (size_t i = 0; i < NUM_LEDS; ++i)
    pinMode(LED_PINS[i], OUTPUT);
  set_all_leds(LOW);
}

void loop() {
  if (Serial.available() > 0) {
    char cmd = static_cast<char>(Serial.read());
    process_command(cmd);
  }
}
