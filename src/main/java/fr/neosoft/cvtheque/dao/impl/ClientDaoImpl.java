package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import fr.neosoft.cvtheque.dao.ClientDao;
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

}
