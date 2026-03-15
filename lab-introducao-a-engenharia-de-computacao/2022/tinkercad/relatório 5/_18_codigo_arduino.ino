namespace Config {
  constexpr int LED_1 = 13;
  constexpr int LED_2 = 12;
  constexpr int LED_3 = 11;
  constexpr int LED_4 = 10;
  constexpr int BTN_1 = A2;
  constexpr int BTN_2 = A3;
}

void setup() {
  pinMode(Config::LED_1, OUTPUT);
  pinMode(Config::LED_2, OUTPUT);
  pinMode(Config::LED_3, OUTPUT);
  pinMode(Config::LED_4, OUTPUT);
  pinMode(Config::BTN_1, INPUT_PULLUP);
  pinMode(Config::BTN_2, INPUT_PULLUP);
}

void loop() {
  int btn1 = digitalRead(Config::BTN_1);
  int btn2 = digitalRead(Config::BTN_2);
  int on1 = (btn1 == LOW) ? HIGH : LOW;
  int on2 = (btn2 == LOW) ? HIGH : LOW;

  digitalWrite(Config::LED_1, on1);
  digitalWrite(Config::LED_3, on1);
  digitalWrite(Config::LED_2, on2);
  digitalWrite(Config::LED_4, on2);
}
