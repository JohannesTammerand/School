#include "point2.h"
#include <iostream>
using namespace std;

class Line2{
public:
    Line2 (Point2* np1, Point2* np2) : p1{np1}, p2{np2} {};
    float length();
    friend ostream& operator<<(ostream& os, const Line2& l);
    Point2* getP1();
    Point2* getP2();
private:
    Point2* p1{};
    Point2* p2{};
};