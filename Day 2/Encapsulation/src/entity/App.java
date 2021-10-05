package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

    private static List<Sach> khoSach = new ArrayList<>();
    private static List<BanDoc> danhSachBanDoc = new ArrayList<>();
    private static List<QLMuonSach> bangQLMuonSach = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        menu(input);
    }
    
    public static void menu(Scanner input){
        System.out.println("-------------------------------------------------");
        System.out.println("QUẢN LÝ MƯỢN SÁCH THƯ VIỆN");
        System.out.println("-------------------------------------------------");
        System.out.println("1. Nhập đầu sách mới.");
        System.out.println("2. In ra những đầu sách đã có.");
        System.out.println("3. Nhập bạn đọc mới");
        System.out.println("4. In ra danh sách bạn đọc đã có.");
        System.out.println("5. Lập bảng quản lý mượn sách cho tất cả bạn đọc");
        System.out.println("6. Lập bảng quản lý mượn sách cho 1 bạn đọc");
        System.out.println("7. In ra danh sách quản lý đã có");
        System.out.println("8. Sắp xếp danh sách quản lý mượn sách theo tên bạn đọc, số lượng cuốn sách được mượn (giảm dần).");
        System.out.println("9. Tìm kiếm và hiển thị danh sách mượn sách theo tên bạn đọc.");
        System.out.println("0. Thoát");
        int part = nhap(input, 0, 9);
        switch(part){
            case 0:
                System.out.println("CHÀO BẠN !!!");
                break;
            case 1:
                themSach(input);
                break;
            case 2:
                inKhoSach(input);
                break;
            case 3:
                themBanDoc(input);
                break;
            case 4:
                inDanhSachBanDoc(input);
                break;
            case 5: 
                nhapSachChoTatCaBanDoc(input);
                break;
            case 6:
                nhapSachCho1BanDoc(input);
                break;
            case 7:
                inBangQuanLyMuonSach(input);
                break;
            case 8:
                sapXepTheoTenBanDocVaSoLuongSachMuon(input);
                break;
            case 9:
                timKiemDanhSachMuonTheoTen(input);
                break;
        }
    }

    public static void themSach(Scanner input){
        Sach sach = new Sach(input);
        khoSach.add(sach);
        System.out.println("Thêm sách thành công.");
        System.out.println();
        System.out.println("Chọn thao tác tiếp theo");
        menu(input);
    }

    public static void inKhoSach(Scanner input){
        for (Sach sach : khoSach) {
            System.out.println(sach);
        }
        menu(input);
    }

    public static void themBanDoc(Scanner input){
        BanDoc banDoc = new BanDoc(input);
        danhSachBanDoc.add(banDoc);
        System.out.println("Thêm bạn đọc thành công");
        System.out.println();
        System.out.println("Chọn thao tác tiếp theo");
        menu(input);
    }

    public static void inDanhSachBanDoc(Scanner input){
        for (BanDoc banDoc : danhSachBanDoc) {
            System.out.println(banDoc);
        }
        menu(input);
    }

    public static void nhapSachChoTatCaBanDoc(Scanner input){
        for (BanDoc banDoc : danhSachBanDoc) {
            int demSachDaMuon = demSoSachBanDocDaMuon(banDoc);
            System.out.println("Bạn đọc: " + banDoc.getMaBanDoc() + " - " + banDoc.getHoTen());
            System.out.println("(Có thể mượn thêm tối đa " + (5 - demSachDaMuon) + ") đầu sách khác");
            System.out.print("Nhập số lượng sách muốn mượn: " );
            int soLuonng = nhap(input, 1, 5 - demSachDaMuon);
            for(int i = 1; i <= soLuonng; i++){
                Sach sach = timSachBangId(input, i);
                if(sach != null){
                    System.out.print("Nhập số cuốn (Tối đa 3 cuốn): ");
                    int soLuong = nhap(input, 1, 3);
                    QLMuonSach banGhi = timKiemBanGhi(banDoc, sach);
                    if(banGhi != null){
                        if(banGhi.getSoLuongSach() == 3){
                            System.out.println("Bạn đã mượn tối đa sách này, không thể mượn thêm");
                        }else if(banGhi.getSoLuongSach() + soLuong > 3){
                            System.out.println("Bạn chỉ có thể mượn sách này thêm " + (3 - banGhi.getSoLuongSach()) + " cuốn");
                            System.out.print("Nhập lại số cuốn");
                            banGhi.setSoLuongSach(banGhi.getSoLuongSach() + nhap(input, 0, 3 - banGhi.getSoLuongSach()));
                            System.out.println("Đã mượn sách thành công. Có thể mượn thêm tối đa " 
                                        + (3 - banGhi.getSoLuongSach()) + " cuốn " + sach.getTenSach());
                        }            
                    }else{
                        banGhi = new QLMuonSach(banDoc, sach, soLuong);
                        bangQLMuonSach.add(banGhi);
                        System.out.println("Đã mượn sách thành công. Có thể mượn thêm tối đa " 
                                        + (3 - banGhi.getSoLuongSach()) + " cuốn " + sach.getTenSach());
                    }                   
                }
            }
        }
        System.out.println("Hoàn thành tạo bảng");
        menu(input);
    }

    public static void nhapSachCho1BanDoc(Scanner input){
        String maBanDoc = input.next();
        Optional<BanDoc> rawBanDoc = timBanDocTheoId(maBanDoc);
        if(rawBanDoc.isPresent()){
            BanDoc banDoc = rawBanDoc.get();
            int demSoSachDaMuon = demSoSachBanDocDaMuon(banDoc);
            if(demSoSachDaMuon >= 5){
                System.out.println("Đã mượn hết số lượng đầu sách có thể");
            }else{
                System.out.println("Bạn đọc: " + banDoc.getMaBanDoc() + " - " + banDoc.getHoTen());
                System.out.println("(Có thể mượn thêm tối đa " + (5 - demSoSachDaMuon) + ") đầu sách khác");
                System.out.print("Nhập số lượng sách muốn mượn: " );
                int soLuonng = nhap(input, 1, 5 - demSoSachDaMuon);
                for(int i = 1; i <= soLuonng; i++){
                    Sach sach = timSachBangId(input, i);
                    if(sach != null){
                        System.out.print("Nhập số cuốn (Tối đa 3 cuốn): ");
                        int soLuong = nhap(input, 1, 3);
                        QLMuonSach banGhi = timKiemBanGhi(banDoc, sach);
                        if(banGhi != null){
                            if(banGhi.getSoLuongSach() + soLuong > 3){
                                System.out.println("Bạn chỉ có thể mượn sách này thêm " + (3 - banGhi.getSoLuongSach()) + " cuốn");
                                System.out.print("Nhập lại số cuốn: ");
                                banGhi.setSoLuongSach(banGhi.getSoLuongSach() + nhap(input, 0, 3 - banGhi.getSoLuongSach()));
                            }
                            banGhi.setSoLuongSach(banGhi.getSoLuongSach() + soLuong);
                        }else{
                            banGhi = new QLMuonSach(banDoc, sach, soLuong);
                            bangQLMuonSach.add(banGhi);
                        }
                        System.out.println("Đã mượn sách thành công. Có thể mượn thêm tối đa " 
                                            + (3 - banGhi.getSoLuongSach()) + " cuốn " + sach.getTenSach());                   
                    }
                }
            }
        }
        menu(input);
    }

    public static void inBangQuanLyMuonSach(Scanner input){
        for (QLMuonSach qlMuonSach : bangQLMuonSach) {
            System.out.println(qlMuonSach);
        }
        menu(input);
    }

    public static void sapXepTheoTenBanDocVaSoLuongSachMuon(Scanner input){
        for(int i = 0; i < bangQLMuonSach.size() - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < bangQLMuonSach.size(); j++){
                int soSanhTen = bangQLMuonSach.get(minIndex).getBanDoc().getHoTen()
                                .compareTo(bangQLMuonSach.get(j).getBanDoc().getHoTen());
                if(soSanhTen > 0){
                    minIndex = j;
                }else if(soSanhTen == 0){
                    int soSanhSoSachMuon = bangQLMuonSach.get(minIndex).getSoLuongSach() - bangQLMuonSach.get(j).getSoLuongSach();
                    if(soSanhSoSachMuon < 0){
                        minIndex = j;
                    }
                }
            }
            if(minIndex != i){
                QLMuonSach temp = bangQLMuonSach.get(minIndex);
                bangQLMuonSach.set(minIndex, bangQLMuonSach.get(i));
                bangQLMuonSach.set(i, temp);
            }
        }
        System.out.println("Sắp xếp thành công");
        menu(input);
    }

    public static void timKiemDanhSachMuonTheoTen(Scanner input){
        input.nextLine();
        System.out.print("Nhập tên bạn đọc muốn tìm: ");
        String name = input.nextLine();
        List<QLMuonSach> list = new ArrayList<>();
        for (QLMuonSach qlMuonSach : bangQLMuonSach) {
            if(qlMuonSach.getBanDoc().getHoTen().toLowerCase().contains(name.toLowerCase())){
                list.add(qlMuonSach);
            }
        }
        if(list.size() == 0){
            System.out.println("Không tìm thấy bạn đọc có tên giống: " + name);
        }else{
            System.out.println("Dữ liệu tìm thấy:");
            for (QLMuonSach qlMuonSach : list) {
                System.out.println(qlMuonSach);
            }
        }
        menu(input);
    }

    public static int nhap(Scanner input, int min, int max){
        int option = -1;
        while(option == -1){
            try {
                option = input.nextInt();
                if(option < min || option > max){
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                System.out.print("Giá trị không phù hợp. Mời nhập lại: ");
                input.nextLine();
                option = -1;
            }
        }
        return option;
    }

    private static Sach timSachBangId(Scanner input, int index){
        boolean lapLai = true;
        Sach sach = null;
        while(lapLai){
            System.out.println("Nhập mã sách cho cuốn thứ " + index + ": ");
            String maSach = input.next();
            Optional<Sach> rawSach = khoSach
                                .stream()
                                .filter(s -> s.getMaSach().equals(maSach))
                                .findFirst();
            if(rawSach.isPresent()){
                sach = rawSach.get();
                lapLai = false;
            }else{
                System.out.println("Không tìm thấy sách trong kho");
                System.out.println("Bạn có muốn tìm lại sách không?");
                System.out.print("Ấn 'Y' để tìm lại, nút bất kì để kết thúc thao tác: ");
                if(!input.next().equalsIgnoreCase("y")){
                    lapLai = false;
                }
            }
        }
        return sach ;
    }

    private static QLMuonSach timKiemBanGhi(BanDoc banDoc, Sach sach){
        for (QLMuonSach qlMuonSach : bangQLMuonSach) {
            if(qlMuonSach.getBanDoc() == banDoc && qlMuonSach.getSach() == sach){
                return qlMuonSach;
            }
        }
        return null;
    }

    private static int demSoSachBanDocDaMuon(BanDoc banDoc){
        int count = 0;
        for (QLMuonSach qlMuonSach : bangQLMuonSach) {
            if(qlMuonSach.getBanDoc() == banDoc){
                count++;
            }
        }
        return count;
    }

    private static Optional<BanDoc> timBanDocTheoId(String id){
        Optional<BanDoc> rawBanDoc = danhSachBanDoc
                                        .stream()
                                        .filter(b -> b.getMaBanDoc().equals(id))
                                        .findFirst();
        return rawBanDoc;
    }
}

