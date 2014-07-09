package fr.neosoft.cvtheque.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.neosoft.cvtheque.entities.Langage;

/**
 * Interface for the "langage" dao.
 * 
 * @author Adrien Cambillau
 *
 */
@Stateless
@Local
public interface LangageDao extends GenericDao<Langage>{
	/**
	 * Retrieve all the "langage".
	 * @return List<Langage> the list of all the "langage"
	 */
	public List<Langage> findAllLangages();
	/**
	 * Recherche un langage suivant son libellé.
	 * @param libelle le libellé du langage recherché
	 * @return Langage le langage trouvé
	 */
	public Langage findLanguageByName(final String libelle);
	/**
	 * Recherche une liste de langages contenant le libellé en paramètre.
	 * @param libelle le libelle du langage
	 * @return List<Langage> la liste des langages contenant le libellé
	 */
	public List<Langage> findLanguagesByName(final String libelle);
}
