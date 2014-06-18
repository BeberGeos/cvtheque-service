package fr.neosoft.cvtheque.services;

import java.util.List;

import fr.neosoft.cvtheque.entities.Utilisateur;
import fr.neosoft.cvtheque.utils.FonctionnelleException;
import fr.neosoft.cvtheque.utils.TypeDocument;

/**
 * Interface de gestion des CV
 * 
 * @author Adrien Cambillau
 *
 */
public interface GererCVService {
	
	/**
	 * Cette méthode va retourner une liste d’utilisateurs pour les paramètres donnés. 
	 * Les deux paramètres ne peuvent être null (au moins un des deux non null).
	 * 
	 * @param idLangage l'id du langage
	 * @param idCategory l'id de la catégorie
	 * @return Utilisateur l'utilisateur trouvé
	 * @throws FonctionnelleException
	 */
	public List<Utilisateur> searchUserByLanguageOrCategory(final Long idLangage, final Long idCategory)throws FonctionnelleException;
	
	/**
	 * Cette méthode va permettre de créer un fichier CV pour un utilisateur. 
	 * L’identifiant de l’utilisateur doit être non null et le typeDocument provient de l’ENUM TypeDocument.
	 * 
	 * @param idUser l'id de l'utilisateur
	 * @param typeDocument le type de document du CV
	 * @throws FonctionnelleException
	 */
	public void createCV(final Long idUser, final TypeDocument typeDocument)throws FonctionnelleException;
}
