package fr.neosoft.cvtheque.dao;

import java.util.List;

import fr.neosoft.cvtheque.entities.Utilisateur;

/**
 * Interface for the "utilisateur" dao.
 * 
 * @author Adrien Cambillau
 *
 */
public interface UtilisateurDao extends GenericDao<Utilisateur>{
	/**
	 * Retrieve all the "utilisateur".
	 * @return List<Utilisateur> the list of all the "utilisateur"
	 */
	public abstract List<Utilisateur> findAllUtilisateurs();
}
