#include <iostream>
#include <vector>
#include <string>

using namespace std;

vector<int> positiivsed(vector<vector<string>> sisend){
    vector<int> tulemus;

    for (vector<string> s1 : sisend){
        int kokku = 0;
        for(string s2 : s1){
            try{
                if (stoi(s2) > 0){
                    kokku++;
                }
            } catch (invalid_argument){
                cout << "Ei saa teisendada täisarvuks - \"" << s2 << "\"\n";
            }
        }
        tulemus.push_back(kokku);
    }

    return tulemus;
}

int main(){
    vector<vector<string>> sisend {
        {"-3", "kaks", "112"},
        {"-3"},
        {"6", "-4", "11", "77"}};

    vector<int> v = positiivsed(sisend);

    for (int el : v){
        cout << el << " ";
    }
    cout << endl;
}