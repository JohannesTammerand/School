#include <cmath>
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>

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
    fstream sisend(failinimi);
    fstream valjund("tulemused.txt");

    string rida;

    int kogus;
    double keskmine;
    double summa;
    while(getline(sisend, rida)) {
        kogus = 0;
        keskmine = 0;

        while(rida.find(";;") != string::npos){
            int index = rida.find(";;");

            rida.replace(index, 2, ' ');
        }

        keskmine /= kogus;
        valjund << keskmine << "\n";
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