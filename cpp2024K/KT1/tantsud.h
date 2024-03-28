#include <iostream>
#include <fstream>
#include <sstream>
using namespace std;

template<int pikkus>
void tantsud(string failinimi, string nimeosa){
    string rida;
    fstream fail(failinimi);
    int summa = 0;
    for (int i = 0; i < pikkus; i++)
    {
        getline(fail, rida);
        if (rida.find(nimeosa) != string::npos){
            cout << rida << endl;
            summa ++;
        }
    }
    
    cout << "Tantsija oskab " << summa << " tantsu." << endl;
}