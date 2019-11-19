package entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@NamedQueries({ @NamedQuery(name = "Tour.timDsTour", query = "SELECT t FROM Tour t ") })
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
	@JoinColumn(name = "malichtrinh", updatable = true)
	private Set<LichTrinh> lichTrinhs;
	
	private boolean daXoa;

	public Tour() {
		// TODO Auto-generated constructor stub
	}

	public Tour(String maTour, String tenTour, String moTa, double donGiaNguoiLon, double donGiaTreEm, boolean daXoa,
			NhanVien nhanVien, DiaDanh diaDanh) {
		super();
		this.maTour = maTour;
		this.tenTour = tenTour;
		this.moTa = moTa;
		this.donGiaNguoiLon = donGiaNguoiLon;
		this.donGiaTreEm = donGiaTreEm;
		this.daXoa = daXoa;
		this.nhanVien = nhanVien;
		this.diaDanh = diaDanh;
	}

	public Tour(String maTour, Set<LichTrinh> lichTrinhs) {
		super();
		this.maTour = maTour;
		this.lichTrinhs = lichTrinhs;
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

	public boolean isDaXoa() {
		return daXoa;
	}

	public void setDaXoa(boolean daXoa) {
		this.daXoa = daXoa;
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

	public Set<LichTrinh> getLichTrinhs() {
		return lichTrinhs;
	}

	public void setLichTrinhs(Set<LichTrinh> lichTrinhs) {
		this.lichTrinhs = lichTrinhs;
	}

	@Override
	public String toString() {
		return "Tour [maTour=" + maTour + ", tenTour=" + tenTour + "]";
	}

}
