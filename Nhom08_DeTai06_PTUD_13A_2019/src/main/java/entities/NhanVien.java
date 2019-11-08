package entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "nhanvien")
@NamedQueries({
		@NamedQuery(name = "nv.timTheoTaiKhoan", query = "SELECT nv FROM NhanVien nv WHERE nv.taiKhoan.userName=:user AND nv.taiKhoan.passWord=:pass") })
public class NhanVien {
	@Id
	private String maNV;
	private String soCMND;
	private String hoVaTen;
	private Date ngaySinh;
	private String soDienThoai;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "quyen")
	private Quyen quyen;

	@Embedded
	private DiaChi diaChi;

	@Embedded
	private TaiKhoan taiKhoan;

	public NhanVien() {

	}

	public NhanVien(String maNV, String soCMND, String hoVaTen, Date ngaySinh, String soDienThoai) {
		super();
		this.maNV = maNV;
		this.soCMND = soCMND;
		this.hoVaTen = hoVaTen;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
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

	public Quyen getQuyen() {
		return quyen;
	}

	public void setQuyen(Quyen quyen) {
		this.quyen = quyen;
	}

	public DiaChi getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(DiaChi diaChi) {
		this.diaChi = diaChi;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", soCMND=" + soCMND + ", hoVaTen=" + hoVaTen + ", ngaySinh=" + ngaySinh
				+ ", soDienThoai=" + soDienThoai + ", quyen=" + quyen + ", diaChi=" + diaChi + ", taiKhoan=" + taiKhoan
				+ "]";
	}

}
