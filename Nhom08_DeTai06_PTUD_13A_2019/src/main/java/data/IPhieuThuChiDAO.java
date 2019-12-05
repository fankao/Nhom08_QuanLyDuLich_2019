package data;

import java.util.List;

import entities.LoaiPhieu;
import entities.PhieuThuChi;

public interface IPhieuThuChiDAO {
	public String phatSinhMaPhieu(LoaiPhieu loaiPhieu);

	public PhieuThuChi themPhieu(PhieuThuChi p);

	public PhieuThuChi suaPhieu(PhieuThuChi p);

	public PhieuThuChi layThongTinPhieu(String ma);

	public List<PhieuThuChi> layDSPhieuTheoThang(int thang);

	public List<PhieuThuChi> layDSPhieuThuTheoPhieuDK(String maPDK);

	public List<PhieuThuChi> layDSPhieuChiTheoPhieuDK(String maPDK);

}
