namespace Config {
  constexpr int LED_PIN = 13;
  constexpr long SERIAL_BAUD = 9600;
  constexpr int BLINK_COUNT = 10;
  constexpr int BLINK_FAST_MS = 50;
  constexpr int BLINK_SLOW_MS = 100;
  constexpr int MORSE_DOT_MS = 100;
  constexpr int MORSE_DASH_MS = 300;
  constexpr int MORSE_GAP_MS = 200;
  constexpr int FADE_STEP = 51;
  constexpr int FADE_DELAY_MS = 200;
}

void blink_led(int interval_ms) {
  for (int i = 0; i < Config::BLINK_COUNT; ++i) {
    digitalWrite(Config::LED_PIN, HIGH);
    delay(static_cast<unsigned long>(interval_ms));
    digitalWrite(Config::LED_PIN, LOW);
    delay(static_cast<unsigned long>(interval_ms));
  }
}

void send_morse(const char* code) {
  while (*code != '\0') {
    if (*code == '.') {
      digitalWrite(Config::LED_PIN, HIGH);
      delay(Config::MORSE_DOT_MS);
    } else if (*code == '-') {
      digitalWrite(Config::LED_PIN, HIGH);
      delay(Config::MORSE_DASH_MS);
    }
    digitalWrite(Config::LED_PIN, LOW);
    delay(Config::MORSE_GAP_MS);
    ++code;
  }
}

void fade_led() {
  for (int v = 0; v <= 255; v += Config::FADE_STEP) {
    analogWrite(Config::LED_PIN, v);
    delay(Config::FADE_DELAY_MS);
  }
  for (int v = 255; v >= 0; v -= Config::FADE_STEP) {
    analogWrite(Config::LED_PIN, v);
    delay(Config::FADE_DELAY_MS);
  }
}

void process_command(char cmd) {
  switch (cmd) {
    case 'a': blink_led(Config::BLINK_SLOW_MS); break;
    case 'b': blink_led(Config::BLINK_FAST_MS); break;
    case 'p': send_morse(".--."); break;
    case 'u': send_morse("..-"); break;
    case 'c': send_morse("-.-."); break;
    case 'x': digitalWrite(Config::LED_PIN, LOW); break;
    case 'w': fade_led(); break;
    default:  Serial.println("Comando invalido."); break;
  }
}

void setup() {
  Serial.begin(Config::SERIAL_BAUD);
  pinMode(Config::LED_PIN, OUTPUT);
}

void loop() {
  if (Serial.available() > 0) {
    char cmd = static_cast<char>(Serial.read());
    process_command(cmd);
  }
}
