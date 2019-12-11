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

/**
 * PhieuThuChiDAOimpl.java
 * 
 * @author Minh Chiến <br>
 *         Ngày tạo: 10/11/2019
 *
 */
public class PhieuThuChiDAOimpl implements IPhieuThuChiDAO {
	private EntityManager em;

	/**
	 * Khởi tạo đối tượng
	 */
	public PhieuThuChiDAOimpl() {
		em = EntityManagerConnection.getInstance().getEntityManager();
	}

	/**
	 * Thêm phiếu thu
	 * 
	 * @param p: phiếu thu
	 * @return PhieuThuChi
	 */
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

	/**
	 * Phát sinh mã phiếu thu chi
	 * 
	 * @param loaiPhieu: loại phiếu thu chi
	 * @return String
	 */
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

	/**
	 * Sửa thông tin phiếu thu chi
	 * 
	 * @param p: phiếu thu chi
	 * @return PhieuThuChi
	 */
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

	/**
	 * Lấy danh sách phiếu thu theo tháng
	 * 
	 * @param tháng: tháng
	 * @return List<PhieuThuChi>
	 */
	@Override
	public List<PhieuThuChi> layDSPhieuTheoThang(int thang) {
		TypedQuery<PhieuThuChi> query = em.createNamedQuery("PTC.timDSPhieuTheoThang", PhieuThuChi.class);
		query.setParameter("thang", thang);
		List<PhieuThuChi> list = query.getResultList();
		return list.size() != 0 ? list : new ArrayList<PhieuThuChi>();
	}

	/**
	 * Lấy DS phiếu thu chi theo phiếu đăng ký
	 * 
	 * @param maPDK: mã phiếu đăng ký
	 * @return List<PhieuThuChi>
	 */
	@Override
	public List<PhieuThuChi> layDSPhieuThuTheoPhieuDK(String maPDK) {
		TypedQuery<PhieuThuChi> query = em.createNamedQuery("PTC.timDSPhieuThuTheoPDK", PhieuThuChi.class);
		query.setParameter("loaiPhieu", LoaiPhieu.PHIEUTHU);
		query.setParameter("mapdk", maPDK);
		List<PhieuThuChi> list = query.getResultList();
		return list.size() != 0 ? list : new ArrayList<PhieuThuChi>();
	}
	/**
	 * Lấy danh sách phiếu chi theo phiếu đăng ký
	 * @param maPDK: mã phiếu đăng ký
	 * @return List<PhieuThuChi>
	 */
	@Override
	public List<PhieuThuChi> layDSPhieuChiTheoPhieuDK(String maPDK) {
		TypedQuery<PhieuThuChi> query = em.createNamedQuery("PTC.timDSPhieuChiTheoPDK", PhieuThuChi.class);
		query.setParameter("loaiPhieu", LoaiPhieu.PHIEUCHI);
		query.setParameter("mapdk", maPDK);
		List<PhieuThuChi> list = query.getResultList();
		return list.size() != 0 ? list : new ArrayList<PhieuThuChi>();
	}

}
