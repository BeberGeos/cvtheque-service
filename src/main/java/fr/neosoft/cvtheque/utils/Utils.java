package fr.neosoft.cvtheque.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

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

	public static boolean checkNotNull(String nomParam, Object objet)throws FonctionnelleException{
		boolean isOk = false;
		if(objet == null){
			throw new FonctionnelleException(Constantes.OBJECT_NULL, nomParam);
		}else{
			isOk = true;
		}
		return isOk;
	}

}
