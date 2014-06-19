package fr.neosoft.cvtheque.dao;

import java.util.List;

import fr.neosoft.cvtheque.entities.Client;

/**
 * Interface for the "client" dao.
 * 
 * @author Adrien Cambillau
 *
 */
public interface ClientDao extends GenericDao<Client>{
	/**
	 * Retrieve all the "client".
	 * @return List<Client> the list of all the "client"
	 */
	public List<Client> findAllClients();

	/**
	 * Cherche un client suivant son numéro SIRET.
	 * @param siret le numéro SIRET du client
	 * @return Client le client trouvé
	 */
	public Client findClientBySiret(final Long siret);
}
