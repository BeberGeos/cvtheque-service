package fr.neosoft.cvtheque.services;

import fr.neosoft.cvtheque.entities.Experience;
import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.utils.FonctionnelleException;

public interface GererUtilisateurService {
	public abstract void createUser(String lastName, String firstName, String birthDate)throws FonctionnelleException;
	public abstract void updateProfil(Utilisateur user)throws FonctionnelleException;
	public abstract void updateSkill(Utilisateur user, Experience experience)throws FonctionnelleException;
	public abstract Utilisateur searchUsers(String lastName, String firstName, String birthDate)throws FonctionnelleException;
	public abstract Utilisateur searchUsersByClient(Long idClient)throws FonctionnelleException;
	public abstract Utilisateur searchUser(Long idUser)throws FonctionnelleException;
}
