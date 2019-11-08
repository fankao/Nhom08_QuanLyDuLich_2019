package data.impl;

import java.util.List;

import javax.persistence.EntityManager;

import data.EntityManagerConnection;
import data.ITouDAO;
import entities.Tour;

public class TourDAOImpl implements ITouDAO {
	private EntityManager em;

	public TourDAOImpl() {
		em = EntityManagerConnection.getInstance().getEntityManager();
	}

	@Override
	public List<Tour> layDSTour() {
		return null;
	}

	@Override
	public List<Tour> layDSTourTheoMa(String maTour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tour> layDSTourChuaDangKy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tour> layDSTourDaDangKy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tour layTourTheoMa(String maTour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tour themTour(Tour tour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tour suaTour(Tour tour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean xoaTour(Tour tour) {
		// TODO Auto-generated method stub
		return false;
	}

}
