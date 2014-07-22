package fr.neosoft.cvtheque.services.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import fr.neosoft.cvtheque.bean.AdresseType;
import fr.neosoft.cvtheque.bean.CategorieType;
import fr.neosoft.cvtheque.bean.CompetenceDetailType;
import fr.neosoft.cvtheque.bean.CompetenceType;
import fr.neosoft.cvtheque.bean.Cv;
import fr.neosoft.cvtheque.bean.ExperienceType;
import fr.neosoft.cvtheque.bean.FonctionType;
import fr.neosoft.cvtheque.bean.IdentiteType;
import fr.neosoft.cvtheque.bean.ObjectFactory;
import fr.neosoft.cvtheque.dao.UtilisateurDao;
import fr.neosoft.cvtheque.dao.impl.UtilisateurDaoImpl;
import fr.neosoft.cvtheque.entities.Competence;
import fr.neosoft.cvtheque.entities.Experience;
import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.services.GererCVService;
import fr.neosoft.cvtheque.utils.Constantes;
import fr.neosoft.cvtheque.utils.FonctionnelleException;
import fr.neosoft.cvtheque.utils.TechniqueException;

/**
 * Implémentation de l'interface de gestion des CV.
 * 
 * @author Adrien Cambillau
 *
 */
public class GererCVServiceImpl implements GererCVService {
	static {
		final SimpleDateFormat formatDate= new SimpleDateFormat("yyyyMMdd");
		final Calendar dateJour = Calendar.getInstance();
		dateJourFormat = formatDate.format(dateJour.getTime());
	}

	private static String dateJourFormat;
	private UtilisateurDao userDao;
	private static ObjectFactory objectFactory = new ObjectFactory();
	private static String fonctionUser;
	
	//FIXME Tweak nécessaire pour les tests, ne pas utiliser pour autre chose
	public GererCVServiceImpl(EntityManager entityManager, String fonctionUser){
		super();
		this.userDao = new UtilisateurDaoImpl(entityManager);
		this.fonctionUser = fonctionUser;
	}

	public GererCVServiceImpl() {
		super();
	}

	public List<Utilisateur> searchUserByLanguageOrCategory(final Long idLangage, final
			Long idCategory) throws FonctionnelleException {
		List<Utilisateur> users;
		if(idLangage != null || idCategory != null){
			users = getUserDao().findUserByLanguageOrCategory(idLangage, idCategory);
			if(users.isEmpty()){
				throw new FonctionnelleException(Constantes.NO_USER_FOUND, String.valueOf(idLangage) + " " + String.valueOf(idCategory));
			}
		}else{
			throw new FonctionnelleException(Constantes.FIELD_REQUIRED, String.valueOf(idLangage) + " " + String.valueOf(idCategory));
		}
		return users;
	}

	public void createCV(final Long idUser)
			throws FonctionnelleException {
		if(idUser == null){
			throw new FonctionnelleException(Constantes.FIELD_REQUIRED, String.valueOf(idUser));
		}
		int id = idUser.intValue();
		Utilisateur user = getUserDao().find(id);
		if(user == null){
			throw new FonctionnelleException(Constantes.NO_USER_FOUND, String.valueOf(idUser));
		}else{
			final Cv cvUtilisateur = objectFactory.createCv();
			final IdentiteType identiteTypeUtilisateur = objectFactory.createIdentiteType();
			final AdresseType adresseTypeUtilisateur = objectFactory.createAdresseType();
			final FonctionType fonctionTypeUtilisateur = objectFactory.createFonctionType();
			final CompetenceType competenceTypeTechniqueUtilisateur = objectFactory.createCompetenceType();
			final CompetenceDetailType competenceDetailTypeTechniqueUtilisateur = objectFactory.createCompetenceDetailType();
			final CompetenceType competenceTypeFonctionnelleUtilisateur = objectFactory.createCompetenceType();
			final CompetenceDetailType competenceDetailTypeFonctionnelleUtilisateur = objectFactory.createCompetenceDetailType();
			final ExperienceType experienceTypeUtilisateur = objectFactory.createExperienceType();

			initAdresseUtilisateur(adresseTypeUtilisateur, user);

			initIdentiteUtilisateur(identiteTypeUtilisateur, adresseTypeUtilisateur, user);

			initFonctionUtilisateur(fonctionTypeUtilisateur, user);

			initCompetenceDetailTechniqueUtilisateur(competenceDetailTypeTechniqueUtilisateur, user);

			initCompetenceDetailFonctionnelleUtilisateur(competenceDetailTypeFonctionnelleUtilisateur, user);

			competenceTypeTechniqueUtilisateur.getCompetence().add(competenceDetailTypeTechniqueUtilisateur);

			competenceTypeFonctionnelleUtilisateur.getCompetence().add(competenceDetailTypeFonctionnelleUtilisateur);

			initExperience(experienceTypeUtilisateur, user);

			cvUtilisateur.setIdentite(identiteTypeUtilisateur);
			cvUtilisateur.setFonction(fonctionTypeUtilisateur);
			cvUtilisateur.setCompetenceTechnique(competenceTypeTechniqueUtilisateur);
			cvUtilisateur.setCompetenceFonctionnelle(competenceTypeFonctionnelleUtilisateur);
			cvUtilisateur.getExperience().add(experienceTypeUtilisateur);

			final JAXBContext jaxbContext;
			try {
				final String CHEMIN_FICHIER_GENERE = "src\\main\\resources\\CV_" + user.getNom() + "_" + user.getPrenom() 
						+ "_" + dateJourFormat + ".xml";
				final String CHEMIN_SCHEMA ="src\\main\\resources\\xsd\\schemaCV.xsd";

				jaxbContext = JAXBContext.newInstance(Cv.class);
				final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				final FileWriter writer = new FileWriter(CHEMIN_FICHIER_GENERE);
				writer.write("<?xml-stylesheet type=\"text/xsl\" href=\"xsl\\CV.xsl\">");
				writer.write("\n");

				final File fileSchema = new File(CHEMIN_SCHEMA);
				final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				final Schema schema = schemaFactory.newSchema(fileSchema);

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.setSchema(schema);
				jaxbMarshaller.marshal(cvUtilisateur, writer);
			} catch (JAXBException e) {
				throw new TechniqueException(Constantes.XML_GENERATION_PROBLEM, "");
			} catch (IOException e) {
				throw new TechniqueException(Constantes.FILE_IO_ERROR, "");
			} catch (SAXException e) {
				throw new TechniqueException(Constantes.XML_SCHEMA_EXCEPTION, "");
			}
		}
	}

	private static void initExperience(final ExperienceType experienceType, final Utilisateur user) {
		List<String> listePileLogicielles = new ArrayList<String>();
		List<String> listeDescription = new ArrayList<String>();

		for(Iterator<Experience> iterExp = user.getExperiences().iterator(); iterExp.hasNext();){
			Experience exp = iterExp.next();
			experienceType.setDateDebut(exp.getDateDebut().getTime());
			experienceType.setDateFin(exp.getDateFin().getTime());
			experienceType.setClient(exp.getClient().getNom());
			experienceType.setLocalisation(exp.getClient().getAdresse().getVille() + " (" + String.valueOf(exp.getClient().getAdresse().getCodePostal()).substring(0, 2) + ")");
			listeDescription.add(exp.getDetailExperience());

			for(Iterator<Competence> iterComp = exp.getCompetences().iterator(); iterComp.hasNext();){
				Competence comp = iterComp.next();
				listePileLogicielles.add(comp.getLangage().getLibelle());
			}
			experienceType.getDescription().addAll(listeDescription);
			experienceType.getPileLogiciel().addAll(listePileLogicielles);
		}

	}

	private static void initCompetenceDetailFonctionnelleUtilisateur(
			final CompetenceDetailType competenceDetailTypeFonctionnelleUtilisateur, final Utilisateur user) {
		competenceDetailTypeFonctionnelleUtilisateur.setTitreCompetence("");
		competenceDetailTypeFonctionnelleUtilisateur.getCategorie().add(initCategorieFonctionnelleUtilisateur(user));

	}

	private static CategorieType initCategorieFonctionnelleUtilisateur(final Utilisateur user){
		CategorieType categorieType = objectFactory.createCategorieType();
		categorieType.setTitreCategorie("");
		for(Iterator<Experience> iterExp = user.getExperiences().iterator(); iterExp.hasNext();){
			Experience exp = iterExp.next();
			for(Iterator<Competence> iterComp = exp.getCompetences().iterator(); iterComp.hasNext();){
				Competence comp = iterComp.next();
				if(comp.getCategorie().getLibelle().equals("Compétences fonctionnelles")){
					categorieType.getLibelleCategorie().add(comp.getLangage().getLibelle());
				}
			}
		}

		return categorieType;
	}

	private static void initCompetenceDetailTechniqueUtilisateur(
			final CompetenceDetailType competenceDetailTypeTechniqueUtilisateur, final Utilisateur user) {
		competenceDetailTypeTechniqueUtilisateur.setTitreCompetence("");
		competenceDetailTypeTechniqueUtilisateur.getCategorie().add(initCategorieTechniqueUtilisateur(user));

	}

	private static CategorieType initCategorieTechniqueUtilisateur(final Utilisateur user){
		CategorieType categorieType = objectFactory.createCategorieType();
		
		for(Iterator<Experience> iterExp = user.getExperiences().iterator(); iterExp.hasNext();){
			Experience exp = iterExp.next();
			for(Iterator<Competence> iterComp = exp.getCompetences().iterator(); iterComp.hasNext();){
				Competence comp = iterComp.next();
				if(comp.getCategorie().getLibelle().equals("Compétences techniques")){
					categorieType.setTitreCategorie(comp.getLibelle());
					categorieType.getLibelleCategorie().add(comp.getLangage().getLibelle());
				}
			}
		}
		return categorieType;
	}

	private static void initFonctionUtilisateur(final FonctionType fonctionTypeUtilisateur, final Utilisateur user) {
		final Calendar dateJour = Calendar.getInstance();
		final int anneeEnCours = dateJour.get(Calendar.YEAR);
		List<Integer> listeAnneesExp = new ArrayList<Integer>();
		for(Iterator<Experience> iterExp = user.getExperiences().iterator(); iterExp.hasNext();){
			Experience exp = iterExp.next();
			listeAnneesExp.add(Integer.valueOf(exp.getDateDebut().get(Calendar.YEAR)));
		}
		Collections.sort(listeAnneesExp);
		int premiereAnneeExp = listeAnneesExp.get(0).intValue();
		fonctionTypeUtilisateur.setAnneeExperience(anneeEnCours - premiereAnneeExp);
		fonctionTypeUtilisateur.setTitre(getFonctionUser());
	}

	private static void initIdentiteUtilisateur(final IdentiteType identiteTypeUtilisateur,
			final AdresseType adresseTypeUtilisateur, final Utilisateur user) {
		identiteTypeUtilisateur.setAdresse(adresseTypeUtilisateur);
		identiteTypeUtilisateur.setNom(user.getNom());
		identiteTypeUtilisateur.setPrenom(user.getPrenom());

	}

	private static void initAdresseUtilisateur(final AdresseType adresseTypeUtilisateur, final Utilisateur user) {
		adresseTypeUtilisateur.setCodePostal(String.valueOf(user.getAdresse().getCodePostal()));
		adresseTypeUtilisateur.setNumero(null);
		adresseTypeUtilisateur.setRue(user.getAdresse().getRue());
		adresseTypeUtilisateur.setVille(user.getAdresse().getVille());
	}

	public UtilisateurDao getUserDao() {
		return userDao;
	}

	public static String getFonctionUser() {
		return fonctionUser;
	}

	public static void setFonctionUser(String fonctionUser) {
		GererCVServiceImpl.fonctionUser = fonctionUser;
	}

}
