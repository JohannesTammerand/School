#include "point2.h"
#include <iostream>
#include <cmath>

using namespace std;

float Point2::distanceFrom(shared_ptr<Point2> p){
    Point2 punkt = *p;
    return sqrt(powf(punkt.x - x, 2) + powf(punkt.y - y, 2));
}

ostream& operator<<(ostream& os, const Point2& p){
    os << "(" << p.x << ", " << p.y << ")";
    return os; 
}

float Point2::getNx(){
    return x;
}

float Point2::getNy(){
    return y;
}

void Point2::setNx(float x){
    x = x;
}

void Point2::setNy(float y){
    y = y;
}

