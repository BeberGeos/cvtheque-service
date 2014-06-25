package fr.neosoft.cvtheque.utils;

/**
 * Code des messages erreurs et autres constantes utiles.
 * 
 * @author Adrien Cambillau
 *
 */
public class Constantes {
	
	public final static String USER_ALREADY_IN_DB = "Utilisateur déjà présent en base de données.";
	public static final String DATE_FORMAT = "Format de date incorrect.";
	public static final String OBJECT_NULL = "L'objet est null.";
	public static final String DATE_PARSE_ERROR = "Erreur lors du formatage de la date.";
	public static final String CONSTRAINT_VIOLATION = "Violation de contrainte d'intégrité sur l'entité.";
	public static final String FIELD_REQUIRED = "Champ requis non renseigné.";
	public static final String CONNECTION_ERROR = "Erreur lors de la connexion à la base de données.";
	public static final String DISCONNECTION_ERROR = "Erreur lors de la déconnexion à la base de données.";
	public static final String EXPERIENCE_NOT_COMPLETE = "Certains champs requis de l'expérience ne sont pas complets.";
	public static final String NO_USER_FOUND = "Aucun utilisateur trouvé.";
	public static final String LANGUAGE_ALREADY_IN_DB = "Le langage est déjà présent en base de données.";
	public static final String CATEGORY_ALREADY_IN_DB = "La catégorie est déjà présente en base de données.";
	public static final String NO_CLIENT_FOUND = "Aucun client ne correspond à ce SIRET.";
	public static final String NO_LANGUAGE_FOUND = "Aucun langage ne correspond à cet identifiant.";
	public static final String NO_CATEGORY_FOUND = "Aucune catégorie trouvée.";
	public static final String CLIENT_ALREADY_IN_DB = "Le client est déjà en base de données.";

}
