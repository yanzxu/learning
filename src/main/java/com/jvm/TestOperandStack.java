package com.jvm;

public class TestOperandStack {
    public static void main(String[] args) {
        int i1 = 10;
        i1++;
        int i2 = 10;
        ++i2;

        int i5=10;
        int i6= ++i5;

        int i3 = 10;
        int i4=i3++;




        int i7=10;
        i7=i7++;

        int i8=10;
        i8=++i8;



        int i9=10;
        int i10 = i9++ + ++i9;

        System.out.println("i1: " + i1 + "  i2: " + i2 + "  i3: " + i3 + "  i4: " + i4 + "  i5: " + i5 + "  i6: " + i6 + "  i7: " + i7 + "  i8: " + i8 +"  i9: " + i9 +"  i10: " + i10);
    }
}
