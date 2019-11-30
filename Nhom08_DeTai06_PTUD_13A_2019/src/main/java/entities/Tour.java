package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "tour")
@NamedQueries({ @NamedQuery(name = "Tour.timDSTour", query = "SELECT t FROM Tour t ORDER BY t.id"),
		@NamedQuery(name = "Tour.timDSTourDaXoa", query = "SELECT t FROM Tour t WHERE t.daXoa=True ORDER BY t.id"),
		@NamedQuery(name = "Tour.timDsTourChuaDuyet", query = "SELECT t FROM Tour t WHERE t.daXoa=:daXoa AND t.daDuyet=:daDuyet ORDER BY t.id"),
		@NamedQuery(name = "Tour.timDsTourDaDuyet", query = "SELECT t FROM Tour t WHERE t.daXoa=:daXoa AND t.daDuyet=:daDuyet ORDER BY t.id"),
		@NamedQuery(name = "Tour.timDsTourTheoNhanVien", query = "SELECT t FROM Tour t WHERE t.daXoa=:daXoa AND daDuyet=False AND t.nhanVien.maNV=:manv ORDER BY t.id"), })
public class Tour implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(columnDefinition = "VARCHAR(20)", unique = true, nullable = false)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tour")
	private List<NgayKhoiHanh> ngayKhoiHanh = new ArrayList<NgayKhoiHanh>();

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
		super();
	}

	public Tour(int id, String maTour, String tenTour, String moTa, double donGiaNguoiLon, double donGiaTreEm,
			NhanVien nhanVien, DiaDanh diaDanh, List<NgayKhoiHanh> ngayKhoiHanh, int[] thoiGian, PhuongTien phuongTien,
			String diemKhoiHanh, String diemDen, boolean daDuyet, boolean daXoa) {
		super();
		this.id = id;
		this.maTour = maTour;
		this.tenTour = tenTour;
		this.moTa = moTa;
		this.donGiaNguoiLon = donGiaNguoiLon;
		this.donGiaTreEm = donGiaTreEm;
		this.nhanVien = nhanVien;
		this.diaDanh = diaDanh;
		this.ngayKhoiHanh = ngayKhoiHanh;
		this.thoiGian = thoiGian;
		this.phuongTien = phuongTien;
		this.diemKhoiHanh = diemKhoiHanh;
		this.diemDen = diemDen;
		this.daDuyet = daDuyet;
		this.daXoa = daXoa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<NgayKhoiHanh> getNgayKhoiHanh() {
		return ngayKhoiHanh;
	}

	public void setNgayKhoiHanh(List<NgayKhoiHanh> ngayKhoiHanh) {
		this.ngayKhoiHanh = ngayKhoiHanh;
	}

	public int[] getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(int[] thoiGian) {
		this.thoiGian = thoiGian;
	}

	public PhuongTien getPhuongTien() {
		return phuongTien;
	}

	public void setPhuongTien(PhuongTien phuongTien) {
		this.phuongTien = phuongTien;
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

	public boolean isDaDuyet() {
		return daDuyet;
	}

	public void setDaDuyet(boolean daDuyet) {
		this.daDuyet = daDuyet;
	}

	public boolean isDaXoa() {
		return daXoa;
	}

	public void setDaXoa(boolean daXoa) {
		this.daXoa = daXoa;
	}

}
