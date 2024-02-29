#include <iostream>
#include <cmath>
#include <random>
#include <iomanip>
using namespace std;

void kolmnurk(){
    cout << "Sisesta kolmnurga kõrgus: ";
    int korgus;
    cin >> korgus;
    for (int i = 0; i < korgus; i++)
    {
        cout << string(korgus-i-1, ' ') + string(i*2+1, '*') + '\n';
    }
}

void krediitkaart(){
    long nr;
    cout << "Sisesta krediitkaardi number";
    cin >> nr;

    int check = nr%10;
    nr = (nr-check)/10;

    int summa = 0;
    int j = 0;
    bool kahekordista = true;
    for (long i = 10; i < nr*10; i*=10)
    {
        j = nr%i/(i/10);

        if (kahekordista){
            j *= 2;
            kahekordista = false;
        } else {
            kahekordista = true;
        }

        if (j >= 10){
            summa += j/10 + j%10;
        } else {
            summa += j;
        }
    }
    
    if (10-summa%10 == check){
        cout << "Number töötab";
    } else {
        cout << "Number ei tööta";
    }
}

void tabel(){
    double algus;
    double samm;
    double lopp;
    cout << "Sisesta algus: ";
    cin >> algus;
    cout << "Sisesta samm: ";
    cin >> samm;
    cout << "Sisesta lõpp: ";
    cin >> lopp;

    cout << "\n  Nael\t\tKg";
    cout << "\n-----------------------\n";
    for(; algus <= lopp; algus += samm){
        cout << "  " << algus << "\t\t" << setprecision(4) << algus/2.205 << "\n";
    }
}

void blackjack(){
    default_random_engine genereerija;
    uniform_int_distribution<int> jaotus(4, 11);

    int summa = 0;
    int kaart;
    string input;
    while (summa < 21){
        kaart = jaotus(genereerija);
        summa += kaart;
        cout << "Tuli kaart: " << kaart << " hetkeseis: " << summa << "\n";
        if (summa < 21){
            cout << "Kas soovid kaarti (j/e)?";
            cin >> input;
            if (input == "e"){
                cout << "Lõpuseis: " << summa;
                break;
            }
        }
    }

    if (summa == 21){
        cout << "Võitsid!";
    } else if (summa > 21) {
        cout << "Kahjuks kaotasid.";
    }
}

int main(){
    kolmnurk();
}