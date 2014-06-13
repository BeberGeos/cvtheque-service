package fr.neosoft.cvtheque.services.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import fr.neosoft.cvtheque.dao.impl.ManagerDaoImpl;
import fr.neosoft.cvtheque.dao.impl.UtilisateurDaoImpl;
import fr.neosoft.cvtheque.entities.Adresse;
import fr.neosoft.cvtheque.entities.Experience;
import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.services.GererUtilisateurService;
import fr.neosoft.cvtheque.utils.Constantes;
import fr.neosoft.cvtheque.utils.FonctionnelleException;
import fr.neosoft.cvtheque.utils.Utils;

/**
 * Implémentation de l'interface de gestion des utilisateurs.
 * 
 * @author Adrien Cambillau
 *
 */
public class GererUtilisateurServiceImpl implements GererUtilisateurService {
	private ManagerDaoImpl managerDao = new ManagerDaoImpl();
	
	public void createUser(final String lastName, final String firstName, final String birthDate)
			throws FonctionnelleException {
		UtilisateurDaoImpl userDao = managerDao.getDaoUtilisateur();
		List<Utilisateur> users = userDao.findUsers(lastName, firstName, birthDate);
		
		
		
		//Check si il existe déjà un client avec les paramètres donnés, si oui on lève une exception
		if(users.size() > 0){
			throw new FonctionnelleException(Constantes.USER_ALREADY_IN_DB, "");
		}else{
			Utilisateur userToInsert;
			userToInsert = new Utilisateur();
			userToInsert.setNom(lastName);
			userToInsert.setPrenom(firstName);
			userToInsert.setDateNaissance(new Timestamp(Utils.createDateFromString(birthDate).getTimeInMillis()));
			
			/* Check de la validité des données suivant la validation de l'entité.
			 * Taille des nom et prénom inférieure à 50 caractères et date de naissance dans le passé.
			 */
			ValidatorFactory validFactory = Validation.buildDefaultValidatorFactory();
			Validator validator = validFactory.getValidator();
			Set<ConstraintViolation<Utilisateur>> constraintViolations = validator.validate(userToInsert);
			
			//Si il y a violation de contrainte on lève une exception.
			if(constraintViolations.size() > 0){
				throw new FonctionnelleException(Constantes.USER_CONSTRAINT_VIOLATION, "");
			}else{
				userDao.create(userToInsert);
			}
		}
	}

	public void updateProfil(Utilisateur user) throws FonctionnelleException {
		Adresse userAdresse = user.getAdresse();
		if(userAdresse != null && userAdresse.getRue().equals("") && userAdresse.getCodePostal() > 0 
				&& userAdresse.getVille().equals("")){
			
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

	public List<Utilisateur> searchUsersByClient(Long idClient)
			throws FonctionnelleException {
		// TODO Auto-generated method stub
		return null;
	}

	public Utilisateur searchUser(Long idUser) throws FonctionnelleException {
		// TODO Auto-generated method stub
		return null;
	}

}
