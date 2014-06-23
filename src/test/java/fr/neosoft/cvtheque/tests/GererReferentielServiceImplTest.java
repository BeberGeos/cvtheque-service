package fr.neosoft.cvtheque.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.neosoft.cvtheque.dao.CategorieDao;
import fr.neosoft.cvtheque.dao.ClientDao;
import fr.neosoft.cvtheque.dao.LangageDao;
import fr.neosoft.cvtheque.entities.Adresse;
import fr.neosoft.cvtheque.entities.Categorie;
import fr.neosoft.cvtheque.entities.Client;
import fr.neosoft.cvtheque.entities.Langage;
import fr.neosoft.cvtheque.services.GererReferentielService;
import fr.neosoft.cvtheque.services.impl.GererReferentielServiceImpl;
import fr.neosoft.cvtheque.utils.FonctionnelleException;

public class GererReferentielServiceImplTest {
	
	@Mock
	ClientDao clientDao;
	
	@Mock
	LangageDao langageDao;
	
	@Mock
	CategorieDao categorieDao;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateClient() {
		Client clt = new Client();
		clt.setNom("Client");
		clt.setSiret(12345678912345L);
		
		Adresse adr = new Adresse();
		adr.setCodePostal(33760);
		adr.setRue("Rue des Lilas");
		adr.setVille("TestVillePleaseIgnore");
		
		clt.setAdresse(adr);
		GererReferentielService refService = new GererReferentielServiceImpl(); 
		try{
			refService.createClient(clt);
		}catch(FonctionnelleException e){
			fail("Données en entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testUpdateClient() {
		Client clt = new Client();
		clt.setNom("Client");
		clt.setSiret(12345678912345L);
		
		Adresse adr = new Adresse();
		adr.setCodePostal(33760);
		adr.setRue("Rue des Lilas");
		adr.setVille("TestVillePleaseIgnore");
		
		clt.setAdresse(adr);
		GererReferentielService refService = new GererReferentielServiceImpl(); 
		refService.createClient(clt);
		try{
			refService.updateClient(clt);
		}catch(FonctionnelleException e){
			fail("Données en entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testCreateLangage() {
		Langage langage = new Langage();
		langage.setLibelle("Java");
		
		GererReferentielService refService = new GererReferentielServiceImpl();
		try{
			refService.createLangage(langage);
		}catch(FonctionnelleException e){
			fail("Données d'entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testUpdateLangage() {
		Langage langage = new Langage();
		langage.setLibelle("Java");
		
		GererReferentielService refService = new GererReferentielServiceImpl();
		refService.createLangage(langage);
		try{
			langage.setLibelle("TestJava");
			refService.updateLangage(langage);
		}catch(FonctionnelleException e){
			fail("Données d'entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testCreateCategory() {
		Categorie categ = new Categorie();
		categ.setLibelle("Programmation");
		
		GererReferentielService refService = new GererReferentielServiceImpl();
		try{
			refService.createCategory(categ);
		}catch(FonctionnelleException e){
			fail("Données d'entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testUpdateCategory() {
		Categorie categ = new Categorie();
		categ.setLibelle("Programmation");
		
		GererReferentielService refService = new GererReferentielServiceImpl();
		refService.createCategory(categ);
		try{
			categ.setLibelle("TestProgrammation");
			refService.updateCategory(categ);
		}catch(FonctionnelleException e){
			fail("Données d'entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testSearchClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchlangage() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchListLangage() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchListCategory() {
		fail("Not yet implemented");
	}

}
