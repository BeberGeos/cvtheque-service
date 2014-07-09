package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import fr.neosoft.cvtheque.dao.CompetenceDao;
import fr.neosoft.cvtheque.entities.Competence;

/**
 * Implementation of the competence Dao interface.
 * 
 * @author Adrien Cambillau
 *
 */
public class CompetenceDaoImpl extends GenericDaoImpl<Competence> implements CompetenceDao {

	public CompetenceDaoImpl(EntityManager entityManager) {
		super();
		setEntityManager(entityManager);
	}

	public List<Competence> findAllCompetences() {
		final List<Competence> listCompetences = getEntityManager().createNamedQuery("Competence.findAll").getResultList();
		return listCompetences;
	}

}
