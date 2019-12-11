package data.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import data.EntityManagerConnection;
import data.INhanVienDAO;
import entities.NhanVien;
import entities.TaiKhoan;

/**
 * NhanVienDAOImpl.java
 * 
 * @author Gia Hưng <br>
 *         Ngày tạo 10/11/2019
 *
 */
public class NhanVienDAOImpl implements INhanVienDAO {
	private EntityManager em;

	/**
	 * Khởi tạo đối tượng
	 */
	public NhanVienDAOImpl() {
		em = EntityManagerConnection.getInstance().getEntityManager();
	}

	/**
	 * Lấy thông tin nhân viên theo mã
	 * 
	 * @param maNV: mã nhân viên
	 * @return NhanVien
	 */
	@Override
	public NhanVien layNhanVienTheoMa(String maNV) {

		return em.find(NhanVien.class, maNV);
	}

	/**
	 * Lấy thông tin nhân viên theo tài khoản
	 * 
	 * @param tk: tài khoản
	 * @return NhanVien
	 */
	@Override
	public NhanVien layNhanVienTheoTaiKhoan(TaiKhoan tk) {
		TypedQuery<NhanVien> query = em.createNamedQuery("nv.timTheoTaiKhoan", NhanVien.class);
		query.setParameter("user", tk.getUserName());
		query.setParameter("pass", tk.getPassWord());
		NhanVien nv = query.getResultList().size() == 0 ? null : query.getSingleResult();
		return nv;
	}
}
