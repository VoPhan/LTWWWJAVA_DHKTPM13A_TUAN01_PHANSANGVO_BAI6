package daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.bson.types.ObjectId;

import entities.Person;

public class PersonDAO {
	private EntityManager em;
	
	public PersonDAO() {
		em = MyEntityManager.getInstance().getEntityManager();
	}

	public boolean addPerson(Person ps) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(ps);
			tr.commit();
			return true;
		}catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return false;
	}

	public boolean removePerson(Person ps) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.remove(em.find(Person.class, ps.getId()));
			tr.commit();
			return true;
		}catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return false;
	}

	public boolean updatePerson(Person ps) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(ps);
			tr.commit();
			return true;
		}catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return false;
	}
	public List<Person> getPeople(){
		List<Person> people = new ArrayList<Person>();
		List<?> temp = em.createNativeQuery("db.person.find({})", Person.class).getResultList();
		temp.forEach(x -> {
			Person person = (Person) x;
			people.add(person);
		});
		return people;
	}
}
