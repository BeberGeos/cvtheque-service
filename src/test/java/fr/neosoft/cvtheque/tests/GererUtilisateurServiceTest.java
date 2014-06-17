package fr.neosoft.cvtheque.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.neosoft.cvtheque.dao.UtilisateurDao;
import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.services.GererUtilisateurService;
import fr.neosoft.cvtheque.services.impl.GererUtilisateurServiceImpl;
import fr.neosoft.cvtheque.utils.Constantes;
import fr.neosoft.cvtheque.utils.FonctionnelleException;
import fr.neosoft.cvtheque.utils.Utils;

public class GererUtilisateurServiceTest {
	
	@Mock
	UtilisateurDao userDaoMock;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateUserNoName() {
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		GererUtilisateurService gererUserTest = new GererUtilisateurServiceImpl();
		try{
			gererUserTest.createUser(user);
			fail("Un champ est vide, doit lever une exception.");
		}catch(FonctionnelleException e){
			assertEquals("Pas de nom -> Exception", e.getCodeErreur(), Constantes.FIELD_REQUIRED);
		}
	}

	@Test
	public void testCreateUser() {
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		GererUtilisateurService gererUserTest = new GererUtilisateurServiceImpl();
		try{
			gererUserTest.createUser(user);
		}catch(FonctionnelleException e){
			fail("Données en entrée correctes, ne doit pas lever d'exception.");
		}
	}

}
