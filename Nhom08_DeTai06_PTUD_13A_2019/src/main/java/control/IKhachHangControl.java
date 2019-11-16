package control;

import java.util.List;

import entities.KhachHang;

public interface IKhachHangControl {
	public List<KhachHang> layDSKhachHang();
	public List<KhachHang> layDSKhachHangTheoYeuCau(int yc,String tim);
}
