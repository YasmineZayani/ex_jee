package tn.iit.user.bean;

public class User {
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String numtel;
	private String domaine;
	private String cv;


	
	public User(int id, String nom, String prenom, String adresse, String numtel, String domaine,String cv) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numtel = numtel;
		this.domaine = domaine;
		this.cv =cv;
	}

	public User( String nom, String prenom, String adresse, String numtel, String domaine,String cv) {
		super();	
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numtel = numtel;
		this.domaine = domaine;
		this.cv =cv;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNumtel() {
		return numtel;
	}

	public void setNumtel(String numtel) {
		this.numtel = numtel;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}
	


}
