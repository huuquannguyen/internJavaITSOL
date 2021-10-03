package array;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AEx3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] arr = AEx1.fillArray(input);
        countAppearanceFindMax(arr);
    }

    public static void countAppearanceFindMax(int[] arr){
        Map<Integer, Integer> map = new HashMap<>();
        int max = 1;
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i]) + 1);
                if(max < map.get(arr[i])){
                    max = map.get(arr[i]);
                    maxIndex = i;
                }
            }else{
                map.put(arr[i], 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry);
        }
        System.out.println("Phan tu xuat hien nhieu nhat: " + arr[maxIndex]);
    }
}
