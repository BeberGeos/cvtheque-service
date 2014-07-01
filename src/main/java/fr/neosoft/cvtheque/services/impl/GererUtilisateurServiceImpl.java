package fr.neosoft.cvtheque.services.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.neosoft.cvtheque.dao.AdresseDao;
import fr.neosoft.cvtheque.dao.ExperienceDao;
import fr.neosoft.cvtheque.dao.UtilisateurDao;
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
@Stateless
@Remote(GererUtilisateurService.class)
public class GererUtilisateurServiceImpl implements GererUtilisateurService {
	private UtilisateurDao userDao;
	private AdresseDao adresseDao;
	private ExperienceDao experienceDao;

	public void createUser(final Utilisateur user)
			throws FonctionnelleException {
		final Utilisateur dbUser = getUserDao().find(user.getId());

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
				getUserDao().create(user);
			}
		}
	}

	public void updateProfil(Utilisateur user) throws FonctionnelleException {
		final Adresse userAdresse = user.getAdresse();

		//Check si l'adresse est renseignée, si c'est le cas on vérifie que tous les champs obligatoires sont remplis.
		if(userAdresse != null && !userAdresse.getRue().isEmpty() && userAdresse.getCodePostal() > 0 
				&& !userAdresse.getVille().isEmpty() && !user.getNom().isEmpty() && !user.getPrenom().isEmpty()
				&& user.getDateNaissance() != null){

			Utils.checkConstraints(userAdresse, userAdresse.getRue() + " " + userAdresse.getCodePostal() + " " 
					+ userAdresse.getVille());
			Utils.checkConstraints(user, user.getDateNaissance().toString());

			/* Vérification si l'adresse est déjà présente en base, si oui on ne l'ajoute pas. 
			 * Sinon on set l'adresse de l'utilisateur à celle trouvée.
			 */
			final Adresse dbAdresse = getAdresseDao().find(userAdresse.getId());
			if(dbAdresse == null){
				getAdresseDao().create(userAdresse);
			}else{
				user.setAdresse(dbAdresse);
			}
			getUserDao().update(user);
		}else{
			throw new FonctionnelleException(Constantes.FIELD_REQUIRED, user.toString());
		}
	}

	public void updateSkill(Utilisateur user, Experience experience) throws FonctionnelleException {
		List<Experience> userExperiences = user.getExperiences();
		if(experience.getClient() != null && experience.getCompetences() != null && !experience.getCompetences().isEmpty()
				&& experience.getDateDebut() != null){
			if(userExperiences.contains(experience)){
				getExperienceDao().update(experience);
			}else{
				getExperienceDao().create(experience);
				userExperiences.add(experience);
				getUserDao().update(user);
			}
		}else{
			throw new FonctionnelleException(Constantes.EXPERIENCE_NOT_COMPLETE, experience.toString());
		}

	}

	public List<Utilisateur> searchUsers(String lastName, String firstName,
			String birthDate) throws FonctionnelleException {
		final List<Utilisateur> listUtilisateurs = getUserDao().findUsers(lastName, firstName, birthDate);
		if(listUtilisateurs.isEmpty()){
			throw new FonctionnelleException(Constantes.NO_USER_FOUND, firstName + " " + lastName + " " + birthDate);
		}
		return listUtilisateurs;
	}

	public List<Utilisateur> searchUsersByClient(Long idClient)
			throws FonctionnelleException {
		final List<Utilisateur> listUtilisateurs = getUserDao().findUsersByClient(idClient);
		if(listUtilisateurs.isEmpty()){
			throw new FonctionnelleException(Constantes.NO_USER_FOUND, String.valueOf(idClient));
		}
		return listUtilisateurs;
	}

	public Utilisateur searchUser(Long idUser) throws FonctionnelleException {
		final Utilisateur user = getUserDao().find(idUser);
		if(user == null){
			throw new FonctionnelleException(Constantes.NO_USER_FOUND, user.toString());
		}
		return user;
	}

	public UtilisateurDao getUserDao() {
		return userDao;
	}

	public AdresseDao getAdresseDao() {
		return adresseDao;
	}

	public ExperienceDao getExperienceDao() {
		return experienceDao;
	}

}
