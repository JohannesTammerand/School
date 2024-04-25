#ifndef CIRCLE2_H
#define CIRCLE2_H

#include "line2.cpp"

class Circle2{
public:
    Circle2 () : p1{make_shared<Point2>()}, r{0} {};
    Circle2 (shared_ptr<Point2> p1, float nr) : p1{p1}, r{nr >= 0 ? nr : 0} {};
    Circle2 (const Circle2& cr) : p1{cr.p1}, r{cr.r} {};
    Circle2 (Circle2 && cr) : p1{cr.p1}, r{cr.r} {
        cr.p1 = nullptr;
        cr.r = 0;
    }

    float circumference();
    float area();
    bool contains(shared_ptr<Point2> p);
    bool contains(shared_ptr<Line2> l);
    void scale(float factor);
    friend ostream& operator<<(ostream& os, Circle2& c);

    shared_ptr<Point2> getNp1();
    float getNr();
    void setNp1(shared_ptr<Point2> p);
    void setNr(float nr);
private:
    shared_ptr<Point2> p1{};
    float r{};
};

#endif