#include <iostream>
#include <vector>
using namespace std;

struct Yhendus{
    string kliendiNimi;
    string serveriNimi;
    int number;

    Yhendus(string kNimi, string sNimi, int number2) : kliendiNimi{kNimi}, serveriNimi{sNimi}, number{number2}{};
    friend ostream& operator<<(ostream& os, const Yhendus& y);
};

class Server{
    private:
        string nimi;
        vector<Yhendus*> yhendused;
    public:
        Server(string nimi2) : nimi{nimi2}{};
        string getNimi() {return nimi;}
        vector<Yhendus*> getViidad() {return yhendused;}
        void lisaYhendus (Yhendus*);
        void kuvaYhendused();
        ~Server();
};

