public class QLMuonSach {
    private BanDoc banDoc;
    private Sach sach;
    private int soLuongSach;

    public QLMuonSach(BanDoc banDoc, Sach sach, int soLuongSach) {
        this.banDoc = banDoc;
        this.sach = sach;
        this.soLuongSach = soLuongSach;
    }

    

    public QLMuonSach() {

    }

    public BanDoc getBanDoc() {
        return banDoc;
    }

    public void setBanDoc(BanDoc banDoc) {
        this.banDoc = banDoc;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuongSach() {
        return soLuongSach;
    }

    public void setSoLuongSach(int soLuongSach) {
        this.soLuongSach = soLuongSach;
    }

    // @Override
    // public boolean equals(Object obj) {
    //     if(!(obj instanceof QLMuonSach)){
    //         return false;
    //     }
    //     QLMuonSach bangQLMuonSach2 = (QLMuonSach) obj;
    //     if(bangQLMuonSach2.getBanDoc() == this.banDoc && bangQLMuonSach2.getSach() == this.sach){
    //         return true;
    //     }
    //     return false;
    // }
    
    // @Override
    // public int hashCode() {
    //     return banDoc.hashCode() + sach.hashCode();
    // }

    @Override
    public String toString() {
        return "Tên người mượn: " + banDoc.getHoTen() + " - Tên sách: " + sach.getTenSach() + " - Số lượng:" + soLuongSach;
    }
}
