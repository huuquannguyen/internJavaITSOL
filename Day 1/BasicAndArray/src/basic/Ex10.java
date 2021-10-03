package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex10 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String part = Ex1.choosePart(List.of("a", "b", "c"), input);
        switch(part){
            case "a" :
                System.out.println(SNT7());
                break;
            case "b" :
                System.out.println(allSNT());
                break;
            case "c" :
                System.out.println(daoSNT7());
                break;
        }
    }

    //a
    public static List<Integer> SNT7(){
        List<Integer> list = new ArrayList<>();
        for(int i = 1000000; i < 9999999; i++){
            if(Ex4.isSNT(i)){
                list.add(i);
            }
        }
        return list;
    }

    //b
    public static List<Integer> allSNT(){
        List<Integer> list = new ArrayList<>();
        int[] SNT = {2,3,5,7};
        for (int i : SNT) {
            String s = "";
            allSNT(s, list, i);
        }
        return list;
    }

    private static void allSNT(String s, List<Integer> list, int digit){
        s += digit;
        if(s.length() == 7){
            list.add(Integer.parseInt(s));
        }else{
            allSNT(s, list, 2);
            allSNT(s, list, 3);
            allSNT(s, list, 5);
            allSNT(s, list, 7);
        }
    }


    //c
    public static List<Integer> daoSNT7(){
        List<Integer> list = new ArrayList<>();
        for(int i = 1000000; i < 9999999; i++){
            if(Ex4.isSNT(i)){
                int dao = inverse(i);
                if(Ex4.isSNT(dao)){
                    list.add(i);
                }   
            }
        }
        return list;
    }

    public static int inverse(int n){
        String s = String.valueOf(n);
        StringBuilder inverse = new StringBuilder();
        for(int i = s.length() - 1; i >= 0; i--){
            inverse.append(s.charAt(i));
        }
        return Integer.parseInt(inverse.toString());
    }

}
