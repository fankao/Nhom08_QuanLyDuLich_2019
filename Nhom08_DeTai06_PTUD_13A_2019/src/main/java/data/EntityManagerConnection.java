package data;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerConnection {
	private static EntityManagerConnection instance = null;
	private EntityManager em;

	public EntityManagerConnection() {
		em = Persistence.createEntityManagerFactory("Nhom08_DeTai06_PTUD_13A_2019").createEntityManager();
	}

	public static EntityManagerConnection getInstance() {
		if (instance == null) {
			instance = new EntityManagerConnection();
		}
		return instance;
	}

	public EntityManager getEntityManager() {
		return em;
	}

}
