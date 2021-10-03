package array;

import java.util.Arrays;
import java.util.Scanner;

public class AEx6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap mang a: ");
        int[] a = AEx1.fillArray(input);
        System.out.println("Nhap mang b: ");
        int[] b = AEx1.fillArray(input);
        System.out.println("Nhap vi tri chen: ");
        int p = AEx1.takeInputGreaterThanZero(input);
        while(p >= a.length){
            System.out.print("p must to be less than " + a.length + ": ");
            p = AEx1.takeInputGreaterThanZero(input);
        }
        a = addArray(a, b, p);
        System.out.println("a: " + Arrays.toString(a));
    }

    public static int[] addArray(int[] a, int[] b, int p){
        int[] temp = new int[a.length + b.length];
        boolean added = false;
        for(int i = 0; i < a.length; i++){
            if(i == p){
                for(int j = 0; j < b.length; j++){
                    temp[i + j] = b[j];
                }
                added = true;
            }
            if(added){
                temp[i + b.length] = a[i];
            }else{
                temp[i] = a[i];
            }
        }
        return temp;
    }
}
