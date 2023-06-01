package com.example.controller.HoKhauManageController;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.example.services.HoKhauService;
import com.example.main.QuanLyNhanKhau;
import com.example.model.HoKhau;
import com.example.model.ThanhVienCuaHo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TachHoKhauController  implements Initializable {

	@FXML
	private TextField maHoKhauTf;
	@FXML
	private TableView<HoKhau> chonHoCanTachTv;
	@FXML
	private TableColumn<HoKhau, String> maHoKhauCol;
	@FXML
	private TableColumn<HoKhau, String> hoTenChuHoCol;
	@FXML
	private TableColumn<HoKhau, String> diaChiCol;
	private ObservableList<HoKhau> hoKhauCanTachList;

	@FXML
	private TextField chuHoHienTaiTf;
	@FXML
	private TextField chuHoMoiTf;
	@FXML
	private TextField diaChiTf;
	@FXML
	private TextField maHoKhauMoiTf;
	@FXML
	private TextField maKhuVucTf;

	@FXML
	private TableView<ThanhVienCuaHo> nhanKhau1Tv;
	@FXML
	private TableColumn<ThanhVienCuaHo, String> hoTen1Col;
	@FXML
	private TableColumn<ThanhVienCuaHo, Date> ngaySinh1Col;
	@FXML
	private TableColumn<ThanhVienCuaHo, String> quanHeVoiChuHo1Col;
	private ObservableList<ThanhVienCuaHo> nhanKhauList1;

	@FXML
	private Button themNguoiBt;
	@FXML
	private Button xoaNguoiBt;

	@FXML
	private TableView<ThanhVienCuaHo> nhanKhau2Tv;
	@FXML
	private TableColumn<ThanhVienCuaHo, String> hoTen2Col;
	@FXML
	private TableColumn<ThanhVienCuaHo, Date> ngaySinh2Col;
	@FXML
	private TableColumn<ThanhVienCuaHo, String> quanHeVoiChuHo2Col;
	private ObservableList<ThanhVienCuaHo> nhanKhauList2;

	@FXML
	private Button xacNhanBt;
	@FXML
	private Button huyBoBt;

	private int idChuHo;
	private int idNhanKhau;
	private int idChuHoMoi;
	private String hoTen1;
	private Date namSinh1;
	private String hoTen2;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hoKhauCanTachList = FXCollections.observableArrayList(

		);
		maHoKhauCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("maHoKhau"));
		hoTenChuHoCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("hoTenChuHo"));
		diaChiCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("diaChi"));
		chonHoCanTachTv.setItems(hoKhauCanTachList);
		showInfor();


		chonHoCanTachTv.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				nhanKhauList1.clear();
				idChuHo = chonHoCanTachTv.getSelectionModel().getSelectedItem().getIdChuHo();
				String hoTenChuHoHienTai = chonHoCanTachTv.getSelectionModel().getSelectedItem().getHoTenChuHo();
				hienThongTinGiaDinh();
				chuHoHienTaiTf.setText(hoTenChuHoHienTai);
			}
		});

		nhanKhauList1 = FXCollections.observableArrayList(

		);
		hoTen1Col.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("hoTen"));
		ngaySinh1Col.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, Date>("namSinh"));
		quanHeVoiChuHo1Col.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("quanHeVoiChuHo"));
		nhanKhau1Tv.setItems(nhanKhauList1);
		nhanKhau1Tv.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				hoTen1 = nhanKhau1Tv.getSelectionModel().getSelectedItem().getHoTen();
				namSinh1 = nhanKhau1Tv.getSelectionModel().getSelectedItem().getNamSinh();
				idNhanKhau = nhanKhau1Tv.getSelectionModel().getSelectedItem().getIdNhanhKhau();
			}
		});


		nhanKhauList2 = FXCollections.observableArrayList(

		);
		hoTen2Col.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("hoTen"));
		ngaySinh2Col.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, Date>("namSinh"));
		quanHeVoiChuHo2Col.setCellValueFactory(new PropertyValueFactory<ThanhVienCuaHo, String>("quanHeVoiChuHo"));
		nhanKhau2Tv.setItems(nhanKhauList2);
		nhanKhau2Tv.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				hoTen2 = nhanKhau2Tv.getSelectionModel().getSelectedItem().getHoTen();
			}
		});

	}

	private void showInfor() {
		HoKhauService conn = new HoKhauService();
		ResultSet rs = conn.getHoKhau();
		try {
			if (rs != null) {
				while (rs.next()) {
					HoKhau hk = new HoKhau();
					hk.setIdChuHo(rs.getInt("idChuHo"));
					hk.setMaHoKhau(rs.getString("maHoKhau"));
					hk.setHoTenChuHo(rs.getString("hoTen"));
					hk.setDiaChi(rs.getString("diaChi"));
					hoKhauCanTachList.add(hk);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void hienThongTinGiaDinh() {
		HoKhauService conn = new HoKhauService();
		ResultSet rs = conn.getGiaDinh(idChuHo);
		try {
			if (rs != null) {
				while (rs.next()) {
					ThanhVienCuaHo tvch = new ThanhVienCuaHo();
					tvch.setIdNhanhKhau(rs.getInt("idNhanKhau"));
					tvch.setHoTen(rs.getString("hoTen"));
					tvch.setNamSinh(rs.getDate("namSinh"));
					tvch.setQuanHeVoiChuHo(rs.getString("quanHeVoiNhanKhau"));
					nhanKhauList1.add(tvch);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void themNguoi(ActionEvent event) {
		boolean isInHoMoi = false;
		// duyệt qua danh sách thành viên trong hộ mới, nếu nhân khẩu ddang trỏ có thì isInHoMoi = true
		for (ThanhVienCuaHo i : nhanKhauList2) {
			if (hoTen1.equals(i.getHoTen())) {
				isInHoMoi = true;
			}
		}

		// nếu là chủ hộ mới thì isInHoMoi = true
		if (hoTen1.equals(chuHoMoiTf.getText()))
			isInHoMoi = true;

		if (isInHoMoi) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Nhân khẩu đã nằm trong hộ mới");

			alert.showAndWait();
		} else {
			TextInputDialog dialog = new TextInputDialog("");
			dialog.setTitle("Quan he voi chu ho");
			dialog.setHeaderText("Look, a Text Input Dialog");
			dialog.setContentText("Nhập quan hệ với chủ hộ: ");

			// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				if (result.get().equals("Chủ hộ")) {
					chuHoMoiTf.setText(hoTen1);
					idChuHoMoi = idNhanKhau;
				} else {
					ThanhVienCuaHo tvch = new ThanhVienCuaHo();
					tvch.setHoTen(hoTen1);
					tvch.setNamSinh(namSinh1);
					tvch.setQuanHeVoiChuHo(result.get());
					nhanKhauList2.add(tvch);
				}
			}
		}

	}
	@FXML
	void xoaNguoi(ActionEvent event) {
		for (ThanhVienCuaHo i : nhanKhauList2) {
			if (hoTen2.equals(i.getHoTen())) {
				nhanKhauList2.remove(i);
				break;
			}
		}
	}

	@FXML
	void xacNhan(ActionEvent event) throws IOException {
		if (maKhuVucTf.getText().trim().isEmpty()
				|| diaChiTf.getText().trim().isEmpty()
				|| maHoKhauMoiTf.getText().trim().isEmpty()
				|| chuHoMoiTf.getText().trim().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("cảnh báo");
			alert.setContentText("Vui lòng nhập hết các trường bắt buộc");
			alert.showAndWait();
		} else {
			DataHoKhauMoi.hoTenChuHo = chuHoMoiTf.getText();
			DataHoKhauMoi.diaChi = diaChiTf.getText();
			DataHoKhauMoi.maHoKhau = maHoKhauMoiTf.getText();
			DataHoKhauMoi.maKhuVuc = maKhuVucTf.getText();
			DataHoKhauMoi.ngaySinh = null;
			DataHoKhauMoi.idChuHo = idChuHoMoi;
			DataHoKhauMoi.soCMT = null;
			DataHoKhauMoi.thanhVienCuaHoList = nhanKhauList2;
			HoKhauService hks = new HoKhauService();
			hks.tachHoKhau(nhanKhauList2, idChuHoMoi);
			xacNhanBt.getScene().getWindow().hide();
			//chuyển trang
			FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.class.getResource("ho-khau.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
			QuanLyNhanKhau.window.setScene(scene);
		}
	}
	@FXML
	void huyBo(ActionEvent event) {
		huyBoBt.getScene().getWindow().hide();
	}



}
