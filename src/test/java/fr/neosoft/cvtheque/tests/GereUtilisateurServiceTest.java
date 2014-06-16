package fr.neosoft.cvtheque.tests;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.neosoft.cvtheque.dao.impl.UtilisateurDaoImpl;

public class GereUtilisateurServiceTest {
	
	@Mock
	UtilisateurDaoImpl userDaoMock;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateUser() {
		fail("");
	}

}
