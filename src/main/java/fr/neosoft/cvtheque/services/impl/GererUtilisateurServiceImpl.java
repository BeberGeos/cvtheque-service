package fr.neosoft.cvtheque.services.impl;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import fr.neosoft.cvtheque.dao.impl.AdresseDaoImpl;
import fr.neosoft.cvtheque.dao.impl.ExperienceDaoImpl;
import fr.neosoft.cvtheque.dao.impl.ManagerDaoImpl;
import fr.neosoft.cvtheque.dao.impl.UtilisateurDaoImpl;
import fr.neosoft.cvtheque.entities.Adresse;
import fr.neosoft.cvtheque.entities.Experience;
import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.services.GererUtilisateurService;
import fr.neosoft.cvtheque.utils.Constantes;
import fr.neosoft.cvtheque.utils.FonctionnelleException;

/**
 * Implémentation de l'interface de gestion des utilisateurs.
 * 
 * @author Adrien Cambillau
 *
 */
public class GererUtilisateurServiceImpl implements GererUtilisateurService {
	private ManagerDaoImpl managerDao = new ManagerDaoImpl();
	private UtilisateurDaoImpl userDao = managerDao.getDaoUtilisateur();
	private AdresseDaoImpl adresseDao = managerDao.getDaoAdresse();
	private ExperienceDaoImpl experienceDao = managerDao.getDaoExperience();
	
	public void createUser(final Utilisateur user)
			throws FonctionnelleException {
		Utilisateur dbUser = userDao.find(user.getId());
		
		//Check si il existe déjà un client avec les paramètres donnés, si oui on lève une exception
		if(dbUser != null){
			throw new FonctionnelleException(Constantes.USER_ALREADY_IN_DB, user.getPrenom() + " " + user.getNom());
		}else{
			final Utilisateur userToInsert;
			userToInsert = new Utilisateur();
			userToInsert.setNom(user.getNom());
			userToInsert.setPrenom(user.getPrenom());
			userToInsert.setDateNaissance(user.getDateNaissance());
			
			/* TODO A mettre dans une méthode dans Utils
			 * Check de la validité des données suivant la validation de l'entité.
			 * Taille des nom et prénom inférieure à 50 caractères et date de naissance dans le passé.
			 */
			ValidatorFactory validFactory = Validation.buildDefaultValidatorFactory();
			Validator validator = validFactory.getValidator();
			Set<ConstraintViolation<Utilisateur>> constraintViolations = validator.validate(userToInsert);
			
			//Si il y a violation de contrainte on lève une exception.
			if(constraintViolations.size() > 0){
				throw new FonctionnelleException(Constantes.USER_CONSTRAINT_VIOLATION, user.getPrenom() + " " + user.getNom());
			}else{
				userDao.create(userToInsert);
			}
		}
	}

	public void updateProfil(Utilisateur user) throws FonctionnelleException {
		Adresse userAdresse = user.getAdresse();
		
		//Check si l'adresse est renseignée, si c'est le cas on vérifie que tous les champs obligatoires sont remplis.
		if(userAdresse != null && !userAdresse.getRue().isEmpty() && userAdresse.getCodePostal() > 0 
				&& !userAdresse.getVille().isEmpty()){
			
			/* TODO A mettre dans une méthode dans Utils
			 * Check de la validité des données suivant la validation de l'entité.
			 * Nom de ville < 50 caractères et code postal à 5 chiffres.
			 */
			ValidatorFactory validFactory = Validation.buildDefaultValidatorFactory();
			Validator validator = validFactory.getValidator();
			Set<ConstraintViolation<Adresse>> constraintViolations = validator.validate(userAdresse);
			
			//Si contrainte violée alors on lève une exception.
			if(constraintViolations.size() > 0){
				throw new FonctionnelleException(Constantes.ADDRESS_CONSTRAINT_VIOLATION, "Nom ville : " + userAdresse.getVille()
						+ " code postal : " + userAdresse.getCodePostal());
			}else{
				/*Vérification si l'adresse est déjà présente en base, si oui on ne l'ajoute pas. 
				 * Sinon on set l'adresse de l'utilisateur à celle trouvée.
				 */
				Adresse dbAdresse = adresseDao.find(userAdresse.getId());
				if(dbAdresse == null){
					adresseDao.create(userAdresse);
				}else{
					user.setAdresse(dbAdresse);
				}
			}
			userDao.update(user);
		}/* 
		else if(userAdresse != null && userAdresse.getRue().isEmpty() && userAdresse.getCodePostal() == 0 
				&& userAdresse.getVille().isEmpty()){
			
		}
		*/
	}

	public void updateSkill(Utilisateur user, Experience experience) throws FonctionnelleException {
		//TODO A finir
		List<Experience> userExperiences = user.getExperiences();
		if(userExperiences.contains(experience)){
			
		}
		
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
