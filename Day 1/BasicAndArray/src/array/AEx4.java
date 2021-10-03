package array;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AEx4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] arr = AEx1.fillArrayGreaterThanZero(input);
        countAppearanceGreaterThanZero(arr);
    }

    public static void countAppearanceGreaterThanZero(int[] arr){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i]) + 1);
            }else{
                map.put(arr[i], 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }


}
