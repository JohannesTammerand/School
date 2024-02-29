#include <iostream>

using namespace std;

double ruutjuur(){
    int n;
    cout << "Sisesta positiivne täisarv";
    cin >> n;

    double alg;
    double r;

    alg = n/2.0;
    r = n/alg;
    double alg2 = alg;
    alg = (alg + r) / 2;

    while (alg/alg2 > 1.01){
        r = n/alg;
        alg2 = alg;
        alg = (alg + r) / 2;
    }

    cout << alg;
}

int main(){
    ruutjuur();
}