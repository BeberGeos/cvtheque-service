package fr.neosoft.cvtheque.dao;

import java.util.List;

import fr.neosoft.cvtheque.entities.Adresse;

/**
 * Interface for the "adresse" dao.
 * 
 * @author Adrien Cambillau
 *
 */
public interface AdresseDao extends GenericDao<Adresse>{
	/**
	 * Retrieve all the "adresse".
	 * @return List<Adresse> the list of all the "adresse"
	 */
	public List<Adresse> findAllAdresses();
}
