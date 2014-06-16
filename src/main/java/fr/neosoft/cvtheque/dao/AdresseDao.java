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
	
	/**
	 * Trouve des adresses suivant les paramètres donnés.
	 * @param rue le nom de la rue
	 * @param ville le nom de la ville
	 * @param codePostal le code postale
	 * @return List<Adresse> la liste des adresses trouvées
	 */
	public List<Adresse> findAdresses(final String rue, final String ville, final int codePostal);
}
