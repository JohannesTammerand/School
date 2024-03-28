#include <iostream>
#include "fun1.h"
using namespace std;

float stiilipunktid(float hinded[], int pikkus){
    float suurim = 0;
    float vaikseim = 0;
    float summa = 0;

    for (int i = 0; i < pikkus; i++)
    {
        if (hinded[i] < vaikseim || vaikseim == 0){
            vaikseim = hinded[i];
        }

        if (hinded[i] > suurim || suurim == 0){
            suurim = hinded[i];
        }

        summa += hinded[i];
    }
    
    return summa - suurim - vaikseim;
}

string parimaStiiliga(string nimed[], float hinded[], int pikkus){
    int suurimIndex = 0;

    for (int i = 0; i < pikkus; i++)
    {
        if (hinded[i] > hinded[suurimIndex]){
            suurimIndex = i;
        }
    }
    
    return nimed[suurimIndex];
}
/*
int main(){
    cout << "Suusataja punktid : [17.5, 18.0, 17.5, 18.5, 19.0]" << endl;
    float hinded[]{17.5, 18.0, 17.5, 18.5, 19.0};
    cout << "Suusataja lõplik hinne: " << stiilipunktid(hinded, 5) << endl << endl;

    cout << "Suusatajad: [a, b, c, d]" << endl;
    cout << "Suusatajate punktid: [129.0, 129.8, 129.2, 135.2]" << endl;
    string nimed[]{"a", "b", "c", "d"};
    float punktid[]{129.0, 129.8, 129.2, 135.2};
    cout << "Parim suustaja: " << parimaStiiliga(nimed, punktid, 4) << endl;
    
}
*/