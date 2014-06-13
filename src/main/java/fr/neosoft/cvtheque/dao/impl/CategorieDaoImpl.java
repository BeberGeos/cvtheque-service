package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import fr.neosoft.cvtheque.dao.CategorieDao;
import fr.neosoft.cvtheque.entities.Categorie;

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

}
