package array;

import java.util.Arrays;
import java.util.Scanner;

public class AEx11 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] matrix = fillSquareMatrix(input);
        sumCheo(matrix);
    }

    public static int[][] fillSquareMatrix(Scanner input){
        System.out.print("Matrix size: ");
        int size = AEx1.takeInputGreaterThanZero(input);
        int[][] matrix = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print("matrix[" + i + "][" + j + "]: ");
                matrix[i][j] = AEx1.takeInput(input);
            }
        }
        return matrix;
    }

    public static void sumCheo(int[][] matrix){
        int sumCheoChinh = 0;
        int sumCheoPhu = 0;
        for(int i = 0; i < matrix.length; i++){
            sumCheoChinh += matrix[i][i];
            sumCheoPhu += matrix[matrix.length - i - 1][i];
        }
        System.out.println("Tong duong cheo chinh: " + sumCheoChinh);
        System.out.println("Tong duong cheo phu: " + sumCheoPhu);
        System.out.println(Arrays.deepToString(matrix));
    }
}
