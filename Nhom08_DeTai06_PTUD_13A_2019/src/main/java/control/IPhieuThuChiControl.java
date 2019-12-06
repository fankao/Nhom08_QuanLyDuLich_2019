package control;

import java.util.List;

import entities.LoaiPhieu;
import entities.PhieuThuChi;

public interface IPhieuThuChiControl {
	public String phatSinhMaPhieu(LoaiPhieu loaiPhieu);

	public PhieuThuChi themPhieu(PhieuThuChi p);

	public PhieuThuChi layThongTinPhieu(String ma);

	public PhieuThuChi suaPhieu(PhieuThuChi p);


	public List<PhieuThuChi> layDSPhieuTheoPhieuDK(String maPDK);
	public List<PhieuThuChi> layDSPhieuTheoThang(int thang);
	
	public double tinhTongTienPhieuThuTheoPDK(String maPDK);
	
	public double tinhTongTienPhieuChiTheoPDK(String maPDK);
}
