#ifndef MYYJA_H
#define MYYJA_H

#include "kaup.cpp"

struct Myyja{
public:
    Myyja(string nNimi) : nimi{nNimi} {};

    void lisaKaup(Kaup&);
    void kustutaKaup(string);
    void muudaHind(string, double);
    void kuva();

    string getNimi();
    vector<Kaup> getKaubad();
private:
    string nimi;
    vector<Kaup> kaubad;
};

#endif