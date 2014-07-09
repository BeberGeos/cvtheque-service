package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import fr.neosoft.cvtheque.dao.ExperienceDao;
import fr.neosoft.cvtheque.entities.Experience;

/**
 * Implementation of the experience Dao interface.
 * 
 * @author Adrien Cambillau
 *
 */
public class ExperienceDaoImpl extends GenericDaoImpl<Experience> implements ExperienceDao {

	public ExperienceDaoImpl(EntityManager entityManager) {
		super();
		setEntityManager(entityManager);
	}

	public List<Experience> findAllExperiences() {
		final List<Experience> listExperiences = getEntityManager().createNamedQuery("Experience.findAll").getResultList();
		return listExperiences;
	}

}
