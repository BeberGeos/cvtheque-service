package fr.neosoft.cvtheque.utils;

/**
 * Permet de traduire les exceptions levées par l'appli.
 * 
 * @author Adrien Cambillau
 *
 */
public class FonctionnelleException extends RuntimeException {
	
	private String codeErreur;
	private String nomParam;
	
	/**
	 * Permet de traduire les exceptions de l'application.
	 * 
	 * @param codeErreur le code de l'erreur
	 * @param nomParam le paramètre en erreur
	 */
	public FonctionnelleException(String codeErreur, String nomParam){
		super();
		this.codeErreur = codeErreur;
		this.nomParam = nomParam;
	}

	public String getCodeErreur() {
		return codeErreur;
	}

	public void setCodeErreur(String codeErreur) {
		this.codeErreur = codeErreur;
	}

	public String getNomParam() {
		return nomParam;
	}

	public void setNomParam(String nomParam) {
		this.nomParam = nomParam;
	}

}
