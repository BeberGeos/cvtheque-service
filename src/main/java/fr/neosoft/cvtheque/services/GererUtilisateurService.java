package fr.neosoft.cvtheque.services;

import java.util.List;

import fr.neosoft.cvtheque.entities.Experience;
import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.utils.FonctionnelleException;

/**
 * Interface de gestion des utilisateurs.
 * Ce service va permettre la création, la modification et la recherche d’utilisateur. 
 * 
 * @author Adrien Cambillau
 *
 */
public interface GererUtilisateurService {
	/**
	 * Cette méthode va permettre la création d’un utilisateur à partir de son nom, prénom et de sa date de naissance. 
	 * L’association nom, prénom et date de naissance devra être unique en base de données. 
	 * Si cette association est présente une exception de type FonctionnelleException est jetée. 
	 * Le nom et le prénom auront une limite de 50 caractères (javax.validation). 
	 * La date de naissance devra correspondre au format JJ/MM/AAAA (la date de Naissance sera dans le passé, contrôle par javax.validation). 
	 * Les trois critères devront être remplis sinon une FonctionnelleException sera jetée.
	 * 
	 * @param lastName le nom de l'utilisateur
	 * @param firstName le prénom de l'utilisateur
	 * @param birthDate la date de naissance de l'utilisateur
	 * @throws FonctionnelleException
	 */
	public void createUser(String lastName, String firstName, String birthDate)throws FonctionnelleException;
	/**
	 * Cette méthode va permettre à un utilisateur de modifier son profil (adresse, nom, prenom, date de naissance). 
	 * Le contrôle de l’adresse sera effectué dans cette méthode. 
	 * Les champs rue, code postal et ville seront obligatoire si au moins l’un d’entre eux est renseigné. 
	 * Une validation sera effectué sur le code postal (javax .validation) avec pour un nombre max de caractères à 5. 
	 * De même le nom de la ville sera limité à 50 caractères.
	 * 
	 * @param user l'utilisateur
	 * @throws FonctionnelleException
	 */
	public void updateProfil(Utilisateur user)throws FonctionnelleException;
	/**
	 * Cette méthode va permettre de créer ou de modifier l’expérience d’un utilisateur. 
	 * L’expérience devra contenir un client, une expérience minimum et une date début.
	 * 
	 * @param user l'utilisateur
	 * @param experience l'experience
	 * @throws FonctionnelleException
	 */
	public void updateSkill(Utilisateur user, Experience experience)throws FonctionnelleException;
	/**
	 * Cette méthode va retourner une liste d’utilisateurs pour les paramètres donnés.
	 * 
	 * @param lastName le nom de l'utilisateur
	 * @param firstName le prénom de l'utilisateur
	 * @param birthDate la date de naissance de l'utilisateur
	 * @return Utilisateur l'utilisateur trouvé
	 * @throws FonctionnelleException
	 */
	public Utilisateur searchUsers(String lastName, String firstName, String birthDate)throws FonctionnelleException;
	/**
	 * Cette méthode va retourner une liste d’utilisateurs pour les paramètres donnés.
	 * 
	 * @param idClient l'id du client
	 * @return List<Utilisateur> la liste d'utilisateurs trouvés
	 * @throws FonctionnelleException
	 */
	public List<Utilisateur> searchUsersByClient(Long idClient)throws FonctionnelleException;
	/**
	 * Cette méthode va retourner un utilisateur pour un identifiant donné.
	 * 
	 * @param idUser l'id de l'utilisateur
	 * @return Utilisateur l'utilisateur trouvé
	 * @throws FonctionnelleException
	 */
	public Utilisateur searchUser(Long idUser)throws FonctionnelleException;
}
