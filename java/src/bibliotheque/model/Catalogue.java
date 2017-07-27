package bibliotheque.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Catalogue {
	
	private final StringProperty titre, auteur, edition, type, etat;
	
	public Catalogue() {
		this.auteur = new SimpleStringProperty();
		this.titre = new SimpleStringProperty();
		this.edition = new SimpleStringProperty();
		this.type = new SimpleStringProperty();
		this.etat = new SimpleStringProperty();
	}

	public String getTitre() {
		return titre.get();
	}
	public void setTitre(String titr) {
		this.titre.set(titr);
	}
	public StringProperty titreProperty() {
		return titre;
	}
	
	
	public String getAuteur() {
		return auteur.get();
	}
	public void setAuteur(String auteu) {
		this.auteur.set(auteu);
	}
	public StringProperty auteurProperty() {
		return auteur;
	}

	
	public String getEdition() {
		return edition.get();
	}
	public void setEdition(String editio) {
		this.edition.set(editio);
	}
	public StringProperty editionProperty() {
		return edition;
	}
	
	
	public String getType() {
		return type.get();
	}
	public void setType(String typ) {
		this.type.set(typ);
	}
	public StringProperty typeProperty() {
		return type;
	}
	
	
	public String getEtat() {
		return etat.get();
	}
	public void setEtat(String eta) {
		this.etat.set(eta);
	}
	public StringProperty etatProperty() {
		return etat;
	}
}
