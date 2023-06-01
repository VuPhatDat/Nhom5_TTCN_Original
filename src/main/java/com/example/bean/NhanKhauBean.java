package com.example.bean;

import java.util.ArrayList;
import java.util.List;

import com.example.model.ChungMinhThu;
import com.example.model.GiaDinh;
import com.example.model.NhanKhau;
import com.example.model.TieuSu;

public class NhanKhauBean {
    private NhanKhau nhanKhau;
    private ChungMinhThu chungMinhThu;
    private List<TieuSu> listTieuSu;
    private List<GiaDinh> listGiaDinh;

    public NhanKhauBean(NhanKhau nhanKhauModel, ChungMinhThu chungMinhThuModel, List<TieuSu> listTieuSuModels, List<GiaDinh> listGiaDinhModels) {
        this.nhanKhau = nhanKhauModel;
        this.chungMinhThu = chungMinhThuModel;
        this.listTieuSu = listTieuSuModels;
        this.listGiaDinh = listGiaDinhModels;
    }

    public NhanKhauBean() {
        this.nhanKhau = new NhanKhau();
        this.chungMinhThu = new ChungMinhThu();
        this.listGiaDinh = new ArrayList<>();
        this.listTieuSu = new ArrayList<>();
    }

    public NhanKhau getNhanKhau() {
        return nhanKhau;
    }

    public void setNhanKhau(NhanKhau nhanKhau) {
        this.nhanKhau = nhanKhau;
    }

    public ChungMinhThu getChungMinhThu() {
        return chungMinhThu;
    }

    public void setChungMinhThu(ChungMinhThu chungMinhThu) {
        this.chungMinhThu = chungMinhThu;
    }

    public List<TieuSu> getListTieuSu() {
        return listTieuSu;
    }

    public void setListTieuSu(List<TieuSu> listTieuSu) {
        this.listTieuSu = listTieuSu;
    }

    public List<GiaDinh> getListGiaDinh() {
        return listGiaDinh;
    }

    public void setListGiaDinh(List<GiaDinh> listGiaDinh) {
        this.listGiaDinh = listGiaDinh;
    }

}
