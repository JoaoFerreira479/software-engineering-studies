namespace Config {
  constexpr int PIN_RED   = 13;
  constexpr int PIN_GREEN = 11;
  constexpr int PIN_BLUE  = 12;
  constexpr int BUTTON_PIN = A1;
  constexpr unsigned long DEBOUNCE_MS = 300;
  constexpr int NUM_COLORS = 8;
}

static const int COLOR_TABLE[Config::NUM_COLORS][3] = {
  {1, 0, 0},
  {0, 0, 1},
  {0, 1, 1},
  {1, 1, 1},
  {0, 1, 0},
  {1, 1, 0},
  {1, 0, 1},
  {0, 0, 0}
};

static int prev_button = LOW;
static size_t color_index = 0;
static unsigned long last_edge_time = 0;

void set_rgb(int r, int g, int b) {
  digitalWrite(Config::PIN_RED,   r ? HIGH : LOW);
  digitalWrite(Config::PIN_GREEN, g ? HIGH : LOW);
  digitalWrite(Config::PIN_BLUE,  b ? HIGH : LOW);
}

void setup() {
  pinMode(Config::PIN_RED,    OUTPUT);
  pinMode(Config::PIN_GREEN,  OUTPUT);
  pinMode(Config::PIN_BLUE,   OUTPUT);
  pinMode(Config::BUTTON_PIN, INPUT);
  set_rgb(COLOR_TABLE[0][0], COLOR_TABLE[0][1], COLOR_TABLE[0][2]);
}

void loop() {
  int btn = digitalRead(Config::BUTTON_PIN);
  unsigned long now = millis();

  if (btn == HIGH && prev_button == LOW) {
    if (now - last_edge_time >= Config::DEBOUNCE_MS) {
      last_edge_time = now;
      color_index = (color_index + 1) % static_cast<size_t>(Config::NUM_COLORS);
      set_rgb(COLOR_TABLE[color_index][0], COLOR_TABLE[color_index][1], COLOR_TABLE[color_index][2]);
    }
  }
  prev_button = btn;
}
