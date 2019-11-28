package data.impl;

import java.util.ArrayList;
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
	 * Trả về toàn bộ danh sách tour
	 */
	@Override
	public List<Tour> layDSTour() {
		TypedQuery<Tour> query = em.createNamedQuery("Tour.timDsTour", Tour.class);
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

	@Override
	public List<NgayKhoiHanh> layDSNgayKhoiHanhTheoTour(String maTour) {
		TypedQuery<NgayKhoiHanh> query = em.createQuery("SELECT t FROM NgayKhoiHanh t WHERE t.tour.maTour=:maTour",
				NgayKhoiHanh.class);
		query.setParameter("maTour", maTour);
		List<NgayKhoiHanh> sets = query.getResultList();
		return sets.size() != 0 ? sets : new ArrayList<NgayKhoiHanh>();
	}

	@Override
	public List<Tour> layDSTourDaDuyet() {
		TypedQuery<Tour> query = em.createNamedQuery("Tour.timDsTourDaDuyet", Tour.class);
		query.setParameter("daXoa", false);
		query.setParameter("daDuyet", true);
		List<Tour> dsTour = query.getResultList();
		return dsTour.size() != 0 ? dsTour : new ArrayList<Tour>();
	}

	@Override
	public List<Tour> layDSTourTheoNhanVien(String maNV) {
		TypedQuery<Tour> query = em.createNamedQuery("Tour.timDsTourTheoNhanVien", Tour.class);
		query.setParameter("daXoa", false);
		query.setParameter("manv", maNV);
		List<Tour> dsTour = query.getResultList();
		return dsTour.size() != 0 ? dsTour : new ArrayList<Tour>();
	}

	public static void main(String[] args) {

	}

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

}
