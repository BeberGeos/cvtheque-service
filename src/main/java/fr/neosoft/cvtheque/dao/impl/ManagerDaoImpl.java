package fr.neosoft.cvtheque.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.neosoft.cvtheque.dao.ManagerDao;

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

	public AdresseDaoImpl getDaoAdresse() {
		if(daoAdresse == null){
			daoAdresse = new AdresseDaoImpl(this);
		}
		return daoAdresse;
	}

	public CategorieDaoImpl getDaoCategorie() {
		if(daoCategorie == null){
			daoCategorie = new CategorieDaoImpl(this);
		}
		return daoCategorie;
	}

	public ClientDaoImpl getDaoClient() {
		if(daoClient == null){
			daoClient = new ClientDaoImpl(this);
		}
		return daoClient;
	}

	public CompetenceDaoImpl getDaoCompetence() {
		if(daoCompetence == null){
			daoCompetence = new CompetenceDaoImpl(this);
		}
		return daoCompetence;
	}

	public ExperienceDaoImpl getDaoExperience() {
		if(daoExperience == null){
			daoExperience = new ExperienceDaoImpl(this);
		}
		return daoExperience;
	}

	public LangageDaoImpl getDaoLangage() {
		if(daoLangage == null){
			daoLangage = new LangageDaoImpl(this);
		}
		return daoLangage;
	}

	public UtilisateurDaoImpl getDaoUtilisateur() {
		if(daoUtilisateur == null){
			daoUtilisateur = new UtilisateurDaoImpl(this);
		}
		return daoUtilisateur;
	}

}
