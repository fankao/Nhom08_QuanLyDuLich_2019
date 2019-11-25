package data.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import data.EntityManagerConnection;
import data.ITourDAO;
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
		List<Tour> dsTour = query.getResultList();
		return dsTour;
	}

	/**
	 * @param maTour
	 * @return Thông tin tour
	 */
	@Override
	public Tour layTourTheoMa(String maTour) {
		return em.find(Tour.class, maTour);
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
}
