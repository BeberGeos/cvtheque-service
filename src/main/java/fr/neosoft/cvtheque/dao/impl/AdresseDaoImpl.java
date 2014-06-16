package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import javax.persistence.Query;

import fr.neosoft.cvtheque.dao.AdresseDao;
import fr.neosoft.cvtheque.entities.Adresse;
import fr.neosoft.cvtheque.entities.Utilisateur;

/**
 * Implementation of the adresse Dao interface.
 * 
 * @author Adrien Cambillau
 *
 */
public class AdresseDaoImpl extends GenericDaoImpl<Adresse> implements AdresseDao {
	
	public AdresseDaoImpl(ManagerDaoImpl managerDao) {
		super();
	}
	
	public List<Adresse> findAllAdresses(){
		List<Adresse> listAdresses = this.entityManager.createNamedQuery("Adresse.findAll").getResultList();
		
		return listAdresses;
	}

	public List<Adresse> findAdresses(final String rue, final String ville, final int codePostal) {
		String jpql = "SELECT a FROM Adresse a WHERE a.adresse.rue = :rue AND a.adresse.ville = :ville AND a.adresse.codePostal = :codePostal";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("rue", rue);
		query.setParameter("ville", ville);
		query.setParameter("codePostal", codePostal);
		
		List<Adresse> adresses = query.getResultList();
		return adresses;
	}

}
