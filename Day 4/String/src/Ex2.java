public class Ex2 {
    public static void main(String[] args) {
        System.out.println(upperLower("dfACVBdjf"));
    }

    public static String upperLower(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(i % 2 == 0){
                if(s.charAt(i) >= 97){
                    sb.append((char)(s.charAt(i) - 32));
                }else{
                    sb.append(s.charAt(i));
                }
                
            }else{
                if(s.charAt(i) < 97){
                    sb.append((char)(s.charAt(i) + 32));
                }else{
                    sb.append(s.charAt(i));
                }
            }
        }
        return sb.toString();
    }
}
