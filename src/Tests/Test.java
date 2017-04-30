package Tests;

/**
 * Created by caitlincoyiuto on 2017-03-05.
 */


import static java.lang.System.out;

public class Test {

        public static void main (String[] args) {
            byte b;
            int  i;

            out.printf("Type extending and truncating -- see slides\n");
            b = -6;
            i = b;
            // I am extending or promoting b by storing 1 byte into a 4 byte integer
            out.printf ("b: 0x%x  %d\t i: 0x%x  %d\n", b, b, i, i);
            out.printf ("b: %s  0x%x %d\t\t i: %s  0x%x %d\n", Integer.toBinaryString(b & 0xff), b, b, Integer.toBinaryString(i), i, i);


        }
    }

