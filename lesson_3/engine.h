#ifndef engine_h
#define engine_h

#include <string>

class Engine {
private:
    float volume;
    int horsePower;
    
public:
    Engine(float volume, int horsePower): volume(volume), horsePower(horsePower) {}
    Engine() {}

    std::string toString(); 
     
};

#endif
