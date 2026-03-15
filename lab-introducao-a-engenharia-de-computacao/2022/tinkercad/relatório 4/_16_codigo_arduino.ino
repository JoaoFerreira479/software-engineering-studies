namespace Config {
  constexpr int PIN_GREEN  = 13;
  constexpr int PIN_YELLOW = 12;
  constexpr int PIN_RED    = 11;
  constexpr int PIN_BLUE   = 10;

  constexpr unsigned long SEMAPHORE_CYCLE_MS = 9000;
  constexpr unsigned long GREEN_MS           = 4000;
  constexpr unsigned long GREEN_PLUS_YELLOW_MS = 6000;
  constexpr unsigned long BLINK_CYCLE_MS     = 2000;
  constexpr unsigned long BLINK_ON_MS        = 1000;
}

class Timer {
public:
  explicit Timer(unsigned long interval_ms) : interval_ms_(interval_ms) {
    reset();
  }

  void reset() {
    start_ = millis();
  }

  unsigned long elapsed_ms() const {
    return millis() - start_;
  }

  bool expired() const {
    return elapsed_ms() >= interval_ms_;
  }

  bool check_and_reset() {
    if (expired()) {
      reset();
      return true;
    }
    return false;
  }

private:
  unsigned long interval_ms_;
  unsigned long start_;
};

static Timer semaphore_timer(Config::SEMAPHORE_CYCLE_MS);
static Timer blink_timer(Config::BLINK_CYCLE_MS);

void update_semaphore() {
  unsigned long t = semaphore_timer.elapsed_ms();

  if (t < Config::GREEN_MS) {
    digitalWrite(Config::PIN_GREEN,  HIGH);
    digitalWrite(Config::PIN_YELLOW, LOW);
    digitalWrite(Config::PIN_RED,    LOW);
  } else if (t < Config::GREEN_PLUS_YELLOW_MS) {
    digitalWrite(Config::PIN_GREEN,  LOW);
    digitalWrite(Config::PIN_YELLOW, HIGH);
    digitalWrite(Config::PIN_RED,    LOW);
  } else {
    digitalWrite(Config::PIN_GREEN,  LOW);
    digitalWrite(Config::PIN_YELLOW, LOW);
    digitalWrite(Config::PIN_RED,    HIGH);
  }

  if (semaphore_timer.check_and_reset())
    (void)0;
}

void update_blink_led() {
  unsigned long t = blink_timer.elapsed_ms();
  digitalWrite(Config::PIN_BLUE, t < Config::BLINK_ON_MS ? HIGH : LOW);
  blink_timer.check_and_reset();
}

void setup() {
  pinMode(Config::PIN_GREEN,  OUTPUT);
  pinMode(Config::PIN_YELLOW, OUTPUT);
  pinMode(Config::PIN_RED,    OUTPUT);
  pinMode(Config::PIN_BLUE,   OUTPUT);
}

void loop() {
  update_semaphore();
  update_blink_led();
}
