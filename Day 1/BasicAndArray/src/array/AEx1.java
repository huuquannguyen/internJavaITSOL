package array;

import java.util.Scanner;

public class AEx1 {
    public static void main(String[] args) {
        int[] arr = {15, 2, 1, 2, 2, 15};
        System.out.println(checkDX(arr));
    }

    public static boolean checkDX(int[] arr){
        for(int i = 0; i < arr.length; i++){
            if(i > arr.length - 1 - i){
                return true;
            }
            if(arr[i] != arr[arr.length - 1 - i]){
                return false;
            }
        }
        return true;
    }

    public static int[] fillArray(Scanner input){
        System.out.print("Type array length: ");
        int n = takeInputGreaterThanZero(input);
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            System.out.print("arr[" + i + "] : ");
            arr[i] = takeInput(input);
        }
        return arr;
    }

    public static int takeInput(Scanner input){
        int n = 0;
        while(n == 0){
            try {
               n = input.nextInt();
               if(n == 0){
                   break;
               }
            }catch (Exception e) {
                System.out.print("You must type an integer: ");
                input.next();
                n = 0;
            }
        }
        return n;
    }

    public static int takeInputGreaterThanZero(Scanner input){
        int n = 0;
        while(n == 0){
            try {
               n = input.nextInt();
               if(n <= 0){
                   throw new IllegalArgumentException("Value must be greater than 0: ");
               }
            }catch (IllegalArgumentException i){
                System.out.print(i.getMessage());
                n = 0;
            }
             catch (Exception e) {
                System.out.print("You must type an integer: ");
                input.next();
                n = 0;
            }
        }
        return n;
    }

    public static int[] fillArrayGreaterThanZero(Scanner input){
        System.out.print("Type array length: ");
        int n = takeInputGreaterThanZero(input);
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            System.out.print("arr[" + i + "] : ");
            arr[i] = takeInputGreaterThanZero(input);
        }
        return arr;
    }
}
