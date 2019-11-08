package data;

import java.util.List;

import entities.KhachHang;

public interface KhachHangDAO {

 	public KhachHang layTTKhachHangTheoMa(String ma);

	public List<KhachHang> layDsKhachHang();
}
