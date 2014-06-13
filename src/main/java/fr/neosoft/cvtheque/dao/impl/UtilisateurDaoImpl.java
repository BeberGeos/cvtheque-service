package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

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

}
