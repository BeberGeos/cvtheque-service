package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import fr.neosoft.cvtheque.dao.ExperienceDao;
import fr.neosoft.cvtheque.entities.Experience;

/**
 * Implementation of the experience Dao interface.
 * 
 * @author Adrien Cambillau
 *
 */
public class ExperienceDaoImpl extends GenericDaoImpl<Experience> implements ExperienceDao {

	public ExperienceDaoImpl(ManagerDaoImpl managerDao) {
		super();
	}

	public List<Experience> findAllExperiences() {
		List<Experience> listExperiences = this.entityManager.createNamedQuery("Experience.findAll").getResultList();
		return listExperiences;
	}

}
