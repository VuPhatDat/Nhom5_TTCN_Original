package com.example.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class QuanLyNhanKhau extends Application {

    public static Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.getIcons().add(new Image(Objects.requireNonNull(QuanLyNhanKhau.class.getResourceAsStream("/image/logo.png"))));
        stage.setTitle("Quản lý nhân khẩu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}