package data.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import data.EntityManagerConnection;
import data.IDiaChiDAO;
import entities.DiaChi;

public class DiaChiDAO implements IDiaChiDAO {
	private EntityManager em;

	public DiaChiDAO() {
		em = EntityManagerConnection.getInstance().getEntityManager();
	}

	@Override
	public List<String> layDSHuyen(String maTinh) {
		Query query = em.createNativeQuery("SELECT h.ten FROM Huyen h WHERE tinhId='" + maTinh + "'");
		List<String> lstHuyen = query.getResultList();
		return lstHuyen;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,String> layDSTinh() {
		Query query1 = em.createNativeQuery("SELECT h.id FROM Tinh h");
		Query query2 = em.createNativeQuery("SELECT h.ten FROM Tinh h");
		DiaChi diaChi  = new DiaChi();
		return null;
		
	}


	
}
