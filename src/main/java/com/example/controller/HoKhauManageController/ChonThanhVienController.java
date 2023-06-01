package com.example.controller.HoKhauManageController;

import java.io.IOException;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.example.services.NhanKhauService;
import com.example.model.NhanKhau;
import com.example.model.ThanhVienCuaHo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChonThanhVienController   implements Initializable {
	@FXML
	private Button themThanhVienBt;
	@FXML
	private Button xoaThanhVienBt;
	@FXML
	private TableView<NhanKhau> nhanKhauTv;
	@FXML
	private TableColumn<NhanKhau, Integer> soCMTCol;
	@FXML
	private TableColumn<NhanKhau, String> hoTenCol;
	@FXML
	private TableColumn<NhanKhau, Date> ngaySinhCol;
	@FXML
	private TableColumn<NhanKhau, String> gioiTinhCol;
	@FXML
	private TableColumn<NhanKhau, String> diaChiHienNayCol;
	private ObservableList<NhanKhau> nhanKhauList;

	@FXML
	private TextField searchHoTenTf;
	@FXML
	private Button luuThayDoiBt;

	@FXML
	private TableView<ThanhVienCuaHo> thanhVienCuaHoTv;
	@FXML
	private TableColumn<ThanhVienCuaHo, Date> ngaySinhTVCol;
	@FXML
	private TableColumn<ThanhVienCuaHo, String> quanHeVoiChuHoTVCol;
	@FXML
	private TableColumn<ThanhVienCuaHo, String> hoTenTVCol;
	private ObservableList<ThanhVienCuaHo> thanhVienCuaHoList;

	private boolean checkPerson = false;
	private int idNhanKhau;
	private String hoTen;
	private Date namSinh;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		nhanKhauList = FXCollections.observableArrayList(

		);
		soCMTCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("ID"));
		hoTenCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
		ngaySinhCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Date>("namSinh"));
		gioiTinhCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
		diaChiHienNayCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("diaChiHienNay"));
		nhanKhauTv.setItems(nhanKhauList);
		showInforNhanKhau();

		nhanKhauTv.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				int id = nhanKhauTv.getSelectionModel().getSelectedItem().getID();

				NhanKhauService conn = new NhanKhauService();
				if (conn.checkPerson(id)) {
					checkPerson = true;
					idNhanKhau = id;
					hoTen = nhanKhauTv.getSelectionModel().getSelectedItem().getHoTen();
					namSinh = nhanKhauTv.getSelectionModel().getSelectedItem().getNamSinh();
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning Dialog");
					alert.setContentText("Nhân khẩu đã nằm trong hộ mới");
					alert.showAndWait();
				}
			}
		});

		thanhVienCuaHoList = FXCollections.observableArrayList(

		);
		hoTenTVCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("hoTen"));
		ngaySinhTVCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, Date>("namSinh"));
		quanHeVoiChuHoTVCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("quanHeVoiChuHo"));
		thanhVienCuaHoTv.setItems(thanhVienCuaHoList);
		thanhVienCuaHoTv.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				idNhanKhau = thanhVienCuaHoTv.getSelectionModel().getSelectedItem().getIdNhanhKhau();
			}
		});
	}

	public void showInforNhanKhau() {
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

	public void searchHoTen(ActionEvent event) {
		nhanKhauList.clear();
		if (searchHoTenTf.getText().trim().isEmpty()) {
			showInforNhanKhau();
		}
		NhanKhauService conn = new NhanKhauService();
		ResultSet rs = conn.getNhanKhauByName(searchHoTenTf.getText().trim());
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
	void themThanhVien(ActionEvent event) throws SQLException {
		// nếu nhan khâu chưa nằm trong hộ nào thì thêm quan hệ vói chủ hộ và them vào cơ sở dữ liệu Quan hê vói chủ hộ nếu xác nhận
		if (checkPerson == true) {
			TextInputDialog dialog = new TextInputDialog("");
			dialog.setTitle("Quan he voi chu ho");
			dialog.setHeaderText("Look, a Text Input Dialog");
			dialog.setContentText("Please enter your relationship:");

			// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				ThanhVienCuaHo tvch = new ThanhVienCuaHo();
				tvch.setIdNhanhKhau(idNhanKhau);
				tvch.setHoTen(hoTen);
				tvch.setNamSinh(namSinh);
				tvch.setQuanHeVoiChuHo(result.get());
				thanhVienCuaHoList.add(tvch);
			}
		}
	}

	@FXML
	void xoaThanhVien(ActionEvent event) {
		for (ThanhVienCuaHo i : thanhVienCuaHoList) {
			if (idNhanKhau == i.getIdNhanhKhau()) {
				thanhVienCuaHoList.remove(i);
				break;
			}
		}
	}

	@FXML
	void luuThayDoi(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hokhaumanage/them-moi-ho-khau.fxml"));
		Parent root = loader.load();
		ThemMoiHoKhauController themHoKhau = loader.getController();
		DataHoKhauMoi.thanhVienCuaHoList = thanhVienCuaHoList;
		themHoKhau.setThongTinChuHo();
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.getOwner().hide();
		Scene scene = new Scene(root);
		stage.setTitle("Thêm mới hộ khẩu");
		stage.setScene(scene);
		stage.show();
	}

}
