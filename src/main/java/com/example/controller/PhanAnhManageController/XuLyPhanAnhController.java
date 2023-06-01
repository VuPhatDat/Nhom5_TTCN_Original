package com.example.controller.PhanAnhManageController;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
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
	@FXML
	private TableColumn<PhanAnh, Button> xoaPaCol;
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
		xoaPaCol.setCellValueFactory(new PropertyValueFactory<PhanAnh, Button>("xoaButton"));
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
					phanAnh.getXoaButton().setOnAction(this::xoaPhanAnh);
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
    	if (Objects.equals(b.getText(), "Duyệt")) {
    		b.setText("Đã duyệt");
    		for (PhanAnh i : phanAnhList) {
				if(Objects.equals(i.getStatusButton().getText(), "Đã duyệt")) {
    				PhanAnhServices conn = new PhanAnhServices();
    				ResultSet rs = conn.updatePhanAnh(i.getID());	
    			}
    		}
    	}
    }
    
    public void xoaPhanAnh(ActionEvent event) {
    	Button b = (Button) event.getSource();
    	if (Objects.equals(b.getText(), "Xóa")) {
    		b.setText("Đã xóa");
    		for (PhanAnh i : phanAnhList) {
				if(Objects.equals(i.getXoaButton().getText(), "Đã xóa")) {
    				PhanAnhServices conn = new PhanAnhServices();
    				ResultSet rs = conn.deletePhanAnh(i.getID());	
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



