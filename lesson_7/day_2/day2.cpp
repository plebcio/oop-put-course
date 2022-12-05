#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <unordered_map>

using namespace std;

/*
A - rock     +1 
B - paper    +2
C - scissors +3

X - rock
Y - paper
Z - scissors
*/

class Round {
    char op_choice;
    char my_choice;  

public:
    int round_score(){
        int points_for_choice = my_choice -'X' + 1;
        switch(op_choice){
            case 'A':{
                if (my_choice == 'X') return points_for_choice + 3;
                if (my_choice == 'Y') return points_for_choice + 6;
                if (my_choice == 'Z') return points_for_choice + 0;
            }
            case 'B':{
                if (my_choice == 'Y') return points_for_choice + 3;
                if (my_choice == 'Z') return points_for_choice + 6;
                if (my_choice == 'X') return points_for_choice + 0;
            }
            case 'C':{
                if (my_choice == 'Z') return points_for_choice + 3;
                if (my_choice == 'X') return points_for_choice + 6;
                if (my_choice == 'Y') return points_for_choice + 0;
            }
        }
    }

    Round(char a, char b): op_choice(a), my_choice(b) {};

};

int main(){
    int score = 0;

    char a, b;

    fstream file;
    file.open("dane.txt", std::ios::in);
    string line;

	while (getline(file, line))
	{
        
        a = line[0];
        b = line[2];

        Round this_round(a, b);
        score += this_round.round_score();
        
	}

    cout << score << "\n";

}