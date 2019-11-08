package utils;

import java.io.Serializable;

public class MaPhatSinh implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maTour;
	private String maKH;
	private String maPDK;
	private String maKHTG;
	private String maPC;

	public MaPhatSinh() {
		super();
	}

	public MaPhatSinh(String maTour, String maKH, String maPDK, String maKHTG, String maPC) {
		super();
		this.maTour = maTour;
		this.maKH = maKH;
		this.maPDK = maPDK;
		this.maKHTG = maKHTG;
		this.maPC = maPC;

	}

	public String getMaTuor() {
		return maTour;
	}

	public void setMaTour(String maTuor) {
		this.maTour = maTuor;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getMaPDK() {
		return maPDK;
	}

	public void setMaPDK(String maPDK) {
		this.maPDK = maPDK;
	}

	public String getMaKHTG() {
		return maKHTG;
	}

	public void setMaKHTG(String maKHTG) {
		this.maKHTG = maKHTG;
	}

	public String getMaPC() {
		return maPC;
	}

	public void setMaPC(String maPC) {
		this.maPC = maPC;
	}

}
