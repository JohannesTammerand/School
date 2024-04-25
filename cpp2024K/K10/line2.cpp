#include "line2.h"
#include "point2.cpp"
#include <iostream>

using namespace std;

float Line2::length(){
    Point2 p = *p1;
    return p.distanceFrom(p2);
}

shared_ptr<Point2> Line2::getNp1(){
    return p1;
}

shared_ptr<Point2> Line2::getNp2(){
    return p2;
}

void Line2::setNp1(shared_ptr<Point2> p){
    p1 = p;
}

void Line2::setNp2(shared_ptr<Point2> p){
    p2 = p;
}

ostream& operator<<(ostream& os, const Line2& l){
    shared_ptr<Point2> pt1 = l.p1;
    shared_ptr<Point2> pt2 = l.p2;
    os << pt1 << " - " << pt2;
    return os; 
}
