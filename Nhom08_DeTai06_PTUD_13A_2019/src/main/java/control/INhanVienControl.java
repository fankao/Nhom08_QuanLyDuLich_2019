package control;

import entities.NhanVien;
import entities.TaiKhoan;

public interface INhanVienControl {
	public NhanVien layNhanVienTheoMa(String maNV);

	public NhanVien layNhanVienTheoTaiKhoan(TaiKhoan tk);
}
