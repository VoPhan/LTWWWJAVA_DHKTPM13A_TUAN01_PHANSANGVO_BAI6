package daos;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class MyEntityManager {
	private static MyEntityManager instance = null;
	private EntityManager em = null;
	
	private MyEntityManager() {
		em = Persistence.createEntityManagerFactory("LTWWWJAVA_DHKTPM13A_TUAN01_PHANSANGVO_BAI6").createEntityManager();
	}
	
	public synchronized static MyEntityManager getInstance() {
		if(instance == null)
			instance = new MyEntityManager();
		return instance;
	}
	
	public EntityManager getEntityManager() {
		return em;
	}
}	
