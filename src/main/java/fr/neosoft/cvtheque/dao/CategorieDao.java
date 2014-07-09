package fr.neosoft.cvtheque.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.neosoft.cvtheque.entities.Categorie;

/**
 * Interface for the "categorie" dao.
 * 
 * @author Adrien Cambillau
 *
 */
@Stateless
@Local
public interface CategorieDao extends GenericDao<Categorie>{
	/**
	 * Retrieve all the "categorie".
	 * @return List<Categorie> the list of all the "categorie"
	 */
	public List<Categorie> findAllCategories();
	/**
	 * Trouve une catégorie suivant le nom.
	 * @param libelle le libellé de la catégorie recherchée
	 * @return Categorie la catégorie trouvée
	 */
	public Categorie findCategoryByName(final String libelle);
}
