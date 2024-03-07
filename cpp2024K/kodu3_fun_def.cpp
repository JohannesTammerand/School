#include <cmath>
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

int fun(int n1, int n2){
    return n1+n2;
}

int fun(double n){
    return round(n*n);
}

string fun(unsigned int n, string sone){
    string tulemus = "";
    for (size_t i = 0; i < n; i++)
    {
        tulemus += sone;
    }
    return tulemus;
}

double kehamassiindeks(double pikkus, int kaal){
    return kaal/(pikkus*pikkus);
}

string hinnang(double kehamassiindeks){
    if (kehamassiindeks <= 19){
        return "alakaal";
    } else if (kehamassiindeks < 25){
        return "normaalkaal";
    } else if (kehamassiindeks < 30){
        return "ülekaal";
    } else {
        return "rasvumine";
    }
}

void andmed_failist(string failinimi){
    ifstream sisend(failinimi);
    ofstream valjund("tulemused.txt");

    string rida;

    int kogus;
    double keskmine;
    double summa = 0;
    while(getline(sisend, rida)) {
        vector<string> tulemus;
        istringstream ss(rida);
        string t;

        while(getline(ss, t, ';')){
            if (!t.empty()) {
                tulemus.push_back(t);
            }
            ss.get();
        }

        keskmine = 0;
        kogus = 0;
        for (string arv : tulemus){
            keskmine += stod(arv);
            summa += stod(arv);
            kogus++;
        }
        keskmine /= kogus;

        valjund << keskmine << endl;
    }
    valjund << summa;

    sisend.close();
    valjund.close();
}
/*
int main(int argc, char *argv[]){
    double pikkus = stod(argv[1]);
    int kaal = stoi(argv[2]);
    
    double kmi(kehamassiindeks(pikkus, kaal));
    cout << kmi << "   " << hinnang(kmi);
}
*/

int main(){
    andmed_failist("sisend.txt");
}