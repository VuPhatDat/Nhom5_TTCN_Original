package com.example.model;

import javafx.scene.control.Button;

public class PhanAnh {
	private int ID;
	private String hoTen;
	private String noiDung;
	private String status;
	private Button statusButton;
	
	
	public PhanAnh() {
		this.setStatusButton(new Button("Duyệt"));
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Button getStatusButton() {
		return statusButton;
	}
	public void setStatusButton(Button statusButton) {
		this.statusButton = statusButton;
	}
	
	
	
}
