package fr.neosoft.cvtheque.services.impl;

import java.util.List;

import fr.neosoft.cvtheque.bean.AdresseType;
import fr.neosoft.cvtheque.bean.CompetenceDetailType;
import fr.neosoft.cvtheque.bean.CompetenceType;
import fr.neosoft.cvtheque.bean.Cv;
import fr.neosoft.cvtheque.bean.ExperienceType;
import fr.neosoft.cvtheque.bean.FonctionType;
import fr.neosoft.cvtheque.bean.IdentiteType;
import fr.neosoft.cvtheque.bean.ObjectFactory;
import fr.neosoft.cvtheque.dao.UtilisateurDao;
import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.services.GererCVService;
import fr.neosoft.cvtheque.utils.Constantes;
import fr.neosoft.cvtheque.utils.FonctionnelleException;

/**
 * Implémentation de l'interface de gestion des CV.
 * 
 * @author Adrien Cambillau
 *
 */
public class GererCVServiceImpl implements GererCVService {
	private UtilisateurDao userDao;
	private static ObjectFactory objectFactory = new ObjectFactory();

	public List<Utilisateur> searchUserByLanguageOrCategory(final Long idLangage, final
			Long idCategory) throws FonctionnelleException {
		List<Utilisateur> users;
		if(idLangage != null || idCategory != null){
			users = getUserDao().findUserByLanguageOrCategory(idLangage, idCategory);
			if(users.isEmpty()){
				throw new FonctionnelleException(Constantes.NO_USER_FOUND, String.valueOf(idLangage) + " " + String.valueOf(idCategory));
			}
		}else{
			throw new FonctionnelleException(Constantes.FIELD_REQUIRED, String.valueOf(idLangage) + " " + String.valueOf(idCategory));
		}
		return users;
	}

	public void createCV(final Long idUser)
			throws FonctionnelleException {
		if(idUser == null){
			throw new FonctionnelleException(Constantes.FIELD_REQUIRED, String.valueOf(idUser));
		}
		Utilisateur user = getUserDao().find(idUser);
		
		final Cv cvUtilisateur = objectFactory.createCv();
        final IdentiteType identiteTypeUtilisateur = objectFactory.createIdentiteType();
        final AdresseType adresseTypeUtilisateur = objectFactory.createAdresseType();
        final FonctionType fonctionTypeUtilisateur = objectFactory.createFonctionType();
        final CompetenceType competenceTypeTechniqueUtilisateur = objectFactory.createCompetenceType();
        final CompetenceDetailType competenceDetailTypeTechniqueDeveloppementUtilisateur = objectFactory.createCompetenceDetailType();
        final CompetenceDetailType competenceDetailTypeTechniqueServeurAppliUtilisateur = objectFactory.createCompetenceDetailType();
        final CompetenceType competenceTypeFonctionnelleUtilisateur = objectFactory.createCompetenceType();
        final CompetenceDetailType competenceDetailTypeFonctionnelleUtilisateur = objectFactory.createCompetenceDetailType();
        final ExperienceType experienceTypeUtilisateurASP = objectFactory.createExperienceType();
        final ExperienceType experienceTypeUtilisateurCarsat = objectFactory.createExperienceType();
        
        //TODO A terminer
	}

	public UtilisateurDao getUserDao() {
		return userDao;
	}

}
