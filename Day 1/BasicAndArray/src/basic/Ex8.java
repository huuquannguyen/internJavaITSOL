package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String part = Ex1.choosePart(List.of("a", "b"), input);
        switch(part){
            case "a" :
                System.out.println(nghichDao());
                break;
            case "b" :
                System.out.println(nghichDaoChiaHet10());
                break;
        }
    }

    public static List<Integer> nghichDaoChiaHet10(){
        List<Integer> list = new ArrayList<>();
        for(int i = 100; i <= 999; i++){
            if((Ex5.sumDigits(i) * 2) % 10 == 0){
                String s = Integer.toString(i);
                StringBuilder sb = new StringBuilder(s);
                for(int j = 2; j >= 0; j--){
                    sb.append(s.charAt(j));
                }
                list.add(Integer.parseInt(sb.toString()));
            }
        }
        return list;
    }

    public static List<Integer> nghichDao(){
        List<Integer> list = new ArrayList<>();
        for(int i = 100; i <= 999; i++){
            String s = Integer.toString(i);
            StringBuilder sb = new StringBuilder(s);
            for(int j = 2; j >= 0; j--){
                sb.append(s.charAt(j));
            }
            list.add(Integer.parseInt(sb.toString()));
        }
        return list;
    }
}
