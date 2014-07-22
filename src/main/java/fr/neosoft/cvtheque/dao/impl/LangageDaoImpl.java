package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.neosoft.cvtheque.dao.LangageDao;
import fr.neosoft.cvtheque.entities.Langage;

/**
 * Implementation of the langage Dao interface.
 * 
 * @author Adrien Cambillau
 *
 */
public class LangageDaoImpl extends GenericDaoImpl<Langage> implements LangageDao {

	public LangageDaoImpl(EntityManager entityManager) {
		super();
		setEntityManager(entityManager);
	}
	
	public LangageDaoImpl() {
		super();
	}

	public List<Langage> findAllLangages() {
		final List<Langage> listLangages = getEntityManager().createNamedQuery("Langage.findAll").getResultList();
		return listLangages;
	}

	public Langage findLanguageByName(String libelle) {
		final String jpql = "SELECT l FROM Langage l WHERE l.langage.libelle = :libelle";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter("libelle", libelle);
		
		Langage langage = (Langage) query.getSingleResult();
		return langage;
	}

	@Override
	public List<Langage> findLanguagesByName(String libelle) {
		final List<Langage> listLangages = getEntityManager().createNamedQuery("Langage.findByName").getResultList();
		return listLangages;
	}

}
