package fr.neosoft.cvtheque.utils;

public class FonctionnelleException extends RuntimeException {
	
	private String codeErreur;
	private String nomParam;
	
	public FonctionnelleException(String codeErreur, String nomParam){
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
