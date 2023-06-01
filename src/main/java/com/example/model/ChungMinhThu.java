package com.example.model;

import java.util.Date;

public class ChungMinhThu {
	private int ID;
	private int idNhanKhau;
	private String soCMT;
	private Date ngayCap;
	private String noiCap;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getIdNhanKhau() {
		return idNhanKhau;
	}

	public void setIdNhanKhau(int idNhanKhau) {
		this.idNhanKhau = idNhanKhau;
	}

	public String getSoCMT() {
		return soCMT;
	}

	public void setSoCMT(String soCMT) {
		this.soCMT = soCMT;
	}

	public Date getNgayCap() {
		return ngayCap;
	}

	public void setNgayCap(Date ngayCap) {
		this.ngayCap = ngayCap;
	}

	public String getNoiCap() {
		return noiCap;
	}

	public void setNoiCap(String noiCap) {
		this.noiCap = noiCap;
	}
	
	
}
