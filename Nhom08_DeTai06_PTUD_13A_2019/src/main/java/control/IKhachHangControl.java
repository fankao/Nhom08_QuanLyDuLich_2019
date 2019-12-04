package control;

import java.util.List;

import entities.KhachHang;

public interface IKhachHangControl {
	public List<KhachHang> layDSKhachHang();

	public KhachHang themKhachHang(KhachHang kh);

	public KhachHang layTTKhachHangTheoYeuCau(int yc, String tim);
	
	public KhachHang suaKhachHang(KhachHang kh);
}
