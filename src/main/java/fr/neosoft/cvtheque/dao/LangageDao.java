package fr.neosoft.cvtheque.dao;

import java.util.List;

import fr.neosoft.cvtheque.entities.Langage;

/**
 * Interface for the "langage" dao.
 * 
 * @author Adrien Cambillau
 *
 */
public interface LangageDao extends GenericDao<Langage>{
	/**
	 * Retrieve all the "langage".
	 * @return List<Langage> the list of all the "langage"
	 */
	public List<Langage> findAllLangages();
}
