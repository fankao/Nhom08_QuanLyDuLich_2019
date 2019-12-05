package data.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import data.EntityManagerConnection;
import data.IPhieuDangKyDAO;
import entities.KhachHangThamGia;
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
		return list.size() != 0 ? list : new ArrayList<PhieuDangKy>();
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
		return list.size() != 0 ? list : new ArrayList<PhieuDangKy>();
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
			pdk.setMaPhieuDK(phatSinhMaPDK());
			int i = 1;
			List<KhachHangThamGia> khtg = new ArrayList<KhachHangThamGia>();
			for (KhachHangThamGia khachHangThamGia : pdk.getKhachHangThamGias()) {
				khachHangThamGia.setMaKHTG(pdk.getMaPhieuDK() + "-KHTG00" + i);
				i++;
				khtg.add(khachHangThamGia);
			}
			pdk.setKhachHangThamGias(khtg);
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

		TypedQuery<PhieuDangKy> query = em.createNamedQuery("PDK.timPDKTheoMa", PhieuDangKy.class);
		query.setParameter("maPDK", maPDK);
		List<PhieuDangKy> list = query.getResultList();
		return list.size() != 0 ? list.get(0) : null;
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
		return list.size() != 0 ? list : new ArrayList<PhieuDangKy>();
	}

	@Override
	public List<KhachHangThamGia> layDSKhachThamGiaTour(String maTour) {

		@SuppressWarnings("unchecked")
		List<KhachHangThamGia> list = em
				.createQuery(
						"SELECT pdk.khachHangThamGias FROM PhieuDangKy pdk WHERE pdk.ngayKhoiHanh.tour.maTour=:matour")
				.setParameter("matour", maTour).getResultList();
		return list.size() != 0 ? list : new ArrayList<KhachHangThamGia>();
	}

	@Override
	public String phatSinhMaPDK() {
		List<Integer> listID = em.createQuery("SELECT pdk.id FROM PhieuDangKy pdk", Integer.class).getResultList();
		if (listID.size() != 0) {
			int max = listID.get(0);
			for (Integer x : listID) {
				if (x > max) {
					max = x;
				}
			}
			return "PDK00" + (max + 1) + "/" + LocalDate.now().getMonthValue() + "" + LocalDate.now().getYear();
		}
		return "PDK001/" + LocalDate.now().getMonthValue() + "" + LocalDate.now().getYear();
	}

	@Override
	public PhieuDangKy layPhieuDangKyTheoKHVaNgayKH(String maKH, String maNGKH) {
		TypedQuery<PhieuDangKy> query = em.createNamedQuery("PDK.timPDKTheoKHVaNgayKhoiHanh", PhieuDangKy.class);
		query.setParameter("makh", maKH);
		query.setParameter("malt", maNGKH);
		List<PhieuDangKy> list = query.getResultList();
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public List<PhieuDangKy> layDSPhieuDangKyTheoThang(int thang) {
		TypedQuery<PhieuDangKy> query = em.createNamedQuery("PDK.timDSPDKTheoThang", PhieuDangKy.class);
		query.setParameter("thang", thang);
		List<PhieuDangKy> list = query.getResultList();
		return list.size() != 0 ? list : new ArrayList<PhieuDangKy>();
	}
}
