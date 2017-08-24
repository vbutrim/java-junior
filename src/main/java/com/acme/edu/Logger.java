package com.acme.edu;

import java.util.Objects;

import static java.lang.System.lineSeparator;

public class Logger {
    static private String previousString = "";
    static private int countPrevString = 1;

    static private boolean toInput = false;
    static private int currentSum = 0;

    public static void log(Object message) {
        if (toInput && !(message instanceof Integer)) {
            System.out.print("primitive: " + currentSum + lineSeparator());
            toInput = false;
            currentSum = 0;
        }

        if (!(message instanceof String)) {
            flushString();
        }

        if (message instanceof String) {
            String[] args = ((String) message).split(" ");
            if ("str".equals(args[0])) {
                message = args[1];
            }

            if (!previousString.equals((String) message)) {
                flushString();
                previousString = (String) message;
                ++countPrevString;
            } else {
                ++countPrevString;
            }

            return;

            // System.out.print("string: ");
        } else if (message instanceof Character) {
            System.out.print("char: ");
        } else if (message instanceof Integer) {
            if (currentSum != 0 && currentSum + (int) message < Integer.MIN_VALUE + (int)message - 2) {
                System.out.print("primitive: " + currentSum + lineSeparator());
                toInput = false;
                currentSum = 0;
            }
            currentSum += (int) message;
            toInput = true;

            countPrevString = 0;
            previousString = "";

            return;
        } else if (message instanceof Byte ||
                message instanceof Boolean) {
            System.out.print("primitive: ");
        } else
            System.out.print("reference: ");

        System.out.print(message + lineSeparator());

        countPrevString = 0;
        previousString = "";
    }

    public strictfp static void main(String... args) {
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
        byte bbb = (byte)ii;
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

        //"a" + "b" + "c" + "d" ->
        new StringBuilder("a") //Fluent API | interface
                .append("b")
                .append("c")
                .append("d"); //Oracle JVM optimization
        //endregion
    }

    // public static void flush

    public static void flushInt() {
        if (toInput) {
            System.out.print("primitive: " + currentSum + lineSeparator());
            toInput = false;
            currentSum = 0;
        }
    }

    public static void flushString() {
        if ( countPrevString != 0 && !"".equals(previousString)) {
            System.out.print("string: " + previousString);
            if (countPrevString > 1) {
                System.out.print(" (x" + countPrevString + ")");
            }
            System.out.print(lineSeparator());
            countPrevString = 0;
            previousString = "";
        }
    }
}
