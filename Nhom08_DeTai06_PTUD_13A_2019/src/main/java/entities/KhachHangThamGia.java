package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "khachhangthamgia")
public class KhachHangThamGia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String maKHTG;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String hoTenKHTG;
	private Date ngaySinh;

	@Enumerated(EnumType.STRING)
	private DoTuoi doTuoi;

	public KhachHangThamGia() {
		// TODO Auto-generated constructor stub
	}

	public int tinhTuoiKhachHang() {
		LocalDate date = this.getNgaySinh().toLocalDate();
		Period period = Period.between(date, LocalDate.now());
		return period.getYears();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maKHTG == null) ? 0 : maKHTG.hashCode());
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
		KhachHangThamGia other = (KhachHangThamGia) obj;
		if (maKHTG == null) {
			if (other.maKHTG != null)
				return false;
		} else if (!maKHTG.equals(other.maKHTG))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaKHTG() {
		return maKHTG;
	}

	public void setMaKHTG(String maKHTG) {
		this.maKHTG = maKHTG;
	}

	public String getHoTenKHTG() {
		return hoTenKHTG;
	}

	public void setHoTenKHTG(String hoTenKHTG) {
		this.hoTenKHTG = hoTenKHTG;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public DoTuoi getDoTuoi() {
		return doTuoi;
	}

	public void setDoTuoi(DoTuoi doTuoi) {
		this.doTuoi = doTuoi;
	}

	public KhachHangThamGia(String maKHTG, String hoTenKHTG, Date ngaySinh) {
		super();
		this.maKHTG = maKHTG;
		this.hoTenKHTG = hoTenKHTG;
		this.ngaySinh = ngaySinh;

	}

}
