#include "arvutused.h"
#include <cmath>
#include <iostream>
using namespace std;

int suurim(int* i, int* j, int* k){
    int suurim = *i > *j ? *i : *j;
    suurim = suurim > *k ? suurim : *k;

    return suurim;
}

int suurim(int& i, int& j, int& k){
    int suurim = i > j ? i : j;
    suurim = suurim > k ? suurim : k;

    return suurim;
}

int suurim(int& i, int* j, int* k){
    int suurim = i > *j ? i : *j;
    suurim = suurim > *k ? suurim : *k;

    return suurim;
}

void suurim(int* i, int* j, int*k, int* suurim){
    *suurim = *i > *j ? *i : *j;
    *suurim = *suurim > *k ? *suurim : *k;
}

void suurim(int& i, int& j, int& k, int& suurim){
    suurim = i > j ? i : j;
    suurim = suurim > k ? suurim : k;
}

/*
int main(){
    int a = 2;
    int b = 4;
    int c = 3;
    int tulemus = 0;

    int* p_a = &a;
    int* p_b = &b;
    int* p_c = &c;
    int* p_tulemus = &tulemus;

    suurim(a, b, c, tulemus);
    cout << tulemus << endl;
}
*/