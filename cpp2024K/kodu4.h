#ifndef KODU4_H
#define KODU4_H

#include <concepts>
using namespace std;

template <typename T, typename U>
T liida(T t, U u){
    return t + u;
}

template <typename T>
bool onVahemikus(T t, int i1, int i2){
    return t >= i1 && t <= i2
}

template <typename T>
//requires integral<T> || floating_point<T>
class Massiiv{
public:
    Massiiv();
    T getElement(int i);
    bool kasTais();
    bool kasTyhi();
    int getSuurus();
    void print();
    void lisaElement(T el);
    void kustutaElement();
    void asendaElement(T el1, T el2);
    int vabuKohti();
    bool kasSisaldub(T el);
private:
    T massiiv[100];
};

template <typename T>
Massiiv<T>::Massiiv(){}

template <typename T>
T Massiiv<T>::getElement(int i){
    if (massiiv[i] != 0){
        return massiiv[i];
    } else {
        return -1111;
    }
}

template <typename T>
bool Massiiv<T>::kasTais(){
    for (T elem : massiiv){
        if (elem == 0){
            return false;
        }
    }
    return true;
}

template <typename T>
bool Massiiv<T>::kasTyhi(){
    for (T elem : massiiv){
        if (elem != 0){
            return false;
        }
    }
    return true;
}

template <typename T>
int Massiiv<T>::getSuurus(){
    int count = 0;
    for (T elem : massiiv)
    {
        if (elem == 0){break;}
        count++;
    }
    return count;
}

template <typename T>
void Massiiv<T>::print(){
    string s = "";
    for(T elem : massiiv){
        if (elem == 0){break;}
        cout << elem << " ";
    }
    cout << endl;
}

template <typename T>
void Massiiv<T>::lisaElement(T el){
    for (int i = 0; i < 100; i++)
    {
        if (massiiv[i] == 0){
            massiiv[i] = el;
            break;
        }
    }
}

template <typename T>
void Massiiv<T>::kustutaElement(){
    for (int i = 0; i < 100; i++)
    {
        if (massiiv[i] == 0){
            massiiv[i-1] = 0;
            break;
        }
    }
}

template <typename T>
void Massiiv<T>::asendaElement(T el1, T el2){
    for (int i = 0; i < 100; i++)
    {
        if (massiiv[i] == el1){
            massiiv[i] = el2;
        }
    }
    
}

template <typename T>
int Massiiv<T>::vabuKohti(){
    int count = 0;
    for (T elem : massiiv)
    {
        if (elem == 0){break;}
        count++;
    }
    return 100 - count;
}

template <typename T>
bool Massiiv<T>::kasSisaldub(T el){
    for(T elem : massiiv){
        if (elem == el){
            return true;
        }
    }
    return false;
}

#endif