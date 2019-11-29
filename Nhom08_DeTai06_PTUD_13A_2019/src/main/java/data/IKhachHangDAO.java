package data;

import java.util.List;

import entities.KhachHang;

public interface IKhachHangDAO {
	public KhachHang themKhachHang(KhachHang kh);

	public KhachHang suaKhachHang(KhachHang kh);
	
	public String phatSinhMaKH();

	public List<KhachHang> layDSKhachHang();

	public KhachHang layDSKhachHangTheoTen(String ten);

	public KhachHang layTTKhachHangTheoSDT(String sdt);

	public KhachHang layTTKhachHangTheoCMND(String cmnd);
}
