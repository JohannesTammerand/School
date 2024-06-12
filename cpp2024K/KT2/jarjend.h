#ifndef JARJEND_H
#define JARJEND_H

#include <memory>
#include <concepts>
#include <vector>

template <typename T>
requires std::integral<T> || std::floating_point<T>
class Jarjend{
public:
    void lisa(std::shared_ptr<T>);
    void kustuta(T);
    void kuva();
private:
    std::vector<std::shared_ptr<T>> j;
};



#endif