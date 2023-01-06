#include <iostream>
#include <assert.h>
#include "Game.h"

int main(void)
{
    Game *game;
    game = new FakeFootballGame();

    assert(game->result() == "1:0");
    std::cout << "OK" << std::endl;

    
    assert(game->result() == "2:0");
    std::cout << "OK" << std::endl;
    return 0;

}

