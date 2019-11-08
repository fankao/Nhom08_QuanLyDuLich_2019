package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quyen")
public class Quyen {
	@Id
	private int quyen;
	@Column(columnDefinition = "NVARCHAR(100)")
	private String ghiChu;

	public Quyen() {
		// TODO Auto-generated constructor stub
	}

	public Quyen(int quyen, String ghiChu) {
		super();
		this.quyen = quyen;
		this.ghiChu = ghiChu;
	}

	public int getQuyen() {
		return quyen;
	}

	public void setQuyen(int quyen) {
		this.quyen = quyen;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

}
