#ifndef KAUP_H
#define KAUP_H

#include <string>
#include <vector>
using namespace std;

struct Kaup{
public:
    Kaup(string nNimi, float nHind) : nimi{nNimi}, hind{nHind} {};

    friend ostream& operator<<(ostream&, Kaup);
    void setMyyja(string);
    string getNimi();
    void setHind(float);
private:
    string nimi;
    float hind;
    string myyja;
};

#endif