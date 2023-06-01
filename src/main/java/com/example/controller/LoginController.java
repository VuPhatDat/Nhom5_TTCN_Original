package com.example.controller;

import com.example.main.QuanLyNhanKhau;
import com.example.model.User;
import com.example.services.MySqlConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginController {
    @FXML
    public AnchorPane warning;

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;

    public static User currentUser = new User();

    public boolean checkUser(String userName, String password) throws SQLException {
        Connection connection = MySqlConnection.getMySqlConnection();

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM users WHERE userName = '" + userName +"'");

        if (rs == null) {
            return false;
        }

        while (rs.next()) {
            if (rs.getString("passwd") == null ? password == null : rs.getString("passwd").equals(password)) {
                LoginController.currentUser.setID(rs.getInt("ID"));
                LoginController.currentUser.setUserName(rs.getString("userName"));
                return true;
            }
        }

        connection.close();
        return false;
    }

    public void login() throws SQLException, IOException {

        if (checkUser(userName.getText().trim(), password.getText().trim())) {
            FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhau.class.getResource("trang-chu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
            QuanLyNhanKhau.window.setScene(scene);
        } else {
            warning.setVisible(true);
            userName.setText("");
            password.setText("");
        }
    }
}