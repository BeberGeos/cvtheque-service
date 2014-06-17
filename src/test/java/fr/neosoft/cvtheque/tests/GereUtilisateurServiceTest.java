package fr.neosoft.cvtheque.tests;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.neosoft.cvtheque.dao.UtilisateurDao;
import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.utils.FonctionnelleException;
import fr.neosoft.cvtheque.utils.Utils;

public class GereUtilisateurServiceTest {
	
	@Mock
	UtilisateurDao userDaoMock;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	@Ignore
	public void testCreateUserNoName() {
		//TODO IMPLEMENTER
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		try{
			userDaoMock.create(user);
		}catch(FonctionnelleException e){
			fail("Données en entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testCreateUser() {
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		try{
			userDaoMock.create(user);
		}catch(FonctionnelleException e){
			fail("Données en entrée correctes, ne doit pas lever d'exception.");
		}
	}

}
