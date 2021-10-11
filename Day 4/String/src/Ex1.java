public class Ex1 {
    public static void main(String[] args) {
        for (int i = 100000; i <= 999999; i++) {
            if(kiemTraThuanNghich(String.valueOf(i))){
                System.out.print(i + " ");
            }
        }
    }

    public static boolean kiemTraThuanNghich(String s){
        for(int i = 0; i < s.length() - 1; i++){
            if(i > s.length() - 1 - i){
                break;
            }
            if(s.charAt(i) != s.charAt(s.length() - 1 - i)){
                return false;
            }
        }
        return true;
    }
}
