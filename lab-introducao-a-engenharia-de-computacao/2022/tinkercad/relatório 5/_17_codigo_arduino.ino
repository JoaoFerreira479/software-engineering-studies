namespace Config {
  constexpr int PIN_RED   = 9;
  constexpr int PIN_GREEN = 10;
  constexpr int PIN_BLUE  = 11;
  constexpr unsigned long INTERVAL_MS = 1000;
}

struct Rgb {
  int r;
  int g;
  int b;
};

static const Rgb COLORS[] = {
  {255, 0, 0},
  {0, 255, 0},
  {0, 0, 255},
  {255, 255, 0},
  {0, 255, 255},
  {255, 0, 255},
  {255, 255, 255},
  {0, 0, 0}
};

constexpr size_t NUM_COLORS = sizeof(COLORS) / sizeof(COLORS[0]);

static unsigned long last_switch_time = 0;
static size_t color_index = 0;

void set_color(const Rgb& c) {
  analogWrite(Config::PIN_RED,   c.r);
  analogWrite(Config::PIN_GREEN, c.g);
  analogWrite(Config::PIN_BLUE,  c.b);
}

void setup() {
  pinMode(Config::PIN_RED,   OUTPUT);
  pinMode(Config::PIN_GREEN, OUTPUT);
  pinMode(Config::PIN_BLUE,  OUTPUT);
}

void loop() {
  unsigned long now = millis();
  if (now - last_switch_time >= Config::INTERVAL_MS) {
    last_switch_time = now;
    set_color(COLORS[color_index]);
    color_index = (color_index + 1) % NUM_COLORS;
  }
}
