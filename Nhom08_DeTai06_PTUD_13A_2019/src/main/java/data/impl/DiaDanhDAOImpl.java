package data.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import data.EntityManagerConnection;
import data.IDiaDanhDAO;
import entities.DiaDanh;

public class DiaDanhDAOImpl implements IDiaDanhDAO {
	private EntityManager em;

	public DiaDanhDAOImpl() {
		em = EntityManagerConnection.getInstance().getEntityManager();
	}

	@Override
	public List<DiaDanh> layDSDiaDanh() {
		TypedQuery<DiaDanh> query = em.createNamedQuery("DiaDanh.timDSDiaDanh", DiaDanh.class);
		List<DiaDanh> list = query.getResultList();
		return list;
	}
	public static void main(String[] args) {
		DiaDanhDAOImpl  danhDAOImpl =  new DiaDanhDAOImpl();
		System.out.println(danhDAOImpl.layDSDiaDanh());
	}

}
