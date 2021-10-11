public class Ex4 {
    public static void main(String[] args) {
        tuDaiNhat("afadf     fadsf adsf adsfads fa   fadsfasdfa");
    }

    public static void tuDaiNhat(String s){
        String s1 = s.replaceAll("\\s\\s+", " ").trim();
        String[] arr = s1.split(" ");
        int maxLengthIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].length() > arr[maxLengthIndex].length()){
                maxLengthIndex = i;
            }
        }
        System.out.println("Tu dai nhat: " + arr[maxLengthIndex]);
        System.out.println("Vi tri tu dai nhat: " + (maxLengthIndex + 1));
    }
}
