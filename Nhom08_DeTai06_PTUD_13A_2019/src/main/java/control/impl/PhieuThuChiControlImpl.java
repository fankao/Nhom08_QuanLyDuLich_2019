package control.impl;

import java.util.List;

import control.IPhieuThuChiControl;
import data.IPhieuThuChiDAO;
import data.impl.PhieuThuChiDAOimpl;
import entities.LoaiPhieu;
import entities.PhieuThuChi;

public class PhieuThuChiControlImpl implements IPhieuThuChiControl {
	private IPhieuThuChiDAO phieuThuChiDAO;

	public PhieuThuChiControlImpl() {
		phieuThuChiDAO = new PhieuThuChiDAOimpl();
	}

	@Override
	public String phatSinhMaPhieu(LoaiPhieu loaiPhieu) {
		// TODO Auto-generated method stub
		return phieuThuChiDAO.phatSinhMaPhieu(loaiPhieu);
	}

	@Override
	public PhieuThuChi themPhieu(PhieuThuChi p) {
		// TODO Auto-generated method stub
		return phieuThuChiDAO.themPhieu(p);
	}

	@Override
	public PhieuThuChi layThongTinPhieu(String ma) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PhieuThuChi> layDSPhieu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PhieuThuChi> layDSPhieuTheoPhieuDK(String maPDK) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhieuThuChi suaPhieu(PhieuThuChi p) {
		// TODO Auto-generated method stub
		return phieuThuChiDAO.suaPhieu(p);
	}

	@Override
	public double tinhTongTienPhieuThuTheoPDK(String maPDK) {
		List<PhieuThuChi> list = phieuThuChiDAO.layDSPhieuThuTheoPhieuDK(maPDK);
		double tongTienPhieuThu = 0;
		if (list.size() != 0) {
			for (PhieuThuChi pt : list) {
				tongTienPhieuThu += pt.getSoTien();
			}
		}
		return tongTienPhieuThu;
	}

	@Override
	public double tinhTongTienPhieuPhieuChiTheoPDK(String maPDK) {
		List<PhieuThuChi> list = phieuThuChiDAO.layDSPhieuChiTheoPhieuDK(maPDK);
		double tongTienPhieuChi = 0;
		if (list.size() != 0) {
			for (PhieuThuChi pt : list) {
				tongTienPhieuChi += pt.getSoTien();
			}
		}
		return tongTienPhieuChi;
	}

}
