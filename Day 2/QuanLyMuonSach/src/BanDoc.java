

import java.util.Scanner;

public class BanDoc {

    private static int maBanDocNext = 100001;

    private String maBanDoc;
    private String hoTen;
    private String diaChi;
    private String sDT;
    private LoaiBanDoc loaiBanDoc;

    public BanDoc(Scanner input){
        this.maBanDoc = String.valueOf(maBanDocNext).substring(1);
        maBanDocNext++;
        if(maBanDocNext >= 1000000){
            throw new RuntimeException("Limited User");
        }
        input.nextLine();
        System.out.println("Điền thông tin bạn đọc");
        System.out.print("Họ và tên: ");
        this.hoTen = input.nextLine();
        System.out.print("Địa chỉ: ");
        this.diaChi = input.nextLine();
        System.out.print("Số điện thoại: ");
        this.sDT = input.next();
        System.out.println("Loại bạn đọc:");
        System.out.println("1. Sinh viên");
        System.out.println("2. Học viên cao học");
        System.out.println("3. Giáo viên");
        int loaiBanDoc = App.nhap(input, 1, 3);
        switch(loaiBanDoc){
            case 1:
                this.loaiBanDoc = LoaiBanDoc.SINH_VIEN;
                break;
            case 2:
                this.loaiBanDoc = LoaiBanDoc.HOC_VIEN_CAO_HOC;
                break;
            case 3:
                this.loaiBanDoc = LoaiBanDoc.GIAO_VIEN;
                break;
        }
    }

    public BanDoc(String hoTen, String diaChi, String sDT, LoaiBanDoc loaiBanDoc) {
        this.maBanDoc = String.valueOf(maBanDocNext).substring(1);
        maBanDocNext++;
        if(maBanDocNext >= 1000000){
            throw new RuntimeException("Limited User");
        }
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.sDT = sDT;
        this.loaiBanDoc = loaiBanDoc;
    }
    public String getMaBanDoc(){
        return maBanDoc;
    }
    public String getHoTen() {
        return hoTen;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public String getSDT() {
        return sDT;
    }
    public void setSDT(String sDT) {
        this.sDT = sDT;
    }
    public LoaiBanDoc getLoaiBanDoc() {
        return loaiBanDoc;
    }
    public void setLoaiBanDoc(LoaiBanDoc loaiBanDoc) {
        this.loaiBanDoc = loaiBanDoc;
    }

    @Override
    public String toString() {
        return maBanDoc + " - " + hoTen + " - " + diaChi + " - " + sDT + " - " + loaiBanDoc;
    }
}
