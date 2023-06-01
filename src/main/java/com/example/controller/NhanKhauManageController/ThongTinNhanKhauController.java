package com.example.controller.NhanKhauManageController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.example.services.NhanKhauService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class ThongTinNhanKhauController {

    @FXML
    private Label bietDanhLb;

    @FXML
    private Text bietTiengDanTocT;

    @FXML
    private Text danTocT;

    @FXML
    private Text gioiTinhT;

    @FXML
    private Label hoTenLb;

    @FXML
    private Text ngaySinhT;

    @FXML
    private Text ngheNghiepT;

    @FXML
    private Text nguyenQuanT;

    @FXML
    private Text noiLamViecT;

    @FXML
    private Text noiThuongTruT;

    @FXML
    private Text queQuanT;
    
    @FXML
    private Text quocTichT;

    @FXML
    private Text soCMTT;

    @FXML
    private Text soHoChieuT;

    @FXML
    private Text tdChuyenMonT;

    @FXML
    private Text tdHocVanT;

    @FXML
    private Text tdNgoaiNguT;

    @FXML
    private Text tonGiaoT;
	
	public void showInforByID(int id) {
		NhanKhauService conn = new NhanKhauService();
		ResultSet rs = conn.getNhanKhauByID(id);
		try {
			if (rs != null) {
				while (rs.next()) {
					this.bietDanhLb.setText(rs.getString("bietDanh")); 
					this.bietTiengDanTocT.setText(rs.getString("bietTiengDanToc")); 
					this.danTocT.setText(rs.getString("danToc"));
					this.gioiTinhT.setText(rs.getString("gioiTinh"));
					this.hoTenLb.setText(rs.getString("hoTen"));
					DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
					this.ngaySinhT.setText(dateFormat.format(rs.getDate("namSinh")));
					this.ngheNghiepT.setText(rs.getString("ngheNghiep"));
					this.nguyenQuanT.setText(rs.getString("nguyenQuan"));
					this.noiLamViecT.setText(rs.getString("noiLamViec"));
					this.noiThuongTruT.setText(rs.getString("noiThuongTru"));
					this.queQuanT.setText(rs.getString("diaChiHienNay"));
					this.quocTichT.setText(rs.getString("quocTich"));
					this.soCMTT.setText(rs.getString("soCMT"));
					this.soHoChieuT.setText(rs.getString("soHoChieu"));
					this.tdChuyenMonT.setText(rs.getString("TrinhDoChuyenMon"));
					this.tdHocVanT.setText(rs.getString("trinhDoHocVan"));
					this.tdNgoaiNguT.setText(rs.getString("trinhDoNgoaiNgu"));
					this.tonGiaoT.setText(rs.getString("tonGiao"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
