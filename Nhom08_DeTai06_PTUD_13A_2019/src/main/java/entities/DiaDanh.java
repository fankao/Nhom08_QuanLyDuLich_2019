package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "diadanh")
@NamedQueries({ @NamedQuery(name = "DiaDanh.timDSDiaDanh", query = "SELECT d FROM DiaDanh d") })
public class DiaDanh {
	@Id
	@Column(name = "madiadanh",columnDefinition = "CHAR(20)")
	private String maDiaDanh;

	@Column(name = "tendiadanh",columnDefinition = "NVARCHAR(255)")
	private String tenDiaDanh;

	public DiaDanh() {
		super();
	}

	public DiaDanh(String maDiaDanh, String tenDiaDanh) {
		super();
		this.maDiaDanh = maDiaDanh;
		this.tenDiaDanh = tenDiaDanh;
	}

	public String getMaDiaDanh() {
		return maDiaDanh;
	}

	public void setMaDiaDanh(String maDiaDanh) {
		this.maDiaDanh = maDiaDanh;
	}

	public String getTenDiaDanh() {
		return tenDiaDanh;
	}

	public void setTenDiaDanh(String tenDiaDanh) {
		this.tenDiaDanh = tenDiaDanh;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maDiaDanh == null) ? 0 : maDiaDanh.hashCode());
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
		DiaDanh other = (DiaDanh) obj;
		if (maDiaDanh == null) {
			if (other.maDiaDanh != null)
				return false;
		} else if (!maDiaDanh.equals(other.maDiaDanh))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.tenDiaDanh;
	}
}
