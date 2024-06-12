#include "klientarvuti.h"
#include <iostream>

//using namespace std;

void Klientarvuti::lisaKasutaja(shared_ptr<Kasutaja> k){
    for (shared_ptr<Kasutaja> k1 : kasutajad){
        if (k1 == k){
            cout << "Kasutaja " << k->getNimi() << " on juba olemas kliendiarvutis " << nimi << endl;
            return;
        }
    }

    kasutajad.push_back(k);
    cout << "Kliendiarvutisse " << nimi << " on lisatud " << *k << endl;
}

void Klientarvuti::kustutaKasutaja(string k){
    auto it = kasutajad.begin();
    auto lopp = kasutajad.end();

    for(; it != lopp; it++){
        Kasutaja k1 = **it;

        if (k1.getNimi() == k){
            kasutajad.erase(it);
            cout << "Kasutaja " << k << " on eemdaldatud kliendiarvutist " << nimi << endl;
            return;
        }
    }

    cout << "Kasutajat " << k << " ei leitud kliendiarvutist " << nimi << endl;    
}

void Klientarvuti::kuva(){
    cout << "Klientarvutis " << nimi << " on järgmised kasutajad:" << endl;
    for (shared_ptr<Kasutaja> k : kasutajad){
        cout << *k << endl;
    }
}

string Klientarvuti::getNimi(){
    return nimi;
}

vector<shared_ptr<Kasutaja>>* Klientarvuti::getKasutajad(){
    return &kasutajad;
}