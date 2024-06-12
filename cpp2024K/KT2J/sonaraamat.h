#ifndef SONARAAMAT_H
#define SONARAAMAT_H

#include <map>
#include <memory>

using namespace std;

template <typename T>
requires integral<T> || floating_point<T> 
class Sonaraamat{
public:
    void lisa(shared_ptr<T>);
    void vahenda(shared_ptr<T>);
    int suurus();
    pair<shared_ptr<T>, int> koigeSagedasem();
private:
    map<shared_ptr<T>, int> sonaraamat;
};

#endif