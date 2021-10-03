package basic;

import java.util.ArrayList;
import java.util.List;

public class Ex7 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i = 111111; i <= 999999; i+=2){
            if(Ex4.isSNT(i)){
                list.add(i);
            }
            if(i % 100000 == 99999){
                i += 100000;
            }
            if( i % 10000 == 9999){
                i += 10000;
            }
            if (i % 1000 == 999){
                i += 1000;
            }
            if(i % 100 == 99){
                i += 100;
            }
            if(i % 10 == 9){
                i +=10;
            }    
        }
        System.out.println(list);
        System.out.println("Tong so nguyen co co 6 chu so le: " + list.size());
    }
}
