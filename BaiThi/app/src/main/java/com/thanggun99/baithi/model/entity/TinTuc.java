package com.thanggun99.baithi.model.entity;

import java.io.Serializable;

public class TinTuc implements Serializable{
    public static final String TIN_TUC = "TIN_TUC";

    private String date;
    private String title;
    private String moTa;
    private String hinhAnh;
    private String chiTietUrl;

    public TinTuc(String date, String title, String moTa, String hinhAnh, String chiTietUrl) {
        this.date = date;
        this.title = title;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.chiTietUrl = chiTietUrl;
    }

    public TinTuc() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getChiTietUrl() {
        return chiTietUrl;
    }

    public void setChiTietUrl(String chiTietUrl) {
        this.chiTietUrl = chiTietUrl;
    }
}
