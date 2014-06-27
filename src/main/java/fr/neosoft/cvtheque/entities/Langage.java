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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Size;


/**
 * The persistent class for the langage database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Langage.findAll", query="SELECT l FROM Langage l"),
	@NamedQuery(name="Langage.findByName", query="SELECT l FROM Langage l WHERE l.libelle LIKE '%:libelle%'")
})
@Stateless
@Local
public class Langage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="DATE_MODIFICATION")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Calendar dateModification;

	@Column(name="LIBELLE")
	@Size(max=20)
	private String libelle;

	@Version
	@Column(name="VERSION")
	private int version;

	//bi-directional many-to-one association to Competence
	@OneToMany(mappedBy="langage")
	private List<Competence> competences;

	public Langage() {
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

	public List<Competence> getCompetences() {
		return this.competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	@Override
	public String toString() {
		return "Langage [id=" + id + ", dateModification=" + dateModification
				+ ", libelle=" + libelle + ", version=" + version
				+ ", competences=" + competences + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((competences == null) ? 0 : competences.hashCode());
		result = prime
				* result
				+ ((dateModification == null) ? 0 : dateModification.hashCode());
		result = prime * result + id;
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
		Langage other = (Langage) obj;
		if (competences == null) {
			if (other.competences != null)
				return false;
		} else if (!competences.equals(other.competences))
			return false;
		if (dateModification == null) {
			if (other.dateModification != null)
				return false;
		} else if (!dateModification.equals(other.dateModification))
			return false;
		if (id != other.id)
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