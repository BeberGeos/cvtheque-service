package fr.neosoft.cvtheque.services.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.neosoft.cvtheque.dao.CategorieDao;
import fr.neosoft.cvtheque.dao.ClientDao;
import fr.neosoft.cvtheque.dao.LangageDao;
import fr.neosoft.cvtheque.entities.Adresse;
import fr.neosoft.cvtheque.entities.Categorie;
import fr.neosoft.cvtheque.entities.Client;
import fr.neosoft.cvtheque.entities.Langage;
import fr.neosoft.cvtheque.services.GererReferentielService;
import fr.neosoft.cvtheque.utils.Constantes;
import fr.neosoft.cvtheque.utils.FonctionnelleException;
import fr.neosoft.cvtheque.utils.Utils;

/**
 * Implémentation de l'interface de gestion des clients, langages et catégories.
 * 
 * @author Adrien Cambillau
 *
 */
@Stateless
@Remote(GererReferentielService.class)
public class GererReferentielServiceImpl implements GererReferentielService {
	private ClientDao clientDao;
	private LangageDao languageDao;
	private CategorieDao categoryDao;

	public void createClient(final Client client) throws FonctionnelleException {
		Client dbClient = getClientDao().find(client.getId());

		if(dbClient != null){
			throw new FonctionnelleException(Constantes.CLIENT_ALREADY_IN_DB, client.toString());
		}else{
			Adresse clientAdresse = client.getAdresse();
			if(!client.getNom().isEmpty() && client.getSiret() != 0 && clientAdresse != null && clientAdresse.getCodePostal() != 0 
					&& !clientAdresse.getRue().isEmpty() && !clientAdresse.getVille().isEmpty()){
				Utils.checkConstraints(client, client.toString());
				Utils.checkConstraints(clientAdresse, clientAdresse.toString());
				getClientDao().create(client);
			}else{
				throw new FonctionnelleException(Constantes.FIELD_REQUIRED, client.toString());
			}
		}
	}

	public void updateClient(Client client) throws FonctionnelleException {
		Adresse clientAdresse = client.getAdresse();
		if(!client.getNom().isEmpty() && client.getSiret() != 0 && clientAdresse != null && clientAdresse.getCodePostal() != 0 
				&& !clientAdresse.getRue().isEmpty() && !clientAdresse.getVille().isEmpty()){
			Utils.checkConstraints(client, client.toString());
			Utils.checkConstraints(clientAdresse, clientAdresse.toString());
			getClientDao().update(client);
		}else{
			throw new FonctionnelleException(Constantes.FIELD_REQUIRED, client.toString());
		}
	}

	public void createLangage(final Langage langage) throws FonctionnelleException {
		Langage dbLanguage = getLanguageDao().findLanguageByName(langage.getLibelle());

		if(dbLanguage != null){
			throw new FonctionnelleException(Constantes.LANGUAGE_ALREADY_IN_DB, langage.toString());
		}else if(!langage.getLibelle().isEmpty()){
			Utils.checkConstraints(langage, langage.toString());
			getLanguageDao().create(langage);
		}else{
			throw new FonctionnelleException(Constantes.FIELD_REQUIRED, langage.toString());
		}
	}

	public void updateLangage(Langage langage) throws FonctionnelleException {
		Langage dbLanguage = getLanguageDao().findLanguageByName(langage.getLibelle());

		if(dbLanguage != null){
			throw new FonctionnelleException(Constantes.LANGUAGE_ALREADY_IN_DB, langage.toString());
		}else if(!langage.getLibelle().isEmpty()){
			Utils.checkConstraints(langage, langage.toString());
			getLanguageDao().update(langage);
		}else{
			throw new FonctionnelleException(Constantes.FIELD_REQUIRED, langage.toString());
		}
	}

	public void createCategory(final Categorie categorie)
			throws FonctionnelleException {
		Categorie dbCategorie = getCategoryDao().findCategoryByName(categorie.getLibelle());

		if(dbCategorie != null){
			throw new FonctionnelleException(Constantes.CATEGORY_ALREADY_IN_DB, categorie.toString());
		}else if(!categorie.getLibelle().isEmpty()){
			Utils.checkConstraints(categorie, categorie.toString());
			getCategoryDao().create(categorie);
		}else{
			throw new FonctionnelleException(Constantes.FIELD_REQUIRED, categorie.toString());
		}
	}

	public void updateCategory(Categorie categorie)
			throws FonctionnelleException {
		Categorie dbCategorie = getCategoryDao().findCategoryByName(categorie.getLibelle());

		if(dbCategorie != null){
			throw new FonctionnelleException(Constantes.CATEGORY_ALREADY_IN_DB, categorie.toString());
		}else if(!categorie.getLibelle().isEmpty()){
			Utils.checkConstraints(categorie, categorie.toString());
			getCategoryDao().update(categorie);
		}else{
			throw new FonctionnelleException(Constantes.FIELD_REQUIRED, categorie.toString());
		}
	}

	public Client searchClient(final Long siret) throws FonctionnelleException {
		Client client = getClientDao().findClientBySiret(siret);

		if(client == null){
			throw new FonctionnelleException(Constantes.NO_CLIENT_FOUND, String.valueOf(siret));
		}
		return client;
	}

	public Langage searchLangage(final Long idLangage) throws FonctionnelleException {
		Langage langage = getLanguageDao().find(idLangage);

		if(langage == null){
			throw new FonctionnelleException(Constantes.NO_LANGUAGE_FOUND, String.valueOf(idLangage));
		}
		return langage;
	}

	public List<Langage> searchListLangage(final String libelle)
			throws FonctionnelleException {
		List<Langage> languages = getLanguageDao().findLanguagesByName(libelle);
		if(languages.isEmpty()){
			throw new FonctionnelleException(Constantes.NO_LANGUAGE_FOUND, "");
		}
		return languages;
	}

	public List<Categorie> searchListCategory() throws FonctionnelleException {
		List<Categorie> categories = getCategoryDao().findAllCategories();

		if(categories.isEmpty()){
			throw new FonctionnelleException(Constantes.NO_CATEGORY_FOUND, "");
		}
		return categories;
	}

	public ClientDao getClientDao() {
		return clientDao;
	}

	public LangageDao getLanguageDao() {
		return languageDao;
	}

	public CategorieDao getCategoryDao() {
		return categoryDao;
	}

}
