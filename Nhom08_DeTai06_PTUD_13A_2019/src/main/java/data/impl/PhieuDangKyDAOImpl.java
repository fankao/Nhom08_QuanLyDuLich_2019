package data.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import data.EntityManagerConnection;
import data.IPhieuDangKyDAO;
import entities.PhieuDangKy;

public class PhieuDangKyDAOImpl implements IPhieuDangKyDAO {
	private EntityManager em;

	/**
	 * Constructor khởi tạo đối tượng PhieuDangKyDAO
	 */
	public PhieuDangKyDAOImpl() {
		em = EntityManagerConnection.getInstance().getEntityManager();
	}

	/**
	 * Lấy danh sách phiếu đăng ký
	 */
	@Override
	public List<PhieuDangKy> layDSPhieuDangKy() {
		TypedQuery<PhieuDangKy> query = em.createNamedQuery("PDK.timDSPDK", PhieuDangKy.class);
		List<PhieuDangKy> list = query.getResultList();
		return list;
	}

	/**
	 * Lấy danh sách tour theo khách hàng
	 * 
	 * @param maKH: mã khách hàng đăng ký
	 * @return list: danh sách phiếu đăng ký
	 */
	@Override
	public List<PhieuDangKy> layDSPhieuDangKyTheoKH(String maKH) {
		TypedQuery<PhieuDangKy> query = em.createNamedQuery("PDK.timDSPDKTheoKH", PhieuDangKy.class);
		query.setParameter("makh", maKH);
		List<PhieuDangKy> list = query.getResultList();
		return list;
	}

	/**
	 * Thêm phiếu đăng ký
	 * 
	 * @param pdk: phiếu đăng ký cần tạo
	 * @return pdk: phiếu đăng ký được tạo
	 */
	@Override
	public PhieuDangKy themPhieuDangKy(PhieuDangKy pdk) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(pdk);
			tr.commit();
			return pdk;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * sửa phiếu đăng ký
	 * 
	 * @param pdk: phiếu đăng ký cần sửa
	 * @return pdk: phiếu đăng ký sau khi sửa
	 */
	@Override
	public PhieuDangKy suaPhieuDangKy(PhieuDangKy pdk) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(pdk);
			tr.commit();
			return pdk;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Lấy thông tin phiếu đăng ký
	 * 
	 * @param: maPDK: mã phiếu đăng ký
	 */
	@Override
	public PhieuDangKy layTTPhieuDangKyTheoMa(String maPDK) {

		return em.find(PhieuDangKy.class, maPDK);
	}

	/**
	 * Lây danh sách phiếu đăng ký theo tour
	 * 
	 * @param maTour: mã tour
	 * @return danh sách phiếu đăng ky theo mã tour
	 */
	@Override
	public List<PhieuDangKy> layDSPhieuDangKyTheoTour(String maTour) {
		TypedQuery<PhieuDangKy> query = em.createNamedQuery("PDK.timDSTheoTour", PhieuDangKy.class);
		query.setParameter("matour", maTour);
		List<PhieuDangKy> list = query.getResultList();
		return list;
	}

}
