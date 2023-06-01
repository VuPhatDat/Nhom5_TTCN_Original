package com.example.controller;

import com.example.services.MySqlConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class TrangChuController extends Controller implements Initializable {
    @FXML
    Text tongNhanKhau;
    @FXML
    Text tongHoKhau;
    @FXML
    Text tamTru;
    @FXML
    Text tamVang;
    @FXML
    Text tongPhanAnh;
    @FXML
    Text daXuLy;

    public TrangChuController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }

    public void setData() {
        try {
            Connection connection = MySqlConnection.getMySqlConnection();
            String query = "SELECT COUNT(*) AS tong FROM nhan_khau";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                tongNhanKhau.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            query = "SELECT COUNT(*) AS tong FROM ho_khau";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()){
                tongHoKhau.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            query = "SELECT COUNT(*) AS tong FROM tam_tru WHERE denNgay > NOW()";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                tamTru.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            query = "SELECT COUNT(*) AS tong FROM tam_vang WHERE denNgay > NOW()";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                tamVang.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            query = "SELECT COUNT(*) AS tong FROM phan_anh";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()){
                tongPhanAnh.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            query = "SELECT COUNT(*) AS tong FROM phan_anh WHERE status = 'Đã giải quyết'";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()){
                daXuLy.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
