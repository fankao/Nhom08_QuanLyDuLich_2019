package entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phieuchi")
public class PhieuChi {
	@Id
	@Column(columnDefinition = "VARCHAR(20)")
	private String maPhieuChi;

	private Date ngayTaoPhieuChi;

	@Column(columnDefinition = "MONEY")
	private double soTienChi;

	@Column(columnDefinition = "NTEXT")
	private String lyDoChi;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "phieudangkyID", nullable = false, columnDefinition = "VARCHAR(20)", updatable = true)
	private PhieuDangKy pdk;

	public PhieuChi() {
		// TODO Auto-generated constructor stub
	}

	public PhieuChi(String maPhieuChi, Date ngayTaoPhieuChi, double soTienChi, String lyDoChi, PhieuDangKy pdk) {
		super();
		this.maPhieuChi = maPhieuChi;
		this.ngayTaoPhieuChi = ngayTaoPhieuChi;
		this.soTienChi = soTienChi;
		this.lyDoChi = lyDoChi;
		this.pdk = pdk;
	}

	public String getMaPhieuChi() {
		return maPhieuChi;
	}

	public void setMaPhieuChi(String maPhieuChi) {
		this.maPhieuChi = maPhieuChi;
	}

	public Date getNgayTaoPhieuChi() {
		return ngayTaoPhieuChi;
	}

	public void setNgayTaoPhieuChi(Date ngayTaoPhieuChi) {
		this.ngayTaoPhieuChi = ngayTaoPhieuChi;
	}

	public double getSoTienChi() {
		return soTienChi;
	}

	public void setSoTienChi(double soTienChi) {
		this.soTienChi = soTienChi;
	}

	public String getLyDoChi() {
		return lyDoChi;
	}

	public void setLyDoChi(String lyDoChi) {
		this.lyDoChi = lyDoChi;
	}

	public PhieuDangKy getPdk() {
		return pdk;
	}

	public void setPdk(PhieuDangKy pdk) {
		this.pdk = pdk;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maPhieuChi == null) ? 0 : maPhieuChi.hashCode());
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
		PhieuChi other = (PhieuChi) obj;
		if (maPhieuChi == null) {
			if (other.maPhieuChi != null)
				return false;
		} else if (!maPhieuChi.equals(other.maPhieuChi))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PhieuChi [maPhieuChi=" + maPhieuChi.trim() + ", ngayTaoPhieuChi=" + ngayTaoPhieuChi + ", soTienChi="
				+ soTienChi + ", lyDoChi=" + lyDoChi + "]";
	}

}
