package com.example.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.services.NhanKhauService;
import com.example.model.NhanKhau;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NhanKhauController extends Controller implements Initializable{
	@FXML
	private Button trangChuBt;
	@FXML
	private Button nhanKhauBt;
	@FXML
	private Button hoKhauBt;
	@FXML
	private Button thongKeBt;
	@FXML
	private Button phanAnhBt;
	@FXML
    private TableView<NhanKhau> nhanKhauTv;
	@FXML
    private TableColumn<NhanKhau, Integer> idCol;
	@FXML
    private TableColumn<NhanKhau, String> hoTenCol;
	@FXML
    private TableColumn<NhanKhau, Date> ngaySinhCol;
	@FXML
    private TableColumn<NhanKhau, String> gioiTinhCol;
	@FXML
    private TableColumn<NhanKhau, String> diaChiHienNayCol;
	private ObservableList<NhanKhau> nhanKhauList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		nhanKhauList = FXCollections.observableArrayList(
				
		);
		idCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("ID"));
		hoTenCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
		ngaySinhCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Date>("namSinh"));
		gioiTinhCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
		diaChiHienNayCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("diaChiHienNay"));
		nhanKhauTv.setItems(nhanKhauList);
		showInfor();
	}
	
	public void showInfor() {
    	NhanKhauService conn = new NhanKhauService();
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
	
	public void denTrangChu() throws IOException {
		super.denTrangChu();
	}

    public void denHoKhau() throws IOException {
    	super.denHoKhau();
    }

    public void denNhanKhau() throws IOException {
    	super.denNhanKhau();
    }

    public void denThongKe() throws IOException {
    	super.denThongKe();
    }

    public void denPhanAnh() throws IOException {
    	super.denPhanAnh();
    }

    public void themMoi(ActionEvent event) throws IOException {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();			
		loader.setLocation(getClass().getResource("/com/example/nhankhaumanage/them-moi-nhan-khau.fxml"));			
		loader.load();
		Parent root = loader.getRoot();
		Stage modal_dialog = new Stage(StageStyle.DECORATED);
        modal_dialog.initModality(Modality.WINDOW_MODAL);
        modal_dialog.initOwner(stage);
        modal_dialog.setTitle("Them moi nhan khau");
        Scene scene = new Scene(root);	
		modal_dialog.setScene(scene);
		modal_dialog.show();
    }
    
    public void dkTamVang(ActionEvent event) throws IOException {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();			
		loader.setLocation(getClass().getResource("/com/example/nhankhaumanage/dk-tam-vang.fxml"));			
		loader.load();
		Parent root = loader.getRoot();
		Stage modal_dialog = new Stage(StageStyle.DECORATED);
        modal_dialog.initModality(Modality.WINDOW_MODAL);
        modal_dialog.initOwner(stage);
        modal_dialog.setTitle("Đăng kí tạm vắng");
        Scene scene = new Scene(root);	
		modal_dialog.setScene(scene);
		modal_dialog.show();
    }
    
    public void dkTamTru(ActionEvent event) throws IOException {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();			
		loader.setLocation(getClass().getResource("/com/example/nhankhaumanage/dk-tam-tru.fxml"));			
		loader.load();
		Parent root = loader.getRoot();
		Stage modal_dialog = new Stage(StageStyle.DECORATED);
        modal_dialog.initModality(Modality.WINDOW_MODAL);
        modal_dialog.initOwner(stage);
        modal_dialog.setTitle("Đăng kí tạm trú");
        Scene scene = new Scene(root);	
		modal_dialog.setScene(scene);
		modal_dialog.show();
    }
    
    public void khaiTu(ActionEvent event) throws IOException {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();			
		loader.setLocation(getClass().getResource("/com/example/nhankhaumanage/khai-tu.fxml"));			
		loader.load();
		Parent root = loader.getRoot();
		Stage modal_dialog = new Stage(StageStyle.DECORATED);
        modal_dialog.initModality(Modality.WINDOW_MODAL);
        modal_dialog.initOwner(stage);
        modal_dialog.setTitle("Khai tử");
        Scene scene = new Scene(root);	
		modal_dialog.setScene(scene);
		modal_dialog.show();
    }
}

