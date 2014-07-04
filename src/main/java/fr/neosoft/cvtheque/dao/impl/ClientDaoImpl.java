package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.neosoft.cvtheque.dao.ClientDao;
import fr.neosoft.cvtheque.entities.Client;

/**
 * Implementation of the client Dao interface.
 * 
 * @author Adrien Cambillau
 *
 */
@Stateless
@Local
public class ClientDaoImpl extends GenericDaoImpl<Client> implements ClientDao {
	
	public ClientDaoImpl(){
		super();
	}
	
	public ClientDaoImpl(EntityManager entityManager) {
		super();
		setEntityManager(entityManager);
	}

	public List<Client> findAllClients() {
		final List<Client> listClients = getEntityManager().createNamedQuery("Client.findAll").getResultList();
		return listClients;
	}

	public Client findClientBySiret(Long siret) {
		final String jpql = "SELECT c FROM Client c WHERE c.client.siret = :siret";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter("siret", siret);
		
		final Client client = (Client) query.getSingleResult();
		return client;
	}

}
