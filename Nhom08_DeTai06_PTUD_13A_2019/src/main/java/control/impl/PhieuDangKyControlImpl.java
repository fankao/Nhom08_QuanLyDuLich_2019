package control.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.IPhieuDangKyControl;
import data.IPhieuDangKyDAO;
import data.impl.PhieuDangKyDAOImpl;
import entities.KhachHangThamGia;
import entities.PhieuDangKy;

public class PhieuDangKyControlImpl implements IPhieuDangKyControl {
	private IPhieuDangKyDAO phieuDangKyDAO;

	public PhieuDangKyControlImpl() {
		// TODO Auto-generated constructor stub
		phieuDangKyDAO = new PhieuDangKyDAOImpl();
	}

	@Override
	public List<PhieuDangKy> layDSPhieuDangKy() {
		// TODO Auto-generated method stub
		return phieuDangKyDAO.layDSPhieuDangKy();
	}

	@Override
	public List<PhieuDangKy> layDSPhieuDangKyTheoKH(String maKH) {
		// TODO Auto-generated method stub
		return phieuDangKyDAO.layDSPhieuDangKyTheoKH(maKH);
	}

	@Override
	public List<PhieuDangKy> layDSPhieuDangKyTheoTour(String maTour) {
		// TODO Auto-generated method stub
		return phieuDangKyDAO.layDSPhieuDangKyTheoTour(maTour);
	}

	@Override
	public PhieuDangKy themPhieuDangKy(PhieuDangKy pdk) {
		// TODO Auto-generated method stub
		return phieuDangKyDAO.themPhieuDangKy(pdk);
	}

	@Override
	public PhieuDangKy suaPhieuDangKy(PhieuDangKy pdk) {
		// TODO Auto-generated method stub
		return phieuDangKyDAO.suaPhieuDangKy(pdk);
	}

	@Override
	public PhieuDangKy layTTPhieuDangKyTheoMa(String maPDK) {
		// TODO Auto-generated method stub
		return phieuDangKyDAO.layTTPhieuDangKyTheoMa(maPDK);
	}

	@Override
	public List<KhachHangThamGia> layDSKhachThamGiaTour(String maTour) {
		// TODO Auto-generated method stub
		return phieuDangKyDAO.layDSKhachThamGiaTour(maTour);
	}

	/**
	 * Cập nhật trạng thái đã huỷ cho các phiếu đăng ký không đủ số lượng khách tham
	 * gia
	 */
	@Override
	public List<PhieuDangKy> capNhatTrangThaiDangKyTour(int yeuCau, List<PhieuDangKy> pdks) {
		List<PhieuDangKy> dsPhieuLoc = new ArrayList<PhieuDangKy>();
		for (PhieuDangKy p : pdks) {
			switch (yeuCau) {
			case 1:
				if (p.getNgayKhoiHanh().capNhatNgayKhoiHanhKhongDuSoLuong(p.getNgayKhoiHanh()) != null) {
					PhieuDangKy pdkHuy = suaPhieuDangKy(p);
					dsPhieuLoc.add(pdkHuy);
				}
				break;
			case 2:
				Date date = new Date(p.getNgayKhoiHanh().getNgayKhoiHanh().getTime());
				LocalDate ngayKH = date.toLocalDate();
				if (ngayKH.isEqual(LocalDate.now())
						&& p.getNgayKhoiHanh().capNhatNgayKhoiHanhKhongDuSoLuong(p.getNgayKhoiHanh()) == null
						&& p.isDaHuyPhieu() == false) {
					p.setDaHoanThanhTour(true);
					dsPhieuLoc.add(p);
				}
				break;

			default:
				break;
			}

		}
		return dsPhieuLoc;
	}

	@Override
	public PhieuDangKy layPhieuDangKyTheoKHVaNgayKH(String maKH, String maNGKH) {
		// TODO Auto-generated method stub
		return phieuDangKyDAO.layPhieuDangKyTheoKHVaNgayKH(maKH, maNGKH);
	}

	@Override
	public List<PhieuDangKy> layDSPhieuDangKyTheoThang(int thang) {

		return phieuDangKyDAO.layDSPhieuDangKyTheoThang(thang);
	}
}
