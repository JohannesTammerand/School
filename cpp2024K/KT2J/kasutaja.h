#ifndef KASUTAJA_H
#define KASUTAJA_H

#include <string>

using namespace std;

class Kasutaja{
public:
    Kasutaja(string uN, bool uA) : nimi{uN}, admin{uA} {};

    void muudaOigus(bool);
    friend ostream& operator<<(ostream&, Kasutaja);

    string getNimi();
private:
    string nimi;
    bool admin;
};

#endif