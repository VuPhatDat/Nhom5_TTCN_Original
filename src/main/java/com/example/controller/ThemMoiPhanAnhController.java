package com.example.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.main.QuanLyNhanKhau;
import com.example.model.NhanKhau;
import com.example.services.PhanAnhServices;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ThemMoiPhanAnhController implements Initializable{

    @FXML
    private TextField hoTenTf;

    @FXML
    private Button huyBoBt;

    @FXML
    private TextField idTf;

    @FXML
    private TextArea noiDungTa;

    @FXML
    private Button themBt;

    @FXML
    private TableView<NhanKhau> nhanKhauTv;
    @FXML
    private TableColumn<NhanKhau, String> diaChiCol;
    @FXML
    private TableColumn<NhanKhau, String> gioiTinhCol;
    @FXML
    private TableColumn<NhanKhau, String> hoTenCol;
    @FXML
    private TableColumn<NhanKhau, Date> ngaySinhCol;    
    @FXML
    private TableColumn<NhanKhau, Integer> idCol;
    private ObservableList<NhanKhau> nhanKhauList;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	nhanKhauList = FXCollections.observableArrayList(
				
		);
		idCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("ID"));
		hoTenCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
		gioiTinhCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
		ngaySinhCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Date>("namSinh"));
		diaChiCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("diaChiHienNay"));
		nhanKhauTv.setItems(nhanKhauList);
		showInfor();
		
		nhanKhauTv.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
            	idTf.setText(Integer.toString(nhanKhauTv.getSelectionModel().getSelectedItem().getID()));
            	hoTenTf.setText(nhanKhauTv.getSelectionModel().getSelectedItem().getHoTen());
            }
    	});

	}
    
    
    
    private void showInfor() {
		// TODO Auto-generated method stub
    	PhanAnhServices conn = new PhanAnhServices();
		ResultSet rs = conn.getNhanKhau();
		try {
			if (rs != null) {
				while (rs.next()) {
					NhanKhau nhanKhau = new NhanKhau();
					nhanKhau.setID(rs.getInt("ID"));
					nhanKhau.setHoTen(rs.getString("hoTen"));
					nhanKhau.setNamSinh(rs.getDate("namSinh"));
					nhanKhau.setGioiTinh(rs.getString("gioiTinh"));
					nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
					nhanKhauList.add(nhanKhau);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@FXML
    void themMoi(ActionEvent event) throws IOException {
    	if (hoTenTf.getText().trim().isEmpty() || idTf.getText().trim().isEmpty() 
    			|| noiDungTa.getText().trim().isEmpty()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("cảnh báo");
    		alert.setContentText("Vui lòng nhập hết các trường bắt buộc");
    		alert.showAndWait();
    	} else {
	    	PhanAnhServices pas = new PhanAnhServices();
	    	pas.insertPhanAnh(Integer.valueOf(idTf.getText()), hoTenTf.getText(), noiDungTa.getText());
	    	themBt.getScene().getWindow().hide();
	    	FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.class.getResource("phan-anh.fxml"));
	        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
	        QuanLyNhanKhau.window.setScene(scene);
    	}
    }
    
    @FXML
    void huyBo(ActionEvent event) {
    	huyBoBt.getScene().getWindow().hide();
    }

	

}

