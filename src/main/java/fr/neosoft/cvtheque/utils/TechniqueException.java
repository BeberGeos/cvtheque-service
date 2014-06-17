package fr.neosoft.cvtheque.utils;

public class TechniqueException extends RuntimeException {
	private String codeErreur;
	private String nomParam;
	
	/**
	 * Permet de traduire les exceptions techniques (parse, nullpointer etc...).
	 * 
	 * @param codeErreur le code de l'erreur
	 * @param nomParam le paramètre en erreur
	 */
	public TechniqueException(String codeErreur, String nomParam){
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
