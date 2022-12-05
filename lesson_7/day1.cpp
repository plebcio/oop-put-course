#include <iostream>
#include <vector>
#include <string>
#include <fstream>


using namespace std;

class Elf { 
private:
    vector <int> bag;

public:
    void add_elem(int cal){
        bag.push_back(cal);
    }
    int bag_sum(){
        long sum = 0;
        for (auto elem: bag){
            sum += elem;
        }
        return sum;
    }

};

int main(){
    vector <Elf> elves;
    int weight;
    Elf tmp_elf;

    fstream file;
    file.open("dane.txt", std::ios::in);
    string line;
    
	while (getline(file, line))
	{
        if (line.length() == 0){
            elves.push_back(tmp_elf);
            tmp_elf = Elf();
            continue;
        }

        tmp_elf.add_elem(stoi(line));
	}

    elves.push_back(tmp_elf);


    int biggest_bag = 0;
    for (Elf elf: elves){
        if (elf.bag_sum() > biggest_bag){
            biggest_bag = elf.bag_sum();
        }
    }

    cout << biggest_bag << endl;

}