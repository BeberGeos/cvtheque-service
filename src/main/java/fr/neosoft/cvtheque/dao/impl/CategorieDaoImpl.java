package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import javax.persistence.Query;

import fr.neosoft.cvtheque.dao.CategorieDao;
import fr.neosoft.cvtheque.entities.Categorie;
import fr.neosoft.cvtheque.entities.Langage;

/**
 * Implementation of the categorie Dao interface.
 * 
 * @author Adrien Cambillau
 *
 */
public class CategorieDaoImpl extends GenericDaoImpl<Categorie> implements CategorieDao {
	
	public CategorieDaoImpl(ManagerDaoImpl managerDao) {
		super();
	}

	public List<Categorie> findAllCategories() {
		List<Categorie> listCategories = this.entityManager.createNamedQuery("Categorie.findAll").getResultList();
		return listCategories;
	}

	public Categorie findCategoryByName(String libelle) {
		String jpql = "SELECT c FROM Categorie c WHERE c.categorie.libelle = :libelle";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("libelle", libelle);
		
		Categorie categorie = (Categorie) query.getSingleResult();
		return categorie;
	}

}
