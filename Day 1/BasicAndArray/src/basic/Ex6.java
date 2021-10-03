package basic;

import java.util.Scanner;

public class Ex6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = Ex1.takeInput(input);
        System.out.println(n + " = " + phanTich(n));
    }

    public static String phanTich(int a){
        StringBuilder sb = new StringBuilder();
        while(a > 1){
            for(int i = 2; i <= a; i++){
                if(i == a || Ex4.isSNT(a)){
                    sb.append(a);
                    a = 1;
                    break;
                }else{
                    if(a % i == 0){
                        sb.append(i + " x ");
                        a = a / i;
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }


}
