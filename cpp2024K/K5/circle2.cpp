#include "circle2.h"
#include <cmath>

float Circle2::circumference(){
    return 2*r*M_PI;
}

float Circle2::area(){
    return r*r*M_PI;
}

bool Circle2::contains(Point2* p){
    Point2 pt = *p1;
    if (pt.distanceFrom(p) <= r){
        return true;
    }

    return false;
}

bool Circle2::contains(Line2* l){
    Point2 pt = *p1;
    Line2 ln = *l;
    if (pt.distanceFrom(ln.getP1()) <= r && pt.distanceFrom(ln.getP2()) <= r){
        return true;
    }

    return false;
}

void Circle2::scale(float factor){
    if (factor >= 0){
        r *= factor;
    }
}

ostream& operator<<(ostream& os, Circle2& c){
    os << "(" << c.p1 << ", " << c.r << ")";
}