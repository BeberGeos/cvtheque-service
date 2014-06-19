package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import javax.persistence.Query;

import fr.neosoft.cvtheque.dao.ClientDao;
import fr.neosoft.cvtheque.entities.Categorie;
import fr.neosoft.cvtheque.entities.Client;

/**
 * Implementation of the client Dao interface.
 * 
 * @author Adrien Cambillau
 *
 */
public class ClientDaoImpl extends GenericDaoImpl<Client> implements ClientDao {
	
	public ClientDaoImpl(ManagerDaoImpl managerDao) {
		super();
	}

	public List<Client> findAllClients() {
		List<Client> listClients = this.entityManager.createNamedQuery("Client.findAll").getResultList();
		return listClients;
	}

	public Client findClientBySiret(Long siret) {
		String jpql = "SELECT c FROM Client c WHERE c.client.siret = :siret";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("siret", siret);
		
		Client client = (Client) query.getSingleResult();
		return client;
	}

}
