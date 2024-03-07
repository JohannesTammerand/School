#include  <iostream>
#include "kodu4.h"

using namespace std;

int main(){
    Massiiv<int> m{};
    m.lisaElement(5);
    m.lisaElement(8);
    m.lisaElement(5);
    m.lisaElement(5);
    m.print();
    m.kustutaElement();
    m.print();
    m.lisaElement(25);
    m.asendaElement(m.getElement(0), 9);
    m.print();
    cout << m.getElement(8) << '\n';
    cout << boolalpha << m.kasTais() << " " << m.kasTyhi() << '\n';
    cout << m.vabuKohti() << " "  << m.kasSisaldub(23) << " " << m.getSuurus() << '\n';
}

