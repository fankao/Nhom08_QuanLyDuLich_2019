package entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "khachhang")
@NamedQueries({ @NamedQuery(name = "KH.timTatCaKH", query = "SELECT kh FROM KhachHang kh"),
		@NamedQuery(name = "KH.timTheoTenKH", query = "SELECT kh FROM KhachHang kh WHERE kh.hoVaTen LIKE :ten"),
		@NamedQuery(name = "KH.timTheoSDT", query = "SELECT kh FROM KhachHang kh WHERE kh.soDienThoai LIKE :sdt"),
		@NamedQuery(name = "KH.timTheoCMND", query = "SELECT kh FROM KhachHang kh WHERE kh.soCMND LIKE :cmnd") })
public class KhachHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition = "VARCHAR(20)", unique = true)
	private String maKH;

	private String soCMND;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String hoVaTen;
	private Date ngaySinh;
	@Column(columnDefinition = "VARCHAR(20)")
	private String soDienThoai;
	@Embedded
	private DiaChi diaChi;

	public KhachHang() {
		// TODO Auto-generated constructor stub
	}

	public KhachHang(String maKH, String soCMND, String hoVaTen, Date ngaySinh, String soDienThoai) {
		super();
		this.maKH = maKH;
		this.soCMND = soCMND;
		this.hoVaTen = hoVaTen;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getSoCMND() {
		return soCMND;
	}

	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public DiaChi getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(DiaChi diaChi) {
		this.diaChi = diaChi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maKH == null) ? 0 : maKH.hashCode());
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
		KhachHang other = (KhachHang) obj;
		if (maKH == null) {
			if (other.maKH != null)
				return false;
		} else if (!maKH.equals(other.maKH))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return String.format("%-20s%-20s%s", hoVaTen, soDienThoai, soCMND);
	}
}
