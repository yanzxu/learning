package com.leetcode.medium;

public class Num59 {
    public static void main(String[] args) {
        int[][] matrix = generateMatrix(3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(matrix[i][j]);
            }
        }
    }

    public  static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int max = n*n;
        int num = 1;
        int l = 0;
        int r = n-1;
        int u = 0;
        int d = n-1;

        while (num<=max){
            for (int i = l;i<=r;i++)
                matrix[u][i] = num ++;
            u++;

            for (int i = u; i <= d; i++)
                matrix[i][r] = num++;
            r--;

            for (int i = r; i >=l ; i--)
                matrix[d][i] = num++;
            d--;

            for (int i = d; i >=u ; i--)
                matrix[i][l] = num++;
            l++;
        }

        return matrix;
    }

}
