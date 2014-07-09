package fr.neosoft.cvtheque.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.neosoft.cvtheque.entities.Utilisateur;

/**
 * Interface for the "utilisateur" dao.
 * 
 * @author Adrien Cambillau
 *
 */
@Stateless
@Local
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
	/**
	 * Trouve des utilisateurs suivant un client donné.
	 * 
	 * @param idClient l'id du client à qui appartiennent les utilisateurs recherchés
	 * @return List<Utilisateur> la liste des utilisateurs trouvés
	 */
	public List<Utilisateur> findUsersByClient(final Long idClient);
	/**
	 * Trouve un utilisateur suivant
	 * 
	 * @param idLangage
	 * @param idCategory
	 * @return
	 */
	public List<Utilisateur> findUserByLanguageOrCategory(final Long idLangage,
			final Long idCategory);
}
