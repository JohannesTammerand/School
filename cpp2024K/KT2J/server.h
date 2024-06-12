#ifndef SERVER_H
#define SERVER_H

#include "klientarvuti.cpp"
#include <map>

class Server{
public:
    Server(string uN) : nimi{uN} {};

    void lisaKlientarvuti(Klientarvuti*);
    void kuva();
private:
    string nimi;
    map<string, vector<shared_ptr<Kasutaja>>*> kliendid;
};

using namespace std;

#endif