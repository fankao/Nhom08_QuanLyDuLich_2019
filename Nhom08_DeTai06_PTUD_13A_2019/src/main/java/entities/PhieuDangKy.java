package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import constant.HangSo;

@Entity
@Table(name = "phieudangky")
@NamedQueries({ @NamedQuery(name = "PDK.timDSPDK", query = "SELECT pdk FROM PhieuDangKy pdk ORDER BY pdk.id"),
		@NamedQuery(name = "PDK.timDSPDKTheoThang", query = "SELECT pdk FROM PhieuDangKy pdk WHERE MONTH(pdk.ngayTaoPhieu)=:thang ORDER BY pdk.id"),
		@NamedQuery(name = "PDK.timPDKTheoMa", query = "SELECT pdk FROM PhieuDangKy pdk WHERE pdk.maPhieuDK = : maPDK ORDER BY pdk.id"),
		@NamedQuery(name = "PDK.timDSPDKTheoKH", query = "SELECT pdk FROM PhieuDangKy pdk WHERE pdk.kh.maKH = :makh ORDER BY pdk.id"),
		@NamedQuery(name = "PDK.timDSTheoTour", query = "SELECT pdk FROM PhieuDangKy pdk WHERE pdk.ngayKhoiHanh.tour.maTour=:matour ORDER BY pdk.id"),
		@NamedQuery(name = "PDK.timDSKhachThamGiaTheoTour", query = "SELECT pdk.khachHangThamGias FROM PhieuDangKy pdk WHERE pdk.ngayKhoiHanh.tour.maTour=:matour ORDER BY pdk.id"),
		@NamedQuery(name = "PDK.timPDKTheoKHVaNgayKhoiHanh", query = "SELECT pdk FROM PhieuDangKy pdk WHERE pdk.kh.maKH = :makh AND pdk.ngayKhoiHanh.maLT=:malt ORDER BY pdk.id") })
public class PhieuDangKy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
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
	private List<KhachHangThamGia> khachHangThamGias = new ArrayList<KhachHangThamGia>();

	private boolean thamGiaTourDangKy;

	private boolean daHoanThanhTour;

	private boolean daHuyPhieu;

	public PhieuDangKy() {

	}

	public PhieuDangKy(String maPhieuDK, Date ngayTaoPhieu, NhanVien nv, KhachHang kh, NgayKhoiHanh ngayKhoiHanh,
			boolean daHoanThanhTour, boolean daHuyPhieu) {
		super();
		this.maPhieuDK = maPhieuDK;
		this.ngayTaoPhieu = ngayTaoPhieu;
		this.nv = nv;
		this.kh = kh;
		this.ngayKhoiHanh = ngayKhoiHanh;
		this.daHoanThanhTour = daHoanThanhTour;
		this.daHuyPhieu = daHuyPhieu;
	}

	/**
	 * tính số người theo độ tuôi
	 * 
	 * @param list: danh sách khách hàng tham gia của tour
	 * @return: trả về mảng độ tuổi, gồm 2 phần tử
	 */
	public int[] tinhSoNguoiTheoDoTuoi(List<KhachHangThamGia> list) {
		int dotuoi[] = new int[2];
		int soNguoiLon = 0;
		int soTreEm = 0;
		for (KhachHangThamGia x : list) {
			LocalDate ngaySinh = x.getNgaySinh().toLocalDate();
			Period period = Period.between(ngaySinh, LocalDate.now());
			if (period.getYears() >= HangSo.NGUOILON) {
				soNguoiLon++;
				dotuoi[0] = soNguoiLon;
			} else {
				soTreEm++;
				dotuoi[1] = soTreEm;
			}
		}
		return dotuoi;
	}

	/**
	 * Tính thành tiền dựa và danh sách khách hàng tham gia tour
	 * 
	 * @param list: danh sách khách hàng tham fi
	 * @return
	 */
	public double[] tinhThanhTien(List<KhachHangThamGia> list) {
		double[] thanhTien = new double[2];
		int[] songuoi = tinhSoNguoiTheoDoTuoi(this.getKhachHangThamGias());
		if (songuoi.length == 0) {
			thanhTien[0] = 0;
			thanhTien[1] = 0;
			return thanhTien;
		} else {
			thanhTien[0] = songuoi[0] * ngayKhoiHanh.getTour().getDonGiaNguoiLon();
			thanhTien[1] = songuoi[1] * ngayKhoiHanh.getTour().getDonGiaTreEm();

		}
		return thanhTien;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NgayKhoiHanh getNgayKhoiHanh() {
		return ngayKhoiHanh;
	}

	public void setNgayKhoiHanh(NgayKhoiHanh ngayKhoiHanh) {
		this.ngayKhoiHanh = ngayKhoiHanh;
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

	public List<KhachHangThamGia> getKhachHangThamGias() {
		return khachHangThamGias;
	}

	public void setKhachHangThamGias(List<KhachHangThamGia> khachHangThamGias) {
		this.khachHangThamGias = khachHangThamGias;
	}

	public boolean isThamGiaTourDangKy() {
		return thamGiaTourDangKy;
	}

	public void setThamGiaTourDangKy(boolean thamGiaTourDangKy) {
		this.thamGiaTourDangKy = thamGiaTourDangKy;
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
