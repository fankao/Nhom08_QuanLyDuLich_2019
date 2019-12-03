package data;

import java.util.List;

import entities.LoaiPhieu;
import entities.PhieuThuChi;

public interface IPhieuThuChiDAO {
	public String phatSinhMaPhieu(LoaiPhieu loaiPhieu);

	public PhieuThuChi themPhieu(PhieuThuChi p);

	public PhieuThuChi suaPhieu(PhieuThuChi p);

	public PhieuThuChi layThongTinPhieu(String ma);

	public List<PhieuThuChi> layDSPhieu();

	public List<PhieuThuChi> layDSPhieuTheoPhieuDK(String maPDK);

}
