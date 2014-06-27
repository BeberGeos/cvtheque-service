package fr.neosoft.cvtheque.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;


/**
 * The persistent class for the adresse database table.
 * 
 */
@Entity
@NamedQuery(name="Adresse.findAll", query="SELECT a FROM Adresse a")
@Stateless
@Local
public class Adresse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CODE_POSTAL")
	@Max(99999)
	private int codePostal;

	@Column(name="DATE_MODIFICATION")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Calendar dateModification;

	@Column(name="RUE")
	private String rue;

	@Version
	@Column(name="VERSION")
	private int version;

	@Column(name="VILLE")
	@Size(max=50)
	private String ville;

	//bi-directional many-to-one association to Client
	@OneToMany(mappedBy="adresse")
	private List<Client> clients;

	//bi-directional many-to-one association to Utilisateur
	@OneToMany(mappedBy="adresse")
	private List<Utilisateur> utilisateurs;

	public Adresse() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodePostal() {
		return this.codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public Calendar getDateModification() {
		return this.dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public String getRue() {
		return this.rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<Client> getClients() {
		return this.clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	@Override
	public String toString() {
		return "Adresse [id=" + id + ", codePostal=" + codePostal
				+ ", dateModification=" + dateModification + ", rue=" + rue
				+ ", version=" + version + ", ville=" + ville + ", clients="
				+ clients + ", utilisateurs=" + utilisateurs + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clients == null) ? 0 : clients.hashCode());
		result = prime * result + codePostal;
		result = prime
				* result
				+ ((dateModification == null) ? 0 : dateModification.hashCode());
		result = prime * result + id;
		result = prime * result + ((rue == null) ? 0 : rue.hashCode());
		result = prime * result
				+ ((utilisateurs == null) ? 0 : utilisateurs.hashCode());
		result = prime * result + version;
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adresse other = (Adresse) obj;
		if (clients == null) {
			if (other.clients != null)
				return false;
		} else if (!clients.equals(other.clients))
			return false;
		if (codePostal != other.codePostal)
			return false;
		if (dateModification == null) {
			if (other.dateModification != null)
				return false;
		} else if (!dateModification.equals(other.dateModification))
			return false;
		if (id != other.id)
			return false;
		if (rue == null) {
			if (other.rue != null)
				return false;
		} else if (!rue.equals(other.rue))
			return false;
		if (utilisateurs == null) {
			if (other.utilisateurs != null)
				return false;
		} else if (!utilisateurs.equals(other.utilisateurs))
			return false;
		if (version != other.version)
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}
}