package fr.neosoft.cvtheque.services;

import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.utils.FonctionnelleException;
import fr.neosoft.cvtheque.utils.TypeDocument;

public interface GererCVService {
	public abstract Utilisateur searchUserByLangageOrCategory(Long idLangage, Long idCategory)throws FonctionnelleException;
	public abstract void createCV(Long idUser, TypeDocument typeDocument)throws FonctionnelleException;
}
