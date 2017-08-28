package demopackage;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ArraysDemo {
    public static void main(String... args) {
        int[][] array = new int[5][];
        for (int[] elementL1 : array) {
            for (int elementL2 : elementL1) {
                System.out.print(elementL2 + " ");
            }
            System.out.println();
        }
        array[0] = new int[1];

        int[] array2 = new int[] {1, 3, 5}; //Array literal
        m(new int[] {1,2,3});
        m(1,2,3);

        int[][] matrix = {
                {1,2},
                {},
                {1,2,3,4}
        };

//        System.arraycopy();
    }

    private static void m(int... param) {
    }

    public strictfp static void main1(String... args) {
        //region целочисленные
        byte b = 0b0; //literal
        short s = 01_000;
        int i = 0X10;
        long l = 1_000_000_000_000L;
        //endregion

        //region с плавающей точкой
        float f = 0.1F; //IEEE 754
        double d = -2.2e-3;
        //endregion

        //region символьный
        char c = '\uAAAA';
        //endregion

        //region логический
        boolean bb = true | false;
        //endregion

        //region Castings
        int ii = 1_000;
        byte bbb = (byte) ii;
//        System.out.println(bbb);
        //endregion

        //region Overflow
        byte value = -128;
        for (int counter = 0; counter < 257; counter++) {
            value++;
        }
//        System.out.println(value);
        //endregion

        //region Precision loss
        System.out.println(.1 + .2);
        //endregion

        //region Strings
        String str1 = "test" + " string"; //pool check + new
        String str2 = "test string"; //string pool
        System.out.println(str1 == str2); //interning

        String aaaa = new String("a");
        String bbbb = "a";
        System.out.println(aaaa.equals(bbbb));

        bbbb.toUpperCase();
        System.out.println(bbbb);
        //endregion

        //region Equality issues
        str1.equals("a"); //NPE!!!!
        "a".equals(str1);
        Objects.equals(str1, "a");
        //endregion

        //region String Extensions
        StringBuilder sbl;
        StringBuffer sbf;

        //"a" + "b" + "c" + "d" -> //Oracle JVM optimization
        new StringBuilder("a") //Fluent API | interface
                .append("b")
                .append("c")
                .append("d");
        //endregion

        //region Extract explaining variable
        Object message = null;
        boolean isBoolean = message instanceof Boolean;
        boolean isByte = message instanceof Byte;

        if (message instanceof Integer || isBoolean || isByte) {

        }
        //endregion

        //region operators
        //region promotions
        //byte, short, int -> int; long -> long; float, double -> f/d.
        //endregion
        //region bitwise
        System.out.println(4 >>> 33); // &, |,  ~, ^
        //endregion
        //region logical
//        System.out.println(f1() && f2()); //& | !
        //fopen("file") or die();
        //endregion
        //region compare
        System.out.println((2 != 3) && (1 == 1)); // > < != <= >= instanceof
        //endregion
        //region ternary
        System.out.println( 1!=1 ? 0 : f() );
        //endregion

        //region controlling executions
        if (true) {
            //....
        } else if(false) {

        } else {

        }

        String sss = null;
        final String sss2 = "5";
        switch (sss) {
            case sss2:
                System.out.println("1");
            case "4":
                System.out.println("5");
                break;
            case "3":
                System.out.println("3");
                break;
            default:
                System.out.println("def!!!!");
                break;
        }

        //endregion

        //region Cycles
        for (int j = 0; j < 10; j++) {

        }

        outer: do {
            inner: while (1 == theOne()) {
                break outer;
//                continue inner;
            }
        } while (false);

        for (String current : args) {

        }

        System.out.println("");
        //endregion
    }

    private static int theOne() {
        return 1;
    }

    private static boolean f() {
        System.out.println("!!!!!!!");
        return false;
    }
}
