package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
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

	public UtilisateurDaoImpl(EntityManager entityManager) {
		super();
		setEntityManager(entityManager);
	}
	
	public UtilisateurDaoImpl(){
		super();
	}

	public List<Utilisateur> findAllUtilisateurs() {
		final List<Utilisateur> listUtilisateurs = getEntityManager().createNamedQuery("Utilisateur.findAll").getResultList();
		return listUtilisateurs;
	}

	public List<Utilisateur> findUsers(final String lastName, final String firstName, final String birthDate) {
		final String jpql = "SELECT u FROM Utilisateur u WHERE u.utilisateur.nom = :lastName AND u.utilisateur.prenom = :firstName AND u.utilisateur.dateNaissance = :birthDate";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter("lastName", lastName);
		query.setParameter("firstName", firstName);
		query.setParameter("birthDate", birthDate);

		final List<Utilisateur> users = query.getResultList();
		return users;
	}

	public List<Utilisateur> findUsersByClient(final Long idClient) {
		String jpql = "SELECT u FROM Utilisateur u JOIN u.adresse adr WHERE adr IN (SELECT c FROM Client c JOIN adr WHERE c.client.id = :idClient)";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter("idClient", idClient);

		List<Utilisateur> users = query.getResultList();
		return users;
	}

	public List<Utilisateur> findUserByLanguageOrCategory(final Long idLangage,
			final Long idCategory) {
		String jpql;
		Query query = null;
		if(idLangage != null && idCategory != null){
			jpql = "SELECT u FROM Utilisateur u JOIN u.experiences exp JOIN exp.competences comp WHERE comp.categorie.id = :idCategory AND comp.langage.id = :idLanguage";
			query = getEntityManager().createQuery(jpql);
			query.setParameter("idCategory", idCategory);
			query.setParameter("idLanguage", idLangage);
		}else if(idLangage != null && idCategory == null){
			jpql = "SELECT u FROM Utilisateur u JOIN u.experiences exp JOIN exp.competences comp WHERE comp.langage.id = :idLanguage";
			query = getEntityManager().createQuery(jpql);
			query.setParameter("idLanguage", idLangage);
		}else if(idLangage == null && idCategory != null){
			jpql = "SELECT u FROM Utilisateur u JOIN u.experiences exp JOIN exp.competences comp WHERE comp.categorie.id = :idCategory";
			query = getEntityManager().createQuery(jpql);
			query.setParameter("idCategory", idCategory);
		}

		List<Utilisateur> users = query.getResultList();
		return users;
	}
}
