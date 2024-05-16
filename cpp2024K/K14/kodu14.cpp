#include "kodu14.h"
#include <string>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

template <typename T>
void liida(vector<T>& v, T& lisa){
    [lisa](vector<T> & v){
        for (int i = 0; i < v.size(); i++){
            v[i] += lisa;
        }
    }(v);
}

template <typename T>
void vali(vector<T>& v, T a, T b, vector<T>& v1){
    copy_if(v.begin(), v.end(), back_inserter(v1), [a, b](int x){return x <= b && x >= a;});
}

int main(){
    vector<int> v1{1, 2, 3, 4, 5};
    int l1{10};
    liida<int>(v1, l1);

    vector<int> v2;
    vali(v1, 11, 13, v2);

    for_each(v1.begin(), v1.end(), [](int x){cout << x << " ";});
    cout << endl;

    for_each(v2.begin(), v2.end(), [](int x){cout << x << " ";});
    cout << endl;
    
    return 0;
}