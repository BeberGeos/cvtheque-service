package fr.neosoft.cvtheque.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.neosoft.cvtheque.dao.UtilisateurDao;
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
	List<Competence> comp;
	
	@Mock
	Client clt;
	
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
	
	@Test
	public void testUpdateprofil(){
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("Nom");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		GererUtilisateurService gererUserTest = new GererUtilisateurServiceImpl();
		try{
			gererUserTest.updateProfil(user);
		}catch(FonctionnelleException e){
			fail("Données en entrée correctes, ne doit pas lever d'exception.");
		}
	}
	
	@Test
	public void testUpdateUserNoName() {
		Utilisateur user = new Utilisateur();
		Calendar cal = Utils.createDateFromString("17/01/1991");
		user.setNom("");
		user.setPrenom("Prenom");
		user.setDateNaissance(cal);
		GererUtilisateurService gererUserTest = new GererUtilisateurServiceImpl();
		try{
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
	@Ignore
	public void testSearchUsersByClient(){
		
	}
	
	@Test
	@Ignore
	public void testSearchUser(){
		
	}

}
