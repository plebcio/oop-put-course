class Game
{
public:
    virtual std::string result() = 0;
};


class FakeFootballGame: public Game {
public:
    std::string result() override {
        return "1:0";
    }
};
