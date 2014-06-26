package fr.neosoft.cvtheque.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.neosoft.cvtheque.dao.AdresseDao;
import fr.neosoft.cvtheque.dao.ExperienceDao;
import fr.neosoft.cvtheque.dao.UtilisateurDao;
import fr.neosoft.cvtheque.entities.Adresse;
import fr.neosoft.cvtheque.entities.Client;
import fr.neosoft.cvtheque.entities.Competence;
import fr.neosoft.cvtheque.entities.Experience;
import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.services.impl.GererUtilisateurServiceImpl;
import fr.neosoft.cvtheque.utils.Constantes;
import fr.neosoft.cvtheque.utils.FonctionnelleException;
import fr.neosoft.cvtheque.utils.Utils;

public class GererUtilisateurServiceTest {

	@Mock
	private AdresseDao adresseDaoMock;

	@Mock
	private List<Competence> compsMock;
	
	@Mock
	private List<Utilisateur> usersMock;

	@Mock
	private Client cltMock;
	
	@Mock
	private Experience expMock;

	@Mock
	private UtilisateurDao userDaoMock;
	
	@Mock
	private ExperienceDao experienceDaoMock;

	@Mock
	private GererUtilisateurServiceImpl gererUserTest;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateUserNoName() {
		Utilisateur user = new Utilisateur();

		Mockito.doCallRealMethod().when(gererUserTest).createUser(Mockito.any());
		Mockito.when(gererUserTest.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(userDaoMock.find(0)).thenReturn(null);

		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		try{
			gererUserTest.createUser(user);
			fail("Un champ est vide, doit lever une exception.");
		}catch(FonctionnelleException e){
			assertEquals("Pas de nom -> Exception", e.getCodeErreur(), Constantes.FIELD_REQUIRED);
		}
	}

	@Test
	public void testCreateUser() {
		Mockito.doCallRealMethod().when(gererUserTest).createUser(Mockito.any());
		Mockito.when(gererUserTest.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(userDaoMock.find(0)).thenReturn(null);

		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		try{
			gererUserTest.createUser(user);
		}catch(FonctionnelleException e){
			fail("Données en entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testCreateUserWithUserAlreadyInDB() {
		Mockito.doCallRealMethod().when(gererUserTest).createUser(Mockito.any());
		Mockito.when(gererUserTest.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(userDaoMock.find(0)).thenReturn(new Utilisateur());

		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		try{
			gererUserTest.createUser(user);
			fail("Devrait lever une exception, utilisateur déjà en BDD.");
		}catch(FonctionnelleException e){
			assertEquals("Utilisateur déjà en BDD -> exception", e.getCodeErreur(), Constantes.USER_ALREADY_IN_DB);
		}
	}

	@Test
	public void testUpdateprofil(){
		Mockito.doCallRealMethod().when(gererUserTest).updateProfil(Mockito.any());
		Mockito.when(gererUserTest.getAdresseDao()).thenReturn(adresseDaoMock);
		Mockito.when(gererUserTest.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(adresseDaoMock.find(0)).thenReturn(null);
		
		Adresse adr = new Adresse();
		adr.setCodePostal(33760);
		adr.setVille("Ville");
		adr.setRue("Rue");
		
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		user.setAdresse(adr);
		gererUserTest.createUser(user);
		try{
			user.setNom("TestNom");
			gererUserTest.updateProfil(user);
		}catch(FonctionnelleException e){
			fail("Données en entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testUpdateprofilAdresseInDB(){
		Adresse adr = new Adresse();
		adr.setCodePostal(33760);
		adr.setVille("Ville");
		adr.setRue("Rue");
		
		Mockito.doCallRealMethod().when(gererUserTest).updateProfil(Mockito.any());
		Mockito.when(gererUserTest.getAdresseDao()).thenReturn(adresseDaoMock);
		Mockito.when(gererUserTest.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(adresseDaoMock.find(0)).thenReturn(adr);

		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		gererUserTest.createUser(user);
		user.setAdresse(adr);
		try{
			user.setNom("TestNom");
			gererUserTest.updateProfil(user);
		}catch(FonctionnelleException e){
			fail("Données en entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testUpdateProfilNoName() {
		Mockito.doCallRealMethod().when(gererUserTest).updateProfil(Mockito.any());
		Mockito.when(gererUserTest.getAdresseDao()).thenReturn(adresseDaoMock);
		Mockito.when(adresseDaoMock.find(0)).thenReturn(null);

		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		gererUserTest.createUser(user);
		try{
			user.setNom("");
			gererUserTest.updateProfil(user);
			fail("Un champ est vide, doit lever une exception.");
		}catch(FonctionnelleException e){
			assertEquals("Pas de nom -> Exception", e.getCodeErreur(), Constantes.FIELD_REQUIRED);
		}
	}

	@Test
	public void testUpdateSkill(){
		Mockito.doCallRealMethod().when(gererUserTest).updateSkill(Mockito.any(), Mockito.any());
		Mockito.when(gererUserTest.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(gererUserTest.getExperienceDao()).thenReturn(experienceDaoMock);
		Mockito.when(expMock.getClient()).thenReturn(cltMock);
		Mockito.when(expMock.getCompetences()).thenReturn(compsMock);
		Mockito.when(expMock.getCompetences().isEmpty()).thenReturn(false);
		Mockito.when(expMock.getDateDebut()).thenReturn(Utils.createDateFromString("17/01/1991"));
		
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		user.setExperiences(new ArrayList<Experience>());

		try{
			gererUserTest.updateSkill(user, expMock);
		}catch(FonctionnelleException e){
			fail("Données d'entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testUpdateSkillNoClient(){
		Mockito.doCallRealMethod().when(gererUserTest).updateSkill(Mockito.any(), Mockito.any());
		Mockito.when(gererUserTest.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(gererUserTest.getExperienceDao()).thenReturn(experienceDaoMock);
		Mockito.when(expMock.getClient()).thenReturn(null);
		Mockito.when(expMock.getCompetences()).thenReturn(compsMock);
		Mockito.when(expMock.getCompetences().isEmpty()).thenReturn(false);
		Mockito.when(expMock.getDateDebut()).thenReturn(Utils.createDateFromString("17/01/1991"));
		
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);

		try{
			gererUserTest.updateSkill(user, expMock);
			fail("Client null -> Exception");
		}catch(FonctionnelleException e){
			assertEquals("Client null -> exception", e.getCodeErreur(), Constantes.EXPERIENCE_NOT_COMPLETE);
		}
	}

	@Test
	public void testUpdateSkillNoCompetence(){
		Mockito.doCallRealMethod().when(gererUserTest).updateSkill(Mockito.any(), Mockito.any());
		Mockito.when(gererUserTest.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(gererUserTest.getExperienceDao()).thenReturn(experienceDaoMock);
		Mockito.when(expMock.getClient()).thenReturn(cltMock);
		Mockito.when(expMock.getCompetences()).thenReturn(compsMock);
		Mockito.when(expMock.getCompetences().isEmpty()).thenReturn(true);
		Mockito.when(expMock.getDateDebut()).thenReturn(Utils.createDateFromString("17/01/1991"));
		
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);

		try{
			gererUserTest.updateSkill(user, expMock);
			fail("Competence null -> Exception");
		}catch(FonctionnelleException e){
			assertEquals("Competence null -> exception", e.getCodeErreur(), Constantes.EXPERIENCE_NOT_COMPLETE);
		}
	}

	@Test
	public void testUpdateSkillNoDate(){
		Mockito.doCallRealMethod().when(gererUserTest).updateSkill(Mockito.any(), Mockito.any());
		Mockito.when(gererUserTest.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(gererUserTest.getExperienceDao()).thenReturn(experienceDaoMock);
		Mockito.when(expMock.getClient()).thenReturn(cltMock);
		Mockito.when(expMock.getCompetences()).thenReturn(compsMock);
		Mockito.when(expMock.getCompetences().isEmpty()).thenReturn(false);
		Mockito.when(expMock.getDateDebut()).thenReturn(null);
		
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);

		try{
			gererUserTest.updateSkill(user, expMock);
			fail("Date null -> Exception");
		}catch(FonctionnelleException e){
			assertEquals("Date null -> exception", e.getCodeErreur(), Constantes.EXPERIENCE_NOT_COMPLETE);
		}
	}

	@Test
	public void testSearchUsers(){
		Mockito.doCallRealMethod().when(gererUserTest).searchUsers(Mockito.any(), Mockito.any(), Mockito.any());
		Mockito.when(gererUserTest.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(userDaoMock.findUsers(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(usersMock);
		Mockito.when(usersMock.isEmpty()).thenReturn(false);
		Mockito.when(usersMock.size()).thenReturn(5);

		try{
			List<Utilisateur> users = gererUserTest.searchUsers("Nom", "Prenom", "17/01/1991");
			assertNotEquals(users.size(), 0);
		}catch(FonctionnelleException e){
			fail("Ne devrait pas lever d'exception.");
		}
	}

	@Test
	public void testSearchUsersByClient(){
		Mockito.doCallRealMethod().when(gererUserTest).searchUsersByClient(Mockito.any());
		Mockito.when(gererUserTest.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(userDaoMock.findUsersByClient(0L)).thenReturn(usersMock);
		Mockito.when(usersMock.isEmpty()).thenReturn(false);
		Mockito.when(usersMock.size()).thenReturn(5);
		
		try{
			List<Utilisateur> users = gererUserTest.searchUsersByClient(0L);
			assertNotEquals(users.size(), 0);
		}catch(FonctionnelleException e){
			fail("Ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testSearchUser(){
		Mockito.doCallRealMethod().when(gererUserTest).searchUser(Mockito.any());
		Mockito.when(gererUserTest.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(userDaoMock.find(0L)).thenReturn(new Utilisateur());
		
		try{
			Utilisateur user = gererUserTest.searchUser(0L);
			assertNotNull(user);
		}catch(FonctionnelleException e){
			fail("Ne doit pas lever d'exception.");
		}
	}

}
