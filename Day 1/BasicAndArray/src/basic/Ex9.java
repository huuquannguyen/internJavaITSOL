package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex9 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String part = Ex1.choosePart(List.of("a", "b", "c"), input);
        switch(part){
            case "a" :
                System.out.println(thuanNghich(7, 9));
                break;
            case "b" :
                System.out.println("Dont know");
                break;
            case "c" :
                System.out.println(thuanNghichChia10(7, 9));
        }
    }

    //a
    public static List<Integer> thuanNghich(int min, int max){
        List<Integer> list = new ArrayList<>();
        for(int i = min; i <= max; i++){
            int[] range = findRange(i);
            int minHalf = range[0];
            int maxHalf = range[1];
            if(i % 2 != 0){
                //vi 7 va 9 la so le nen phai them 1 so o giua
                //vd: 3 so dau la 100, sau khi them 0 se dc 1000 va add them 001 theo thuat toan ta duoc 1000001 co 7 chu so
                for(int j = 0; j <= 9; j++){
                   for(int k = minHalf; k < maxHalf; k++){
                        String s = Integer.toString(k);
                        StringBuilder sb = new StringBuilder(s + j);
                        for(int n = i/2; n > 0; n--){
                            sb.append(s.charAt(n-1));
                        }
                        list.add(Integer.parseInt(sb.toString()));
                   } 
                }
            }else{
                for(int j = minHalf; j <= maxHalf; j++){
                    String s = Integer.toString(j);
                    StringBuilder sb = new StringBuilder(s);
                    for(int k = i/2; k > 0; k--){
                        sb.append(s.charAt(k-1));
                    }
                    list.add(Integer.parseInt(sb.toString()));
                }
            }
        }
        return list;
    }

    //b
    

    //c
    public static List<Integer> thuanNghichChia10(int min, int max){
        List<Integer> list = new ArrayList<>();
        for(int i = min; i <= max; i++){
            int[] range = findRange(i);
            int minHalf = range[0];
            int maxHalf = range[1];
            if(i % 2 != 0){
                for(int j = 0; j <= 9; j++){    
                    for(int k = minHalf; k < maxHalf; k++){
                        if((Ex5.sumDigits(k) * 2 + j) % 10 == 0){
                            String s = Integer.toString(k);
                            StringBuilder sb = new StringBuilder(s + j);
                            for(int n = i/2; n > 0; n--){
                                sb.append(s.charAt(n-1));
                            }
                            list.add(Integer.parseInt(sb.toString()));
                        }
                    } 
                }
            }else{               
                for(int j = minHalf; j <= maxHalf; j++){
                    if((Ex5.sumDigits(i) * 2) % 10 == 0 ){
                        String s = Integer.toString(j);
                        StringBuilder sb = new StringBuilder(s);
                        for(int k = i/2; k > 0; k--){
                            sb.append(s.charAt(k-1));
                        }
                        list.add(Integer.parseInt(sb.toString()));
                    }
                }
            }
        }
        return list;
    }

    //Tim min range va max range cua digitNum/2 so dau
    //Vd: digitNum = 7 => min range = 100, max range = 999 (7 / 2 = 3 => lay tu 100 den 999)
    public static int[] findRange(int digitNum){
        StringBuilder sbMin = new StringBuilder("1");
        StringBuilder sbMax = new StringBuilder("9");
        while(sbMin.length() < digitNum / 2){
            sbMin.append("0");
            sbMax.append("9");
        }
        int[] range = new int[2];
        range[0] = Integer.parseInt(sbMin.toString());
        range[1] = Integer.parseInt(sbMax.toString());
        return range;
    }
}
