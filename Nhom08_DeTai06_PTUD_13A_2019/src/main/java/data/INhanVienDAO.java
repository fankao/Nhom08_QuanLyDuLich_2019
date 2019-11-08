package data;

import entities.NhanVien;
import entities.TaiKhoan;

public interface INhanVienDAO {
	public NhanVien layNhanVienTheoMa(String maNV);

	public NhanVien layNhanVienTheoTaiKhoan(TaiKhoan tk);
}
