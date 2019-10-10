package com.leetcode.easy;

import com.google.common.primitives.Chars;

import java.util.List;

public class Num771 {

    public static void main(String[] args) {
        System.out.println("j = 'aA', s = 'aAAbbb' 宝石数为：" + new Num771().numJewelsInStones1("aA", "aAAbbb"));
        System.out.println("j = 'z', s = 'ZZ' 宝石数为：" + new Num771().numJewelsInStones1("z", "ZZ"));
    }

    public int numJewelsInStones(String j, String s) {
        List<Character> jewelStones = Chars.asList(j.toCharArray());
        return Integer.parseInt(String.valueOf(Chars.asList(s.toCharArray()).stream().filter(jewelStones::contains).count()));
    }

    public int numJewelsInStones1(String J, String S) {
        int count =0;
        int[] j = new int[67];

        for (int i=0;i< J.length();i++){
            j[J.charAt(i)-65] = J.charAt(i);
        }

        for (int i=0;i< S.length();i++){
            if (j[S.charAt(i)-65] >0){
                count ++;
            }
        }

        return count;
    }
}
