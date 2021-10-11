public class Ex6 {
    public static void main(String[] args) {
        splitAndSort("quan quang huu nguyen tuyen");
    }

    public static void splitAndSort(String s){
        s = s.replaceAll("\\s\\s+", " ").trim();
        String[] arr = s.split(" ");
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[minIndex].compareTo(arr[j] ) > 0) {
                    minIndex = j;
                }
            }
            if(minIndex != i){
                String temp = arr[i];
                    arr[i] = arr[minIndex];
                    arr[minIndex] = temp;
            }
            System.out.println(arr[i]);
        }
    }
}
