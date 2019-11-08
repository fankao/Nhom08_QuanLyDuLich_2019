package data;

import java.util.List;

import entities.KhachHang;

public interface IKhachHangDAO {
	public List<KhachHang> layDSKhachHangTheoTen(String ten);

	public List<KhachHang> layTTKhachHangTheoSDT(String sdt);

	public List<KhachHang> layTTKhachHangTheoCMND(String cmnd);
}
