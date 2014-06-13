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
	public List<Utilisateur> findAllUtilisateurs();
	/**
	 * Trouve des utilisateurs suivant certains paramètres.
	 * 
	 * @param lastName nom de l'utilisateur
	 * @param firstName prénom de l'utilisateur
	 * @param birthDate date de naissance de l'utilisateur
	 */
	public List<Utilisateur> findUsers(final String lastName, final String firstName, final String birthDate);
}
