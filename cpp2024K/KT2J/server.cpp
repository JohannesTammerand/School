#include "server.h"
#include <iostream>

using namespace std;

void Server::lisaKlientarvuti(Klientarvuti *k){
    vector<shared_ptr<Kasutaja>>* kasutajad = k->getKasutajad(); 
    kliendid[k->getNimi()] = kasutajad;
}

void Server::kuva(){
    cout << "Serveris " << nimi << " on järgmised klientarvutid:" << endl;

    for (pair<string, vector<shared_ptr<Kasutaja>>*> paar : kliendid){
        cout << "Klientarvutis " << paar.first << " on järgmised kasutajad:" << endl;
        for (shared_ptr<Kasutaja> k : *(paar.second)){
            cout << *k << endl;
        }
    }
}

int main(){
    shared_ptr<Kasutaja> k1{make_shared<Kasutaja>("Mart", true)};
    shared_ptr<Kasutaja> k2{make_shared<Kasutaja>("Mari", false)};
    shared_ptr<Kasutaja> k3{make_shared<Kasutaja>("Marta", true)};
    cout << *k1 << endl;
    cout << *k2 << endl;
    Klientarvuti kl1{"Kodune1"};
    kl1.lisaKasutaja(k1);
    kl1.lisaKasutaja(k2);
    kl1.lisaKasutaja(k2);
    kl1.kustutaKasutaja("Jüri");
    kl1.kustutaKasutaja("Mari");
    kl1.kuva();
    Klientarvuti kl2{"Firma1"};
    kl2.lisaKasutaja(k3);
    Server s{"Jaam"};
    s.lisaKlientarvuti(&kl1);
    s.lisaKlientarvuti(&kl2);
    s.kuva();
}