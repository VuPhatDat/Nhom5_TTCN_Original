package com.example.controller.NhanKhauManageController;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.example.services.NhanKhauService;
import com.example.model.KhaiTu;

import com.example.services.NhanKhauService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class KhaiTuController {

	@FXML
    private Button checkBt;
    @FXML
    private Button checkBt1;
    @FXML
    private ImageView checkImage;
    @FXML
    private ImageView checkImage1;
    @FXML
    private Button huyBoBt;
    @FXML
    private TextArea liDoTa;
    @FXML
    private DatePicker ngayChetDpk;
    @FXML
    private DatePicker ngayKhaiDpk;
    @FXML
    private TextField soCMTTf;
    @FXML
    private TextField soCMTTf1;
    @FXML
    private TextField soGiayKhaiTuTf;
    @FXML
    private Button taoBt;
    

    private KhaiTu khaiTu = new KhaiTu();
    
    @FXML
    void check1(ActionEvent event) {
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
            this.khaiTu.setIdNguoiKhai(tempID);
        } else {
        	Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("cảnh báo");
    		alert.setContentText("Không tìm thấy nhân khẩu trong hệ thống!! Thử lại?");
    		alert.showAndWait();
        }
        
        if (checkImage.getOpacity() == 1 && checkImage1.getOpacity() == 1) {
        	this.soGiayKhaiTuTf.setEditable(true);
            this.ngayKhaiDpk.setEditable(true);
            this.ngayChetDpk.setEditable(true);
            this.liDoTa.setEditable(true);
        }
    }
    
    @FXML
    void check2(ActionEvent event) {
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
        	this.soCMTTf1.setEditable(false);
        	this.checkImage1.setOpacity(1);
            this.khaiTu.setIdNguoiKhai(tempID);
        } else {
        	Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("cảnh báo");
    		alert.setContentText("Không tìm thấy nhân khẩu trong hệ thống!! Thử lại?");
    		alert.showAndWait();
        }
        
        if (checkImage.getOpacity() == 1 && checkImage1.getOpacity() == 1) {
        	this.soGiayKhaiTuTf.setEditable(true);
            this.ngayKhaiDpk.setEditable(true);
            this.ngayChetDpk.setEditable(true);
            this.liDoTa.setEditable(true);
        }
    }

    @FXML
    void huyBo(ActionEvent event) {
    	huyBoBt.getScene().getWindow().hide();
    }

    @FXML
    void tao(ActionEvent event) throws IOException {
    	if (soGiayKhaiTuTf.getText().trim().isEmpty()
                || liDoTa.getText().trim().isEmpty()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("cảnh báo");
    		alert.setContentText("Vui lòng nhập hết các trường bất buộc!");
    		alert.showAndWait();
    	} else {
    		khaiTu.setLyDoChet(liDoTa.getText());
    		khaiTu.setSoGiayKhaiTu(soGiayKhaiTuTf.getText().trim());
    		LocalDate localDate1 = ngayChetDpk.getValue();
    		Instant instant = Instant.from(localDate1.atStartOfDay(ZoneId.systemDefault()));
    		Date date1 = Date.from(instant);
    		khaiTu.setNgayChet(date1);
    		LocalDate localDate2 = ngayKhaiDpk.getValue();
    		Instant instant2 = Instant.from(localDate2.atStartOfDay(ZoneId.systemDefault()));
    		Date date2 = Date.from(instant2);
    		khaiTu.setNgayKhai(date2);
    		
    		NhanKhauService nks = new NhanKhauService();
    		nks.themKhaiTu(khaiTu);
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setTitle("Thông báo");
    		alert.setContentText("Khai tử thành công");
    		alert.showAndWait();
    		taoBt.getScene().getWindow().hide();
//    		FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.class.getResource("nhan-khau.fxml"));
//	        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
//	        QuanLyNhanKhau.window.setScene(scene);
    	}
    }

}
