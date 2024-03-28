#include <iostream>
#include "server.h"
using namespace std;

ostream& operator<<(ostream& os, const Yhendus& y){
    os << "(" << y.kliendiNimi << ", " << y.serveriNimi << ", " << y.number << ")";
    return os;
}

void Server::lisaYhendus(Yhendus* y){
    yhendused.push_back(y);
}

void Server::kuvaYhendused(){
    for (Yhendus* y : yhendused){
        cout << *y << endl;
    }
}

Server :: ~Server(){
    cout << "Katkestan ühendused" << endl;

    for (Yhendus* y : yhendused){
        delete y;
        y = nullptr;
        erase(yhendused, y);
    }
}