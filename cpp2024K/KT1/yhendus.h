#include <iostream>
using namespace std;

struct Yhendus{
    string kliendiNimi;
    string serveriNimi;
    int number;

    Yhendus(string kNimi, string sNimi, int number2) : kliendiNimi{kNimi}, serveriNimi{sNimi}, number{number2}{};
    friend ostream& operator<<(ostream& os, const Yhendus& y);
};

ostream& operator<<(ostream& os, const Yhendus& y){
    os << "(" << y.kliendiNimi << ", " << y.serveriNimi << ", " << y.number << ")";
    return os;
}

