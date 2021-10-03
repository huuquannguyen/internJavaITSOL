package array;

import java.util.Arrays;
import java.util.Scanner;

public class AEx2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(Arrays.toString(fillAndSort(input)));
    }

    public static int[] fillAndSort(Scanner input){
        System.out.print("Type array length: ");
        int n = AEx1.takeInput(input);
        int[] arr = new int[n];
        for(int i = 0; i < n ; i++){
            System.out.print("arr[" + i + "] : ");
            arr[i] = AEx1.takeInput(input);
            for(int j = 0; j < i; j++){
                if(arr[i] < arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
