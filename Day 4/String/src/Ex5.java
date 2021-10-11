public class Ex5 {
    public static void main(String[] args) {
        System.out.println(chuyenTen("Nguyen huu quan"));
    }

    public static String chuyenTen(String s){
        s = Ex3.chuanHoa(s);
        String[] arr = s.split(" ");
        String temp = arr[0];
        arr[0] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;
        StringBuilder sb = new StringBuilder();
        for (String string : arr) {
            sb.append(string + " ");
        }
        return sb.toString().trim();
    }
}
