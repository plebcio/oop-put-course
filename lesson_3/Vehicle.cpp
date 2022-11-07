#include <string>

#include "Vehicle.h"
#include "engine.h"

using namespace std;

Vehicle::Vehicle(Engine engine, int n_wheels, int n_doors, string manufacturer) {
    if (n_doors < 0 || n_wheels < 0) {
        throw std::invalid_argument("received negative value");
    }
    this->engine = engine;
    this->n_wheels = n_wheels;
    this->n_doors = n_doors;
    this->manufacturer = manufacturer; 
}


string Vehicle::toString() {
    return "Made by " + manufacturer + ": " + to_string(n_wheels) + " wheels, " 
        + to_string(n_doors) + " doors, " + engine.toString();
}

string Vehicle::engineData() {
    return engine.toString();
}