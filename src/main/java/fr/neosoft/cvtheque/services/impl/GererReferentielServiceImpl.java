package fr.neosoft.cvtheque.services.impl;

import java.util.List;

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
