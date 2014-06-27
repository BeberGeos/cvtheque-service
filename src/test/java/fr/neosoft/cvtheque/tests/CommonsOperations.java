package fr.neosoft.cvtheque.tests;

import com.ninja_squad.dbsetup.operation.Operation;

import java.util.Calendar;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

/**
 * Permet d'insérer des données de test dans la base de données.
 * @author NLefebvre
 *
 */
public class CommonsOperations {
	 public static final Operation DELETE_ALL =
	            deleteAllFrom("UTILISATEUR", "ADRESSE", "CATEGORIE", "CLIENT", "COMPETENCE","EXP_COMP", "EXPERIENCE", "LANGAGE", "UTIL_EXP");

	    public static final Operation INSERT_REFERENCE_DATA =
	            sequenceOf(
	                    insertInto("ADRESSE")
	                            .columns("ID", "RUE", "CODE_POSTAL", "VILLE", "DATE_MODIFICATION", "VERSION")
	                            .values(1, "dido", 12125, "Paris", Calendar.getInstance(), 1)
	                            .values(2, "fifi", 78785, "Retz", Calendar.getInstance(), 1)
	                            .build(),
	                    insertInto("UTILISATEUR")
	                            .columns("ID", "NOM", "PRENOM", "ID_ADRESSE", "DATE_NAISSANCE", "DATE_MODIFICATION", "VERSION")
	                            .values(1L, "jbnizet",  "Jean-Baptiste Nizet",1, Calendar.getInstance(), Calendar.getInstance(), 1)
	                            .values(2L, "clacote", "Cyril Lacote",2,  Calendar.getInstance(), Calendar.getInstance(), 1)
	                            .build());
}
