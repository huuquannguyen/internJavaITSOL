package basic;

import java.util.Scanner;

public class Ex5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = Ex1.takeInput(input);
        System.out.println("Sum of digits: " + sumDigits(n));
    }

    public static int sumDigits(int n) {
        int result = 0;
        while(n > 0){
            result += n % 10;
            n /= 10;
        }
        return result;
    }
}
