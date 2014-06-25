package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.neosoft.cvtheque.dao.AdresseDao;
import fr.neosoft.cvtheque.entities.Adresse;

/**
 * Implementation of the adresse Dao interface.
 * 
 * @author Adrien Cambillau
 *
 */
public class AdresseDaoImpl extends GenericDaoImpl<Adresse> implements AdresseDao {
	
	public AdresseDaoImpl(EntityManager entityManager) {
		super();
		setEntityManager(entityManager);
	}
	
	public List<Adresse> findAllAdresses(){
		final List<Adresse> listAdresses = getEntityManager().createNamedQuery("Adresse.findAll").getResultList();
		
		return listAdresses;
	}

	public List<Adresse> findAdresses(final String rue, final String ville, final int codePostal) {
		String jpql = "SELECT a FROM Adresse a WHERE a.adresse.rue = :rue AND a.adresse.ville = :ville AND a.adresse.codePostal = :codePostal";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter("rue", rue);
		query.setParameter("ville", ville);
		query.setParameter("codePostal", codePostal);
		
		List<Adresse> adresses = query.getResultList();
		return adresses;
	}

}
