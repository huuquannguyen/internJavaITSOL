public class Ex3 {
    public static void main(String[] args) {
       System.out.println(chuanHoa("afadf     fadsf adsf adsfads fa   fadsfasdfa"));
    }

    public static String chuanHoa(String s){
        StringBuilder s1 = new StringBuilder(s.replaceAll("\\s\\s+", " ").trim());
        if(s1.charAt(0) >= 97){
            s1.setCharAt(0, (char)(s1.charAt(0) - 32));
        }
        for (int i = 1; i < s1.length(); i++) {
            if(s1.charAt(i) == ' ' && s1.charAt(i + 1) >= 97){
                s1.setCharAt(i + 1, (char)(s1.charAt(i + 1) - 32));
            }
        }
        return s1.toString();
    }
}
