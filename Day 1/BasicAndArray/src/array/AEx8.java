package array;

import java.util.Scanner;

public class AEx8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Type array length: ");
        int arrayLength = AEx1.takeInputGreaterThanZero(input);
        while(arrayLength > 1000){
            System.out.print("Array length must be less than 1000: ");
            arrayLength = AEx1.takeInputGreaterThanZero(input);
        }
        int[] arr = new int[arrayLength];
        for(int i = 0; i < arrayLength; i++){
            System.out.print("arr[" + i + "] : ");
            arr[i] = AEx1.takeInput(input);
        }
        maxRoad(arr);
    }

    public static void maxRoad(int[] arr){
        int count = 1;
        int countMax = 1;
        int startIndex = 0;
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] < arr[i + 1]){
                count++;
                if(countMax < count){
                    countMax = count;
                    startIndex = i - count + 2;
                }
            }else{
                count = 1;
            }
        }
        System.out.println("Max length of the road: " + countMax);
        System.out.println("The road start at index: " + startIndex);
    }
}
