#include "portaal.cpp"

int main(){
    Kaup k1{"takisti", 1.5};
    Kaup k2{"sensor", 2.2};
    Kaup k3{"vihik", 0.25};
    Kaup k4{"klade", 3.6};
    Myyja m1{"Oomipood"};
    Myyja m2{"Büroomaailm"};
    m1.lisaKaup(k1);
    m1.lisaKaup(k2);
    m2.lisaKaup(k3);
    m2.lisaKaup(k4);
    m1.kuva();
    m2.kuva();
    m2.kustutaKaup("klade");
    m2.muudaHind("vihik", 0.52);
    m2.kuva();
    Portaal portaal;
    portaal.lisaMyyja(&m1);
    portaal.lisaMyyja(&m2);
    portaal.kuva();
}