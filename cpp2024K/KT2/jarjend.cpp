#include <memory>
#include "jarjend.h"
#include <iostream>

template <typename T>
void Jarjend<T>::lisa(std::shared_ptr<T> el){
    j.push_back(el);
}

template <typename T>
void Jarjend<T>::kustuta (T el){
    for (int i = 0; i < j.size(); i++){
        if (*j[i] == el){
            j.erase(j.begin() + i);
            break;
        }
    }
}

template <typename T>
void Jarjend<T>::kuva(){
    for (std::shared_ptr<T> el : j){
        std::cout << *el << " ";
    }
}


int main(){
    std::shared_ptr<int> esimene (new int(10)), teine (new int(20)), kolmas (new int(30));
    Jarjend<int> jarjend;
    jarjend.lisa(esimene);
    jarjend.lisa(teine);
    jarjend.lisa (kolmas);
    jarjend.kuva();
    std::cout << '\n';
    jarjend.kustuta(20);
    jarjend.kuva();

    return 0;
}