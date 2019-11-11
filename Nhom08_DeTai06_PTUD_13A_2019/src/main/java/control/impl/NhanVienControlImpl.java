package control.impl;

import control.INhanVienControl;
import data.INhanVienDAO;
import data.impl.NhanVienDAOImpl;
import entities.NhanVien;
import entities.TaiKhoan;

/**
 * NhanVienControl.java
 * 
 * @author Minh Chiến, Ngày tạo :8/11/2019
 */
public class NhanVienControlImpl implements INhanVienControl {
	private INhanVienDAO nhanVienDAO;

	/**
	 * Constructo khởi tạo đối tượng NhanVienControl
	 */
	public NhanVienControlImpl() {
		nhanVienDAO = new NhanVienDAOImpl();
	}

	/**
	 * Lấy thông tin nhân viên theo mã 
	 * @return tk: đối tượng nhân viên vừa tìm
	 */
	public NhanVien layNhanVienTheoMa(String maNV) {
		return nhanVienDAO.layNhanVienTheoMa(maNV);
	}

	/**
	 * Lấy thông tin nhân viên theo tài khoản
	 * 
	 * @param tk: tài khoản truyền vào, 
	 * @return nv: đối tượng nhân viên vừa tìm
	 */
	@Override
	public NhanVien layNhanVienTheoTaiKhoan(TaiKhoan tk) {
		return nhanVienDAO.layNhanVienTheoTaiKhoan(tk);
	}

}
