#include <memory>
#include <cstdlib>
#include <iostream>
#include <vector>
#include <random>

using namespace std;

unique_ptr<int[]> loo_andmed_u(int n)
{
    if (n < 1){
        return nullptr;
    }

    unique_ptr<int[]> massiiv(new int[n]);


    for (int i = 0; i < n; i++)
    {
        massiiv[i] = rand();
    }
    return massiiv;
}

unique_ptr<int[]> tootle_andmeid(unique_ptr<int[]> massiiv, int n)
{
    if (massiiv == nullptr) {
        return nullptr;
    }

    int summa = 0;
    for (int i = 0; i < n; i++)
    {
        summa += massiiv[i];
        massiiv[i] = -massiiv[i];
    }

    double keskmine = (double)summa / n;

    cout << summa << endl << keskmine << endl;

    return massiiv;
}

shared_ptr<vector<int>> loo_andmed_s(int n)
{
    if(n < 1) {
        return nullptr;
    }

    shared_ptr<vector<int>> massiiv(new vector<int>);

    for (int i = 0; i < n; i++)
    {
        massiiv->push_back(rand());
    }

    return massiiv;
}

shared_ptr<vector<int>> tootle_andmeid(shared_ptr<vector<int>> massiiv)
{
    if (massiiv == nullptr) {
        return nullptr;
    }

    int summa = 0;
    for (int i = 0; i < (massiiv).size(); i++)
    {
        summa += massiiv->at(i);
        (massiiv)[i] = -(massiiv)[i];
    }

    double keskmine = (double)summa / (massiiv).size();

    cout << summa << endl << keskmine << endl;

    return massiiv;
}

unique_ptr<int> genereeri_arv()
{
    random_device rand;
    mt19937 gen(rand());
    uniform_int_distribution<> dist(0, 100);

    unique_ptr<int> arv(new int(dist(gen)));

    return arv;
}

void proovi_arvu(weak_ptr<int> i)
{
    if(i.expired())
    {
        cout << "ei eksisteeri" << endl;
    }
    else
    {
        auto p = i.lock();
        cout << *p << endl;
    }
}