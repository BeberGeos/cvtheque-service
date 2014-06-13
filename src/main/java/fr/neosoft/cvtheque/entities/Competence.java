package fr.neosoft.cvtheque.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Version;


/**
 * The persistent class for the competence database table.
 * 
 */
@Entity
@NamedQuery(name="Competence.findAll", query="SELECT c FROM Competence c")
public class Competence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="DATE_MODIFICATION")
	private Timestamp dateModification;

	@Column(name="LIBELLE")
	private String libelle;

	@Version
	@Column(name="VERSION")
	private int version;

	//bi-directional many-to-one association to Categorie
	@ManyToOne
	@JoinColumn(name="ID_CATEGORIE")
	private Categorie categorie;

	//bi-directional many-to-one association to Langage
	@ManyToOne
	@JoinColumn(name="ID_LANGAGE")
	private Langage langage;

	//bi-directional many-to-many association to Experience
	@ManyToMany(mappedBy="competences")
	private List<Experience> experiences;

	public Competence() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDateModification() {
		return this.dateModification;
	}

	public void setDateModification(Timestamp dateModification) {
		this.dateModification = dateModification;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Langage getLangage() {
		return this.langage;
	}

	public void setLangage(Langage langage) {
		this.langage = langage;
	}

	public List<Experience> getExperiences() {
		return this.experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	@Override
	public String toString() {
		return "Competence [id=" + id + ", dateModification="
				+ dateModification + ", libelle=" + libelle + ", version="
				+ version + ", categorie=" + categorie + ", langage=" + langage
				+ ", experiences=" + experiences + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categorie == null) ? 0 : categorie.hashCode());
		result = prime
				* result
				+ ((dateModification == null) ? 0 : dateModification.hashCode());
		result = prime * result
				+ ((experiences == null) ? 0 : experiences.hashCode());
		result = prime * result + id;
		result = prime * result + ((langage == null) ? 0 : langage.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
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
		Competence other = (Competence) obj;
		if (categorie == null) {
			if (other.categorie != null)
				return false;
		} else if (!categorie.equals(other.categorie))
			return false;
		if (dateModification == null) {
			if (other.dateModification != null)
				return false;
		} else if (!dateModification.equals(other.dateModification))
			return false;
		if (experiences == null) {
			if (other.experiences != null)
				return false;
		} else if (!experiences.equals(other.experiences))
			return false;
		if (id != other.id)
			return false;
		if (langage == null) {
			if (other.langage != null)
				return false;
		} else if (!langage.equals(other.langage))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (version != other.version)
			return false;
		return true;
	}
	
}