# Contador pares/ímpares — C++ (domain / infrastructure / application)
CXX = g++
CXXFLAGS = -Wall -std=c++14 -Idomain -Iinfrastructure -Iapplication
TARGET = contador

all: $(TARGET)

$(TARGET): domain/contador.cpp infrastructure/io.cpp application/main.cpp
	$(CXX) $(CXXFLAGS) -o $@ $^

clean:
	rm -f $(TARGET)

.PHONY: all clean
