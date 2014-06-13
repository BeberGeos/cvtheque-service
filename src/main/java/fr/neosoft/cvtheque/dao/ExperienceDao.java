package fr.neosoft.cvtheque.dao;

import java.util.List;

import fr.neosoft.cvtheque.entities.Experience;

/**
 * Interface for the "experience" dao.
 * 
 * @author Adrien Cambillau
 *
 */
public interface ExperienceDao extends GenericDao<Experience>{
	/**
	 * Retrieve all the "experience".
	 * @return List<Experience> the list of all the "experience"
	 */
	public List<Experience> findAllExperiences();
}
