#include "line2.h"
#include "point2.cpp"
#include <iostream>

using namespace std;

float Line2::length(){
    Point2 p = *p1;
    return p.distanceFrom(p2);
}

Point2* Line2::getP1(){
    return p1;
}

Point2* Line2::getP2(){
    return p2;
}

ostream& operator<<(ostream& os, const Line2& l){
    Point2* pt1 = l.p1;
    Point2* pt2 = l.p2;
    os << pt1 << " - " << pt2;
    return os; 
}