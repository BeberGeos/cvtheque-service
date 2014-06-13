package fr.neosoft.cvtheque.services.impl;

import java.util.List;

import fr.neosoft.cvtheque.entities.Categorie;
import fr.neosoft.cvtheque.entities.Client;
import fr.neosoft.cvtheque.entities.Langage;
import fr.neosoft.cvtheque.services.GererReferentielService;
import fr.neosoft.cvtheque.utils.FonctionnelleException;

/**
 * Implémentation de l'interface de gestion des clients, langages et catégories.
 * 
 * @author Adrien Cambillau
 *
 */
public class GererReferentielServiceImpl implements GererReferentielService {

	public void createClient(final Client client) throws FonctionnelleException {
		// TODO Auto-generated method stub

	}

	public void updateClient(Client client) throws FonctionnelleException {
		// TODO Auto-generated method stub

	}

	public void createLangage(final Langage langage) throws FonctionnelleException {
		// TODO Auto-generated method stub

	}

	public void updateLangage(Langage langage) throws FonctionnelleException {
		// TODO Auto-generated method stub

	}

	public void createCategory(final Categorie categorie)
			throws FonctionnelleException {
		// TODO Auto-generated method stub

	}

	public void updateCategory(Categorie categorie)
			throws FonctionnelleException {
		// TODO Auto-generated method stub

	}

	public Client searchClient(final Long siret) throws FonctionnelleException {
		// TODO Auto-generated method stub
		return null;
	}

	public Langage searchlangage(final Long idLangage) throws FonctionnelleException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Langage> searchListLangage(final String libelle)
			throws FonctionnelleException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Categorie> searchListCategory() throws FonctionnelleException {
		// TODO Auto-generated method stub
		return null;
	}

}
