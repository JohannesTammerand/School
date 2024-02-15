#include <iostream>
#include <cmath>
using namespace std;

void yl1(){
    int arv;
    cout << "Sisesta täisarv: ";
    cin >> arv;

    cout << "Viimane number: " << arv%10 << "\n";
    int nihutatud = arv >> 1;
    cout << "Arv nihutatult üks koht paremale: " << nihutatud << "\n";
    cout << "Tulemus nihutatult kaks kohta vasakule: " << (nihutatud << 2) << "\n";
}

void yl2(){
    float kaal;
    cout << "Sisesta kaal: ";
    cin >> kaal;

    float aeg;
    cout << "Sisesta aeg minutites: ";
    cin >> aeg;

    cout << "Tegevus\t\t     Aeg min Kulutatud kcal\n ------------------------------------------\n";
    cout << "Kõndimine\t\t" << aeg << "\t" << kaal*4*aeg/60 << "\n";
    cout << "Treppidel käimine\t" << aeg << "\t" << kaal*5.5f*aeg/60 << "\n";
    cout << "Ujumine\t\t\t" << aeg << "\t" << kaal*10*aeg/60 << "\n";
    cout << "Korvpall\t\t" << aeg << "\t" << kaal*8*aeg/60 << "\n";
}

void yl3(){
    float raadius;
    cout << "Sisesta ringi raadius: ";
    cin >> raadius;

    float pii = 22.0/7.0;
    float pindala = raadius*raadius*pii;
    cout << "Ringi pindala: " << floorf(pindala*100)/100 << "\n";
    cout << "Ringi ümbermõõt: " << floorf(raadius*2*pii*100)/100 << "\n";
    cout << "Raadius kaks korda väiksema pindala korral: " << floorf(sqrt(pindala/2/pii)*100)/100 << "\n";

}

int main(){
    yl3();
}