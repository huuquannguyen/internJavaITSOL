package basic;

import java.util.List;
import java.util.Scanner;

public class Ex1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String part = choosePart(List.of("a", "b"), input);
        switch (part) {
            case "a":
                int n = takeInput(input);
                System.out.println("Result: " + sumA(n));
                break;
            case "b":
                int v = takeInput(input);
                System.out.println("Result: " + sumB(v));
                break;
        }
        input.close();
          
    }

    public static String choosePart(List<String> listPart, Scanner input){
        System.out.print("Choose part: ");
        String part = input.next();
        while(!listPart.contains(part)){
            System.out.print("Dont have this part: ");
            part = input.next();
        }
        return part;
    }

    public static int sumA(int n){
        int result = 0;
        if(n % 2 == 0){
            for(int i = 2; i <= n; i += 2){
                result += i;
            }
        }else{
            for(int i = 1; i <= n; i += 2){
                result += i;
            }
        }
        return result;
    }

    public static double sumB(int n){
        double result = 0;
        for(int i = 1; i <= n; i++){
            result += (double) 1 / i;
        }
        return result;
    }


    // Lay n = 0 la moc, neu input khong phai integer -> set n = 0 va thuc hien lai vong lap
    //Neu input = 0 hoac mot so integer bat ki thi break vong lap va goi ham sum()
    public static int takeInput(Scanner input){
        System.out.print("Type n (n is an integer): ");
        int n = 0;
        while(n == 0){
            try {
               n = input.nextInt();
               if(n == 0){
                   break;
               }
            } catch (Exception e) {
                System.out.print("You must type an integer: ");
                input.next();
                n = 0;
            }
        }
        return n;
    }
}
