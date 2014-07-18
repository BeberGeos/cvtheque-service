package fr.neosoft.cvtheque.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceUnitTransactionType;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.neosoft.cvtheque.dao.UtilisateurDao;
import fr.neosoft.cvtheque.dao.impl.UtilisateurDaoImpl;
import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.services.GererCVService;
import fr.neosoft.cvtheque.services.impl.GererCVServiceImpl;
import fr.neosoft.cvtheque.utils.Constantes;
import fr.neosoft.cvtheque.utils.FonctionnelleException;

public class GererCVServiceTest {
	
	@Mock
	private UtilisateurDao userDaoMock;
	
	@Mock
	private List<Utilisateur> userListMock;
	
	@Mock
	private GererCVServiceImpl gererCVMock;
	
	private static EntityManagerFactory emf ;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Map properties = new HashMap();


		// Ensure RESOURCE_LOCAL transactions is used.
		properties.put(PersistenceUnitProperties.TRANSACTION_TYPE,
				PersistenceUnitTransactionType.RESOURCE_LOCAL.name());

		// Configure the internal EclipseLink connection pool
		properties.put(PersistenceUnitProperties.JDBC_DRIVER, "com.mysql.jdbc.Driver");
		properties.put(PersistenceUnitProperties.JDBC_URL, "jdbc:mysql://localhost:3306/cvtheque");
		properties.put(PersistenceUnitProperties.JDBC_USER, "cvtheque");
		properties.put(PersistenceUnitProperties.JDBC_PASSWORD, "cvtheque");

		// Configure logging. FINE ensures all SQL is shown
		properties.put(PersistenceUnitProperties.LOGGING_LEVEL, "FINE");
		properties.put(PersistenceUnitProperties.LOGGING_TIMESTAMP, "false");
		properties.put(PersistenceUnitProperties.LOGGING_THREAD, "false");
		properties.put(PersistenceUnitProperties.LOGGING_SESSION, "false");

		emf = Persistence.createEntityManagerFactory("cvtheque-service", properties);
		entityManager = (EntityManager) emf.createEntityManager();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSearchUserByLanguageOrCategory() {
		Mockito.doCallRealMethod().when(gererCVMock).searchUserByLanguageOrCategory(Mockito.any(), Mockito.any());
		Mockito.when(gererCVMock.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(userDaoMock.findUserByLanguageOrCategory(Mockito.any(), Mockito.any())).thenReturn(userListMock);
		Mockito.when(userListMock.isEmpty()).thenReturn(false);
		
		try{
			List<Utilisateur> users = gererCVMock.searchUserByLanguageOrCategory(1L, 1L);
		}catch(FonctionnelleException e){
			fail("Ne doit pas lever d'exception.");
		}
	}
	
	@Test
	public void testSearchUserByLanguageOrCategoryNoResult() {
		Mockito.doCallRealMethod().when(gererCVMock).searchUserByLanguageOrCategory(Mockito.any(), Mockito.any());
		Mockito.when(gererCVMock.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(userDaoMock.findUserByLanguageOrCategory(Mockito.any(), Mockito.any())).thenReturn(userListMock);
		Mockito.when(userListMock.isEmpty()).thenReturn(true);
		
		try{
			List<Utilisateur> users = gererCVMock.searchUserByLanguageOrCategory(1L, 1L);
			fail("Doit lever une exception.");
		}catch(FonctionnelleException e){
			assertEquals("Pas d'utilisateur -> exception", e.getCodeErreur(), Constantes.NO_USER_FOUND);
		}
	}
	
	@Test
	public void testSearchUserByLanguageOrCategoryNoId() {
		Mockito.doCallRealMethod().when(gererCVMock).searchUserByLanguageOrCategory(Mockito.any(), Mockito.any());
		Mockito.when(gererCVMock.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(userDaoMock.findUserByLanguageOrCategory(Mockito.any(), Mockito.any())).thenReturn(userListMock);
		Mockito.when(userListMock.isEmpty()).thenReturn(false);
		
		try{
			List<Utilisateur> users = gererCVMock.searchUserByLanguageOrCategory(null, null);
			fail("Doit lever une exception.");
		}catch(FonctionnelleException e){
			assertEquals("Pas d'utilisateur -> exception", e.getCodeErreur(), Constantes.FIELD_REQUIRED);
		}
	}

	@Test
	public void testSearchUserByLanguageOrCategoryOneId() {
		Mockito.doCallRealMethod().when(gererCVMock).searchUserByLanguageOrCategory(Mockito.any(), Mockito.any());
		Mockito.when(gererCVMock.getUserDao()).thenReturn(userDaoMock);
		Mockito.when(userDaoMock.findUserByLanguageOrCategory(Mockito.any(), Mockito.any())).thenReturn(userListMock);
		Mockito.when(userListMock.isEmpty()).thenReturn(false);
		
		try{
			List<Utilisateur> users = gererCVMock.searchUserByLanguageOrCategory(null, 1L);
		}catch(FonctionnelleException e){
			fail("Ne doit pas lever d'exception.");
		}
	}
	
	@Test
	public void testCreateCV(){
		UtilisateurDao userDao = new UtilisateurDaoImpl(entityManager);
		GererCVService cv = new GererCVServiceImpl(entityManager, "Développeur Java EE");
		
		Long id = 1L;
		int userId = id.intValue();
//		Utilisateur user = userDao.find(userId);
//		System.out.println("HUEHUEHUE " + user.toString());
		cv.createCV(id);
	}
}
