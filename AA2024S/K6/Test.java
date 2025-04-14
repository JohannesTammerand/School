package K6;

import java.util.*;

public class Test {
    private int num;

    public void add(int i){
        num += i;
    }

    public int getNum(){
        return num;
    }

    public static boolean check(String s){
        return s.length() == 0 || s == null;
    }

    public static void main(String[] args) {
        Map<String, ? extends Number> hm = new HashMap<String, Integer>();

        System.out.println(check(null));
    }
}

//public class Movie{
//    String category;
//    String title;
//
//    public Movi
//}

//public class Test2 extends Test{
//
//}
