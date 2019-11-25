package entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tour")
@NamedQueries({ @NamedQuery(name = "Tour.timDsTour", query = "SELECT t FROM Tour t WHERE t.daXoa=:daXoa"),
		@NamedQuery(name = "Tour.timDsTourDaDuyet", query = "SELECT t FROM Tour t WHERE t.daXoa=:daXoa AND t.daDuyet=:daDuyet"),
		@NamedQuery(name = "Tour.timDsTourTheoNhanVien", query = "SELECT t FROM Tour t WHERE t.daXoa=:daXoa AND t.nhanVien.maNV=:manv") })
public class Tour implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(columnDefinition = "VARCHAR(20)")
	private String maTour;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String tenTour;

	@Column(columnDefinition = "NVARCHAR(255)")
	private String moTa;

	@Column(columnDefinition = "MONEY")
	private double donGiaNguoiLon;

	@Column(columnDefinition = "MONEY")
	private double donGiaTreEm;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "manhanvien", nullable = false, updatable = true)
	private NhanVien nhanVien;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "madiadanh", nullable = false, updatable = true)
	private DiaDanh diaDanh;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "matour", updatable = true)
	private Set<NgayKhoiHanh> ngayKhoiHanh;

	private int[] thoiGian = new int[2];

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "NVARCHAR(100)")
	private PhuongTien phuongTien;

	@Column(columnDefinition = "NVARCHAR(255)")
	private String diemKhoiHanh;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String diemDen;

	private boolean daDuyet;
	private boolean daXoa;

	public Tour() {
		// TODO Auto-generated constructor stub
	}

	public String getMaTour() {
		return maTour;
	}

	public void setMaTour(String maTour) {
		this.maTour = maTour;
	}

	public String getTenTour() {
		return tenTour;
	}

	public void setTenTour(String tenTour) {
		this.tenTour = tenTour;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public double getDonGiaNguoiLon() {
		return donGiaNguoiLon;
	}

	public void setDonGiaNguoiLon(double donGiaNguoiLon) {
		this.donGiaNguoiLon = donGiaNguoiLon;
	}

	public double getDonGiaTreEm() {
		return donGiaTreEm;
	}

	public void setDonGiaTreEm(double donGiaTreEm) {
		this.donGiaTreEm = donGiaTreEm;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public DiaDanh getDiaDanh() {
		return diaDanh;
	}

	public void setDiaDanh(DiaDanh diaDanh) {
		this.diaDanh = diaDanh;
	}

	public PhuongTien getPhuongTien() {
		return phuongTien;
	}

	public void setPhuongTien(PhuongTien phuongTien) {
		this.phuongTien = phuongTien;
	}

	public Set<NgayKhoiHanh> getNgayKhoiHanh() {
		return ngayKhoiHanh;
	}

	public void setNgayKhoiHanh(Set<NgayKhoiHanh> ngayKhoiHanh) {
		this.ngayKhoiHanh = ngayKhoiHanh;
	}

	public String getDiemKhoiHanh() {
		return diemKhoiHanh;
	}

	public void setDiemKhoiHanh(String diemKhoiHanh) {
		this.diemKhoiHanh = diemKhoiHanh;
	}

	public String getDiemDen() {
		return diemDen;
	}

	public void setDiemDen(String diemDen) {
		this.diemDen = diemDen;
	}

	public boolean isDaXoa() {
		return daXoa;
	}

	public void setDaXoa(boolean daXoa) {
		this.daXoa = daXoa;
	}

	public int[] getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(int[] thoiGian) {
		this.thoiGian = thoiGian;
	}

	public boolean isDaDuyet() {
		return daDuyet;
	}

	public void setDaDuyet(boolean daDuyet) {
		this.daDuyet = daDuyet;
	}

	@Override
	public String toString() {
		return "Tour [maTour=" + maTour + ", tenTour=" + tenTour + "]";
	}
}
