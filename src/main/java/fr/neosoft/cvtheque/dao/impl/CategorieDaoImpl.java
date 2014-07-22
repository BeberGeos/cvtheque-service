package fr.neosoft.cvtheque.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.neosoft.cvtheque.dao.CategorieDao;
import fr.neosoft.cvtheque.entities.Categorie;

/**
 * Implementation of the categorie Dao interface.
 * 
 * @author Adrien Cambillau
 *
 */
public class CategorieDaoImpl extends GenericDaoImpl<Categorie> implements CategorieDao {
	
	public CategorieDaoImpl(EntityManager entityManager) {
		super();
		setEntityManager(entityManager);
	}
	
	public CategorieDaoImpl(){
		super();
	}

	public List<Categorie> findAllCategories() {
		final List<Categorie> listCategories = getEntityManager().createNamedQuery("Categorie.findAll").getResultList();
		return listCategories;
	}

	public Categorie findCategoryByName(String libelle) {
		final String jpql = "SELECT c FROM Categorie c WHERE c.categorie.libelle = :libelle";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter("libelle", libelle);
		
		final Categorie categorie = (Categorie) query.getSingleResult();
		return categorie;
	}

}
