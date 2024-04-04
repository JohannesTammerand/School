#include "andmed.h"
#include <iostream>
 
template <typename Int, typename Ptr>
concept Yl1 = requires(Int i, Ptr s) {
  { loo_andmed_u(Int{}) } -> std::same_as<Ptr>;
  { tootle_andmeid(Ptr{}, i) } -> std::same_as<Ptr>;
};
 
static_assert(Yl1<int, std::unique_ptr<int[]>>);
 
template <typename Int, typename Ptr>
concept Yl2 = requires(Int i, Ptr s) {
  { loo_andmed_s(Int{}) } -> std::same_as<Ptr>;
  { tootle_andmeid(Ptr{}) } -> std::same_as<Ptr>;
};
 
static_assert(Yl2<int, std::shared_ptr<std::vector<int>>>);
 
template <typename Int, typename U_Ptr, typename W_Ptr, typename S_Ptr>
concept Yl3 = requires(Int i, U_Ptr u, W_Ptr w, S_Ptr s) {
  { genereeri_arv() } -> std::same_as<U_Ptr>;
  { proovi_arvu(W_Ptr{}) } -> std::same_as<void>;
};
 
static_assert(
    Yl3<int, std::unique_ptr<int>, std::weak_ptr<int>, std::shared_ptr<int>>);
 
#ifndef VPL_TEST
int main(int argc, char *argv[]) {
  auto ptr = loo_andmed_u(5);
 
  auto arv = genereeri_arv();
  std::cout << *arv << '\n';
  arv = genereeri_arv();
  std::cout << *arv << '\n';
 
  auto eksisteerivArv = std::make_shared<int>(1);
  proovi_arvu(
      eksisteerivArv);  // jagatud viidast tehakse automaatselt nõrk viit
 
  std::weak_ptr<int> wp;
  proovi_arvu(wp);
 
  auto andmed = loo_andmed_u(10);
  for (int i = 0; i < 10; i++) {
    std::cout << andmed[i] << ' ';
  }
  std::cout << '\n';
  // https://en.cppreference.com/w/cpp/utility/move
  // Või küsi oma sõbralikult naabruskonna praksijuhendajalt
  andmed = tootle_andmeid(std::move(andmed), 10);
 
  for (int i = 0; i < 10; i++) {
    std::cout << andmed[i] << ' ';
  }
  std::cout << '\n';
 
  auto andmed2 = loo_andmed_s(10);
  for (int i = 0; i < 10; i++) {
    std::cout << (*andmed2)[i] << ' ';
  }
  std::cout << '\n';
  andmed2 = tootle_andmeid(andmed2);
 
  for (int i = 0; i < 10; i++) {
    std::cout << (*andmed2)[i] << ' ';
  }
  std::cout << '\n';
 
  return 0;
}
#endif