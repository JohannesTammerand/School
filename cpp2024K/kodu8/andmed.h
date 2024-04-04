#include <memory>
#include <vector>
using namespace std;

unique_ptr<int[]> loo_andmed_u(int);
unique_ptr<int[]> tootle_andmeid(unique_ptr<int[]>, int);
shared_ptr<vector<int>> loo_andmed_s(int);
shared_ptr<vector<int>> tootle_andmeid(shared_ptr<vector<int>>);
unique_ptr<int> genereeri_arv();
void proovi_arvu(weak_ptr<int>);