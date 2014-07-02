package fr.neosoft.cvtheque.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.neosoft.cvtheque.dao.UtilisateurDao;
import fr.neosoft.cvtheque.entities.Utilisateur;
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

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
}
