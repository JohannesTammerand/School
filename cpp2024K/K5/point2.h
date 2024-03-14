#include <iostream>
using namespace std;

class Point2{
public:
    Point2() = default;
    Point2(float nx, float ny) : x{nx}, y{ny} {};
    float distanceFrom(Point2*);
    friend ostream& operator<<(ostream& os, const Point2& p);
private:
    float x{};
    float y{};
};