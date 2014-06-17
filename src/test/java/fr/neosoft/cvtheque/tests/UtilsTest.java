package fr.neosoft.cvtheque.tests;

import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.utils.Constantes;
import fr.neosoft.cvtheque.utils.FonctionnelleException;
import fr.neosoft.cvtheque.utils.TechniqueException;
import fr.neosoft.cvtheque.utils.Utils;

public class UtilsTest {
	@Test
	public void testCreateDateFromStringThrowExceptionEmptyString() {
		try{
			Utils.createDateFromString("");
			fail("Aurait dû échouer à cause d'une chaine vide pour la date.");
		}catch(FonctionnelleException e){
			assertEquals("La date est une chaine vide -> exception.",e.getCodeErreur(), Constantes.DATE_FORMAT);
		}
	}

	@Test
	public void testCreateDateFromStringThrowExceptionAmericanFormat() {
		try{
			Utils.createDateFromString("01/17/1991");
			fail("Le format américain (MM/dd/yyyy) doit lever une exception.");
		}catch(TechniqueException e){
			assertEquals("Date au format américain -> exception.", e.getCodeErreur(), Constantes.DATE_PARSE_ERROR);
		}
	}

	@Test
	public void testCreateDateFromStringThrowExceptionInvalidFormat(){
		try{
			Utils.createDateFromString("01/17/91");
			fail("Format dd/MM/yy incorrect, il faut dd/MM/yyyy.");
		}catch(FonctionnelleException e){
			assertEquals("dd/MM/yy n'est pas un format correct -> exception.", e.getCodeErreur(), Constantes.DATE_FORMAT);
		}
	}

	@Test
	public void testCreateDateFromString() {
		try{
			assertThat("CreateDateFromString retourne un objet Calendar", 
					Utils.createDateFromString("17/01/1991"), isA(Calendar.class));
		}catch(FonctionnelleException e){
			fail("La méthode doit retourner un objet de type Calendar.");
		}
	}

	@Test
	public void testCheckNotNullThrowException() {
		try{
			Object testObject = null;
			Utils.checkNotNull("param", testObject);
			fail("On doit levé une exception si l'objet est null.");
		}catch(FonctionnelleException e){
			assertEquals("Objet null -> exception.", e.getCodeErreur(), Constantes.OBJECT_NULL);
		}
	}

	@Test
	public void testCheckNotNull(){
		try{
			Object objet = new Object();
			Utils.checkNotNull("param", objet);
		}catch(FonctionnelleException e){
			fail("Aucune exception ne doit être levé si l'objet n'est pas null.");
		}
	}

	@Test
	public void checkConstraintThrowExceptionLastNameTooLong(){
		try{
			Utilisateur user = new Utilisateur();
			Calendar cal = Utils.createDateFromString("17/01/1991");
			user.setNom("Chaine de plus de 50 caractères pour le nom. ZZZZZZZZZZZZZZZZZZZZZZZZZZ");
			user.setPrenom("prenom");
			user.setDateNaissance(cal);
			Utils.checkConstraints(user, user.getNom());
			fail("Une exception aurait dû être levée car le nom fait plus de 50 caractères.");
		}catch(FonctionnelleException e){
			assertEquals("Violation de contrainte -> exception", e.getCodeErreur(), Constantes.CONSTRAINT_VIOLATION);
		}
	}
	
	@Test
	public void checkConstraintThrowExceptionFirstNameTooLong(){
		try{
			Utilisateur user = new Utilisateur();
			Calendar cal = Utils.createDateFromString("17/01/1991");
			user.setNom("Nom");
			user.setPrenom("Chaine de plus de 50 caractères pour le nom. ZZZZZZZZZZZZZZZZZZZZZZZZZZ");
			user.setDateNaissance(cal);
			Utils.checkConstraints(user, user.getNom());
			fail("Une exception aurait dû être levée car le prénom fait plus de 50 caractères.");
		}catch(FonctionnelleException e){
			assertEquals("Violation de contrainte -> exception", e.getCodeErreur(), Constantes.CONSTRAINT_VIOLATION);
		}
	}
	
	@Test
	public void checkConstraintThrowExceptionBirthDateNotInThePast(){
		try{
			Utilisateur user = new Utilisateur();
			Calendar cal = Utils.createDateFromString("17/01/2015");
			user.setNom("Nom");
			user.setPrenom("Prenom");
			user.setDateNaissance(cal);
			Utils.checkConstraints(user, user.getNom());
			fail("Une exception aurait dû être levée car la date n'est pas dans le passé.");
		}catch(FonctionnelleException e){
			assertEquals("Violation de contrainte -> exception", e.getCodeErreur(), Constantes.CONSTRAINT_VIOLATION);
		}
	}
	
	@Test
	public void checkConstraint(){
		try{
			Utilisateur user = new Utilisateur();
			Calendar cal = Utils.createDateFromString("17/01/1991");
			user.setNom("Nom");
			user.setPrenom("Prenom");
			user.setDateNaissance(cal);
			Utils.checkConstraints(user, user.getNom());
		}catch(FonctionnelleException e){
			fail("Aucune exception ne doit être levée car les données d'entrée sont bonnes.");
		}
	}

}
