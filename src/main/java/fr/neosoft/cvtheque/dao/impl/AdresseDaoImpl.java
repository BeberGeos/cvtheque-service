package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import fr.neosoft.cvtheque.dao.AdresseDao;
import fr.neosoft.cvtheque.entities.Adresse;

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

}
