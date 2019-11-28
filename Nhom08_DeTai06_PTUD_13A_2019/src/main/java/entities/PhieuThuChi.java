package entities;

import java.sql.Date;

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
import javax.persistence.Table;

@Entity
@Table(name = "phieuthuchi")
public class PhieuThuChi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition = "VARCHAR(20)", unique = true, nullable = false)
	private String maPhieuChi;

	private Date ngayTaoPhieuChi;

	@Column(columnDefinition = "MONEY")
	private double soTien;

	@Column(columnDefinition = "NTEXT")
	private String lyDo;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "phieudangkyID", nullable = false, updatable = true)
	private PhieuDangKy pdk;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "NVARCHAR(100)")
	private LoaiPhieu loaiPhieu;

	public PhieuThuChi() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getSoTien() {
		return soTien;
	}

	public void setSoTien(double soTien) {
		this.soTien = soTien;
	}

	public String getLyDo() {
		return lyDo;
	}

	public void setLyDo(String lyDo) {
		this.lyDo = lyDo;
	}

	public PhieuDangKy getPdk() {
		return pdk;
	}

	public void setPdk(PhieuDangKy pdk) {
		this.pdk = pdk;
	}

	public LoaiPhieu getLoaiPhieu() {
		return loaiPhieu;
	}

	public void setLoaiPhieu(LoaiPhieu loaiPhieu) {
		this.loaiPhieu = loaiPhieu;
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
		PhieuThuChi other = (PhieuThuChi) obj;
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
				+ soTien + ", lyDoChi=" + lyDo + "]";
	}

}
