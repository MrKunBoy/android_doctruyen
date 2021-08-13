package com.nvt.mangaslayer;

public class Truyen {
    private int ID;
    private String Ten;
    private String MoTa;
    private byte[] Image;
    private int YeuThich;
    private String NoiDung;
    private String TacGia;
    private String TheLoai;
    private String TinhTrang;
    private int LuotXem;

    public Truyen(int ID, String ten, String moTa, byte[] image, int yeuThich, String noiDung, String tacGia, String theLoai, String tinhTrang, int luotXem) {
        this.ID = ID;
        Ten = ten;
        MoTa = moTa;
        Image = image;
        YeuThich = yeuThich;
        NoiDung = noiDung;
        TacGia = tacGia;
        TheLoai = theLoai;
        TinhTrang = tinhTrang;
        LuotXem = luotXem;
    }

    public Truyen() {

    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public int getYeuThich() {
        return YeuThich;
    }

    public void setYeuThich(int yeuThich) {
        YeuThich = yeuThich;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String theLoai) {
        TheLoai = theLoai;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public int getLuotXem() {
        return LuotXem;
    }

    public void setLuotXem(int luotXem) {
        LuotXem = luotXem;
    }
}
