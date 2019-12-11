package data.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import data.EntityManagerConnection;
import data.IDiaDanhDAO;
import entities.DiaDanh;

/**
 * DiaDanhDAOImpl.java
 * 
 * @author Minh Chiến<br>
 *         Ngày tạo 10/11/2019
 *
 */
public class DiaDanhDAOImpl implements IDiaDanhDAO {
	private EntityManager em;
	
	/**
	 * Khởi tạo đối tượng
	 */
	public DiaDanhDAOImpl() {
		em = EntityManagerConnection.getInstance().getEntityManager();
	}

	/**
	 * Lấy danh sách tất cả địa danh
	 * @return List<DiaDanh>
	 */
	@Override
	public List<DiaDanh> layDSDiaDanh() {
		TypedQuery<DiaDanh> query = em.createNamedQuery("DiaDanh.timDSDiaDanh", DiaDanh.class);
		List<DiaDanh> list = query.getResultList();
		return list;
	}
}
