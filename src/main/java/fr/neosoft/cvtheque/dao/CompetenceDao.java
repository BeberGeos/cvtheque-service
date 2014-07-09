package fr.neosoft.cvtheque.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.neosoft.cvtheque.entities.Competence;

/**
 * Interface for the "competence" dao.
 * 
 * @author Adrien Cambillau
 *
 */
@Stateless
@Local
public interface CompetenceDao extends GenericDao<Competence>{
	/**
	 * Retrieve all the "competence".
	 * @return List<Competence> the list of all the "competence"
	 */
	public List<Competence> findAllCompetences();
}
