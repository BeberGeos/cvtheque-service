package fr.neosoft.cvtheque.dao;

import javax.persistence.EntityManager;

/**
 * Interface for the manager dao.
 * 
 * @author Adrien Cambillau
 *
 */
public interface ManagerDao {
	
	/**
	 * Connect to the database.
	 * @throws Exception
	 */
	public void connect() throws Exception;
	
	/**
	 * Disconnect from the database.
	 * @throws Exception
	 */
	public void disconnect() throws Exception;
	
	/**
	 * Retrieve the entity manager.
	 * @return EntityManager the current entity manager
	 */
	public EntityManager getEntityManager();
	
	/**
	 * Retrieve the "adresse" dao.
	 * @return AdresseDao the dao to retrieve
	 */
	public AdresseDao getDaoAdresse();
	
	/**
	 * Retrieve the "categorie" dao.
	 * @return CategorieDao the dao to retrieve
	 */
	public CategorieDao getDaoCategorie();
	
	/**
	 * Retrieve the "client" dao.
	 * @return ClientDao the dao to retrieve
	 */
	public ClientDao getDaoClient();
	
	/**
	 * Retrieve the "competence" dao.
	 * @return CompetenceDao the dao to retrieve
	 */
	public CompetenceDao getDaoCompetence();
	
	/**
	 * Retrieve the "experience" dao.
	 * @return ExperienceDao the dao to retrieve
	 */
	public ExperienceDao getDaoExperience();
	
	/**
	 * Retrieve the "langage" dao.
	 * @return LangageDao the dao to retrieve
	 */
	public LangageDao getDaoLangage();
	
	/**
	 * Retrieve the "utilisateur" dao.
	 * @return UtilisateurDao the dao to retrieve
	 */
	public UtilisateurDao getDaoUtilisateur();
}
