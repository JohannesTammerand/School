#include <iostream>
#include <memory>
using namespace std;

class Point2{
public:
    Point2 () : x{0}, y{0} {};
    Point2 (float nx, float ny) : x{nx}, y{ny} {};

    float distanceFrom(shared_ptr<Point2>);
    friend ostream& operator<<(ostream& os, const Point2& p);
    
    float getNx();
    void setNx(float x);
    float getNy();
    void setNy(float y);
private:
    float x{};
    float y{};
};