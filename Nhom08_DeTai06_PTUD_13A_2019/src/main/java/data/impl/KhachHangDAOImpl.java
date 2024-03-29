package data.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import data.EntityManagerConnection;
import data.IKhachHangDAO;
import entities.KhachHang;

/**
 * KhachHangDAOImpl.java <br>
 * 
 * @author Minh Chien<br>
 *         Ngay Tao 08/11/2019
 * 
 *
 */
public class KhachHangDAOImpl implements IKhachHangDAO {
	private EntityManager em;

	/**
	 * Khởi tạo đối tượng
	 */
	public KhachHangDAOImpl() {
		em = EntityManagerConnection.getInstance().getEntityManager();
	}

	/**
	 * Lấy danh sách khách hàng theo tên(tìm kiếm tương đối)
	 * 
	 * @param ten: tên khách hàng
	 * @return danh sách khách hàng
	 */
	@Override
	public KhachHang layDSKhachHangTheoTen(String ten) {
		TypedQuery<KhachHang> query = em.createNamedQuery("KH.timTheoTenKH", KhachHang.class);
		query.setParameter("ten", ten);
		List<KhachHang> list = query.getResultList();

		return list.size() != 0 ? list.get(0) : null;
	}

	/**
	 * Lấy danh sách khách hàng theo số điện thoại (tìm kiếm tương đối)
	 * 
	 * @param sdt: số điện thoại khách hàng
	 * @return danh sách khách hàng
	 */
	@Override
	public KhachHang layTTKhachHangTheoSDT(String sdt) {
		TypedQuery<KhachHang> query = em.createNamedQuery("KH.timTheoSDT", KhachHang.class);
		query.setParameter("sdt", sdt);
		List<KhachHang> list = query.getResultList();
		return list.size() != 0 ? list.get(0) : null;
	}

	/**
	 * Lấy thông tin khách hàng theo CMND
	 * 
	 * @param cmnd: chứng minh nhân dân
	 * @return KhachHang
	 */
	@Override
	public KhachHang layTTKhachHangTheoCMND(String cmnd) {
		TypedQuery<KhachHang> query = em.createNamedQuery("KH.timTheoCMND", KhachHang.class);
		query.setParameter("cmnd", cmnd);
		List<KhachHang> list = query.getResultList();
		return list.size() != 0 ? list.get(0) : null;
	}

	/**
	 * Lấy danh sách tất cả khách hàng
	 * 
	 * @return List<KhachHang>
	 */
	@Override
	public List<KhachHang> layDSKhachHang() {
		TypedQuery<KhachHang> query = em.createNamedQuery("KH.timTatCaKH", KhachHang.class);
		List<KhachHang> list = query.getResultList();
		return list.size() != 0 ? list : new ArrayList<KhachHang>();
	}

	/**
	 * Thêm khách hàng
	 * 
	 * @param kh: khách hàng
	 * @return KhachHang
	 */
	@Override
	public KhachHang themKhachHang(KhachHang kh) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			kh.setMaKH(phatSinhMaKH());
			em.persist(kh);
			tr.commit();
			return kh;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	/**
	 * Sửa thông tin khách hàng
	 * 
	 * @param kh: khách hàng
	 * @return KhachHang
	 */
	@Override
	public KhachHang suaKhachHang(KhachHang kh) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(kh);
			tr.commit();
			return kh;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	/**
	 * Phát sinh mã khách hàng
	 * 
	 * @return String
	 */
	@Override
	public String phatSinhMaKH() {
		String query = "SELECT t.id FROM KhachHang t ORDER BY t.id";
		List<Integer> lstId = em.createQuery(query, Integer.class).getResultList();
		if (lstId.size() != 0) {
			int max = lstId.get(0);
			for (Integer x : lstId) {
				if (x > max) {
					max = x;
				}
			}
			return "KH00" + (max + 1);
		}
		return "KH001";
	}

}
