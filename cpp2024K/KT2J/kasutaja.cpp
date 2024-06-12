#include "kasutaja.h"
#include <iostream>

using namespace std;

void Kasutaja::muudaOigus(bool uus){
    admin = uus;
}

ostream& operator<<(ostream& os, Kasutaja k){
    os << "Kasutaja: " << k.nimi << ", admin - " << boolalpha << k.admin;
    return os;
}

string Kasutaja::getNimi(){
    return nimi;
}