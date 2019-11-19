package control.impl;

import java.util.List;

import control.IPhieuDangKyControl;
import data.IPhieuDangKyDAO;
import data.impl.PhieuDangKyDAOImpl;
import entities.PhieuDangKy;

public class PhieuDangKyControlImpl implements IPhieuDangKyControl{
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

}
