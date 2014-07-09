package fr.neosoft.cvtheque.services.impl;

import java.util.List;

import fr.neosoft.cvtheque.dao.UtilisateurDao;
import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.services.GererCVService;
import fr.neosoft.cvtheque.utils.Constantes;
import fr.neosoft.cvtheque.utils.FonctionnelleException;
import fr.neosoft.cvtheque.utils.TypeDocument;

/**
 * Implémentation de l'interface de gestion des CV.
 * 
 * @author Adrien Cambillau
 *
 */
public class GererCVServiceImpl implements GererCVService {
	private UtilisateurDao userDao;

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

	public void createCV(final Long idUser, final TypeDocument typeDocument)
			throws FonctionnelleException {
		if(idUser == null){
			throw new FonctionnelleException(Constantes.FIELD_REQUIRED, String.valueOf(idUser));
		}
		//TODO Creation du fichier CV
	}

	public UtilisateurDao getUserDao() {
		return userDao;
	}

}
