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