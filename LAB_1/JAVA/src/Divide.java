public class Divide {

    public static void main(String[] args) {

        divide(1, 1, 65536);
        System.out.println("Answer ^ : 0,65536");
        divide(2, 10, 65536);
        System.out.println("Answer ^: 0,131072");
        divide(42, 32, 32);
        System.out.println("Answer ^: 1,1342177281");
        divide(210, 64, 64);
        System.out.println("Answer ^: 3,1207959553");





    }


    public static void divide(int high, int low, int divisor) {

        while (divisor > 1) {
            divisor >>>= 1;

            low >>>= 1;

            low |= ((high & 1) * Integer.MIN_VALUE);

            high >>>= 1;

        }
        System.out.println("=================");
        System.out.println("HIGH: " + high);
        System.out.println("LOW: " + low);
        System.out.println("DIV: " + divisor);
        System.out.println("=================");



    }


}
