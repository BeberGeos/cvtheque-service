package fr.neosoft;

import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.neosoft.cvtheque.utils.Constantes;
import fr.neosoft.cvtheque.utils.FonctionnelleException;
import fr.neosoft.cvtheque.utils.Utils;

public class UtilsTest {

	@Test
	public void testCreateDateFromStringThrowExceptionEmptyString() {
		try{
			Utils.createDateFromString("");
			fail("");
		}
		catch(FonctionnelleException e){
			Assert.assertEquals("",e.getCodeErreur(), Constantes.DATE_FORMAT);
		}
	}
	
	@Test(expected = FonctionnelleException.class)
	public void testCreateDateFromStringThrowExceptionAmericanFormat() {
		Utils.createDateFromString("01/17/1991");
	}
	
	@Test(expected = FonctionnelleException.class)
	public void testCreateDateFromStringThrowExceptionInvalidFormat(){
		Utils.createDateFromString("01/17/91");
	}
	
	@Test
	public void testCreateDateFromString() {
		assertThat("CreateDateFromString return a Calendar", 
				Utils.createDateFromString("17/01/1991"), isA(Calendar.class));
	}

	@Test(expected = FonctionnelleException.class)
	public void testCheckNotNullThrowException() {
		Object testObject = null;
		Utils.checkNotNull("param", testObject);
	}
	
	@Test
	public void testCheckNotNull(){
		Object objet = new Object();
		assertEquals("CheckNotNull return true", true, Utils.checkNotNull("param", objet));
	}

}
