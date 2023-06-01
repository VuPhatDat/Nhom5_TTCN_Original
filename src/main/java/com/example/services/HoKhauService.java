package com.example.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.example.controller.LoginController;
import com.example.controller.HoKhauManageController.DataHoKhauMoi;
import com.example.model.ThanhVienCuaHo;

public class HoKhauService {
	public ResultSet rs = null;

	public ResultSet getHoKhau() {
		String query = "SELECT ho_khau.idChuHo, maHoKhau, nhan_khau.hoTen, ho_khau.diaChi, ho_khau.maKhuVuc FROM ho_khau, nhan_khau where ho_khau.idChuHo = nhan_khau.ID ";

		return resultSet(query);
	}

	public ResultSet getThanhVien() {
		String query = "SELECT hoTen, namSinh, thanh_vien_cua_ho.quanHeVoiChuHo FROM nhan_khau, thanh_vien_cua_ho Where thanh_vien_cua_ho.idNhanKhau = nhan_khau.ID ";
		return resultSet(query);

	}

	public ResultSet getThanhVien(int id) {
		String query = "SELECT hoTen, namSinh, thanh_vien_cua_ho.quanHeVoiChuHo FROM nhan_khau, thanh_vien_cua_ho "
				+ "Where thanh_vien_cua_ho.idNhanKhau = nhan_khau.ID AND nhan_khau.ID =  " + id;
		return resultSet(query);
	}

	public ResultSet getGiaDinh(int id) {
		String query = "Select gia_dinh.idNhanKhau, gia_dinh.hoTen, gia_dinh.namSinh, gia_dinh.quanHeVoiNhanKhau from gia_dinh, ho_khau where gia_dinh.idNhanKhau = ho_khau.idChuHo and ho_khau.idChuHo = " + id ;
		return resultSet(query);
	}

	public ResultSet getIdByHoTen(String hoTen) {
		String query = "select ID from nhan_khau where hoTen = '" + hoTen + "'";
		return resultSet(query);
	}
  
	public ResultSet resultSet (String query) {
		try {
			Connection connection = com.example.services.MySqlConnection.getMySqlConnection();
			Statement stmt  = connection.createStatement();
			rs    = stmt.executeQuery(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public void insertHoKhauMoi () {
		try {
			Connection connection = com.example.services.MySqlConnection.getMySqlConnection();
			String query = "INSERT INTO ho_khau(maHoKhau, idChuHo, maKhuVuc, diaChi, ngayLap)"
					+ " values (?, ?, ?, ?, NOW())";
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, DataHoKhauMoi.maHoKhau);
			preparedStatement.setInt(2, DataHoKhauMoi.idChuHo);
			preparedStatement.setString(3, DataHoKhauMoi.maKhuVuc);
			preparedStatement.setString(4, DataHoKhauMoi.diaChi);

			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				int genID = rs.getInt(1);
				String sql = "INSERT INTO thanh_vien_cua_ho(idNhanKhau, idHoKhau, quanHeVoiChuHo)"
						+ " values (?, ?, ?)";
				for (ThanhVienCuaHo i : DataHoKhauMoi.thanhVienCuaHoList)  {
					try {
						PreparedStatement preStatement = connection.prepareStatement(sql);
						preStatement.setInt(1, i.getIdNhanhKhau());
						preStatement.setInt(2, genID);
						preStatement.setString(3, i.getQuanHeVoiChuHo());
						preStatement.executeUpdate();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tachHoKhau (List<ThanhVienCuaHo> thanhVienList, int idChuHoMoi) {
		// xoa chu ho
//		String query = "Delete from nhan_khau where ID = " + idChuHoMoi;
		String query2 = "DELETE FROM thanh_vien_cua_ho WHERE idNhanKhau = " + idChuHoMoi;

		try {
			Connection connection = com.example.services.MySqlConnection.getMySqlConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.executeUpdate();
			PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
			preparedStatement2.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// xoa cac thanh vien
		for (ThanhVienCuaHo item : thanhVienList) {
//			String sql1 = "Delete from nhan_khau where ID = " + idChuHoMoi;
			String sql2 = "DELETE FROM thanh_vien_cua_ho WHERE idNhanKhau = " + item.getIdNhanhKhau();
			try {
				Connection connection = com.example.services.MySqlConnection.getMySqlConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(sql1);
//                preparedStatement.executeUpdate();
				PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
				preparedStatement2.executeUpdate();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		this.insertHoKhauMoi();
	}

	public void chuyenDi(int idhoKhau, String noiChuyenDen, String lyDoChuyen) {
		String sql = "UPDATE ho_khau SET lyDoChuyen = '"
				+ lyDoChuyen
				+ "',"
				+ "ngayChuyenDi = NOW(), "
				+ "diaChi = '"
				+ noiChuyenDen
				+ "',"
				+ "nguoiThucHien = "
				+ LoginController.currentUser.getID()
				+ " WHERE ho_khau.ID = " + idhoKhau;
		try {
			Connection connection = MySqlConnection.getMySqlConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("services.HoKhauService.chuyenDi()");
			System.out.println(e.getMessage());
		}
	}
}

