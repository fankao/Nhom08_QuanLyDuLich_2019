package entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/********************************************
 * LichTrinh.java
 * 
 * @author Minh Chiến<br>
 *         Ngày tạo: 16/11/2019
 * 
 ********************************************
 */
@Entity
@Table(name = "ngaykhoihanh")
@NamedQueries({
		@NamedQuery(name = "NgayKH.timNgayKhoiHanhTheoNgay", query = "SELECT ngkh FROM NgayKhoiHanh ngkh WHERE ngkh.tour.maTour=:maTour AND (ngkh.ngayKhoiHanh BETWEEN :startDate AND :toDate) ORDER BY ngkh.id"),
		@NamedQuery(name = "NgayKH.timDSNgayKHChuaBiHuyTheoTour", query = "SELECT t FROM NgayKhoiHanh t WHERE t.tour.maTour=:maTour AND t.daXoaDoKhongDuSoLuong = False ORDER BY t.id") })
public class NgayKhoiHanh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String maLT;

	@Temporal(TemporalType.DATE)
	private Date ngayKhoiHanh;
	private int soKhachToiDa;
	private int soKhachDaDangKy;
	private boolean daXoa;
	private boolean daDuSoLuong;
	private boolean daXoaDoKhongDuSoLuong;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "matour", nullable = false)
	private Tour tour;

	public NgayKhoiHanh() {
	}

	/**
	 * Huỷ ngày khởi hành do không đủ số người tham gia
	 * 
	 * @param ngayKhoiHanh: ngày khởi hành cần huỷ
	 * @return: ngày khởi hànhs
	 */
	public NgayKhoiHanh capNhatNgayKhoiHanhKhongDuSoLuong(NgayKhoiHanh ngayKhoiHanh) {
		java.sql.Date ngayKH = new java.sql.Date(ngayKhoiHanh.getNgayKhoiHanh().getTime());
		LocalDate date = ngayKH.toLocalDate();
		int soNguoiToiThieu = Math.round(ngayKhoiHanh.getSoKhachToiDa() * (2 / 3));
		Period period = Period.between(LocalDate.now(), date);

		// Nếu ngày hiện tại cách ngày khởi hành còn 5 ngày và số người đã đăng ký tour
		// không vượt quá 2/3 số khách tối đa
		if ((period.getDays() <= 5 || period.getMonths() >= 1) && ngayKhoiHanh.getSoKhachDaDangKy() < soNguoiToiThieu) {
			// thì tiến hành huỷ ngày khởi hành này
			ngayKhoiHanh.setDaXoaDoKhongDuSoLuong(true);
			return ngayKhoiHanh;
		}
		return null;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getSoKhachToiDa() {
		return soKhachToiDa;
	}

	public void setSoKhachToiDa(int soKhachToiDa) {
		this.soKhachToiDa = soKhachToiDa;
	}

	public int getSoKhachDaDangKy() {
		return soKhachDaDangKy;
	}

	public void setSoKhachDaDangKy(int soKhachDaDangKy) {
		this.soKhachDaDangKy = soKhachDaDangKy;
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

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	@Override
	public String toString() {
		return this.tour + "( Khởi hành ngày: " + this.ngayKhoiHanh + ")";
	}

}
