package array;

import java.util.Arrays;
import java.util.Scanner;

public class AEx7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] arr = AEx2.fillAndSort(input);
        System.out.print("Type a value to add: ");
        int x = AEx1.takeInput(input);
        System.out.println("Array after add: " + Arrays.toString(addToASortedArray(arr, x)));
    }

    public static int[] addToASortedArray(int[] arr, int x){
        int[] temp = new int[arr.length + 1];
        for(int i = 0; i < arr.length; i++){
            temp[i] = arr[i];
            if(x < arr[i]){
                temp[i] = x;
                for(int j = i + 1; j < temp.length; j++){
                    temp[j] = arr[j - 1];
                }
                break;
            }
        }
        return temp;
    }
}
