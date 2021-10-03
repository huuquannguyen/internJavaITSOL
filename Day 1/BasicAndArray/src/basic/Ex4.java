package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a = Ex1.takeInput(input);
        int b = Ex1.takeInput(input);
        System.out.println("List so nguyen to trong khoang [" + a + ", " + b + "]: " + listSNT(a, b));
    }

    public static List<Integer> listSNT(int a, int b){
        List<Integer> result = new ArrayList<>();
        int maxAB = Math.max(a, b);
        int minAB = Math.min(a, b);
        if(maxAB < 2){
            return result;
        }else if(maxAB == 2){
            result.add(2);
            return result;
        }
        for(int i = Math.max(minAB, 3); i <= maxAB; i++){  
            if(isSNT(i)){
                result.add(i);
            }
        }
        return result;
    }

    public static boolean isSNT(int a){
        for(int j = 2; j <= Math.sqrt(a); j++){
            if(a % j == 0){
                return false;
            }
        }
        return true;
    }
}
