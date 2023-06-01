package com.example.controller.NhanKhauManageController;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.Date;

import com.example.services.NhanKhauService;
import com.example.main.QuanLyNhanKhau;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ThemMoiNhanKhauController implements Initializable {
	
	@FXML
	private TextField hoTenTf;
	@FXML
	private TextField nguyenqQuanTf;
	@FXML
	private TextField danTocTf;
	@FXML
	private TextField soCMTTf;
	@FXML
	private TextField noiThuongTruTf;
	@FXML
	private TextField trinhDoNgoaiNguTf;
	@FXML
	private TextField trinhDoHocVanTf;
	@FXML
	private TextField ngheNghiepTf;
	@FXML
	private TextField bietDanhTf;
	@FXML
	private TextField tonGiaoTf;
	@FXML
	private TextField quocTichTf;
	@FXML
	private TextField hoChieuSoTf;
	@FXML
	private TextField diaChiHienNayTf;
	@FXML
	private TextField trinhDoChuyenMonTf;
	@FXML
	private TextField bietTiengDanTocTf;
	@FXML
	private TextField noiLamViecTf;
	@FXML
	private TextField noiSinhTf;
	@FXML
	private DatePicker namSinhDpk;
	@FXML 
	private ChoiceBox<String> gioiTinhCb;
	private String gt[] = {"Nam", "Nữ"};
	@FXML
	private Button khacBt;
	@FXML
	private Button huyBt;
	@FXML
	private Button xacNhanBt;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		gioiTinhCb.getItems().addAll(gt);
	}
	
	@FXML
    void huy(ActionEvent event) throws IOException {
		huyBt.getScene().getWindow().hide();
    }

    @FXML
    void xacNhan(ActionEvent event) {
    	if (hoTenTf.getText() == null || namSinhDpk.getValue() == null || soCMTTf.getText() == null || gioiTinhCb.getValue() == null || noiSinhTf.getText() == null || nguyenqQuanTf.getText() == null ||
    	danTocTf.getText() == null || tonGiaoTf.getText() == null || quocTichTf.getText() == null || soCMTTf.getText() == null || noiThuongTruTf.getText() == null || diaChiHienNayTf.getText() == null ||
    	trinhDoHocVanTf.getText() == null || trinhDoChuyenMonTf.getText() == null || bietTiengDanTocTf.getText() == null || trinhDoNgoaiNguTf.getText() == null || ngheNghiepTf.getText() == null || noiLamViecTf.getText() == null  ) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("cảnh báo");
    		alert.setContentText("Vui lòng nhập hết các trường bắt buộc");
    		alert.showAndWait();
    	} else {
    		NhanKhauService nks = new NhanKhauService();
    		LocalDate localDate = namSinhDpk.getValue();
    		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
    		Date date1 = Date.from(instant);
    		java.sql.Date dateSql = new java.sql.Date(date1.getTime());
    		nks.insertNhanKhau(soCMTTf.getText(), hoTenTf.getText(), bietDanhTf.getText(), dateSql,  gioiTinhCb.getValue(), noiSinhTf.getText(), nguyenqQuanTf.getText(),
    				danTocTf.getText(), tonGiaoTf.getText(), quocTichTf.getText(), hoChieuSoTf.getText(), noiThuongTruTf.getText(), diaChiHienNayTf.getText(), 
    				trinhDoHocVanTf.getText(), trinhDoChuyenMonTf.getText(), bietTiengDanTocTf.getText(), trinhDoNgoaiNguTf.getText(), ngheNghiepTf.getText(), noiLamViecTf.getText());
    	}
    	
    	
    }
    

	
	
	
}
