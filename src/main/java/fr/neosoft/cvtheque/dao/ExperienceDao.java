package fr.neosoft.cvtheque.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.neosoft.cvtheque.entities.Experience;

/**
 * Interface for the "experience" dao.
 * 
 * @author Adrien Cambillau
 *
 */
@Stateless
@Local
public interface ExperienceDao extends GenericDao<Experience>{
	/**
	 * Retrieve all the "experience".
	 * @return List<Experience> the list of all the "experience"
	 */
	public List<Experience> findAllExperiences();
}
