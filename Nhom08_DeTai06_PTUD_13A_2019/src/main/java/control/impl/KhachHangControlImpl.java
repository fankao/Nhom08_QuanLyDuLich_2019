package control.impl;

import java.util.List;

import control.IKhachHangControl;
import data.IKhachHangDAO;
import data.impl.KhachHangDAOImpl;
import entities.KhachHang;

public class KhachHangControlImpl implements IKhachHangControl {
	private IKhachHangDAO khachHangDAO;

	public KhachHangControlImpl() {
		// TODO Auto-generated constructor stub
		khachHangDAO = new KhachHangDAOImpl();
	}

	/**
	 * Lấy danh sách khách hàng
	 * 
	 * @return List
	 */
	@Override
	public List<KhachHang> layDSKhachHang() {
		// TODO Auto-generated method stub
		return khachHangDAO.layDSKhachHang();
	}

	/**
	 * Lấy danh sách khách hàng theo yêu cầu
	 * 
	 * @param yc
	 * @param tim <br>
	 *            yc = 1, tim = tenKhachHang, Lấy danh sách khách hàng theo tên<br>
	 *            yc = 2, tim = sdt, Lấy danh sách khách hàng theo SĐT<br>
	 *            yc = 3, tim = CMND, Lấy danh sách khách hàng theo số CMDN<br>
	 * @return List
	 */
	@Override
	public KhachHang layTTKhachHangTheoYeuCau(int yc, String tim) {
		// TODO Auto-generated method stub
		switch (yc) {
		case 1:
			return khachHangDAO.layDSKhachHangTheoTen(tim);
		case 2:
			return khachHangDAO.layTTKhachHangTheoSDT(tim);
		case 3:
			return khachHangDAO.layTTKhachHangTheoCMND(tim);
		}
		return null;
	}

	@Override
	public KhachHang themKhachHang(KhachHang kh) {

		return khachHangDAO.themKhachHang(kh);
	}

	@Override
	public KhachHang suaKhachHang(KhachHang kh) {
		// TODO Auto-generated method stub
		return khachHangDAO.suaKhachHang(kh);
	}

}
