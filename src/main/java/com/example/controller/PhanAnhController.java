package com.example.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.model.PhanAnh;
import com.example.services.PhanAnhServices;

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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class PhanAnhController extends Controller implements Initializable{
	@FXML
	private Button trangChuBt;
	@FXML
    private Button hoKhauBt;
    @FXML
    private Button nhanKhauBt;
    @FXML
    private Button phanAnhBt;
    @FXML
    private Button thongKeBt;	
    @FXML
    private Button themMoiBt;
    @FXML
    private Button xuLyBt;
    @FXML 
    private TextField searchHoTenTf;
    
    @FXML
    private TableView<PhanAnh> phanAnhTv;
	@FXML
    private TableColumn<PhanAnh, Integer> IDCol;
	@FXML
    private TableColumn<PhanAnh, String> hoTenCol;
	@FXML
	private TableColumn<PhanAnh, String> noiDungCol;
	@FXML
	private TableColumn<PhanAnh, String> statusCol;
	private ObservableList<PhanAnh> phanAnhList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		phanAnhList = FXCollections.observableArrayList(
				
		);
		IDCol.setCellValueFactory(new PropertyValueFactory<PhanAnh, Integer>("ID"));
		hoTenCol.setCellValueFactory(new PropertyValueFactory<PhanAnh, String>("hoTen"));
		noiDungCol.setCellValueFactory(new PropertyValueFactory<PhanAnh, String>("noiDung"));
		statusCol.setCellValueFactory(new PropertyValueFactory<PhanAnh, String>("status"));
		phanAnhTv.setItems(phanAnhList);
		showInfor();

	}
  
    
    public void denThemMoi(ActionEvent event) throws IOException {
    	Node node = (Node) event.getSource();
    	Stage stage = (Stage) node.getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/com/example/phananhmanage/them-moi-phan-anh.fxml"));
    	loader.load();
    	Parent root = loader.getRoot();
    	Stage modal_dialog = new Stage(StageStyle.DECORATED);
    	modal_dialog.initModality(Modality.WINDOW_MODAL);
    	modal_dialog.initOwner(stage);
    	modal_dialog.setTitle("Them Moi");
    	Scene scene = new Scene(root);
    	modal_dialog.setScene(scene);
    	modal_dialog.show();
    }
    
    public void denXuLy(ActionEvent event) throws IOException {
    	Node node = (Node) event.getSource();
    	Stage stage = (Stage) node.getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/com/example/phananhmanage/xu-ly-phan-anh.fxml"));
    	loader.load();
    	Parent root = loader.getRoot();
    	Stage modal_dialog = new Stage(StageStyle.DECORATED);
    	modal_dialog.initModality(Modality.WINDOW_MODAL);
    	modal_dialog.initOwner(stage);
    	modal_dialog.setTitle("Xử lý phản ánh");
    	Scene scene = new Scene(root);
    	modal_dialog.setScene(scene);
    	modal_dialog.show();
    }
    
    
    public void showInfor() {
    	PhanAnhServices conn = new PhanAnhServices();
		ResultSet rs = conn.getPhanAnh();
		try {
			if (rs != null) {
				while (rs.next()) {
					PhanAnh phanAnh = new PhanAnh();
					phanAnh.setID(rs.getInt("ID"));
					phanAnh.setHoTen(rs.getString("hoTen"));
					phanAnh.setNoiDung(rs.getString("noiDung"));
					phanAnh.setStatus(rs.getString("status"));
					phanAnhList.add(phanAnh);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void searchHoTen(ActionEvent event) {
    	phanAnhList.clear();
    	if (searchHoTenTf.getText().trim().isEmpty()) {
    		showInfor();
    	}
    	PhanAnhServices conn = new PhanAnhServices();
		ResultSet rs = conn.getPhanAnh(searchHoTenTf.getText().trim());
		try {
			if (rs != null) {
				while (rs.next()) {
					PhanAnh phanAnh = new PhanAnh();
					phanAnh.setID(rs.getInt("ID"));
					phanAnh.setHoTen(rs.getString("hoTen"));
					phanAnh.setNoiDung(rs.getString("noiDung"));
					phanAnh.setStatus(rs.getString("status"));
					phanAnhList.add(phanAnh);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}


