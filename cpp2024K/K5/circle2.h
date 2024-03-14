#include "point2.cpp"
#include "line2.cpp"

class Circle2{
public:
    Circle2 (Point2* np1, float nr) : p1{np1}, r{nr >= 0 ? nr : 0} {};
    float circumference();
    float area();
    bool contains(Point2* p);
    bool contains(Line2* l);
    void scale(float factor);
    friend ostream& operator<<(ostream& os, Circle2& c);
private:
    Point2* p1{};
    float r{};
};