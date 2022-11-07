#include <iostream>

#include "Vehicle.h"
#include "engine.h"


int main(void)
{

    Engine e = Engine(4.5, 66);
    Vehicle v = Vehicle(e, 4, 3, "Ferrari");

    std::cout <<  v.engineData() <<" "<< v.toString() << "\n";

}
