package fr.neosoft.cvtheque.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.neosoft.cvtheque.dao.CategorieDao;
import fr.neosoft.cvtheque.dao.ClientDao;
import fr.neosoft.cvtheque.dao.LangageDao;
import fr.neosoft.cvtheque.entities.Adresse;
import fr.neosoft.cvtheque.entities.Categorie;
import fr.neosoft.cvtheque.entities.Client;
import fr.neosoft.cvtheque.entities.Langage;
import fr.neosoft.cvtheque.services.impl.GererReferentielServiceImpl;
import fr.neosoft.cvtheque.utils.FonctionnelleException;

public class GererReferentielServiceImplTest {
	
	@Mock
	private ClientDao clientDaoMock;
	
	@Mock
	private LangageDao langageDaoMock;
	
	@Mock
	private CategorieDao categorieDaoMock;
	
	@Mock
	private GererReferentielServiceImpl refService;
	
	@Mock
	private List<Categorie> categsMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateClient() {
		Mockito.doCallRealMethod().when(refService).createClient(Mockito.any());
		Mockito.when(refService.getClientDao()).thenReturn(clientDaoMock);
		Mockito.when(clientDaoMock.find(0)).thenReturn(null);
		
		Client clt = new Client();
		clt.setNom("Client");
		clt.setSiret(12345678912345L);
		
		Adresse adr = new Adresse();
		adr.setCodePostal(33760);
		adr.setRue("Rue des Lilas");
		adr.setVille("TestVillePleaseIgnore");
		
		clt.setAdresse(adr);
		try{
			refService.createClient(clt);
		}catch(FonctionnelleException e){
			fail("Données en entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testUpdateClient() {
		Mockito.doCallRealMethod().when(refService).updateClient(Mockito.any());
		Mockito.when(refService.getClientDao()).thenReturn(clientDaoMock);
		
		Client clt = new Client();
		clt.setNom("Client");
		clt.setSiret(12345678912345L);
		
		Adresse adr = new Adresse();
		adr.setCodePostal(33760);
		adr.setRue("Rue des Lilas");
		adr.setVille("TestVillePleaseIgnore");
		
		clt.setAdresse(adr);
		refService.createClient(clt);
		try{
			refService.updateClient(clt);
		}catch(FonctionnelleException e){
			fail("Données en entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testCreateLangage() {
		Mockito.doCallRealMethod().when(refService).createLangage(Mockito.any());
		Mockito.when(refService.getLanguageDao()).thenReturn(langageDaoMock);
		Mockito.when(langageDaoMock.findLanguageByName(Mockito.any())).thenReturn(null);
		
		Langage langage = new Langage();
		langage.setLibelle("Java");
		
		try{
			refService.createLangage(langage);
		}catch(FonctionnelleException e){
			fail("Données d'entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testUpdateLangage() {
		Mockito.doCallRealMethod().when(refService).updateLangage(Mockito.any());
		Mockito.when(refService.getLanguageDao()).thenReturn(langageDaoMock);
		Mockito.when(langageDaoMock.findLanguageByName(Mockito.any())).thenReturn(null);
		
		Langage langage = new Langage();
		langage.setLibelle("Java");
		
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
		Mockito.doCallRealMethod().when(refService).createCategory(Mockito.any());
		Mockito.when(refService.getCategoryDao()).thenReturn(categorieDaoMock);
		Mockito.when(categorieDaoMock.find(Mockito.any())).thenReturn(null);
		
		Categorie categ = new Categorie();
		categ.setLibelle("Programmation");
		
		try{
			refService.createCategory(categ);
		}catch(FonctionnelleException e){
			fail("Données d'entrée correctes, ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testUpdateCategory() {
		Mockito.doCallRealMethod().when(refService).updateCategory(Mockito.any());
		Mockito.when(refService.getCategoryDao()).thenReturn(categorieDaoMock);
		Mockito.when(categorieDaoMock.findCategoryByName(Mockito.any())).thenReturn(null);
		
		Categorie categ = new Categorie();
		categ.setLibelle("Programmation");
		
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
		Mockito.doCallRealMethod().when(refService).searchClient(Mockito.any());
		Mockito.when(refService.getClientDao()).thenReturn(clientDaoMock);
		Mockito.when(clientDaoMock.findClientBySiret(0L)).thenReturn(new Client());
		
		try{
			Client client = refService.searchClient(0L);
			assertNotNull(client);
		}catch(FonctionnelleException e){
			fail("Ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testSearchlangage() {
		Mockito.doCallRealMethod().when(refService).searchLangage(Mockito.any());
		Mockito.when(refService.getLanguageDao()).thenReturn(langageDaoMock);
		Mockito.when(langageDaoMock.find(0L)).thenReturn(new Langage());
		
		try{
			Langage client = refService.searchLangage(0L);
			assertNotNull(client);
		}catch(FonctionnelleException e){
			fail("Ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testSearchListLangage() {
		Mockito.doCallRealMethod().when(refService).searchClient(Mockito.any());
		Mockito.when(refService.getLanguageDao()).thenReturn(langageDaoMock);
		Mockito.when(langageDaoMock.findLanguagesByName(Mockito.any())).thenReturn(Mockito.anyListOf(Langage.class));
		
		try{
			List<Langage> languages = refService.searchListLangage("Java");
			assertNotNull(languages);
		}catch(FonctionnelleException e){
			fail("Ne doit pas lever d'exception.");
		}
	}

	@Test
	public void testSearchListCategory() {
		Mockito.doCallRealMethod().when(refService).searchClient(Mockito.any());
		Mockito.when(refService.getCategoryDao()).thenReturn(categorieDaoMock);
		Mockito.when(categorieDaoMock.findAllCategories()).thenReturn(categsMock);
		
		try{
			List<Categorie> categories = refService.searchListCategory();
			assertNotNull(categories);
		}catch(FonctionnelleException e){
			fail("Ne doit pas lever d'exception.");
		}
	}

}
