package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import fr.neosoft.cvtheque.dao.CompetenceDao;
import fr.neosoft.cvtheque.entities.Competence;

/**
 * Implementation of the competence Dao interface.
 * 
 * @author Adrien Cambillau
 *
 */
public class CompetenceDaoImpl extends GenericDaoImpl<Competence> implements CompetenceDao {

	public CompetenceDaoImpl(ManagerDaoImpl managerDao) {
		super();
	}

	public List<Competence> findAllCompetences() {
		List<Competence> listCompetences = this.entityManager.createNamedQuery("Competence.findAll").getResultList();
		return listCompetences;
	}

}
