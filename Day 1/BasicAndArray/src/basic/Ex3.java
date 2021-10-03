package basic;

import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a = Ex1.takeInput(input);
        int b = Ex1.takeInput(input);
        System.out.println("Choose part");
        String part = input.next();
        switch (part){
            case "a" :
                System.out.println("Uoc chung lon nhat: " + ucln(a, b));
                break;
            case "b" :
                System.out.println("Boi chung nho nhat: " + bcnn(a, b));
                break;
        }
    }

    public static int ucln(int a, int b){
        if(a == 0 || b == 0){
            return a + b;
        }
        while(a != b){
            if(a > b){
                a = a - b;
            }else{
                b = b - a;
            }
        }
        return a;
    }

    public static int bcnn(int a, int b){
        int maxBcnn = a * b;
        int unln = ucln(a, b);
        return maxBcnn / unln;
    }
}
