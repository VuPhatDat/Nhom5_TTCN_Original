package com.example.controller.HoKhauManageController;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.services.HoKhauService;
import com.example.main.QuanLyNhanKhau;
import com.example.model.HoKhau;


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

public class ChuyenDiController implements Initializable{

    @FXML
    private TextField diaChiChuyenDenTf;
    @FXML
    private TextField diaChiHienTaiTf;
    @FXML
    private TextField tenChuHoTf;
    @FXML
    private Button xacNhanBt;
    @FXML
    private Button huyBoBt;
    @FXML
    private TextArea liDoChuyenTa;
    @FXML
    private TextField nhapMaHoKhauTf;
    @FXML
    private TextField maHoKhauTf;
    @FXML
    private TextField maKhuVucTf;
    @FXML
    private TableView<HoKhau> hoKhauTv;
    @FXML
    private TableColumn<HoKhau, String> diaChiCol;
    @FXML
    private TableColumn<HoKhau, String> maHoKhauCol;
    @FXML
    private TableColumn<HoKhau, String> tenChuHoCol;
    private ObservableList<HoKhau> hoKhauList;
    private int idChuHo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hoKhauList = FXCollections.observableArrayList(

        );
        maHoKhauCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("maHoKhau"));
        tenChuHoCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("hoTenChuHo"));
        diaChiCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("diaChi"));
        hoKhauTv.setItems(hoKhauList);
        showInfor();
        hoKhauTv.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                maHoKhauTf.setText(hoKhauTv.getSelectionModel().getSelectedItem().getMaHoKhau());
                tenChuHoTf.setText(hoKhauTv.getSelectionModel().getSelectedItem().getHoTenChuHo());
                maKhuVucTf.setText(hoKhauTv.getSelectionModel().getSelectedItem().getMaKhuVuc());
                diaChiHienTaiTf.setText(hoKhauTv.getSelectionModel().getSelectedItem().getDiaChi());
                idChuHo = hoKhauTv.getSelectionModel().getSelectedItem().getIdChuHo();
            }
        });

    }

    private void showInfor() {
        HoKhauService conn = new HoKhauService();
        ResultSet rs = conn.getHoKhau();
        try {
            if (rs != null) {
                while (rs.next()) {
                    HoKhau hoKhau = new HoKhau();
                    hoKhau.setIdChuHo(rs.getInt("idChuHo"));
                    hoKhau.setMaKhuVuc(rs.getString("maKhuVuc"));
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
    
    public void searchMaHoKhau(ActionEvent event) {
    	hoKhauList.clear();
		if (nhapMaHoKhauTf.getText().trim().isEmpty()) {
			showInfor();
		}
		HoKhauService nks = new HoKhauService();
		ResultSet rs = nks.getHoKhauByMaHoKhau(nhapMaHoKhauTf.getText());
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
    
    @FXML
    void huyBo(ActionEvent event) throws IOException {
        huyBoBt.getScene().getWindow().hide();
    }

    @FXML
    void xacNhan(ActionEvent event) throws IOException {
        if (diaChiChuyenDenTf.getText().trim().isEmpty() || diaChiHienTaiTf.getText().trim().isEmpty()
                || tenChuHoTf.getText().trim().isEmpty() || liDoChuyenTa.getText().trim().isEmpty()
                || maHoKhauTf.getText().trim().isEmpty() || maKhuVucTf.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("cảnh báo");
            alert.setContentText("Vui lòng nhập hết các trường bắt buộc");
            alert.showAndWait();
        } else {
            HoKhauService hks = new HoKhauService();
            hks.chuyenDi(idChuHo, diaChiChuyenDenTf.getText(), liDoChuyenTa.getText());
            xacNhanBt.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.class.getResource("ho-khau.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
            QuanLyNhanKhau.window.setScene(scene);
        }
    }



}
