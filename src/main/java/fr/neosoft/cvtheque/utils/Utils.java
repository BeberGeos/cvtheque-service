package fr.neosoft.cvtheque.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import fr.neosoft.cvtheque.entities.Utilisateur;

/**
 * Implémentations de méthodes utiles au projet.
 * 
 * @author Adrien Cambillau
 *
 */
public class Utils {

	/**
	 * Permet de transformer une chaine de caractères en date.
	 * 
	 * @param date la chainde de caractères de la date
	 * @return Calendar la date formatée exploitable par l'appli
	 * @throws FonctionnelleException
	 */
	public static Calendar createDateFromString(final String date) throws FonctionnelleException{
		Calendar calendar = Calendar.getInstance();
		if(!date.matches("\\d{2}/\\d{2}/\\d{4}")){
			throw new FonctionnelleException(Constantes.DATE_FORMAT, date);
		}
		try {

			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			formatter.setLenient(false);
			Date formatedDate = formatter.parse(date);

			calendar.setTime(formatedDate);
		} catch (ParseException e) {
			throw new TechniqueException(Constantes.DATE_PARSE_ERROR, date);
		}
		return calendar;
	}

	/**
	 * Check si un objet est nul.
	 * 
	 * @param nomParam 
	 * @param objet l'objet à tester
	 * @throws FonctionnelleException
	 */
	public static void checkNotNull(final String nomParam, final Object objet)throws FonctionnelleException{
		if(objet == null){
			throw new FonctionnelleException(Constantes.OBJECT_NULL, nomParam);
		}
	}
	
	/**
	 * Check de la validité des données suivant la validation de l'entité.
	 * @param object l'object à tester
	 */
	public static void checkConstraints(final Object object, final String nomParam)throws FonctionnelleException{
		ValidatorFactory validFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validFactory.getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
		
		//Si il y a violation de contrainte on lève une exception.
		if(constraintViolations.size() > 0){
			throw new FonctionnelleException(Constantes.CONSTRAINT_VIOLATION, object.toString());
		}
	}

}
