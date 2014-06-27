package fr.neosoft.cvtheque.tests;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import java.util.Calendar;
import java.util.HashMap;
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

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;

import fr.neosoft.cvtheque.dao.UtilisateurDao;
import fr.neosoft.cvtheque.dao.impl.UtilisateurDaoImpl;
import fr.neosoft.cvtheque.entities.Utilisateur;

public class UtilisateurDaoTest {

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
		final Operation operation =
				sequenceOf(
						CommonsOperations.DELETE_ALL,
						CommonsOperations.INSERT_REFERENCE_DATA);
		final DbSetup dbSetup = new DbSetup(new DriverManagerDestination("jdbc:mysql://localhost:3306/cvtheque", "cvtheque", "cvtheque"), operation);
		dbSetup.launch();
	}

	@Test
	public void testCreate() {
		UtilisateurDao userDao = new UtilisateurDaoImpl(entityManager);
		Utilisateur user = new Utilisateur();
		user.setId(3);
		user.setNom("Testa");
		user.setPrenom("Chuck");
		user.setDateNaissance(Calendar.getInstance());
		user.setDateModification(Calendar.getInstance());
		user.setVersion(1);
		user.setAdresse(null);
		entityManager.getTransaction().begin();
		userDao.create(user);
		entityManager.flush();
		entityManager.getTransaction().commit();

		final Utilisateur utilisateur = userDao.find(Integer.valueOf(3));
		System.out.println("Utilisateur : " + utilisateur);
	}

	@Test
	public void testDelete() {
		UtilisateurDao userDao = new UtilisateurDaoImpl(entityManager);
//		testCreate();
		final Utilisateur utilisateur = userDao.find(Integer.valueOf(3));
		entityManager.getTransaction().begin();
		userDao.delete(utilisateur.getId());
		entityManager.flush();
		entityManager.getTransaction().commit();
	}

	@Test
	public void testFind() {
		UtilisateurDao utilisateurDao = new UtilisateurDaoImpl(entityManager);

		final Utilisateur utilisateur = utilisateurDao.find(Integer.valueOf(1));

		System.out.println("Utilisateur : " + utilisateur);
	}

	@Test
	public void testUpdate() {
		UtilisateurDao userDao = new UtilisateurDaoImpl(entityManager);
//		testCreate();
		final Utilisateur utilisateur = userDao.find(Integer.valueOf(3));
		entityManager.getTransaction().begin();
		utilisateur.setNom("Huehuehue");
		userDao.update(utilisateur);
		entityManager.flush();
		entityManager.getTransaction().commit();
		
		final Utilisateur user = userDao.find(Integer.valueOf(3));
		System.out.println("Utilisateur : " + user);
	}

}
