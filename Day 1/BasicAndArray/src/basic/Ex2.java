package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = Ex1.takeInput(input);
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= n/2; i++){
            if(n % i == 0){
                list.add(i);
            }
        }
        list.add(n);
        System.out.println("Các ước của " + n + "là: " + list);
        System.out.println(n + " có " + list.size() + " ước");
    }
}
