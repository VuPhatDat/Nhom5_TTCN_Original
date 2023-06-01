package com.example.controller.NhanKhauManageController;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.example.services.NhanKhauService;

import com.example.main.QuanLyNhanKhau;
import com.example.model.TamVang;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class DKTamVangController {
	@FXML
    private Button checkBt;
    @FXML
    private DatePicker denNgayDpk;
    @FXML
    private Button huyBoBt;
    @FXML
    private TextArea liDoTa;
    @FXML
    private TextField maGiayTamVangTf;
    @FXML
    private TextField noiTamTruTf;
    @FXML
    private TextField soCMTTf;
    @FXML
    private Button taoBt;
    @FXML
    private DatePicker tuNgayDpk;
    @FXML
    private ImageView checkImage;
    
    TamVang tamVang = new TamVang();
    
    @FXML
    void check(ActionEvent event) {
    	
    	String tempCMT = this.soCMTTf.getText().trim() ;
        if (tempCMT.isEmpty()) {
        	Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("cảnh báo");
    		alert.setContentText("Vui lòng nhập số CMT");
    		alert.showAndWait();            
    		return;
        } else {
            try {
                long cmt = Long.parseLong(tempCMT);
            } catch (Exception e) {
            	Alert alert = new Alert(Alert.AlertType.WARNING);
        		alert.setTitle("cảnh báo");
        		alert.setContentText("Vui lòng nhập So CMT đúng định dạng!");
        		alert.showAndWait();
                return;
            }
        }
        NhanKhauService nks = new NhanKhauService();
        int tempID = nks.checkCMT(this.soCMTTf.getText());
        if( tempID != -1){
            // khong cho phep sua lai gia tri
            this.soCMTTf.setEditable(false);
            this.checkImage.setOpacity(1);
            this.maGiayTamVangTf.setEditable(true);
            this.noiTamTruTf.setEditable(true);
            this.denNgayDpk.setEditable(true);
            this.tuNgayDpk.setEditable(true);
            this.liDoTa.setEditable(true);
            
            this.tamVang.setIdNhanKhau(tempID);
        } else {
        	Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("cảnh báo");
    		alert.setContentText("Không tìm thấy nhân khẩu trong hệ thống!! Thử lại?");
    		alert.showAndWait();
        }
    }

    @FXML
    void huyBo(ActionEvent event) {
    	huyBoBt.getScene().getWindow().hide();
    }

    @FXML
    void tao(ActionEvent event) throws IOException {
    	if (maGiayTamVangTf.getText().trim().isEmpty()
                || noiTamTruTf.getText().trim().isEmpty()
                || liDoTa.getText().trim().isEmpty()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("cảnh báo");
    		alert.setContentText("Vui lòng nhập hết chính xác các trường bất buộc!");
    		alert.showAndWait();
    	} else {
    		tamVang.setLyDo(liDoTa.getText());
    		tamVang.setMaGiayTamVang(maGiayTamVangTf.getText());
    		tamVang.setNoiTamTru(noiTamTruTf.getText());
    		LocalDate localDate1 = tuNgayDpk.getValue();
    		Instant instant = Instant.from(localDate1.atStartOfDay(ZoneId.systemDefault()));
    		Date date1 = Date.from(instant);
    		tamVang.setTuNgay(date1);
    		LocalDate localDate2 = denNgayDpk.getValue();
    		Instant instant2 = Instant.from(localDate2.atStartOfDay(ZoneId.systemDefault()));
    		Date date2 = Date.from(instant2);
    		tamVang.setDenNgay(date2);
    		
    		NhanKhauService nks = new NhanKhauService();
    		nks.themTamVang(tamVang);
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setTitle("Thông báo");
    		alert.setContentText("Đăng kí tạm vắng thành công");
    		alert.showAndWait();
    		taoBt.getScene().getWindow().hide();
    		FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.class.getResource("nhan-khau.fxml"));
	        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
	        QuanLyNhanKhau.window.setScene(scene);
    	}
    }
    
}
