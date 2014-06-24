package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

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

	public LangageDaoImpl(ManagerDaoImpl managerDao) {
		super();
	}
	
	public List<Langage> findAllLangages() {
		List<Langage> listLangages = this.entityManager.createNamedQuery("Langage.findAll").getResultList();
		return listLangages;
	}

	public Langage findLanguageByName(String libelle) {
		String jpql = "SELECT l FROM Langage l WHERE l.langage.libelle = :libelle";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("libelle", libelle);
		
		Langage langage = (Langage) query.getSingleResult();
		return langage;
	}

	@Override
	public List<Langage> findLanguagesByName(String libelle) {
		List<Langage> listLangages = this.entityManager.createNamedQuery("Langage.findByName").getResultList();
		return listLangages;
	}

}
