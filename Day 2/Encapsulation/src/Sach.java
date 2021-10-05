

import java.util.Scanner;

public class Sach {

    private static int nextMaSach = 100001;
    
    private String maSach;
    private String tenSach;
    private String tacGia;
    private ChuyenNganh chuyenNganh;
    private int namXuatBan;

    public Sach(String tenSach, String tacGia, ChuyenNganh chuyenNganh, int namXuatBan) {
        this.maSach = String.valueOf(nextMaSach).substring(1);
        nextMaSach++;
        if(nextMaSach >= 1000000){
            throw new RuntimeException("Limited book");
        }
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.chuyenNganh = chuyenNganh;
        this.namXuatBan = namXuatBan;
    }

    public Sach(Scanner input) {
        this.maSach = String.valueOf(nextMaSach).substring(1);
        nextMaSach++;
        if(nextMaSach >= 1000000){
            throw new RuntimeException("Limited book");
        }
        System.out.println("Điền thông tin sách");
        System.out.print("Tên sách: ");
        input.nextLine();
        this.tenSach = input.nextLine();
        System.out.print("Tác giả: ");
        this.tacGia = input.nextLine();
        System.out.println("Chuyên ngành:");
        System.out.println("1. Khoa học tự nhiên.");
        System.out.println("2. Văn học nghệ thuật.");
        System.out.println("3. Điện tử viễn thông.");
        System.out.println("4. Công nghệ thông tin.");
        int chuyenNganh = App.nhap(input, 1, 4);
        switch(chuyenNganh){
            case 1:
                this.chuyenNganh = ChuyenNganh.KHOA_HOC_TU_NHIEN;
                break;
            case 2:
                this.chuyenNganh = ChuyenNganh.VAN_HOC_NGHE_THUAT;
                break;
            case 3:
                this.chuyenNganh = ChuyenNganh.DIEN_TU_VIEN_THONG;
                break;
            case 4:
                this.chuyenNganh = ChuyenNganh.CONG_NGHE_THONG_TIN;
                break;
        }
        System.out.print("Năm xuất bản: ");
        this.namXuatBan = App.nhap(input, 1, 2021);
    }

    public String getMaSach() {
        return maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public ChuyenNganh getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(ChuyenNganh chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    @Override
    public String toString() {
        return maSach + " - " + tenSach + " - " + tacGia + " - " + chuyenNganh + " - " + namXuatBan;
    }

    

    
}
