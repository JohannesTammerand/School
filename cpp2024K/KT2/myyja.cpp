#include "myyja.h"
using namespace std;

string Myyja::getNimi(){
    return nimi;
}

vector<Kaup> Myyja::getKaubad(){
    return kaubad;
}

void Myyja::lisaKaup(Kaup& k){
    k.setMyyja(nimi);
    kaubad.push_back(k);
}

void Myyja::kustutaKaup(string k){
    for(int i = 0; i < kaubad.size(); i++){
        if (kaubad[i].getNimi() == k){
            kaubad.erase(kaubad.begin() + i);
        }
    }
}

void Myyja::muudaHind(string n, double h){
    for(int i = 0; i < kaubad.size(); i++){
        if (kaubad[i].getNimi() == n){
            kaubad[i].setHind(h);
        }
    }
}

void Myyja::kuva(){
    cout << "Müüja " << nimi << " kaubad:\n";
    for(Kaup k : kaubad){
        cout << k << endl;
    }
}