package com.example.model;

import java.util.Date;

public class ThanhVienCuaHo {
	private int idNhanhKhau;
	private int idHoKhau;
	private String quanHeVoiChuHo;
	private String hoTen;
	private Date namSinh;
	
	public int getIdNhanhKhau() {
		return idNhanhKhau;
	}
	
	public void setIdNhanhKhau(int idNhanhKhau) {
		this.idNhanhKhau = idNhanhKhau;
	}
	
	public int getIdHoKhau() {
		return idHoKhau;
	}
	
	public void setIdHoKhau(int idHoKhau) {
		this.idHoKhau = idHoKhau;
	}
	
	public String getQuanHeVoiChuHo() {
		return quanHeVoiChuHo;
	}
	
	public void setQuanHeVoiChuHo(String quanHeVoiChuHo) {
		this.quanHeVoiChuHo = quanHeVoiChuHo;
	}
	
	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public Date getNamSinh() {
		return namSinh;
	}
	
	public void setNamSinh(Date namSinh) {
		this.namSinh = namSinh;
	}
	
	
	
}
