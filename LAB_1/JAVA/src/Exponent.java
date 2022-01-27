//public class Exponent {
//    public static void main(String[] args) {
//        int x = 2;
//        int y = 1;
//
////        System.out.println(exponent(x, y));
//        System.out.println("Final : " + exponent1(x, y));
////        System.out.println(mult(0, 10));
//    }
//
//    // Recursive with 2 methods
//    public static int exponent(int x, int y) {
//        if (y == 1) {
//            return x;
//        }
//        return mult(x, exponent(x, y - 1));
//    }
//
//    public static int mult(int x, int times) {
//        if (times == 1) {
//            return x;
//        }
//        return x + mult(x, times - 1);
//
//    }
////    End recurssion here
//
//
//    public static int exponent1(int x, int y) {
//        int result = x;
//        int newx = x;
//        for (int i = y; i > 1; i--) {
//            for (int j = x; j > 1; j--) {
//                result += newx;
//            }
//            newx = result;
//        }
//        return result;
//    }
//}
