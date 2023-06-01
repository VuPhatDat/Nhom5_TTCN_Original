package com.example.controller.HoKhauManageController;

import java.io.IOException;



import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


import com.example.services.HoKhauService;
import com.example.main.QuanLyNhanKhau;
import com.example.model.ThanhVienCuaHo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ThemMoiHoKhauController  implements Initializable {

	@FXML
	private Button chonChuHoBt;
	@FXML
	private Button chonThanhVienBt;
	@FXML
	private TextField chuHoTf;
	@FXML
	private TextField diaChiTf;
	@FXML
	private TextField maHoKhauTf;
	@FXML
	private TextField maKhuVucTf;
	@FXML
	private TextField ngaySinhChuHoTf;
	@FXML
	protected TextField soCMTTf;
	@FXML
	private Button huyBoBt;
	@FXML
	private Button taoBt;

	@FXML
	private TableView<ThanhVienCuaHo> thanhVienTv;
	@FXML
	private TableColumn<ThanhVienCuaHo, String> hoTenCol;
	@FXML
	private TableColumn<ThanhVienCuaHo, Date> ngaySinhCol;
	@FXML
	private TableColumn<ThanhVienCuaHo, String> quanHeCol;
	private ObservableList<ThanhVienCuaHo> thanhVienList;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		thanhVienList = FXCollections.observableArrayList(

		);
		hoTenCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("hoTen"));
		ngaySinhCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, Date>("namSinh"));
		quanHeCol.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("quanHeVoiChuHo"));
		thanhVienTv.setItems(thanhVienList);
	}

	@FXML
	void chonChuHo(ActionEvent event) throws IOException {
		luuThongTin();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/example/hokhaumanage/chon-chu-ho.fxml"));
		loader.load();
		Parent root = loader.getRoot();
		Stage modal_dialog = new Stage(StageStyle.DECORATED);
		modal_dialog.initModality(Modality.WINDOW_MODAL);
		modal_dialog.initOwner(stage);
		modal_dialog.setTitle("Chọn chủ hộ");
		Scene scene = new Scene(root);
		modal_dialog.setScene(scene);
		modal_dialog.show();
	}

	@FXML
	void chonThanhVien(ActionEvent event) throws IOException {
		luuThongTin();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/example/hokhaumanage/chon-thanh-vien.fxml"));
		loader.load();
		Parent root = loader.getRoot();
		Stage modal_dialog = new Stage(StageStyle.DECORATED);
		modal_dialog.initModality(Modality.WINDOW_MODAL);
		modal_dialog.initOwner(stage);
		modal_dialog.setTitle("Chọn thành viên");
		Scene scene = new Scene(root);
		modal_dialog.setScene(scene);
		modal_dialog.show();
	}

	public void luuThongTin() {
		DataHoKhauMoi.hoTenChuHo = chuHoTf.getText();
		DataHoKhauMoi.ngaySinh = ngaySinhChuHoTf.getText();
		DataHoKhauMoi.soCMT = soCMTTf.getText();
		DataHoKhauMoi.diaChi = diaChiTf.getText();
		DataHoKhauMoi.maHoKhau = maHoKhauTf.getText();
		DataHoKhauMoi.maKhuVuc = maKhuVucTf.getText();
		DataHoKhauMoi.thanhVienCuaHoList = thanhVienList;
	}

	public void setThongTinChuHo() {
		chuHoTf.setText(DataHoKhauMoi.hoTenChuHo);
		ngaySinhChuHoTf.setText(DataHoKhauMoi.ngaySinh);
		soCMTTf.setText(DataHoKhauMoi.soCMT);
		diaChiTf.setText(DataHoKhauMoi.diaChi);
		maHoKhauTf.setText(DataHoKhauMoi.maHoKhau);
		maKhuVucTf.setText(DataHoKhauMoi.maKhuVuc);
		thanhVienList.addAll(DataHoKhauMoi.thanhVienCuaHoList);
	}

	@FXML
	void huyBo(ActionEvent event) throws IOException {
		huyBoBt.getScene().getWindow().hide();
	}

	@FXML
	void tao(ActionEvent event) throws IOException {
		if (chuHoTf.getText().trim().isEmpty() || diaChiTf.getText().trim().isEmpty()
				|| maHoKhauTf.getText().trim().isEmpty()|| ngaySinhChuHoTf.getText().trim().isEmpty()
				|| maKhuVucTf.getText().trim().isEmpty()|| soCMTTf.getText().trim().isEmpty()
				|| thanhVienList.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("cảnh báo");
			alert.setContentText("Vui lòng nhập hết các trường bắt buộc");
			alert.showAndWait();
		} else {
			
			HoKhauService hoKhau = new HoKhauService();
			hoKhau.insertHoKhauMoi();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setTitle("Thông báo");
    		alert.setContentText("Thêm mới hộ khẩu thành công");
    		alert.showAndWait();
			taoBt.getScene().getWindow().hide();
			FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.class.getResource("ho-khau.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
			QuanLyNhanKhau.window.setScene(scene);

		}

	}

}
