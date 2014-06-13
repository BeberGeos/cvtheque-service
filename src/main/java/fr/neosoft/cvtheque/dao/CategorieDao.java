package fr.neosoft.cvtheque.dao;

import java.util.List;

import fr.neosoft.cvtheque.entities.Categorie;

/**
 * Interface for the "categorie" dao.
 * 
 * @author Adrien Cambillau
 *
 */
public interface CategorieDao extends GenericDao<Categorie>{
	/**
	 * Retrieve all the "categorie".
	 * @return List<Categorie> the list of all the "categorie"
	 */
	public List<Categorie> findAllCategories();
}
