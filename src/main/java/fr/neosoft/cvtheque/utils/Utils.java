package fr.neosoft.cvtheque.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	public static Calendar createDateFromString(String date) throws FonctionnelleException{
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
			throw new FonctionnelleException(Constantes.DATE_PARSE_ERROR, date);
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
	public static void checkNotNull(String nomParam, Object objet)throws FonctionnelleException{
		if(objet == null){
			throw new FonctionnelleException(Constantes.OBJECT_NULL, nomParam);
		}
	}

}
