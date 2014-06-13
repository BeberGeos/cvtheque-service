package fr.neosoft.cvtheque.services;

import java.util.List;

import fr.neosoft.cvtheque.entities.Categorie;
import fr.neosoft.cvtheque.entities.Client;
import fr.neosoft.cvtheque.entities.Langage;
import fr.neosoft.cvtheque.utils.FonctionnelleException;

public interface GererReferentielService {
	public abstract void createClient(Client client)throws FonctionnelleException;
	public abstract void updateClient(Client client)throws FonctionnelleException;
	public abstract void createLangage(Langage langage)throws FonctionnelleException;
	public abstract void updateLangage(Langage langage)throws FonctionnelleException;
	public abstract void createCategory(Categorie categorie)throws FonctionnelleException;
	public abstract void updateCategory(Categorie categorie)throws FonctionnelleException;
	
	public abstract Client searchClient(Long siret)throws FonctionnelleException;
	public abstract Langage searchlangage(Long idLangage)throws FonctionnelleException;
	public abstract List<Langage> searchListLangage(String libelle)throws FonctionnelleException;
	public abstract List<Categorie> searchListCategory()throws FonctionnelleException;
}
