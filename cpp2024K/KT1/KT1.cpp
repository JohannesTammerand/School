#include <iostream>
#include "tantsud.h"
#include "fun1.cpp"
#include "server.cpp"
using namespace std;

int main(){
    cout << "Suusataja punktid : [17.5, 18.0, 17.5, 18.5, 19.0]" << endl;
    float hinded[]{17.5, 18.0, 17.5, 18.5, 19.0};
    cout << "Suusataja lõplik hinne: " << stiilipunktid(hinded, 5) << endl << endl;

    cout << "Suusatajad: [a, b, c, d]" << endl;
    cout << "Suusatajate punktid: [129.0, 129.8, 129.2, 135.2]" << endl;
    string nimed[]{"a", "b", "c", "d"};
    float punktid[]{129.0, 129.8, 129.2, 135.2};
    cout << "Parim suustaja: " << parimaStiiliga(nimed, punktid, 4) << endl << endl;

    tantsud<4>("tantsud.txt", "lugu");
    cout << endl << endl;
    
    Yhendus y = Yhendus("a", "b", 3);
    cout << y << endl;
}