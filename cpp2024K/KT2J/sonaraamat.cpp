#include "sonaraamat.h"
#include <iostream>

using namespace std;

template <typename T>
requires integral<T> || floating_point<T>
void Sonaraamat<T>::lisa(shared_ptr<T> uus){
    sonaraamat[uus]++;
}

template <typename T>
requires integral<T> || floating_point<T>
void Sonaraamat<T>::vahenda(shared_ptr<T> a){
    sonaraamat[a]--;
    if (sonaraamat[a] == 0){
        sonaraamat.erase(a);
    }
}

template <typename T>
requires integral<T> || floating_point<T>
int Sonaraamat<T>::suurus(){
    return sonaraamat.size();
}

template <typename T>
requires integral<T> || floating_point<T>
pair<shared_ptr<T>, int> Sonaraamat<T>::koigeSagedasem(){
    pair<shared_ptr<T>, int> sagedaseimPaar;
    int sagedaseimInt = 0;
    for(pair<shared_ptr<T>, int> paar : sonaraamat){
        if (paar.second > sagedaseimInt){
            sagedaseimPaar = paar;
            sagedaseimInt = paar.second;
        }
    }

    return sagedaseimPaar;
}

int main(){
    shared_ptr<int> esimene(new int(10)), teine(new int(20)), kolmas(new int(30));
    Sonaraamat<int> sonaraamat;
    sonaraamat.lisa(esimene);
    sonaraamat.lisa(teine);
    sonaraamat.lisa(kolmas);
    sonaraamat.lisa(kolmas);
    sonaraamat.lisa(kolmas);
    sonaraamat.vahenda(teine);
    pair<shared_ptr<int>, int> paar = sonaraamat.koigeSagedasem();
    cout << "Viitade arv: " << sonaraamat.suurus() << '\n';
    cout << "Enim esineneud: (" << *(paar.first) << ", " << paar.second << ")\n";
}