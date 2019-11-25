package entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/********************************************
 * LichTrinh.java
 * 
 * @author Minh Chiến<br>
 *         Ngày tạo: 16/11/2019
 * 
 ********************************************
 */
@Entity
@Table(name = "lichtrinh")
public class NgayKhoiHanh {
	@Id
	@Column(columnDefinition = "VARCHAR(20)")
	private String maLT;
	private Date ngayKhoiHanh;
	private int soNguoiThamGia;
	private boolean daXoa;
	private boolean daDuSoLuong;
	private boolean daXoaDoKhongDuSoLuong;

	public NgayKhoiHanh() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maLT == null) ? 0 : maLT.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NgayKhoiHanh other = (NgayKhoiHanh) obj;
		if (maLT == null) {
			if (other.maLT != null)
				return false;
		} else if (!maLT.equals(other.maLT))
			return false;
		return true;
	}

	public String getMaLT() {
		return maLT;
	}

	public void setMaLT(String maLT) {
		this.maLT = maLT;
	}

	public Date getNgayKhoiHanh() {
		return ngayKhoiHanh;
	}

	public void setNgayKhoiHanh(Date ngayKhoiHanh) {
		this.ngayKhoiHanh = ngayKhoiHanh;
	}

	public int getSoNguoiThamGia() {
		return soNguoiThamGia;
	}

	public void setSoNguoiThamGia(int soNguoiThamGia) {
		this.soNguoiThamGia = soNguoiThamGia;
	}

	public boolean isDaXoa() {
		return daXoa;
	}

	public void setDaXoa(boolean daXoa) {
		this.daXoa = daXoa;
	}

	public boolean isDaDuSoLuong() {
		return daDuSoLuong;
	}

	public void setDaDuSoLuong(boolean daDuSoLuong) {
		this.daDuSoLuong = daDuSoLuong;
	}

	public boolean isDaXoaDoKhongDuSoLuong() {
		return daXoaDoKhongDuSoLuong;
	}

	public void setDaXoaDoKhongDuSoLuong(boolean daXoaDoKhongDuSoLuong) {
		this.daXoaDoKhongDuSoLuong = daXoaDoKhongDuSoLuong;
	}

	public NgayKhoiHanh(String maLT, Date ngayKhoiHanh, int soNguoiThamGia, boolean daXoa, boolean daDuSoLuong,
			boolean daXoaDoKhongDuSoLuong) {
		super();
		this.maLT = maLT;
		this.ngayKhoiHanh = ngayKhoiHanh;
		this.soNguoiThamGia = soNguoiThamGia;
		this.daXoa = daXoa;
		this.daDuSoLuong = daDuSoLuong;
		this.daXoaDoKhongDuSoLuong = daXoaDoKhongDuSoLuong;
	}

	@Override
	public String toString() {
		return "LichTrinh [maLT=" + maLT + ", ngayKhoiHanh=" + ngayKhoiHanh + "]";
	}

}
