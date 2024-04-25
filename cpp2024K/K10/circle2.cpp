#include "circle2.h"
#include <cmath>

float Circle2::circumference(){
    return 2*r*M_PI;
}

float Circle2::area(){
    return r*r*M_PI;
}

bool Circle2::contains(shared_ptr<Point2> p){
    Point2 pt = *p1;
    if (pt.distanceFrom(p) <= r){
        return true;
    }

    return false;
}

bool Circle2::contains(shared_ptr<Line2> l){
    Point2 pt = *p1;
    Line2 ln = *l;
    if (pt.distanceFrom(ln.getNp1()) <= r && pt.distanceFrom(ln.getNp2()) <= r){
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
    return os;
}

shared_ptr<Point2> Circle2::getNp1(){
    return p1;
}

float Circle2::getNr(){
    return r;
}

void Circle2::setNp1(shared_ptr<Point2> p){
    p1 = p;
}

void Circle2:: setNr(float nr){
    r = nr;
}