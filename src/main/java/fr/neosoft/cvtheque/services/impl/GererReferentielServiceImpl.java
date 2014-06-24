package fr.neosoft.cvtheque.services.impl;

import java.util.List;

import fr.neosoft.cvtheque.dao.CategorieDao;
import fr.neosoft.cvtheque.dao.ClientDao;
import fr.neosoft.cvtheque.dao.LangageDao;
import fr.neosoft.cvtheque.dao.ManagerDao;
import fr.neosoft.cvtheque.dao.impl.ManagerDaoImpl;
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
public class GererReferentielServiceImpl implements GererReferentielService {
	private ManagerDao managerDao = new ManagerDaoImpl();
	private ClientDao clientDao = managerDao.getDaoClient();
	private LangageDao languageDao = managerDao.getDaoLangage();
	private CategorieDao categoryDao = managerDao.getDaoCategorie();

	public void createClient(final Client client) throws FonctionnelleException {
		Adresse clientAdresse = client.getAdresse();
		if(!client.getNom().isEmpty() && client.getSiret() != 0 && clientAdresse.getCodePostal() != 0 
				&& !clientAdresse.getRue().isEmpty() && !clientAdresse.getVille().isEmpty()){
			Utils.checkConstraints(client, client.toString());
			Utils.checkConstraints(clientAdresse, clientAdresse.toString());
			clientDao.create(client);
		}else{
			throw new FonctionnelleException(Constantes.FIELD_REQUIRED, client.toString());
		}
	}

	public void updateClient(Client client) throws FonctionnelleException {
		Adresse clientAdresse = client.getAdresse();
		if(!client.getNom().isEmpty() && client.getSiret() != 0 && clientAdresse.getCodePostal() != 0 
				&& !clientAdresse.getRue().isEmpty() && !clientAdresse.getVille().isEmpty()){
			Utils.checkConstraints(client, client.toString());
			Utils.checkConstraints(clientAdresse, clientAdresse.toString());
			clientDao.update(client);
		}else{
			throw new FonctionnelleException(Constantes.FIELD_REQUIRED, client.toString());
		}
	}

	public void createLangage(final Langage langage) throws FonctionnelleException {
		Langage dbLanguage = languageDao.findLanguageByName(langage.getLibelle());
		
		if(dbLanguage != null){
			throw new FonctionnelleException(Constantes.LANGUAGE_ALREADY_IN_DB, langage.toString());
		}else{
			Utils.checkConstraints(langage, langage.toString());
			languageDao.create(langage);
		}
	}

	public void updateLangage(Langage langage) throws FonctionnelleException {
		Langage dbLanguage = languageDao.findLanguageByName(langage.getLibelle());
		
		if(dbLanguage != null){
			throw new FonctionnelleException(Constantes.LANGUAGE_ALREADY_IN_DB, langage.toString());
		}else{
			Utils.checkConstraints(langage, langage.toString());
			languageDao.update(langage);
		}
	}

	public void createCategory(final Categorie categorie)
			throws FonctionnelleException {
		Categorie dbCategorie = categoryDao.findCategoryByName(categorie.getLibelle());
		
		if(dbCategorie != null){
			throw new FonctionnelleException(Constantes.CATEGORY_ALREADY_IN_DB, categorie.toString());
		}else{
			Utils.checkConstraints(categorie, categorie.toString());
			categoryDao.create(categorie);
		}
	}

	public void updateCategory(Categorie categorie)
			throws FonctionnelleException {
		Categorie dbCategorie = categoryDao.findCategoryByName(categorie.getLibelle());
		
		if(dbCategorie != null){
			throw new FonctionnelleException(Constantes.CATEGORY_ALREADY_IN_DB, categorie.toString());
		}else{
			Utils.checkConstraints(categorie, categorie.toString());
			categoryDao.update(categorie);
		}
	}

	public Client searchClient(final Long siret) throws FonctionnelleException {
		Client client = clientDao.findClientBySiret(siret);
		
		if(client == null){
			throw new FonctionnelleException(Constantes.NO_CLIENT_FOUND, String.valueOf(siret));
		}
		return client;
	}

	public Langage searchlangage(final Long idLangage) throws FonctionnelleException {
		Langage langage = languageDao.find(idLangage);
		
		if(langage == null){
			throw new FonctionnelleException(Constantes.NO_LANGUAGE_FOUND, String.valueOf(idLangage));
		}
		return langage;
	}

	public List<Langage> searchListLangage(final String libelle)
			throws FonctionnelleException {
		List<Langage> languages = languageDao.findLanguagesByName(libelle);
		return languages;
	}

	public List<Categorie> searchListCategory() throws FonctionnelleException {
		List<Categorie> categories = categoryDao.findAllCategories();
		
		if(categories.size() == 0){
			throw new FonctionnelleException(Constantes.NO_CATEGORY_FOUND, "");
		}
		return categories;
	}

}
