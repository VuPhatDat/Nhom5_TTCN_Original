package com.example.controller;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.main.QuanLyNhanKhau;
import com.example.model.PhanAnh;
import com.example.services.PhanAnhServices;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class XuLyPhanAnhController implements Initializable {

	@FXML
	private Button thoatBt;
    @FXML
    private TableView<PhanAnh> xuLyTv;
	@FXML
    private TableColumn<PhanAnh, Integer> idCol;
	@FXML
    private TableColumn<PhanAnh, String> hoTenCol;
	@FXML
	private TableColumn<PhanAnh, String> noiDungCol;
	@FXML
	private TableColumn<PhanAnh, Button> statusCol;
	private ObservableList<PhanAnh> phanAnhList;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		phanAnhList = FXCollections.observableArrayList(
		
		);
		idCol.setCellValueFactory(new PropertyValueFactory<PhanAnh, Integer>("ID"));
		hoTenCol.setCellValueFactory(new PropertyValueFactory<PhanAnh, String>("hoTen"));
		noiDungCol.setCellValueFactory(new PropertyValueFactory<PhanAnh, String>("noiDung"));
		statusCol.setCellValueFactory(new PropertyValueFactory<PhanAnh, Button>("statusButton"));
		xuLyTv.setItems(phanAnhList);
		showInfor();

	}

    private void showInfor() {
    	PhanAnhServices conn = new PhanAnhServices();
		ResultSet rs = conn.getXuLyPhanAnh();
		try {
			if (rs != null) {
				while (rs.next()) {
					PhanAnh phanAnh = new PhanAnh();
					phanAnh.setID(rs.getInt("ID"));
					phanAnh.setHoTen(rs.getString("hoTen"));
					phanAnh.setNoiDung(rs.getString("noiDung"));
					phanAnh.setStatus(rs.getString("status"));
					phanAnh.getStatusButton().setOnAction(this::duyetPhanAnh);
					phanAnhList.add(phanAnh);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    public void duyetPhanAnh(ActionEvent event) {
    	Button b = (Button) event.getSource();
    	if (b.getText() == "Duyệt") {
    		b.setText("Đã duyệt");
    		for (PhanAnh i : phanAnhList) {
    			if(i.getStatusButton().getText() == "Đã duyệt") {
    				PhanAnhServices conn = new PhanAnhServices();
    				ResultSet rs = conn.updatePhanAnh(i.getID());	
    				phanAnhList.remove(i);
    			}
    		}
    	}
    }

	@FXML
    void thoat(ActionEvent event) throws IOException {
		thoatBt.getScene().getWindow().hide();
		FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.class.getResource("phan-anh.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        QuanLyNhanKhau.window.setScene(scene);
    }

	
}



