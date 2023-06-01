package com.example.services;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.model.KhaiTu;
import com.example.model.TamTru;
import com.example.model.TamVang;


import java.util.ArrayList;
import java.util.List;

import com.example.bean.NhanKhauBean;
import com.example.model.*;

import com.example.services.MySqlConnection;
import javafx.scene.control.Alert;

public class NhanKhauService {
	ResultSet rs  = null;
	public ResultSet getNhanKhau() {
    	String query = "SELECT ID, hoTen, namSinh, gioiTinh, diaChiHienNay FROM nhan_khau ";
    	return resultSet(query);
    }
	
	public ResultSet chonChuHoSelect() {
		String query = "SELECT  chung_minh_thu.soCMT, nhan_khau.ID, hoTen, namSinh, gioiTinh, diaChiHienNay FROM nhan_khau, chung_minh_thu where chung_minh_thu.idNhanKhau = nhan_khau.ID ";
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
	
	// kiểm tra nhân khẩu đã nằm trong hộ khẩu nào chưa
	public boolean checkInHoKhau (int idNhanKhau) throws SQLException {
		String query = "SELECT nhan_khau.ID from nhan_khau, thanh_vien_cua_ho where nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau and nhan_khau.ID = " + idNhanKhau;
		if (resultSet(query).next()) {
			return false;
		} else 
			return true;
	}
	public boolean checkPerson(int id) {
        try {
            Connection connection = com.example.services.MySqlConnection.getMySqlConnection();

            String query = "SELECT * FROM ho_khau INNER JOIN thanh_vien_cua_ho ON ho_khau.ID = thanh_vien_cua_ho.idHoKhau"
                        + " WHERE ho_khau.idChuHo = "
                        + id 
                        + " OR thanh_vien_cua_ho.idNhanKhau = "
                        + id;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
        }
        return true;
    }
	
	
	
    public void insertNhanKhau( String soCMT, String hoTen, String bietDanh, Date namSinh, String gioiTinh, String noiSinh,  String nguyeQuan, 
    		String dantoc, String tonGiao, String quocTich, String soHoChieu, String noiThuongTru, String diaChiHienNay, String trinhDoHocVan, String TrinhDoChuyenMon,
    		String bietTiengDanToc, String trinhDoNgoaiNgu, String ngheNghiep, String noiLamViec) {
    	try {
			
			Connection connection = com.example.services.MySqlConnection.getMySqlConnection();

			String INSERT_QUERY = "INSERT INTO nhan_khau ( hoTen, bietDanh, namSinh, gioiTinh, noiSinh, nguyenQuan, "
					+ "danToc, tonGiao, quocTich, soHoChieu, noiThuongTru, diaChiHienNay, trinhDoHocVan, TrinhDoChuyenMon,"
					+ "bietTiengDanToc, trinhDoNgoaiNgu, ngheNghiep, noiLamViec) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, hoTen);
			stmt.setString(2, bietDanh);
			stmt.setDate(3, namSinh);
			stmt.setString(4, gioiTinh);
			stmt.setString(5, noiSinh);
			stmt.setString(6, nguyeQuan);
			stmt.setString(7, dantoc);
			stmt.setString(8, tonGiao);
			stmt.setString(9, quocTich);
			stmt.setString(10, soHoChieu);
			stmt.setString(11, noiThuongTru);
			stmt.setString(12, diaChiHienNay);
			stmt.setString(13, trinhDoHocVan);
			stmt.setString(14, TrinhDoChuyenMon);
			stmt.setString(15, bietTiengDanToc);
			stmt.setString(16, trinhDoNgoaiNgu);
			stmt.setString(17, ngheNghiep);
			stmt.setString(18, noiLamViec);
			
			stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int genID = rs.getInt(1);
                String sql = "INSERT INTO chung_minh_thu(idNhanKhau, soCMT)" 
                            + " values (?, ?)";
                PreparedStatement prst = connection.prepareStatement(sql);
                prst.setInt(1, genID);
                prst.setString(2, soCMT);
                prst.execute();
//                nhanKhauBean.getListTieuSuModels().forEach(tieuSu -> {
//                    try {
//                        String sql_tieu_su = "INSERT INTO tieu_su(idNhanKhau, tuNgay, denNgay, diaChi, ngheNghiep, noiLamViec)" 
//                            + " values (?, ?, ?, ?, ?, ?)";
//                        PreparedStatement preStatement = connection.prepareStatement(sql_tieu_su);
//                        preStatement.setInt(1, genID);
//                        Date tuNgay = new Date(tieuSu.getTuNgay().getTime());
//                        preStatement.setDate(2, tuNgay);
//                        preStatement.setDate(3, new Date(tieuSu.getDenNgay().getTime()));
//                        preStatement.setString(4, tieuSu.getDiaChi());
//                        preStatement.setString(5, tieuSu.getNgheNghiep());
//                        preStatement.setString(6, tieuSu.getNoiLamViec());
//                        preStatement.execute();
//                        preStatement.close();
//                    } catch (Exception e) {
//                        System.out.println(e);
//                    }
//                });
//                nhanKhauBean.getListGiaDinhModels().forEach(giaDinh -> {
//                    try {
//                        String sql_tieu_su = "INSERT INTO gia_dinh(idNhanKhau, hoTen, namSinh, gioiTinh, quanHeVoiNhanKhau, ngheNghiep, diaChiHienTai)" 
//                            + " values (?, ?, ?, ?, ?, ?, ?)";
//                        PreparedStatement preStatement = connection.prepareStatement(sql_tieu_su);
//                        preStatement.setInt(1, genID);
//                        preStatement.setString(2, giaDinh.getHoTen());
//                        preStatement.setDate(3, new Date(giaDinh.getNamSinh().getTime()));
//                        preStatement.setString(4, giaDinh.getGioiTinh());
//                        preStatement.setString(5, giaDinh.getQuanHeVoiNhanKhau());
//                        preStatement.setString(6, giaDinh.getNgheNghiep());
//                        preStatement.setString(7, giaDinh.getDiaChiHienTai());
//                        preStatement.execute();
//                        preStatement.close();
//                    } catch (Exception e) {
//                        System.out.println("controllers.NhanKhauManagerController.AddNewController.addNewPeople()");
//                    }
//                });
            }
            connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public int checkCMT(String cmt) {
        try {
            Connection connection = com.example.services.MySqlConnection.getMySqlConnection();

            String query = "SELECT * FROM nhan_khau LEFT JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau WHERE soCMT = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cmt);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (Exception e) {
        	Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("cảnh báo");
    		alert.setContentText("Có lỗi xảy ra! Vui lòng thử lại");
    		alert.showAndWait();
        }
        return -1;
    }
    
    public void themTamVang(TamVang tamVangModel) {
        try {
            Connection connection = com.example.services.MySqlConnection.getMySqlConnection();

            String query = "INSERT INTO tam_vang(idNhanKhau, maGiayTamVang, noiTamTru, tuNgay, denNgay, lyDo)" + " value (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tamVangModel.getIdNhanKhau());
            preparedStatement.setString(2, tamVangModel.getMaGiayTamVang());
            preparedStatement.setString(3, tamVangModel.getNoiTamTru());
            java.sql.Date tuNgay = new java.sql.Date(tamVangModel.getTuNgay().getTime());
            preparedStatement.setDate(4, tuNgay);
            java.sql.Date denNgay = new java.sql.Date(tamVangModel.getDenNgay().getTime());
            preparedStatement.setDate(5, denNgay);
            preparedStatement.setString(6, tamVangModel.getLyDo());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();         
        } catch (Exception e) {
        	Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("cảnh báo");
    		alert.setContentText("Có lỗi xảy ra! Vui lòng thử lại");
    		alert.showAndWait();
			e.printStackTrace();
        }
    }
    
    public void themTamTru(TamTru tamTruModel) {
        try {
            Connection connection = com.example.services.MySqlConnection.getMySqlConnection();

            String query = "INSERT INTO tam_tru(idNhanKhau, maGiayTamTru, soDienThoaiNguoiDangKy, tuNgay, denNgay, lyDo)" + " value (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tamTruModel.getIdNhanKhau());
            preparedStatement.setString(2, tamTruModel.getMaGiayTamTru());
            preparedStatement.setString(3, tamTruModel.getSoDienThoaiNguoiDangKy());
            Date tuNgay = new Date(tamTruModel.getTuNgay().getTime());
            preparedStatement.setDate(4, tuNgay);
            Date denNgay = new Date(tamTruModel.getDenNgay().getTime());
            preparedStatement.setDate(5, denNgay);
            preparedStatement.setString(6, tamTruModel.getLyDo());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("cảnh báo");
    		alert.setContentText("Có lỗi xảy ra! Vui lòng thử lại");
    		alert.showAndWait();
        }
    }
    
    public void themKhaiTu(KhaiTu khaiTu) {
        try {
            Connection connection = MySqlConnection.getMySqlConnection();
            String query = "INSERT INTO khai_tu(soGiayKhaiTu, idNguoiKhai, idNguoiChet, ngayKhai, ngayChet, lyDoChet)" + " value (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, khaiTu.getSoGiayKhaiTu());
            preparedStatement.setInt(2, khaiTu.getIdNguoiKhai());
            preparedStatement.setInt(3, khaiTu.getIdNguoiChet());
            Date ngayKhai = new Date(khaiTu.getNgayKhai().getTime());
            preparedStatement.setDate(4, ngayKhai);
            Date ngayChet = new Date(khaiTu.getNgayChet().getTime());
            preparedStatement.setDate(5, ngayChet);           
            preparedStatement.setString(6, khaiTu.getLyDoChet());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("cảnh báo");
    		alert.setContentText("Có lỗi xảy ra! Vui lòng thử lại");
    		alert.showAndWait();
        }
    }


    public List<NhanKhauBean> statisticNhanKhau(int TuTuoi, int denTuoi, String gender, String Status, int tuNam, int denNam) {
        List<NhanKhauBean> list = new ArrayList<>();

        String query = "SELECT * FROM nhan_khau "
                + " INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau"
                + " LEFT JOIN tam_tru ON nhan_khau.ID = tam_tru.idNhanKhau "
                + " LEFT JOIN tam_vang ON nhan_khau.ID = tam_vang.idNhanKhau "
                + " WHERE ROUND(DATEDIFF(CURDATE(),namSinh)/365 , 0) >= "
                + TuTuoi
                + " AND ROUND(DATEDIFF(CURDATE(),namSinh)/365 , 0) <= "
                + denTuoi;
        if (!gender.equalsIgnoreCase("Toan Bo")) {
            query += " AND nhan_khau.gioiTinh = '" + gender + "'";
        }
        if (Status.equalsIgnoreCase("Toan bo")) {
            query += " AND (tam_tru.denNgay >= CURDATE() OR tam_tru.denNgay IS NULL)"
                    + " AND (tam_vang.denNgay <= CURDATE() OR tam_vang.denNgay IS NULL)";
        } else if (Status.equalsIgnoreCase("Thuong tru")) {
            query += " AND tam_tru.denNgay IS NULL";

        } else if (Status.equalsIgnoreCase("Tam tru")) {
            query += " AND (YEAR(tam_tru.tuNgay) BETWEEN "
                    + tuNam
                    + " AND "
                    + denNam
                    + ")";
        } else if (Status.equalsIgnoreCase("Tam vang")) {
            query += " AND (YEAR(tam_vang.tuNgay) BETWEEN "
                    + tuNam
                    + " AND "
                    + denNam
                    + ")";
        }
        query += " ORDER BY ngayTao DESC";
        try {
            Connection connection = MySqlConnection.getMySqlConnection();
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int idNhanKhau = -1;
            while (rs.next()){
                NhanKhauBean nhanKhauBean = new NhanKhauBean();
                NhanKhau nhanKhau = nhanKhauBean.getNhanKhau();
                ChungMinhThu chungMinhThu = nhanKhauBean.getChungMinhThu();
                idNhanKhau = rs.getInt("idNhanKhau");
                nhanKhau.setID(idNhanKhau);
                nhanKhau.setBietDanh(rs.getString("bietDanh"));
                nhanKhau.setHoTen(rs.getString("hoTen"));
                nhanKhau.setGioiTinh(rs.getString("gioiTinh"));
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setNguyenQuan(rs.getString("nguyenQuan"));
                nhanKhau.setTonGiao(rs.getString("tonGiao"));
                nhanKhau.setDanToc(rs.getString("danToc"));
                nhanKhau.setQuocTich(rs.getString("quocTich"));
                nhanKhau.setSoHoChieu(rs.getString("soHoChieu"));
                nhanKhau.setNoiThuongTru(rs.getString("noiThuongTru"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                // con nhieu nua
                chungMinhThu.setIdNhanKhau(rs.getInt("idNhanKhau"));
                chungMinhThu.setSoCMT(rs.getString("soCMT"));
                chungMinhThu.setNgayCap(rs.getDate("ngayCap"));
                chungMinhThu.setNoiCap(rs.getString("noiCap"));

                if (idNhanKhau > 0) {
                    String sql = "SELECT * FROM tieu_su WHERE idNhanKhau = " + idNhanKhau;
                    PreparedStatement prst = (PreparedStatement)connection.prepareStatement(sql);
                    ResultSet rs_temp = prst.executeQuery();
                    List<TieuSu> listTieuSu = new ArrayList<>();
                    while (rs_temp.next()) {
                        TieuSu tieuSu = new TieuSu();
                        tieuSu.setID(rs_temp.getInt("ID"));
                        tieuSu.setIdNhanKhau(rs_temp.getInt("idNhanKhau"));
                        tieuSu.setTuNgay(rs_temp.getDate("tuNgay"));
                        tieuSu.setDenNgay(rs_temp.getDate("denNgay"));
                        tieuSu.setNgheNghiep(rs_temp.getString("ngheNghiep"));
                        tieuSu.setNoiLamViec(rs_temp.getString("noiLamViec"));
                        listTieuSu.add(tieuSu);
                    }
                    nhanKhauBean.setListTieuSu(listTieuSu);
                    prst.close();

                    sql = "SELECT * FROM gia_dinh WHERE idNhanKhau = " + idNhanKhau;
                    prst = (PreparedStatement)connection.prepareStatement(sql);
                    rs_temp = prst.executeQuery();
                    List<GiaDinh> listGiaDinh = new ArrayList<>();
                    while (rs_temp.next()) {
                        GiaDinh giaDinh = new GiaDinh();
                        giaDinh.setID(rs_temp.getInt("ID"));
                        giaDinh.setHoTen(rs_temp.getString("hoTen"));
                        giaDinh.setNamSinh(rs_temp.getDate("namSinh"));
                        giaDinh.setGioiTinh(rs_temp.getString("gioiTinh"));
                        giaDinh.setIdNhanKhau(rs_temp.getInt("idNhanKhau"));
                        giaDinh.setDiaChiHienTai(rs_temp.getString("diaChiHienTai"));
                        giaDinh.setNgheNghiep(rs_temp.getString("ngheNghiep"));
                        giaDinh.setQuanHeVoiNhanKhau(rs_temp.getString("quanHeVoiNhanKhau"));
                        listGiaDinh.add(giaDinh);
                    }
                    nhanKhauBean.setListGiaDinh(listGiaDinh);
                    prst.close();
                }
                list.add(nhanKhauBean);
            }
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    
}
