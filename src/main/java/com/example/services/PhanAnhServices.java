package com.example.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PhanAnhServices {
	public ResultSet rs = null;
	
	public ResultSet getPhanAnh() {
		String query = "SELECT ID, hoTen, noiDung, status from phan_anh";
		return resultSet(query);
	}
	
	public ResultSet getPhanAnh(String hoTen) {
		String query = "SELECT ID, hoTen, noiDung, status from phan_anh where hoTen = '" + hoTen + "'";
		return resultSet(query);
	}
	
	public ResultSet getNhanKhau() {
    	String query = "SELECT ID, hoTen, namSinh, gioiTinh, diaChiHienNay FROM nhan_khau ";
    	return resultSet(query);
    }
	
	public ResultSet getXuLyPhanAnh() {
		String query = "SELECT ID, hoTen, noiDung, status from phan_anh where status = 'Đang chờ duyệt'";
		return resultSet(query);
	}
	
	
	
	public ResultSet resultSet (String query) {
		try {
			Connection connection = MySqlConnection.getMySqlConnection();
	    	Statement stmt  = connection.createStatement();
	    	rs    = stmt.executeQuery(query);
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return rs;
	}
	
	public ResultSet insertPhanAnh (int ID, String hoTen, String noiDung) {
		try {
			Connection connection = MySqlConnection.getMySqlConnection();
	    	String query = "Insert into phan_anh ( ID, hoTen, noiDung, status) values (?, ?, ?, 'Đang chờ duyệt') ";
	    	PreparedStatement preparedStatement = connection.prepareStatement(query);
	    	preparedStatement.setInt(1, ID);
	    	preparedStatement.setString(2, hoTen);
	    	preparedStatement.setString(3, noiDung);
	    	preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return rs;
	}
	
	public ResultSet updatePhanAnh (int ID) {
		try {
			Connection connection = MySqlConnection.getMySqlConnection();
	    	Statement statement = connection.createStatement();
	    	String query = "UPDATE phan_anh SET status = 'Đã giải quyết' where ID = " + ID;
	    	statement.executeUpdate(query);
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return rs;
	}
	
	public ResultSet deletePhanAnh (int ID) {
		try {
			Connection connection = MySqlConnection.getMySqlConnection();
	    	Statement statement = connection.createStatement();
	    	String query = "Delete from phan_anh where ID = " + ID;
	    	statement.executeUpdate(query);
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return rs;
	}
}
