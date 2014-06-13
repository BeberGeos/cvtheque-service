package fr.neosoft.cvtheque.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the client database table.
 * 
 */
@Entity
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="DATE_MODIFICATION")
	private Timestamp dateModification;

	@Column(name="NOM")
	private String nom;

	@Column(name="SIRET")
	private int siret;

	@Version
	@Column(name="VERSION")
	private int version;

	//bi-directional many-to-one association to Adresse
	@ManyToOne
	@JoinColumn(name="ID_ADRESSE")
	private Adresse adresse;

	//bi-directional many-to-one association to Experience
	@OneToMany(mappedBy="client")
	private List<Experience> experiences;

	public Client() {
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

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getSiret() {
		return this.siret;
	}

	public void setSiret(int siret) {
		this.siret = siret;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Experience> getExperiences() {
		return this.experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", dateModification=" + dateModification
				+ ", nom=" + nom + ", siret=" + siret + ", version=" + version
				+ ", adresse=" + adresse + ", experiences=" + experiences + "]";
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
				+ ((experiences == null) ? 0 : experiences.hashCode());
		result = prime * result + id;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + siret;
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
		Client other = (Client) obj;
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
		if (siret != other.siret)
			return false;
		if (version != other.version)
			return false;
		return true;
	}
	
}