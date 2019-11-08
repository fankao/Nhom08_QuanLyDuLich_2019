package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tour")
@NamedQueries({ @NamedQuery(name = "Tour.timDsTour", query = "SELECT t FROM Tour t "),
		@NamedQuery(name = "Tour.timDsTourChuaDK", query = "SELECT t FROM Tour t WHERE t.soNguoiDaDangKy = 0"),
		@NamedQuery(name = "Tour.timDsTourDaDK", query = "SELECT t FROM Tour t WHERE t.soNguoiDaDangKy > 0") })
public class Tour {
	@Id
	@Column(columnDefinition = "CHAR(20)")
	private String maTour;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String tenTour;
	private int soLuongKhach;
	@Column(columnDefinition = "NTEXT")
	private String moTa;
	private byte[] hinhAnh;
	private double donGiaNguoiLon;
	private double donGiaTreEm;
	private Date ngayKhoiHanh;
	private Date ngayKetThuc;
	private int soNguoiDaDangKy;

	private boolean daHoanThanhTour;
	private boolean daHuy;
	private boolean daDuSoLuong;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "manhanvien", nullable = false)
	private NhanVien nhanVien;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "madiadanh", nullable = false)
	private DiaDanh diaDanh;

	public Tour() {
		// TODO Auto-generated constructor stub
	}

	public Tour(String maTour, String tenTour, int soLuongKhach, String moTa, byte[] hinhAnh, double donGiaNguoiLon,
			double donGiaTreEm, Date ngayKhoiHanh, Date ngayKetThuc, int soNguoiDaDangKy, boolean daHoanThanhTour,
			boolean daHuy, boolean daDuSoLuong, NhanVien nhanVien, DiaDanh diaDanh) {
		super();
		this.maTour = maTour;
		this.tenTour = tenTour;
		this.soLuongKhach = soLuongKhach;
		this.moTa = moTa;
		this.hinhAnh = hinhAnh;
		this.donGiaNguoiLon = donGiaNguoiLon;
		this.donGiaTreEm = donGiaTreEm;
		this.ngayKhoiHanh = ngayKhoiHanh;
		this.ngayKetThuc = ngayKetThuc;
		this.soNguoiDaDangKy = soNguoiDaDangKy;
		this.daHoanThanhTour = daHoanThanhTour;
		this.daHuy = daHuy;
		this.daDuSoLuong = daDuSoLuong;
		this.nhanVien = nhanVien;
		this.diaDanh = diaDanh;
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

	public int getSoLuongKhach() {
		return soLuongKhach;
	}

	public void setSoLuongKhach(int soLuongKhach) {
		this.soLuongKhach = soLuongKhach;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
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

	public Date getNgayKhoiHanh() {
		return ngayKhoiHanh;
	}

	public void setNgayKhoiHanh(Date ngayKhoiHanh) {
		this.ngayKhoiHanh = ngayKhoiHanh;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public boolean isDaHoanThanhTour() {
		return daHoanThanhTour;
	}

	public void setDaHoanThanhTour(boolean daHoanThanhTour) {
		this.daHoanThanhTour = daHoanThanhTour;
	}

	public boolean isDaHuy() {
		return daHuy;
	}

	public void setDaHuy(boolean daHuy) {
		this.daHuy = daHuy;
	}

	public boolean isDaDuSoLuong() {
		return daDuSoLuong;
	}

	public void setDaDuSoLuong(boolean daDuSoLuong) {
		this.daDuSoLuong = daDuSoLuong;
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

	public int getSoNguoiDaDangKy() {
		return soNguoiDaDangKy;
	}

	public void setSoNguoiDaDangKy(int soNguoiDaDangKy) {
		this.soNguoiDaDangKy = soNguoiDaDangKy;
	}

	/**
	 * Hàm tính thời gian diễn ra tour du lịch
	 * 
	 * @return số ngày
	 */
	public int tinhThoiGian() {
		LocalDate d1 = this.getNgayKhoiHanh().toLocalDate();
		LocalDate d2 = this.getNgayKetThuc().toLocalDate();
		Period period = Period.between(d1, d2);
		return period.getDays();
	}

	/**
	 * Hàm kiểm tra ngày kết thúc tour phải sau ngày khởi hành 2 ngày trở lên;
	 * 
	 * @return true: nếu đúng; false: sai
	 */
	public boolean kiemTraNgayKetThuc() {
		// Ngày kết thúc tour phải sau ngày khởi hành 2 ngày trở lên;
		return tinhThoiGian() >= 2 ? true : false;
	}

	/**
	 * Hàm kiểm tra khởi hành phải sau ngày tạo tour, tức ngày hiện tại 10 ngày
	 * 
	 * @return true: nếu đúng; false: sai
	 */
	public boolean kiemTraNgayKhoiHanh() {
		LocalDate ngayKH = this.getNgayKhoiHanh().toLocalDate();
		Period period = Period.between(LocalDate.now(), ngayKH);
		return period.getDays() >= 10 ? true : false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maTour == null) ? 0 : maTour.hashCode());
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
		Tour other = (Tour) obj;
		if (maTour == null) {
			if (other.maTour != null)
				return false;
		} else if (!maTour.equals(other.maTour))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return this.tenTour;
	}

}
