package bibliotheque.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Emprunt {
	
	private final StringProperty nom_emprunteur, titre_document, rendu;
	private final ObjectProperty<LocalDate> date_emprunt, date_retour;
	
	public Emprunt() {
		this.nom_emprunteur = new SimpleStringProperty();
		this.titre_document = new SimpleStringProperty();
		this.date_emprunt = new SimpleObjectProperty<>();
		this.date_retour = new SimpleObjectProperty<>();
		this.rendu = new SimpleStringProperty();
	}

	public String getNomEmprunteur() {
		return nom_emprunteur.get();
	}
	public void setNomEmprunteur(String emprunteur) {
		this.nom_emprunteur.set(emprunteur);
	}
	public StringProperty nomEmprunteurProperty() {
		return nom_emprunteur;
	}
	
	
	public String getTitreDocument() {
		return titre_document.get();
	}
	public void setTitreDocument(String titre) {
		this.titre_document.set(titre);
	}
	public StringProperty titreDocumentProperty() {
		return titre_document;
	}
	
	
	public LocalDate getDateEmprunt() {
		return date_emprunt.get();
	}
	public void setDateEmprunt(LocalDate date_emprun) {
		this.date_emprunt.set(date_emprun);
	}
	public ObjectProperty dateEmpruntProperty() {
		return date_emprunt;
	}
	
	
	public LocalDate getDateRetour() {
		return date_retour.get();
	}
	public void setDateRetour(LocalDate date_ret) {
		this.date_retour.set(date_ret);
	}
	public ObjectProperty dateRetourProperty() {
		return date_retour;
	}
	
	public String getRendu() {
		return rendu.get();
	}
	public void setRenduDocument(String rendu) {
		this.rendu.set(rendu);
	}
	public StringProperty renduProperty() {
		return rendu;
	}
}
