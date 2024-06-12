#include "kaup.h"
#include <iostream>

using namespace std;

void Kaup::setMyyja(string m){
    myyja = m;
}

void Kaup::setHind(float h){
    hind = h;
}

string Kaup::getNimi(){
    return nimi;
}

ostream& operator<<(ostream& os, Kaup k){
    os << "Nimi: " << k.nimi << ", hind: " << k.hind << ", müüja: " << k.myyja;
    return os;
}