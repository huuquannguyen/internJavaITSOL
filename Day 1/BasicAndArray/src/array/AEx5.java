package array;

import java.util.Scanner;

public class AEx5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] arr = AEx1.fillArray(input);
        System.out.print("Type targer: ");
        int target = AEx1.takeInput(input);
        System.out.println("The nearest index's value in the array to the target is: " + findTargetIndex(arr, target));
    }

    public static int findTargetIndex(int[] arr, int target){
        int minDiff = Math.abs(target - arr[0]);
        int minIndex = 0;
        for(int i = 1; i < arr.length; i++){
            if(minDiff > Math.abs(arr[i] - target)){
                minDiff = Math.abs(arr[i] - target);
                minIndex = i;
            }
        }
        return minIndex;
    }
}
