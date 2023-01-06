#include <iostream>
#include <assert.h>
#include "Game.h"
#include <memory>

int main(void)
{
    auto game = std::unique_ptr<Game>( new FakeFootballGame );

    assert(game->result() == "1:0");
    std::cout << "OK" << std::endl;
    
    assert(game->result() == "2:0");
    std::cout << "OK" << std::endl;
    return 0;

}

