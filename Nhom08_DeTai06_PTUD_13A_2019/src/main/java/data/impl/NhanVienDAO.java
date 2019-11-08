package data.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import data.EntityManagerConnection;
import data.INhanVienDAO;
import entities.NhanVien;
import entities.TaiKhoan;

public class NhanVienDAO implements INhanVienDAO {
	private EntityManager em;

	public NhanVienDAO() {
		em = EntityManagerConnection.getInstance().getEntityManager();
	}

	@Override
	public NhanVien layNhanVienTheoMa(String maNV) {

		return em.find(NhanVien.class, maNV);
	}

	@Override
	public NhanVien layNhanVienTheoTaiKhoan(TaiKhoan tk) {
		TypedQuery<NhanVien> query = em.createNamedQuery("nv.timTheoTaiKhoan", NhanVien.class);
		query.setParameter("user", tk.getUserName());
		query.setParameter("pass", tk.getPassWord());
		NhanVien nv = query.getSingleResult();
		return nv;
	}

	public static void main(String[] args) {
		NhanVienDAO dao = new NhanVienDAO();
		System.out.println(dao.layNhanVienTheoTaiKhoan(new TaiKhoan("NV001", "000000")));
	}

}
