package data.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import data.EntityManagerConnection;
import data.ITourDAO;
import entities.NgayKhoiHanh;
import entities.Tour;

public class TourDAOImpl implements ITourDAO {
	private EntityManager em;

	public TourDAOImpl() {
		em = EntityManagerConnection.getInstance().getEntityManager();
	}

	/**
	 * Trả về toàn bộ danh sách tour chưa được duyệt
	 */
	@Override
	public List<Tour> layDSTourChuaDuyet() {
		TypedQuery<Tour> query = em.createNamedQuery("Tour.timDsTourChuaDuyet", Tour.class);
		query.setParameter("daXoa", false);
		query.setParameter("daDuyet", false);
		List<Tour> dsTour = query.getResultList();
		return dsTour.size() != 0 ? dsTour : new ArrayList<Tour>();
	}

	/**
	 * @param maTour
	 * @return Thông tin tour
	 */
	@Override
	public Tour layTourTheoMa(String maTour) {
		String query = "SELECT t FROM Tour t WHERE maTour=:ma";
		Tour tour = em.createQuery(query, Tour.class).setParameter("ma", maTour).getSingleResult();
		return tour;
	}

	/**
	 * @param tour
	 * @return Tour đã thêm
	 * 
	 */
	@Override
	public Tour themTour(Tour tour) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			tour.setMaTour(phatSinhMaTour());
			em.persist(tour);
			tr.commit();
			return tour;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	/**
	 * Sửa thông tin tour
	 * 
	 * @param tour
	 * @return Tour đã sửa
	 */
	@Override
	public Tour suaTour(Tour tour) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(tour);
			tr.commit();
			return tour;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	/**
	 * trả về danh sách ngày khởi hành của tour
	 */
	@Override
	public List<NgayKhoiHanh> layDSNgayKhoiHanhTheoTour(String maTour) {
		TypedQuery<NgayKhoiHanh> query = em.createQuery("SELECT t FROM NgayKhoiHanh t WHERE t.tour.maTour=:maTour",
				NgayKhoiHanh.class);
		query.setParameter("maTour", maTour);
		List<NgayKhoiHanh> sets = query.getResultList();
		return sets.size() != 0 ? sets : new ArrayList<NgayKhoiHanh>();
	}

	/**
	 * Lấy danh sách tour đã mở bán
	 */
	@Override
	public List<Tour> layDSTourDaDuyet() {
		TypedQuery<Tour> query = em.createNamedQuery("Tour.timDsTourDaDuyet", Tour.class);
		query.setParameter("daXoa", false);
		query.setParameter("daDuyet", true);
		List<Tour> dsTour = query.getResultList();
		return dsTour.size() != 0 ? dsTour : new ArrayList<Tour>();
	}

	/**
	 * Lấy danh sách tour theo nhân viên tạo
	 */
	@Override
	public List<Tour> layDSTourTheoNhanVien(String maNV) {
		TypedQuery<Tour> query = em.createNamedQuery("Tour.timDsTourTheoNhanVien", Tour.class);
		query.setParameter("daXoa", false);
		query.setParameter("manv", maNV);
		List<Tour> dsTour = query.getResultList();
		return dsTour.size() != 0 ? dsTour : new ArrayList<Tour>();
	}

	/**
	 * Phát sinh mã cho tour
	 */
	@Override
	public String phatSinhMaTour() {
		String query = "SELECT t.maTour FROM Tour t";
		List<String> lstId = em.createQuery(query, String.class).getResultList();
		if (lstId.size() != 0) {
			int max = Integer.parseInt(lstId.get(0).substring(3));
			for (String x : lstId) {
				if (Integer.parseInt(x.substring(3)) > max) {
					max = Integer.parseInt(x.substring(3));
				}
			}
			return "T00" + (max + 1);
		}
		return "T001";
	}

	/**
	 * Lấy danh sách tour
	 */
	@Override
	public List<Tour> layDSTour() {
		TypedQuery<Tour> query = em.createNamedQuery("Tour.timDSTour", Tour.class);
		List<Tour> dsTour = query.getResultList();
		return dsTour.size() != 0 ? dsTour : new ArrayList<Tour>();
	}

	@Override
	public String phatSinhNgayKhoiHanh(String maTour) {
		List<Integer> lstId = em
				.createQuery("SELECT ngaykh.id FROM NgayKhoiHanh ngaykh WHERE ngaykh.tour.maTour =:maTour",
						Integer.class)
				.setParameter("maTour", maTour).getResultList();
		if (lstId.size() != 0) {
			int max = lstId.get(0);
			for (Integer x : lstId) {
				if (x > max) {
					max = x;
				}
			}
			return maTour + "-NGKH00" + (max + 1);
		}
		return maTour + "-NGKH001";
	}

	/**
	 * Lấy danh sách ngày khởi hành của tour theo trong khoảng thời gian
	 * 
	 * @param maTour:  mã tour
	 * @param tuNgay:  từ ngày
	 * @param denNgay: đến ngày
	 */
	public List<NgayKhoiHanh> layDSNgayKhoiHanhTheoNgayKhoiHanh(String maTour, Date tuNgay, Date denNgay) {
		TypedQuery<NgayKhoiHanh> query = em.createNamedQuery("NgayKH.timNgayKhoiHanhTheoNgay", NgayKhoiHanh.class);
		query.setParameter("maTour", maTour);
		query.setParameter("startDate", tuNgay);
		query.setParameter("toDate", denNgay);
		List<NgayKhoiHanh> dsNgayKH = query.getResultList();
		return dsNgayKH.size() != 0 ? dsNgayKH : new ArrayList<NgayKhoiHanh>();
	}
}
