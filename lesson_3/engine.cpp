#include <string>
#include <iomanip>
#include <sstream>

#include "engine.h"


std::string Engine::toString(){
    std::stringstream stream;
    stream << std::fixed << std::setprecision(2) << volume;
    return std::to_string(horsePower) + "hp, " + stream.str() + "L"; 
}
