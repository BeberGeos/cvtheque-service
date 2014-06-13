package fr.neosoft.cvtheque.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.neosoft.cvtheque.dao.AdresseDao;
import fr.neosoft.cvtheque.dao.CategorieDao;
import fr.neosoft.cvtheque.dao.ClientDao;
import fr.neosoft.cvtheque.dao.CompetenceDao;
import fr.neosoft.cvtheque.dao.ExperienceDao;
import fr.neosoft.cvtheque.dao.LangageDao;
import fr.neosoft.cvtheque.dao.ManagerDao;
import fr.neosoft.cvtheque.dao.UtilisateurDao;

/**
 * Implementation of the manager Dao interface.
 * 
 * @author Adrien Cambillau
 *
 */
public class ManagerDaoImpl implements ManagerDao {
	
	private EntityManagerFactory emf = null;
	private EntityManager entityManager = null;
	
	private AdresseDaoImpl daoAdresse;
	private CategorieDaoImpl daoCategorie;
	private ClientDaoImpl daoClient;
	private CompetenceDaoImpl daoCompetence;
	private ExperienceDaoImpl daoExperience;
	private LangageDaoImpl daoLangage;
	private UtilisateurDaoImpl daoUtilisateur;

	public void connect() throws Exception {
		disconnect();
		
		emf = Persistence.createEntityManagerFactory("cvtheque");
		entityManager = emf.createEntityManager();
	}

	public void disconnect() throws Exception {
		if(entityManager != null){
			entityManager.close();
			entityManager = null;
		}
		
		if(emf != null){
			emf.close();
			emf = null;
		}
	}
	
	public ManagerDaoImpl(){
		super();
	}
	
	public EntityManager getEntityManager(){
		return entityManager;
	}

	public AdresseDao getDaoAdresse() {
		if(daoAdresse == null){
			daoAdresse = new AdresseDaoImpl(this);
		}
		return daoAdresse;
	}

	public CategorieDao getDaoCategorie() {
		if(daoCategorie == null){
			daoCategorie = new CategorieDaoImpl(this);
		}
		return daoCategorie;
	}

	public ClientDao getDaoClient() {
		if(daoClient == null){
			daoClient = new ClientDaoImpl(this);
		}
		return daoClient;
	}

	public CompetenceDao getDaoCompetence() {
		if(daoCompetence == null){
			daoCompetence = new CompetenceDaoImpl(this);
		}
		return daoCompetence;
	}

	public ExperienceDao getDaoExperience() {
		if(daoExperience == null){
			daoExperience = new ExperienceDaoImpl(this);
		}
		return daoExperience;
	}

	public LangageDao getDaoLangage() {
		if(daoLangage == null){
			daoLangage = new LangageDaoImpl(this);
		}
		return daoLangage;
	}

	public UtilisateurDao getDaoUtilisateur() {
		if(daoUtilisateur == null){
			daoUtilisateur = new UtilisateurDaoImpl(this);
		}
		return daoUtilisateur;
	}

}
