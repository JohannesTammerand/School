#include <string>
#include <vector>
#include <iostream>

using namespace std;

bool kabe(vector<vector<string>> v){
    int musti2 = 0;
    int valgeid2 = 0;
    for (int i = 0; i < v.size(); i++){
        vector<string> v2 = v[i];
        int musti = 0;
        int valgeid = 0;
        for(string s : v2){
            if (s == "1"){
                musti++;
            } else if (s == "0"){
                valgeid++;
            }

            if (i != 0){
                if (musti != musti2 || valgeid != valgeid2){
                    return false;
                    break;
                }
            } else {
                musti2 = musti;
                valgeid2 = valgeid;
            }
        }
    }

    return true;
}

int main(){
    bool b = kabe({{"0", "1", "1", "1"},
                   {"1", "0", "1", "0"},
                   {"A", "1", "B", "0"},
                   {"1", "0", "1", "C"}});

    cout << boolalpha << b << endl;
}