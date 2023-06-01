package com.example.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.services.HoKhauService;
import com.example.services.NhanKhauService;
import com.example.controller.HoKhauManageController.ThongTinHoKhauController;
import com.example.controller.NhanKhauManageController.ThongTinNhanKhauController;
import com.example.model.HoKhau;
import com.example.model.NhanKhau;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HoKhauController extends Controller implements Initializable {
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
    private Button chuyenDiBt;
	@FXML
    private Button tachHoKhauBt;
	@FXML
    private Button themMoiHoKhauBt;
	@FXML
	private TextField searchTf;
	@FXML
    private TableView<HoKhau> hoKhauTv;
	@FXML
    private TableColumn<HoKhau, String> maHoKhauCol;
	@FXML
    private TableColumn<HoKhau, String> tenChuHoCol;
	@FXML
	private TableColumn<HoKhau, String> diaChiCol;
	private ObservableList<HoKhau> hoKhauList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		hoKhauList = FXCollections.observableArrayList(
				
		);
		maHoKhauCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("maHoKhau"));
		tenChuHoCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("hoTenChuHo"));
		diaChiCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("diaChi"));
		hoKhauTv.setItems(hoKhauList);
		showInfor();
		
		hoKhauTv.setOnMousePressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {					
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			    	FXMLLoader loader = new FXMLLoader();			
					loader.setLocation(getClass().getResource("/com/example/hokhaumanage/thong-tin-ho-khau.fxml"));			
					loader.load();
					Parent root = loader.getRoot();					
					ThongTinHoKhauController thongTinHoKhau = loader.getController();
					thongTinHoKhau.setInfor(hoKhauTv.getSelectionModel().getSelectedItem().getMaHoKhau(), 
							hoKhauTv.getSelectionModel().getSelectedItem().getHoTenChuHo(), 
							hoKhauTv.getSelectionModel().getSelectedItem().getDiaChi());
					thongTinHoKhau.hienThongTinGiaDinh(hoKhauTv.getSelectionModel().getSelectedItem().getID());
					Stage modal_dialog = new Stage(StageStyle.DECORATED);
			        modal_dialog.initModality(Modality.WINDOW_MODAL);
			        modal_dialog.initOwner(stage);
			        modal_dialog.setTitle("Thông tin hộ khẩu");
			        Scene scene = new Scene(root);	
					modal_dialog.setScene(scene);
					modal_dialog.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
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
    
    public void searchHoKhau(ActionEvent event) {
    	hoKhauList.clear();
		if (searchTf.getText().trim().isEmpty()) {
			showInfor();
		}
		HoKhauService nks = new HoKhauService();
		ResultSet rs = nks.getHoKhauByMaHoKhau(searchTf.getText());
		try {
			if (rs != null) {
				while (rs.next()) {
					HoKhau hoKhau = new HoKhau();
					hoKhau.setMaHoKhau(rs.getString("maHoKhau"));
					hoKhau.setHoTenChuHo(rs.getString("hoTen"));
					hoKhau.setDiaChi(rs.getString("diaChi"));
					hoKhauList.add(hoKhau);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }
	
    public void themMoiHoKhau(ActionEvent event) throws IOException {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();			
		loader.setLocation(getClass().getResource("/com/example/hokhaumanage/them-moi-ho-khau.fxml"));			
		loader.load();
		Parent root = loader.getRoot();
		Stage modal_dialog = new Stage(StageStyle.DECORATED);
        modal_dialog.initModality(Modality.WINDOW_MODAL);
        modal_dialog.initOwner(stage);
        modal_dialog.setTitle("Thêm mới hộ khẩu");
        Scene scene = new Scene(root);	
		modal_dialog.setScene(scene);
		modal_dialog.show();
    }
    
    public void tachHoKhau(ActionEvent event) throws IOException {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();			
		loader.setLocation(getClass().getResource("/com/example/hokhaumanage/tach-ho-khau.fxml"));			
		loader.load();
		Parent root = loader.getRoot();
		Stage modal_dialog = new Stage(StageStyle.DECORATED);
        modal_dialog.initModality(Modality.WINDOW_MODAL);
        modal_dialog.initOwner(stage);
        modal_dialog.setTitle("Tách hộ khẩu");
        Scene scene = new Scene(root);	
		modal_dialog.setScene(scene);
		modal_dialog.show();
    }
    
    public void chuyenDi(ActionEvent event) throws IOException {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();			
		loader.setLocation(getClass().getResource("/com/example/hokhaumanage/chuyen-di.fxml"));			
		loader.load();
		Parent root = loader.getRoot();
		Stage modal_dialog = new Stage(StageStyle.DECORATED);
        modal_dialog.initModality(Modality.WINDOW_MODAL);
        modal_dialog.initOwner(stage);
        modal_dialog.setTitle("Chuyển đi");
        Scene scene = new Scene(root);	
		modal_dialog.setScene(scene);
		modal_dialog.show();
    }

    public void showInfor() {
    	HoKhauService conn = new HoKhauService();
		ResultSet rs = conn.getHoKhau();
		try {
			if (rs != null) {
				while (rs.next()) {
					HoKhau hoKhau = new HoKhau();
					hoKhau.setID(rs.getInt("ID"));
					hoKhau.setMaHoKhau(rs.getString("maHoKhau"));
					hoKhau.setHoTenChuHo(rs.getString("hoTen"));
					hoKhau.setDiaChi(rs.getString("diaChi"));
					hoKhauList.add(hoKhau);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

