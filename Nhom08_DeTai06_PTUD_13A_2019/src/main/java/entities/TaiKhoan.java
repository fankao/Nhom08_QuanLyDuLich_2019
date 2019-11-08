package entities;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "taikhoan")
public class TaiKhoan {
	private String userName;
	private String passWord;

	public TaiKhoan() {
		// TODO Auto-generated constructor stub
	}

	public TaiKhoan(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
