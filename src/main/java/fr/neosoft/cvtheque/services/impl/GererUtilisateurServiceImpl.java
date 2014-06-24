package fr.neosoft.cvtheque.services.impl;

import java.util.List;

import fr.neosoft.cvtheque.dao.AdresseDao;
import fr.neosoft.cvtheque.dao.ExperienceDao;
import fr.neosoft.cvtheque.dao.ManagerDao;
import fr.neosoft.cvtheque.dao.UtilisateurDao;
import fr.neosoft.cvtheque.dao.impl.ManagerDaoImpl;
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
	private ManagerDao managerDao = new ManagerDaoImpl();
	private UtilisateurDao userDao = managerDao.getDaoUtilisateur();
	private AdresseDao adresseDao = managerDao.getDaoAdresse();
	private ExperienceDao experienceDao = managerDao.getDaoExperience();

	public void createUser(final Utilisateur user)
			throws FonctionnelleException {
		Utilisateur dbUser = userDao.find(user.getId());

		//Check si il existe déjà un client avec les paramètres donnés, si oui on lève une exception
		if(dbUser != null){
			throw new FonctionnelleException(Constantes.USER_ALREADY_IN_DB, user.getPrenom() + " " + user.getNom()
					+ " " + user.getDateNaissance());
		}else{
			if(user.getNom().isEmpty() || user.getPrenom().isEmpty() || user.getDateNaissance() == null){
				throw new FonctionnelleException(Constantes.FIELD_REQUIRED, user.getPrenom() + " " + user.getNom()
						+ " " + user.getDateNaissance());
			}else{
				Utils.checkConstraints(user, user.getPrenom() + " " + user.getNom() + " " 
						+ user.getDateNaissance());
				userDao.create(user);
			}
		}
	}

	public void updateProfil(Utilisateur user) throws FonctionnelleException {
		Adresse userAdresse = user.getAdresse();

		//Check si l'adresse est renseignée, si c'est le cas on vérifie que tous les champs obligatoires sont remplis.
		if(userAdresse != null && !userAdresse.getRue().isEmpty() && userAdresse.getCodePostal() > 0 
				&& !userAdresse.getVille().isEmpty()){

			Utils.checkConstraints(userAdresse, userAdresse.getRue() + " " + userAdresse.getCodePostal() + " " 
					+ userAdresse.getVille());

			/* Vérification si l'adresse est déjà présente en base, si oui on ne l'ajoute pas. 
			 * Sinon on set l'adresse de l'utilisateur à celle trouvée.
			 */
			Adresse dbAdresse = adresseDao.find(userAdresse.getId());
			if(dbAdresse == null){
				adresseDao.create(userAdresse);
			}else{
				user.setAdresse(dbAdresse);
			}
			userDao.update(user);
		}
	}

	public void updateSkill(Utilisateur user, Experience experience) throws FonctionnelleException {
		List<Experience> userExperiences = user.getExperiences();
		if(experience.getClient() != null && experience.getCompetences().size() > 0 && experience.getDateDebut() != null){
			if(userExperiences.contains(experience)){
				experienceDao.update(experience);
			}else{
				experienceDao.create(experience);
				userExperiences.add(experience);
				userDao.update(user);
			}
		}else{
			throw new FonctionnelleException(Constantes.EXPERIENCE_NOT_COMPLETE, experience.toString());
		}

	}

	public List<Utilisateur> searchUsers(String lastName, String firstName,
			String birthDate) throws FonctionnelleException {
		List<Utilisateur> listUtilisateurs = userDao.findUsers(lastName, firstName, birthDate);
		if(listUtilisateurs.isEmpty()){
			throw new FonctionnelleException(Constantes.NO_USER_FOUND, firstName + " " + lastName + " " + birthDate);
		}
		return listUtilisateurs;
	}

	public List<Utilisateur> searchUsersByClient(Long idClient)
			throws FonctionnelleException {
		List<Utilisateur> listUtilisateurs = userDao.findUsersByClient(idClient);
		if(listUtilisateurs.isEmpty()){
			throw new FonctionnelleException(Constantes.NO_USER_FOUND, String.valueOf(idClient));
		}
		return listUtilisateurs;
	}

	public Utilisateur searchUser(Long idUser) throws FonctionnelleException {
		try{
			Utilisateur user = userDao.find(idUser);
			return user;
		}catch(NullPointerException e){
			throw new FonctionnelleException(Constantes.NO_USER_FOUND, String.valueOf(idUser));
		}
	}

}
