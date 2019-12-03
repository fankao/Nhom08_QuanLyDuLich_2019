package control;

import java.util.List;

import entities.LoaiPhieu;
import entities.PhieuThuChi;

public interface IPhieuThuChiControl {
	public String phatSinhMaPhieu(LoaiPhieu loaiPhieu);

	public PhieuThuChi themPhieu(PhieuThuChi p);

	public PhieuThuChi layThongTinPhieu(String ma);

	public PhieuThuChi suaPhieu(PhieuThuChi p);

	public List<PhieuThuChi> layDSPhieu();

	public List<PhieuThuChi> layDSPhieuTheoPhieuDK(String maPDK);
}
