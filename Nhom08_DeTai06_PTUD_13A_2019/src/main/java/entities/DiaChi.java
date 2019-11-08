package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DiaChi implements Serializable {
	private static final long serialVersionUID = 1L;
	private String xa;
	private String huyen;
	private String tinh;

	public DiaChi() {
		// TODO Auto-generated constructor stub
	}

	public DiaChi(String xa, String huyen, String tinh) {
		super();
		this.xa = xa;
		this.huyen = huyen;
		this.tinh = tinh;
	}

	public String getXa() {
		return xa;
	}

	public void setXa(String xa) {
		this.xa = xa;
	}

	public String getHuyen() {
		return huyen;
	}

	public void setHuyen(String huyen) {
		this.huyen = huyen;
	}

	public String getTinh() {
		return tinh;
	}

	public void setTinh(String tinh) {
		this.tinh = tinh;
	}

	@Override
	public String toString() {
		return "DiaChi [xa=" + xa + ", huyen=" + huyen + ", tinh=" + tinh + "]";
	}
}
