package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

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

}
