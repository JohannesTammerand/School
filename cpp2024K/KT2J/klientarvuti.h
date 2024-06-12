#ifndef KLIENTARVUTI_H
#define KLIENTARVUTI_H

#include "kasutaja.h"
#include <string>
#include <vector>
#include <memory>

using namespace std;

class Klientarvuti{
public:
    Klientarvuti(string uN) : nimi{uN} {};

    void lisaKasutaja(shared_ptr<Kasutaja>);
    void kustutaKasutaja(string);
    void kuva();

    string getNimi();
    vector<shared_ptr<Kasutaja>>* getKasutajad();
private:
    string nimi;
    vector<shared_ptr<Kasutaja>> kasutajad;
};

#endif