package fr.neosoft.cvtheque.services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.neosoft.cvtheque.entities.Categorie;
import fr.neosoft.cvtheque.entities.Client;
import fr.neosoft.cvtheque.entities.Langage;
import fr.neosoft.cvtheque.utils.FonctionnelleException;

/**
 * Interface de gestion des clients, langages et categories.
 * 
 * @author Adrien Cambillau
 *
 */
@Stateless
@Remote(GererReferentielService.class)
public interface GererReferentielService {
	/**
	 * Cette méthode va permettre de créer un client dans l’application. 
	 * Ce client devra comporter un nom, un SIRET et une adresse. 
	 * La validation de l’adresse sera sur les champs obligatoires de rue, code postal et ville. 
	 * La validation du SIRET sera effectuée sur un nombre max de caractères (14).
	 * 
	 * @param client le client à créer
	 * @throws FonctionnelleException
	 */
	public void createClient(final Client client)throws FonctionnelleException;
	/**
	 * Cette méthode va permettre de modifier un client dans l’application. 
	 * Les contrôles seront identiques à la création.
	 * 
	 * @param client le client à modifier
	 * @throws FonctionnelleException
	 */
	public void updateClient(Client client)throws FonctionnelleException;
	/**
	 * Cette méthode va permettre la création d’un langage de programmation. 
	 * Le libellé du Langage sera limité à 20 caractères et devra être unique en base de données.
	 * 
	 * @param langage le langage à créer
	 * @throws FonctionnelleException
	 */
	public void createLangage(final Langage langage)throws FonctionnelleException;
	/**
	 * Cette méthode va permettre de modifier un langage de programmation dans l’application. 
	 * Les contrôles seront identiques à la création.
	 * 
	 * @param langage le langage à modifier
	 * @throws FonctionnelleException
	 */
	public void updateLangage(Langage langage)throws FonctionnelleException;
	/**
	 * Cette méthode va permettre la création d’une catégorie. 
	 * Le libellé de la catégorie sera limité à 20 caractères et devra être unique en base de données.
	 * 
	 * @param categorie la categorie à créer
	 * @throws FonctionnelleException
	 */
	public void createCategory(final Categorie categorie)throws FonctionnelleException;
	/**
	 * Cette méthode va permettre de modifier une catégorie dans l’application. 
	 * Les contrôles seront identiques à la création. 
	 * 
	 * @param categorie la categorie à modifier
	 * @throws FonctionnelleException
	 */
	public void updateCategory(Categorie categorie)throws FonctionnelleException;
	
	/**
	 * Cette méthode va retourner un Client pour le paramètre donné.
	 * 
	 * @param siret le numéro siret du client
	 * @return Client le client trouvé
	 * @throws FonctionnelleException
	 */
	public Client searchClient(final Long siret)throws FonctionnelleException;
	/**
	 * Cette méthode va retourner un Langage pour le paramètre donné.
	 * 
	 * @param idLangage l'id du langage
	 * @return Langage le langage trouvé
	 * @throws FonctionnelleException
	 */
	public Langage searchLangage(final Long idLangage)throws FonctionnelleException;
	/**
	 * Cette méthode va retourner une liste de langage.
	 * 
	 * @param libelle le libelle du langage
	 * @return List<Langage> la liste des langages trouvés
	 * @throws FonctionnelleException
	 */
	public List<Langage> searchListLangage(final String libelle)throws FonctionnelleException;
	/**
	 * Cette méthode va retourner une liste de Categorie.
	 * 
	 * @return List<Categorie> la liste des categories trouvées
	 * @throws FonctionnelleException
	 */
	public List<Categorie> searchListCategory()throws FonctionnelleException;
}
