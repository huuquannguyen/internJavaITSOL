public class Ex7 {
    public static void main(String[] args) {
        System.out.println(loaiBoChuoi("daccaasfasfw", "ca"));
    }

    public static String loaiBoChuoi(String s1, String s2){
        if(!s1.contains(s2)){
            return s1;
        }else{
            s1 = s1.replaceAll(s2, "");
            return loaiBoChuoi(s1, s2);
        }
    }
}
