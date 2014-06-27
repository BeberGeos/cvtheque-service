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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u")
@Stateless
@Local
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="DATE_MODIFICATION")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Calendar dateModification;

	@Column(name="DATE_NAISSANCE")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Past
	private Calendar dateNaissance;

	@Column(name="NOM")
	@Size(max=50)
	private String nom;

	@Column(name="PRENOM")
	@Size(max=50)
	private String prenom;

	@Version
	@Column(name="VERSION")
	private int version;

	//bi-directional many-to-many association to Experience
	@ManyToMany
	@JoinTable(
		name="util_exp"
		, joinColumns={
			@JoinColumn(name="ID_UTILISATEUR")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_EXPERIENCE")
			}
		)
	private List<Experience> experiences;

	//bi-directional many-to-one association to Adresse
	@ManyToOne
	@JoinColumn(name="ID_ADRESSE")
	private Adresse adresse;

	public Utilisateur() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDateModification() {
		return this.dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public Calendar getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Calendar dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<Experience> getExperiences() {
		return this.experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", dateModification="
				+ dateModification + ", dateNaissance=" + dateNaissance
				+ ", nom=" + nom + ", prenom=" + prenom + ", version="
				+ version + ", experiences=" + experiences + ", adresse="
				+ adresse + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime
				* result
				+ ((dateModification == null) ? 0 : dateModification.hashCode());
		result = prime * result
				+ ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
		result = prime * result
				+ ((experiences == null) ? 0 : experiences.hashCode());
		result = prime * result + id;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + version;
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
		Utilisateur other = (Utilisateur) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (dateModification == null) {
			if (other.dateModification != null)
				return false;
		} else if (!dateModification.equals(other.dateModification))
			return false;
		if (dateNaissance == null) {
			if (other.dateNaissance != null)
				return false;
		} else if (!dateNaissance.equals(other.dateNaissance))
			return false;
		if (experiences == null) {
			if (other.experiences != null)
				return false;
		} else if (!experiences.equals(other.experiences))
			return false;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}