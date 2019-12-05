package data.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import data.EntityManagerConnection;
import data.IPhieuThuChiDAO;
import entities.LoaiPhieu;
import entities.PhieuThuChi;

public class PhieuThuChiDAOimpl implements IPhieuThuChiDAO {
	private EntityManager em;

	public PhieuThuChiDAOimpl() {
		em = EntityManagerConnection.getInstance().getEntityManager();
	}

	@Override
	public PhieuThuChi themPhieu(PhieuThuChi p) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(p);
			tr.commit();
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public PhieuThuChi layThongTinPhieu(String ma) {

		return null;
	}

	@Override
	public String phatSinhMaPhieu(LoaiPhieu loaiPhieu) {
		List<Integer> lstId = em.createQuery("SELECT p.id FROM PhieuThuChi p ORDER BY p.id", Integer.class)
				.getResultList();
		int max = 0;
		if (lstId.size() != 0) {
			max = lstId.get(lstId.size() - 1);
		}
		if (loaiPhieu.toString().equalsIgnoreCase(LoaiPhieu.PHIEUTHU.toString())) {
			return "PT00" + (max + 1) + "/" + LocalDate.now().getMonthValue() + "" + LocalDate.now().getYear();
		}
		return "PC00" + (max + 1) + "/" + LocalDate.now().getMonthValue() + "" + LocalDate.now().getYear();

	}

	@Override
	public PhieuThuChi suaPhieu(PhieuThuChi p) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(p);
			tr.commit();
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	@Override
	public List<PhieuThuChi> layDSPhieuTheoThang(int thang) {
		TypedQuery<PhieuThuChi> query = em.createNamedQuery("PTC.timDSPhieuTheoThang", PhieuThuChi.class);
		query.setParameter("thang", thang);
		List<PhieuThuChi> list = query.getResultList();
		return list.size() != 0 ? list : new ArrayList<PhieuThuChi>();
	}

	@Override
	public List<PhieuThuChi> layDSPhieuThuTheoPhieuDK(String maPDK) {
		TypedQuery<PhieuThuChi> query = em.createNamedQuery("PTC.timDSPhieuThuTheoPDK", PhieuThuChi.class);
		query.setParameter("loaiPhieu", LoaiPhieu.PHIEUTHU);
		query.setParameter("mapdk", maPDK);
		List<PhieuThuChi> list = query.getResultList();
		return list.size() != 0 ? list : new ArrayList<PhieuThuChi>();
	}

	@Override
	public List<PhieuThuChi> layDSPhieuChiTheoPhieuDK(String maPDK) {
		TypedQuery<PhieuThuChi> query = em.createNamedQuery("PTC.timDSPhieuChiTheoPDK", PhieuThuChi.class);
		query.setParameter("loaiPhieu", LoaiPhieu.PHIEUCHI);
		query.setParameter("mapdk", maPDK);
		List<PhieuThuChi> list = query.getResultList();
		return list.size() != 0 ? list : new ArrayList<PhieuThuChi>();
	}

}
