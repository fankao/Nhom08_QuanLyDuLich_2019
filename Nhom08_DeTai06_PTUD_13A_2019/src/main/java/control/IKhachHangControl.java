package control;

import java.util.List;

import entities.KhachHang;

public interface IKhachHangControl {
	public List<KhachHang> layDSKhachHang();
	public KhachHang themKhachHang(KhachHang kh);
	public List<KhachHang> layDSKhachHangTheoYeuCau(int yc,String tim);
}
