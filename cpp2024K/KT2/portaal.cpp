#include "portaal.h"

using namespace std;

void Portaal::lisaMyyja(Myyja* m){
    myyjad[(*m).getNimi()] = (*m).getKaubad();
}

void Portaal::kuva(){
    cout << "Portaalis müüdavad kaubad:\n";
    for(auto paar : myyjad){
        cout << paar.first << ":\n";
        vector<Kaup> kaubad = paar.second;
        for (Kaup k : kaubad){
            cout << k << endl;
        }
    }
}