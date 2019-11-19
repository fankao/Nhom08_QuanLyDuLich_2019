package entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/********************************************
 * LichTrinh.java
 * 
 * @author Minh Chiến<br>
 *         Ngày tạo: 16/11/2019
 * 
 ********************************************
 */
@Entity
@Table(name = "lichtrinh")
public class LichTrinh {
	@Id
	@Column(columnDefinition = "VARCHAR(20)")
	private String maLT;
	private Date ngayKhoiHanh;
	private int soNguoiThamGia;

	public LichTrinh() {
	}

	public LichTrinh(String maLT, Date ngayKhoiHanh, int soNguoiThamGia) {
		super();
		this.maLT = maLT;
		this.ngayKhoiHanh = ngayKhoiHanh;
		this.soNguoiThamGia = soNguoiThamGia;
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

	public int getSoNguoiThamGia() {
		return soNguoiThamGia;
	}

	public void setSoNguoiThamGia(int soNguoiThamGia) {
		this.soNguoiThamGia = soNguoiThamGia;
	}

	@Override
	public String toString() {
		return "LichTrinh [maLT=" + maLT + ", ngayKhoiHanh=" + ngayKhoiHanh + "]";
	}

}
