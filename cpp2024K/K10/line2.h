#include "point2.cpp"
#include <iostream>
using namespace std;

class Line2{
public:
    Line2 () : p1{make_shared<Point2>()}, p2{make_shared<Point2>()} {};
    Line2 (shared_ptr<Point2> np1, shared_ptr<Point2> np2) : p1{np1}, p2{np2} {};
    Line2 (const Line2& lr) : p1{lr.p1}, p2{lr.p2} {};
    Line2 (Line2&& lr) : p1{lr.p1}, p2{lr.p2} {
        lr.p1 = nullptr;
        lr.p2 = nullptr;
    }

    float length();
    friend ostream& operator<<(ostream& os, const Line2& l);

    shared_ptr<Point2> getNp1();
    shared_ptr<Point2> getNp2();
    void setNp1(shared_ptr<Point2> p);
    void setNp2(shared_ptr<Point2> p);
private:
    shared_ptr<Point2> p1{};
    shared_ptr<Point2> p2{};
};