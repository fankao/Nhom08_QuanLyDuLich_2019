package data;

import java.util.List;

import entities.PhieuDangKy;

public interface IPhieuDangKyDAO {
	public List<PhieuDangKy> layDSPhieuDangKy();

	public List<PhieuDangKy> layDSPhieuDangKyTheoKH(String maKH);

	public List<PhieuDangKy> layDSPhieuDangKyTheoTour(String maTour);

	public PhieuDangKy themPhieuDangKy(PhieuDangKy pdk);

	public PhieuDangKy suaPhieuDangKy(PhieuDangKy pdk);

	public PhieuDangKy layTTPhieuDangKyTheoMa(String maPDK);

}
