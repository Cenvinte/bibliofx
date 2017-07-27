package bibliotheque.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Utilisateur {
	
	private final StringProperty nom, prenom, sexe, profession, adress, telephone;
	private final ObjectProperty<LocalDate> date_naissance;
	
	public Utilisateur() {
		this.nom = new SimpleStringProperty();
		this.prenom = new SimpleStringProperty();
		this.sexe = new SimpleStringProperty();
		this.date_naissance = new SimpleObjectProperty<>();
		this.profession = new SimpleStringProperty();
		this.adress = new SimpleStringProperty();
		this.telephone = new SimpleStringProperty();
	}

	public String getNom() {
		return nom.get();
	}
	public void setNom(String name) {
		this.nom.set(name);
	}
	public StringProperty nomProperty() {
		return nom;
	}
	
	
	public String getPrenom() {
		return prenom.get();
	}
	public void setPrenom(String prename) {
		this.prenom.set(prename);
	}
	public StringProperty prenomProperty() {
		return prenom;
	}

	
	public LocalDate getDateNaissance() {
		return date_naissance.get();
	}
	public void setDateNaissance(LocalDate date) {
		this.date_naissance.set(date);
	}
	public ObjectProperty dateNaissanceProperty() {
		return date_naissance;
	}
	
	
	public String getSexe() {
		return sexe.get();
	}
	public void setSexe(String sex) {
		this.sexe.set(sex);
	}
	public StringProperty sexeProperty() {
		return sexe;
	}
	
	
	public String getProfession() {
		return profession.get();
	}
	public void setProfession(String profess) {
		this.profession.set(profess);
	}
	public StringProperty professionProperty() {
		return profession;
	}
	
	
	public String getAddress() {
		return adress.get();
	}
	public void setAddress(String addres) {
		this.adress.set(addres);
	}
	public StringProperty addressProperty() {
		return adress;
	}
	
	
	public String getTelephone() {
		return telephone.get();
	}
	public void setTelephone(String tel) {
		this.telephone.set(tel);
	}
	public StringProperty telephoneProperty() {
		return telephone;
	}
}
