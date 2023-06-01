package com.example.controller;

import com.example.main.QuanLyNhanKhau;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public abstract class Controller {

    public void denTrangChu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.class.getResource("trang-chu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        QuanLyNhanKhau.window.setScene(scene);
    }

    public void denHoKhau() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.class.getResource("ho-khau.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        QuanLyNhanKhau.window.setScene(scene);
    }

    public void denNhanKhau() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.class.getResource("nhan-khau.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        QuanLyNhanKhau.window.setScene(scene);
    }

    public void denThongKe() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.class.getResource("thong-ke.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        QuanLyNhanKhau.window.setScene(scene);
    }

    public void denPhanAnh() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.class.getResource("phan-anh.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        QuanLyNhanKhau.window.setScene(scene);
    }
}
