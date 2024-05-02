package com.example.btl_foodingo;


import java.io.Serializable;

public class SanPham implements Serializable {
    String tieude;
    int anh;
    double gia;
    int soluong;


    public SanPham(String tieude, int anh, double gia, int soluong) {
        this.tieude = tieude;
        this.anh = anh;
        this.gia = gia;
        this.soluong = soluong;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void tangSoLuong() {
        soluong++;
    }

    public void giamSoLuong() {
        if (soluong > 1) {
            soluong--;
        }
    }
}
