#include <iostream>
#include <sstream>
#include <string>
#include <algorithm>
using namespace std;


string lyhendaja(string nimi){
    string vastus = "";
    
    stringstream ss(nimi);
    string osa;

    while(ss >> osa){
        bool isPerenimi = ss.eof();
        if (isPerenimi){
            vastus = osa + " " + vastus;
        } else {
            replace(osa.begin(), osa.end(), '-', ' ');
            stringstream ss1(osa);

            string eesnimeosa;
            while (ss1 >> eesnimeosa) {
                vastus += eesnimeosa.at(0);
                if(!ss1.eof()) {
                    vastus += '-';
                } else {
                    vastus += ". ";
                }
            }
        }
    }
    return vastus;
}

string sugu(string isikukood){
    if (stoi(isikukood.substr(0, 1)) % 2 == 0){
        return "N";
    } else {
        return "M";
    }
}

string synnikuupaev(string isikukood){
    string paev = isikukood.substr(5, 2);
    string kuu = isikukood.substr(3, 2);
    string aasta = "";

    switch(isikukood[0]) {
        case '1':
        case '2':
            aasta += "18";
            break;
        case '3':
        case '4':
            aasta += "19";
            break;
        case '5':
        case '6':
            aasta += "20";
            break;
        case '7':
        case '8':
            aasta += "21";
            break;
    }

    aasta += isikukood.substr(1, 2);

    return paev + '.' + kuu + '.' + aasta;
}

void tagurpidi(size_t arv, string massiiv[]){
    reverse(massiiv, massiiv+arv);

    for (size_t i = 0; i < arv; i++)
    {
        reverse(massiiv[i].begin(), massiiv[i].end());
    }
}
/*
void sagedus(const string &asukoht){
    ifstream sisend(asukoht);

    if (!sisend.is_open()){
        cerr << "Faili " << asukoht << " ei leitud.\n";
        return;
    }

    string rida;
    while(getLine(sisend, rida)){
        map<char, int> m;
        stringstream ss(rida);
        char sona;

        while (ss >> sona){
            auto it = m.find(sona);

            if (it == m.end()){
                m.insert({sone, 1});
            } else {
                it->second++;
            }
        }

        char maxStr;
        int maxVal{INT_MIN};

        for (auto &[key, value] : m){
            if (value > maxVal) {
                maxVal = value;
                maxStr = key;
            }
        }

        cout << maxStr << " : " << maxVal << '\n';
    }
}
*/


int main(){
    string massiiv[] = {"kaks", "kolm", "neli"};
    tagurpidi(3, massiiv);
}