package com.example.controller.HoKhauManageController;



import java.io.IOException;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.services.NhanKhauService;
import com.example.model.NhanKhau;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChonChuHoController  implements Initializable {


	@FXML
	private TextField chonChuHoTf;
	@FXML
	private TextField chuHoTf;
	@FXML
	private Button xacNhanBt;
	@FXML
	private Button huyBoBt;
	@FXML
	private TableView<NhanKhau> chonChuHoTv;
	@FXML
	private TableColumn<NhanKhau, String> diaChiHienNayCol;
	@FXML
	private TableColumn<NhanKhau, String> gioiTinhCol;
	@FXML
	private TableColumn<NhanKhau, String> hoTenCol;
	@FXML
	private TableColumn<NhanKhau, Date> ngaySinhCol;
	@FXML
	private TableColumn<NhanKhau, String> soCMTCol;
	private ObservableList<NhanKhau> chuHoList;

	private int idChuHo;
	private String hoTen ;
	private Date ngaySinh;
	private String soCMT;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		chuHoList = FXCollections.observableArrayList(

		);
		hoTenCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
		ngaySinhCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Date>("namSinh"));
		gioiTinhCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
		diaChiHienNayCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("diaChiHienNay"));
		soCMTCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("soCMT"));;
		chonChuHoTv.setItems(chuHoList);
		showInfor();

		chonChuHoTv.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				idChuHo = chonChuHoTv.getSelectionModel().getSelectedItem().getID();

				NhanKhauService conn = new NhanKhauService();
				if (conn.checkPerson(idChuHo)) {
					hoTen = chonChuHoTv.getSelectionModel().getSelectedItem().getHoTen();
					soCMT = chonChuHoTv.getSelectionModel().getSelectedItem().getSoCMT();
					ngaySinh = chonChuHoTv.getSelectionModel().getSelectedItem().getNamSinh();
					chuHoTf.setText(hoTen);
				} else {
					chuHoTf.setText("");
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning Dialog");
					alert.setContentText("Nhân khẩu đã nằm trong hộ mới");
					alert.showAndWait();
				}
			}
		});
	}

	private void showInfor() {
		NhanKhauService conn = new NhanKhauService();
		ResultSet rs = conn.chonChuHoSelect();
		try {
			if (rs != null) {
				while (rs.next()) {
					NhanKhau nk = new NhanKhau();
					nk.setID(rs.getInt("ID"));
					nk.setSoCMT(rs.getString("soCMT"));
					nk.setHoTen(rs.getString("hoTen"));
					nk.setNamSinh(rs.getDate("namSinh"));
					nk.setGioiTinh(rs.getString("gioiTinh"));
					nk.setDiaChiHienNay(rs.getString("diaChiHienNay"));
					chuHoList.add(nk);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void huyBo(ActionEvent event) {
		huyBoBt.getScene().getWindow().hide();
	}

	@FXML
	void xacNhan(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hokhaumanage/them-moi-ho-khau.fxml"));
		Parent root = loader.load();
		ThemMoiHoKhauController themHoKhau = loader.getController();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		String strDate = dateFormat.format(ngaySinh);
		DataHoKhauMoi.hoTenChuHo = hoTen;
		DataHoKhauMoi.ngaySinh = strDate;
		DataHoKhauMoi.soCMT = soCMT;
		DataHoKhauMoi.idChuHo = idChuHo;
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
