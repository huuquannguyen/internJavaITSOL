package array;

import java.util.Arrays;
import java.util.Scanner;

public class AEx9 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Fill first matrix: ");
        int[][] m1 = fillMatrix(input);
        System.out.println("Fill second matrix: ");
        int[][] m2 = fillMatrix(input);
        System.out.println("Result: " + Arrays.deepToString(multiple2Matrixes(m1, m2)));
    }

    public static int[][] multiple2Matrixes(int[][] m1, int[][] m2){
        if(m1[0].length != m2.length){
            System.out.println("These matrixes can not multiple to each other");
            return null;
        }
        int[][] result = new int[m1.length][m2[0].length];
        for(int i = 0; i < m1.length; i++ ){
            for(int j = 0; j < m2[0].length; j++){
                for(int l = 0; l < m2.length; l++){
                    result[i][j] += m1[i][l] * m2[l][j];
                }
            }
        }
        return result;
    }

    public static int[][] fillMatrix(Scanner input){
        System.out.print("Total rows: ");
        int row = AEx1.takeInputGreaterThanZero(input);
        System.out.print("Total columns: ");
        int column = AEx1.takeInputGreaterThanZero(input);
        int[][] matrix = new int[row][column];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                System.out.print("matrix[" + i + "][" + j + "]: ");
                matrix[i][j] = AEx1.takeInput(input);
            }
        }
        return matrix;
    }
}
