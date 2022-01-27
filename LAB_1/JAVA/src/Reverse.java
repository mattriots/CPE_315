public class Reverse {

    public static void main(String[] args) {
        int num = 1432216949;
        int num1 = 11;
        int [] binaryNum = {0,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,1,1,1,0,0,1,0,1,0,1,1,1,0,1,0,1};

        reverseBinary(num1);
//        reverse(binaryNum);
    }



    public static void reverseBinary(int num){
        int reversed = 0;

        for(int i = 0; i < 32; i++){
            reversed <<= 1;
            reversed |= (num & 1);
            num >>>= 1;
        }
    }

    public static void reverse(int[] binaryNum){

        int[] binaryReverse = new int[32];

        for(int i = 0; i < binaryNum.length; i++){
            binaryReverse[31 - i] = binaryNum[i];
        }
//
//        for(Integer num : binaryNum){
//            System.out.print(num);
//        }
//        System.out.println();
        for(Integer num : binaryReverse){

            System.out.print(num);
        }
        System.out.print(" <- reversed");


    }

}
