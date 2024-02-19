class Keskmised {
 
  static double aritkeskmine(double arv1, double arv2) {
    double summa = arv1 + arv2;
    return summa / 2;
  }
 
  public static void main(String[] args) {
    double a = 1.5;
    double b = 2.25;
    double c = aritkeskmine(a, b);
    System.out.println("Arvude" + a +" ja " + b + " summa on " + summa);
    System.out.println("Arvude " + a + " ja " + b + " aritmeetiline keskmine on " + c);
    System.out.println("Arvude 3.4 ja -3.4 aritmeetiline keskmine on " + aritkeskmine(-3.4, 3.4));
  }
}