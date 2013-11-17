package modernbox.smartchat.dal;


import java.util.List;

import javax.persistence.EntityManager;

import modernbox.smartchat.dal.model.Openchatroomsmessage;


public class OpenchatroomsmessageDAO {

	public List<Openchatroomsmessage> read(String userName, Long chatMessageEpochTime) {
		EntityManager em = PersistenceManager.createEntityManager();
		try {
			return em.createNamedQuery("Openchatroomsmessage.Get", Openchatroomsmessage.class)
					.setParameter("u", userName)
					.setParameter("r", chatMessageEpochTime)
					.getResultList();
		} finally {
			em.close();
		}
	}

	public void create(Openchatroomsmessage entity) {
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
	
	public void deleteByUserName(String userName) {
		EntityManager em = PersistenceManager.createEntityManager();
		try {
			em.getTransaction().begin();
			
			List<Openchatroomsmessage> ochrms = em.createNamedQuery("Openchatroomsmessage.GetByUser", Openchatroomsmessage.class)
				.setParameter("u", userName)
				.getResultList();
			for (Openchatroomsmessage openchatroomsmessage : ochrms) {
				em.remove(openchatroomsmessage);
			}
			
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
	}
	
	public void deleteByPerformer(String sPerformer, Long epochTime) {
		EntityManager em = PersistenceManager.createEntityManager();
		try {
			em.getTransaction().begin();
			
			List<Openchatroomsmessage> ochrms = em.createNamedQuery("Openchatroomsmessage.GetByUserAndTime", Openchatroomsmessage.class)
					.setParameter("u", sPerformer)
					.setParameter("t", epochTime)
					.getResultList();
			for (Openchatroomsmessage openchatroomsmessage : ochrms) {
				em.remove(openchatroomsmessage);
				em.getTransaction().commit();
				em.getTransaction().begin();
			}
			
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
	}

	public Long getMaxRowID(String userName) {
		Object chatRoomMessagesMaxRowID;
		EntityManager em = PersistenceManager.createEntityManager();
		try {
			chatRoomMessagesMaxRowID = em
					.createNamedQuery("Openchatroomsmessage.GetMaxRowId")
					.setParameter("u", userName)
					.getSingleResult();
		} finally {
			em.close();
		}
		return chatRoomMessagesMaxRowID == null ?  0 : (Long)chatRoomMessagesMaxRowID;
	}

}
