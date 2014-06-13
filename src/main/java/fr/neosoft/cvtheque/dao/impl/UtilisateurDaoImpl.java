package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import javax.persistence.Query;

import fr.neosoft.cvtheque.dao.UtilisateurDao;
import fr.neosoft.cvtheque.entities.Utilisateur;

/**
 * Implementation of the utilisateur Dao interface.
 * 
 * @author Adrien Cambillau
 *
 */
public class UtilisateurDaoImpl extends GenericDaoImpl<Utilisateur> implements UtilisateurDao {

	public UtilisateurDaoImpl(ManagerDaoImpl managerDao) {
		super();
	}

	public List<Utilisateur> findAllUtilisateurs() {
		List<Utilisateur> listUtilisateurs = this.entityManager.createNamedQuery("Utilisateur.findAll").getResultList();
		return listUtilisateurs;
	}

	public List<Utilisateur> findUsers(final String lastName, final String firstName, final String birthDate) {
		String jpql = "SELECT u FROM Utilisateur u WHERE u.utilisateur.nom = :lastName AND u.utilisateur.prenom = :firstName AND u.utilisateur.dateNaissance = :birthDate";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("lastName", lastName);
		query.setParameter("firstName", firstName);
		query.setParameter("birthDate", birthDate);
		
		List<Utilisateur> users = query.getResultList();
		return users;
	}
}
