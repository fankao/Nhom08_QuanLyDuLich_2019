package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "phieudangky")
@NamedQueries({ @NamedQuery(name = "PDK.timDSPDK", query = "SELECT pdk FROM PhieuDangKy pdk"),
		@NamedQuery(name = "PDK.timDSPDKTheoKH", query = "SELECT pdk FROM PhieuDangKy pdk WHERE pdk.kh.maKH = :makh"),
		@NamedQuery(name = "PDK.timDSTheoTour", query = "SELECT pdk FROM PhieuDangKy pdk WHERE pdk.ngayKhoiHanh.tour.maTour=:matour") })
public class PhieuDangKy {
	@Id
	@GeneratedValue(generator = "MaPDKGenerater")
	@GenericGenerator(name = "MaPDKGenerater", strategy = "idgenerater.MaPDKGenerater")
	@Column(columnDefinition = "VARCHAR(20)")
	private String maPhieuDK;

	private Date ngayTaoPhieu;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "nhanvienID", nullable = false)
	private NhanVien nv;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "khachhangID", nullable = false)
	private KhachHang kh;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "mangaykhoihanh", nullable = false)
	private NgayKhoiHanh ngayKhoiHanh;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "khachhangthamgiaID", nullable = false)
	private Set<KhachHangThamGia> khachHangThamGias = new HashSet<KhachHangThamGia>();

	private boolean daHoanThanhTour;

	private boolean daHuyPhieu;

	private static final double THUE = 0.1;

	public PhieuDangKy() {

	}

	public PhieuDangKy(String maPhieuDK, Date ngayTaoPhieu, NhanVien nv, KhachHang kh, NgayKhoiHanh ngayKhoiHanh,
			Set<KhachHangThamGia> khachHangThamGias, boolean daHoanThanhTour, boolean daHuyPhieu) {
		super();
		this.maPhieuDK = maPhieuDK;
		this.ngayTaoPhieu = ngayTaoPhieu;
		this.nv = nv;
		this.kh = kh;
		this.ngayKhoiHanh = ngayKhoiHanh;
		this.khachHangThamGias = khachHangThamGias;
		this.daHoanThanhTour = daHoanThanhTour;
		this.daHuyPhieu = daHuyPhieu;
	}

	public int[] tinhSoNguoiTheoDoTuoi(List<KhachHangThamGia> list) {
		int dotuoi[] = new int[2];
		int soNguoiLon = 0;
		int soTreEm = 0;
		for (KhachHangThamGia x : list) {
			LocalDate ngaySinh = x.getNgaySinh().toLocalDate();
			Period period = Period.between(ngaySinh, LocalDate.now());
			if (period.getYears() >= 18) {
				soNguoiLon++;
				dotuoi[0] = soNguoiLon;
			} else {
				soTreEm++;
				dotuoi[1] = soTreEm;
			}
		}
		return dotuoi;
	}

	public double tinhTongTienPDK(List<KhachHangThamGia> list) {
		double tt = 0;
		int[] songuoi = tinhSoNguoiTheoDoTuoi(list);
		if (songuoi.length == 0)
			return 0;
		else {
			tt = (songuoi[0] * ngayKhoiHanh.getTour().getDonGiaNguoiLon()
					+ songuoi[1] * ngayKhoiHanh.getTour().getDonGiaTreEm()) * getThue();

		}
		return tt;
	}

	public String getMaPhieuDK() {
		return maPhieuDK;
	}

	public void setMaPhieuDK(String maPhieuDK) {
		this.maPhieuDK = maPhieuDK;
	}

	public Date getNgayTaoPhieu() {
		return ngayTaoPhieu;
	}

	public void setNgayTaoPhieu(Date ngayTaoPhieu) {
		this.ngayTaoPhieu = ngayTaoPhieu;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public KhachHang getKh() {
		return kh;
	}

	public void setKh(KhachHang kh) {
		this.kh = kh;
	}

	public Set<KhachHangThamGia> getKhachHangThamGias() {
		return khachHangThamGias;
	}

	public void setKhachHangThamGias(Set<KhachHangThamGia> khachHangThamGias) {
		this.khachHangThamGias = khachHangThamGias;
	}

	public boolean isDaHoanThanhTour() {
		return daHoanThanhTour;
	}

	public void setDaHoanThanhTour(boolean daHoanThanhTour) {
		this.daHoanThanhTour = daHoanThanhTour;
	}

	public boolean isDaHuyPhieu() {
		return daHuyPhieu;
	}

	public void setDaHuyPhieu(boolean daHuyPhieu) {
		this.daHuyPhieu = daHuyPhieu;
	}

	public static double getThue() {
		return THUE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maPhieuDK == null) ? 0 : maPhieuDK.hashCode());
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
		PhieuDangKy other = (PhieuDangKy) obj;
		if (maPhieuDK == null) {
			if (other.maPhieuDK != null)
				return false;
		} else if (!maPhieuDK.equals(other.maPhieuDK))
			return false;
		return true;
	}
}
