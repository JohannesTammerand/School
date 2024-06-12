#ifndef PORTAAL_H
#define PORTAAL_H

#include "myyja.cpp"
#include <map>

struct Portaal{
public:
    void lisaMyyja(Myyja*);
    void kuva();
private:
    map<string, vector<Kaup>> myyjad;
};

#endif