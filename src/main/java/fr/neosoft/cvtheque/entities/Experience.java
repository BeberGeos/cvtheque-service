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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Version;


/**
 * The persistent class for the experience database table.
 * 
 */
@Entity
@NamedQuery(name="Experience.findAll", query="SELECT e FROM Experience e")
public class Experience implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="DATE_DEBUT")
	private Timestamp dateDebut;

	@Column(name="DATE_FIN")
	private Timestamp dateFin;

	@Column(name="DATE_MODIFICATION")
	private Timestamp dateModification;

	@Column(name="DETAIL_EXPERIENCE")
	private String detailExperience;

	@Version
	@Column(name="VERSION")
	private int version;

	//bi-directional many-to-many association to Competence
	@ManyToMany
	@JoinTable(
		name="exp_comp"
		, joinColumns={
			@JoinColumn(name="ID_EXPERIENCE")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_COMPETENCE")
			}
		)
	private List<Competence> competences;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="ID_CLIENT")
	private Client client;

	//bi-directional many-to-many association to Utilisateur
	@ManyToMany(mappedBy="experiences")
	private List<Utilisateur> utilisateurs;

	public Experience() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Timestamp dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Timestamp getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Timestamp dateFin) {
		this.dateFin = dateFin;
	}

	public Timestamp getDateModification() {
		return this.dateModification;
	}

	public void setDateModification(Timestamp dateModification) {
		this.dateModification = dateModification;
	}

	public String getDetailExperience() {
		return this.detailExperience;
	}

	public void setDetailExperience(String detailExperience) {
		this.detailExperience = detailExperience;
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

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	@Override
	public String toString() {
		return "Experience [id=" + id + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", dateModification="
				+ dateModification + ", detailExperience=" + detailExperience
				+ ", version=" + version + ", competences=" + competences
				+ ", client=" + client + ", utilisateurs=" + utilisateurs + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result
				+ ((competences == null) ? 0 : competences.hashCode());
		result = prime * result
				+ ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime
				* result
				+ ((dateModification == null) ? 0 : dateModification.hashCode());
		result = prime
				* result
				+ ((detailExperience == null) ? 0 : detailExperience.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((utilisateurs == null) ? 0 : utilisateurs.hashCode());
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
		Experience other = (Experience) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (competences == null) {
			if (other.competences != null)
				return false;
		} else if (!competences.equals(other.competences))
			return false;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (dateFin == null) {
			if (other.dateFin != null)
				return false;
		} else if (!dateFin.equals(other.dateFin))
			return false;
		if (dateModification == null) {
			if (other.dateModification != null)
				return false;
		} else if (!dateModification.equals(other.dateModification))
			return false;
		if (detailExperience == null) {
			if (other.detailExperience != null)
				return false;
		} else if (!detailExperience.equals(other.detailExperience))
			return false;
		if (id != other.id)
			return false;
		if (utilisateurs == null) {
			if (other.utilisateurs != null)
				return false;
		} else if (!utilisateurs.equals(other.utilisateurs))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}