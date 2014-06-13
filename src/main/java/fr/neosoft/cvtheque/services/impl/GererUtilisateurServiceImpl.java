package fr.neosoft.cvtheque.services.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import fr.neosoft.cvtheque.entities.Adresse;
import fr.neosoft.cvtheque.entities.Experience;
import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.services.GererUtilisateurService;
import fr.neosoft.cvtheque.utils.Constantes;
import fr.neosoft.cvtheque.utils.FonctionnelleException;
import fr.neosoft.cvtheque.utils.Utils;

public class GererUtilisateurServiceImpl implements GererUtilisateurService {
	
	@PersistenceContext
	EntityManager entityManager;

	public void createUser(final String lastName, String firstName, String birthDate)
			throws FonctionnelleException {
		List<Utilisateur> users;
		Utilisateur userToInsert;
		String jpql = "SELECT u FROM Utilisateur u WHERE u.utilisateur.nom = :lastName AND u.utilisateur.prenom = :firstName AND u.utilisateur.dateNaissance = :birthDate";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("lastName", lastName);
		query.setParameter("firstName", firstName);
		query.setParameter("birthDate", birthDate);
		
		users = query.getResultList();
		
		if(users.size() > 0){
			throw new FonctionnelleException(Constantes.USER_ALREADY_IN_DB, "");
		}else{
			userToInsert = new Utilisateur();
			userToInsert.setNom(lastName);
			userToInsert.setPrenom(firstName);
			userToInsert.setDateNaissance(new Timestamp(Utils.createDateFromString(birthDate).getTimeInMillis()));
			
			ValidatorFactory validFactory = Validation.buildDefaultValidatorFactory();
			Validator validator = validFactory.getValidator();
			Set<ConstraintViolation<Utilisateur>> constraintViolations = validator.validate(userToInsert);
			
			if(constraintViolations.size() > 0){
				throw new FonctionnelleException(Constantes.USER_CONSTRAINT_VIOLATION, "");
			}
			
			entityManager.persist(userToInsert);
		}
	}

	public void updateProfil(Utilisateur user) throws FonctionnelleException {
		Adresse userAdresse = user.getAdresse();
		if(userAdresse != null && userAdresse.getRue().equals("")){
			
		}
	}

	public void updateSkill(Utilisateur user, Experience experience) throws FonctionnelleException {
		// TODO Auto-generated method stub

	}

	public Utilisateur searchUsers(String lastName, String firstName,
			String birthDate) throws FonctionnelleException {
		// TODO Auto-generated method stub
		return null;
	}

	public Utilisateur searchUsersByClient(Long idClient)
			throws FonctionnelleException {
		// TODO Auto-generated method stub
		return null;
	}

	public Utilisateur searchUser(Long idUser) throws FonctionnelleException {
		// TODO Auto-generated method stub
		return null;
	}

}
