package com.example.controller.HoKhauManageController;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.model.ThanhVienCuaHo;
import com.example.services.HoKhauService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class ThongTinHoKhauController implements Initializable{

    @FXML
    private Text diaChi;

    @FXML
    private Label maHoKhau;
    
    @FXML
    private Label hoTenChuHo;
    
    @FXML
	private TableView<ThanhVienCuaHo> nhanKhauTv;
	@FXML
	private TableColumn<ThanhVienCuaHo, String> hoTenCol;
	@FXML
	private TableColumn<ThanhVienCuaHo, Date> ngaySinhCol;
	@FXML
	private TableColumn<ThanhVienCuaHo, String> quanHeVoiChuHoCol;
	private ObservableList<ThanhVienCuaHo> nhanKhauList;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		nhanKhauList = FXCollections.observableArrayList(

		);
		hoTenCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("hoTen"));
		ngaySinhCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, Date>("namSinh"));
		quanHeVoiChuHoCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("quanHeVoiChuHo"));
		nhanKhauTv.setItems(nhanKhauList);
	}
	
	public void setInfor(String maHoKhau, String hoTenChuHo, String diaChi) {
		this.hoTenChuHo.setText(hoTenChuHo);
		this.diaChi.setText(diaChi);
		this.maHoKhau.setText(maHoKhau);
	}
	
	public void hienThongTinGiaDinh(int IDHoKhau) {
		HoKhauService conn = new HoKhauService();
		ResultSet rs = conn.getGiaDinh(IDHoKhau);
		try {
			if (rs != null) {
				while (rs.next()) {
					ThanhVienCuaHo tvch = new ThanhVienCuaHo();
					HoKhauService con = new HoKhauService();
					ResultSet r = con.getIdByHoTen(rs.getString("hoTen"));
					r.next();
					tvch.setIdNhanhKhau(r.getInt("ID"));
					tvch.setHoTen(rs.getString("hoTen"));				
					tvch.setNamSinh(rs.getDate("namSinh"));
					tvch.setQuanHeVoiChuHo(rs.getString("quanHeVoiChuHo"));
					nhanKhauList.add(tvch);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}

