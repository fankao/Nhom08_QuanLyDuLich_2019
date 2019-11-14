package data.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import data.EntityManagerConnection;
import data.INhanVienDAO;
import entities.NhanVien;
import entities.TaiKhoan;

public class NhanVienDAOImpl implements INhanVienDAO {
	private EntityManager em;

	public NhanVienDAOImpl() {
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
		NhanVien nv = query.getResultList().size() == 0 ? null : query.getSingleResult();
		return nv;
	}

	public static void main(String[] args) {
		NhanVienDAOImpl daoImpl = new NhanVienDAOImpl();
		System.out.println(daoImpl.layNhanVienTheoTaiKhoan(new TaiKhoan("NV001", "000000")));
	}
}
