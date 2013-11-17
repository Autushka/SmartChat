package modernbox.smartchat.dal;


import java.util.List;

import javax.persistence.EntityManager;

import modernbox.smartchat.dal.model.Openchatroom;


public class OpenchatroomDAO {

	public void create(Openchatroom entity) {
		EntityManager em = PersistenceManager.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
	}
	
	public List<Openchatroom> read(String userName, int chatParticipantRowId) {
		EntityManager em = PersistenceManager.createEntityManager();
		try {
			return em.createNamedQuery("Openchatroom.Get", Openchatroom.class)
					.setParameter("u", userName)
					.setParameter("r", chatParticipantRowId + 1)
					.getResultList();
		} finally {
			em.close();
		}
	}
	
	public List<Openchatroom> readActiveParticipants(String userName) {
		EntityManager em = PersistenceManager.createEntityManager();
		try {
			return em.createNamedQuery("Openchatroom.GetActive", Openchatroom.class)
					.setParameter("u", userName)
					.getResultList();
		} finally {
			em.close();
		}
	}
	
	public List<Openchatroom> readAll() {
		EntityManager em = PersistenceManager.createEntityManager();
		try {
			return em.createNamedQuery("Openchatroom.GetAllActive", Openchatroom.class)
					.getResultList();
		} finally {
			em.close();
		}
	}
	
	public void deleteByUsername(String userName) {
		EntityManager em = PersistenceManager.createEntityManager();
		try {
			em.getTransaction().begin();
			
			List<Openchatroom> ochrs = em.createNamedQuery("Openchatroom.GetByUser", Openchatroom.class)
			 	.setParameter("u", userName)
			 	.getResultList();
			for (Openchatroom openchatroom : ochrs) {
				em.remove(openchatroom);
			}
			
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
	}
	
	public void deleteByUserNameAndParticipantName(String userName, String participantName) {
		EntityManager em = PersistenceManager.createEntityManager();
		try {
			em.getTransaction().begin();
			
			List<Openchatroom> ochrs = em.createNamedQuery("Openchatroom.GetByUserAndParticipant", Openchatroom.class)
					.setParameter("u", userName)
					.setParameter("p", participantName)
					.getResultList();
			for (Openchatroom openchatroom : ochrs) {
				em.remove(openchatroom);
			}
			
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
	}
	
}
