#ifndef vehicle_h
#define vehicle_h


#include <string>
#include <stdexcept>

#include "engine.h"

class Vehicle {
private:

    Engine engine;
    int n_wheels;
    int n_doors;  
    std::string manufacturer;

public:

    Vehicle() {};
    Vehicle(Engine engine, int n_wheels, int n_doors, std::string manufacturer);

    std::string toString();
    std::string engineData();

};

#endif
