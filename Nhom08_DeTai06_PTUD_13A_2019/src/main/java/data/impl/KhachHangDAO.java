package data.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import data.EntityManagerConnection;
import data.IKhachHangDAO;
import entities.KhachHang;

public class KhachHangDAO implements IKhachHangDAO {
	private EntityManager em;

	public KhachHangDAO() {
		em = EntityManagerConnection.getInstance().getEntityManager();
	}

	@Override
	public List<KhachHang> layDSKhachHangTheoTen(String ten) {
		TypedQuery<KhachHang> query = em.createNamedQuery("KH.timTheoTenKH", KhachHang.class);
		query.setParameter("ten", "%" + ten + "%");
		List<KhachHang> list = query.getResultList();
		return list;
	}

	@Override
	public List<KhachHang> layTTKhachHangTheoSDT(String sdt) {
		TypedQuery<KhachHang> query = em.createNamedQuery("KH.timTheoSDT", KhachHang.class);
		query.setParameter("sdt", "%" + sdt + "%");
		List<KhachHang> list = query.getResultList();
		return list;
	}

	@Override
	public List<KhachHang> layTTKhachHangTheoCMND(String cmnd) {
		TypedQuery<KhachHang> query = em.createNamedQuery("KH.timTheoCMND", KhachHang.class);
		query.setParameter("cmnd", "%" + cmnd + "%");
		List<KhachHang> list = query.getResultList();
		return list;
	}

}
