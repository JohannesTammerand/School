#include "point2.h"
#include <iostream>
#include <cmath>

using namespace std;

float Point2::distanceFrom(Point2* p){
    Point2 punkt = *p;
    return sqrt(pow(punkt.x - x, 2) + pow(punkt.y - y, 2));
}

ostream& operator<<(ostream& os, const Point2& p){
    os << "(" << p.x << "," << p.y << ")";
    return os; 
}

