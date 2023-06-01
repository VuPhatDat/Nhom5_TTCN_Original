package com.example.model;

import java.util.Date;

public class DinhChinh {
	private int ID;
	private int idHoKhau;
	private String thongTinThayDoi;
	private String thayDoiTu;
	private String thayDoiThanh;
	private Date ngayThayDoi;
	private int nguoiThayDoi;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getIdHoKhau() {
		return idHoKhau;
	}
	public void setIdHoKhau(int idHoKhau) {
		this.idHoKhau = idHoKhau;
	}
	public String getThongTinThayDoi() {
		return thongTinThayDoi;
	}
	public void setThongTinThayDoi(String thongTinThayDoi) {
		this.thongTinThayDoi = thongTinThayDoi;
	}
	public String getThayDoiTu() {
		return thayDoiTu;
	}
	public void setThayDoiTu(String thayDoiTu) {
		this.thayDoiTu = thayDoiTu;
	}
	public String getThayDoiThanh() {
		return thayDoiThanh;
	}
	public void setThayDoiThanh(String thayDoiThanh) {
		this.thayDoiThanh = thayDoiThanh;
	}
	public Date getNgayThayDoi() {
		return ngayThayDoi;
	}
	public void setNgayThayDoi(Date ngayThayDoi) {
		this.ngayThayDoi = ngayThayDoi;
	}
	public int getNguoiThayDoi() {
		return nguoiThayDoi;
	}
	public void setNguoiThayDoi(int nguoiThayDoi) {
		this.nguoiThayDoi = nguoiThayDoi;
	}
	
}
