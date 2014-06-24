package fr.neosoft.cvtheque.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.neosoft.cvtheque.dao.AdresseDao;
import fr.neosoft.cvtheque.dao.UtilisateurDao;
import fr.neosoft.cvtheque.entities.Adresse;
import fr.neosoft.cvtheque.entities.Client;
import fr.neosoft.cvtheque.entities.Competence;
import fr.neosoft.cvtheque.entities.Experience;
import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.services.GererUtilisateurService;
import fr.neosoft.cvtheque.services.impl.GererUtilisateurServiceImpl;
import fr.neosoft.cvtheque.utils.Constantes;
import fr.neosoft.cvtheque.utils.FonctionnelleException;
import fr.neosoft.cvtheque.utils.Utils;

public class GererUtilisateurServiceTest {
	
	@Mock
	UtilisateurDao userDaoMock;
	
	@Mock
	AdresseDao adresseDaoMock;
	
	@Mock
	List<Competence> comp;
	
	@Mock
	Client clt;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateUserNoName() {
		userDaoMock = Mockito.mock(UtilisateurDao.class);
		Mockito.when(userDaoMock.find(Mockito.anyInt())).thenReturn(null);
		
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
		userDaoMock = Mockito.mock(UtilisateurDao.class);
		Mockito.when(userDaoMock.find(Mockito.anyInt())).thenReturn(null);
		
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
	
	@Test
	public void testCreateUserWithUserAlreadyInDB() {
		userDaoMock = Mockito.mock(UtilisateurDao.class);
		Mockito.when(userDaoMock.find(Mockito.anyInt())).thenReturn(new Utilisateur());
		
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		GererUtilisateurService gererUserTest = new GererUtilisateurServiceImpl();
		try{
			gererUserTest.createUser(user);
			fail("Devrait lever une exception, utilisateur déjà en BDD.");
		}catch(FonctionnelleException e){
			assertEquals("Utilisateur déjà en BDD -> exception", e.getCodeErreur(), Constantes.USER_ALREADY_IN_DB);
		}
	}
	
	@Test
	public void testUpdateprofil(){
		adresseDaoMock = Mockito.mock(AdresseDao.class);
		Mockito.when(adresseDaoMock.find(Mockito.anyInt())).thenReturn(null);
		
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		GererUtilisateurService gererUserTest = new GererUtilisateurServiceImpl();
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
		adresseDaoMock = Mockito.mock(AdresseDao.class);
		Adresse adr = new Adresse();
		Mockito.when(adresseDaoMock.find(Mockito.anyInt())).thenReturn(adr);
		
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		GererUtilisateurService gererUserTest = new GererUtilisateurServiceImpl();
		gererUserTest.createUser(user);
		try{
			user.setNom("TestNom");
			gererUserTest.updateProfil(user);
			Mockito.verify(user).setAdresse(Mockito.refEq(adr));
		}catch(FonctionnelleException e){
			fail("Données en entrée correctes, ne doit pas lever d'exception.");
		}
	}
	
	@Test
	public void testUpdateProfilNoName() {
		adresseDaoMock = Mockito.mock(AdresseDao.class);
		Mockito.when(adresseDaoMock.find(Mockito.anyInt())).thenReturn(null);
		
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		GererUtilisateurService gererUserTest = new GererUtilisateurServiceImpl();
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
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		
		Experience exp = new Experience();
		exp.setClient(clt);
		exp.setCompetences(comp);
		exp.setDateDebut(cal);
		GererUtilisateurService gererUserTest = new GererUtilisateurServiceImpl();
		try{
			gererUserTest.updateSkill(user, exp);
		}catch(FonctionnelleException e){
			fail("Données d'entrée correctes, ne doit pas lever d'exception.");
		}
	}
	
	@Test
	public void testUpdateSkillNoClient(){
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		
		Experience exp = new Experience();
		exp.setClient(null);
		exp.setCompetences(comp);
		exp.setDateDebut(cal);
		GererUtilisateurService gererUserTest = new GererUtilisateurServiceImpl();
		try{
			gererUserTest.updateSkill(user, exp);
			fail("Client null -> Exception");
		}catch(FonctionnelleException e){
			assertEquals("Client null -> exception", e.getCodeErreur(), Constantes.EXPERIENCE_NOT_COMPLETE);
		}
	}
	
	@Test
	public void testUpdateSkillNoCompetence(){
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		
		Experience exp = new Experience();
		exp.setClient(clt);
		exp.setCompetences(null);
		exp.setDateDebut(cal);
		GererUtilisateurService gererUserTest = new GererUtilisateurServiceImpl();
		try{
			gererUserTest.updateSkill(user, exp);
			fail("Competence null -> Exception");
		}catch(FonctionnelleException e){
			assertEquals("Competence null -> exception", e.getCodeErreur(), Constantes.EXPERIENCE_NOT_COMPLETE);
		}
	}
	
	@Test
	public void testUpdateSkillNoDate(){
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		
		Experience exp = new Experience();
		exp.setClient(clt);
		exp.setCompetences(comp);
		exp.setDateDebut(null);
		GererUtilisateurService gererUserTest = new GererUtilisateurServiceImpl();
		try{
			gererUserTest.updateSkill(user, exp);
			fail("Date null -> Exception");
		}catch(FonctionnelleException e){
			assertEquals("Date null -> exception", e.getCodeErreur(), Constantes.EXPERIENCE_NOT_COMPLETE);
		}
	}
	
	@Test
	public void testSearchUsers(){
		userDaoMock = Mockito.mock(UtilisateurDao.class);
		Mockito.when(userDaoMock.findUsers(Mockito.anyString(), Mockito.anyString(), "17/01/1991")).thenReturn(Mockito.anyListOf(Utilisateur.class));
		
		try{
			List<Utilisateur> users = userDaoMock.findUsers("Nom", "Prebom", "17/01/1991");
			assertNotEquals(users.size(), 0);
		}catch(FonctionnelleException e){
			fail("Ne devrait pas lever d'exception.");
		}
	}
	
	@Test
	@Ignore
	public void testSearchUsersByClient(){
		
	}
	
	@Test
	@Ignore
	public void testSearchUser(){
		
	}

}
