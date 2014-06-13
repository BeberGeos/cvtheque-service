package fr.neosoft.cvtheque.services.impl;

import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.services.GererCVService;
import fr.neosoft.cvtheque.utils.FonctionnelleException;
import fr.neosoft.cvtheque.utils.TypeDocument;

public class GererCVServiceImpl implements GererCVService {

	@Override
	public Utilisateur searchUserByLangageOrCategory(Long idLangage,
			Long idCategory) throws FonctionnelleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createCV(Long idUser, TypeDocument typeDocument)
			throws FonctionnelleException {
		// TODO Auto-generated method stub

	}

}
