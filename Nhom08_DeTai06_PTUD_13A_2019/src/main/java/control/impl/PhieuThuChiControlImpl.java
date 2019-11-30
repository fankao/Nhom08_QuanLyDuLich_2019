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

}
