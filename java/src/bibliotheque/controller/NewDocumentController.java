package bibliotheque.controller;

import bibliotheque.model.Catalogue;
import bibliotheque.model.CatalogueDAO;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

public class NewDocumentController implements Initializable {
	
	Catalogue livre;
	
	@FXML
	private JFXTextField edittext_titre;
	@FXML
	private JFXTextField edittext_auteur;
	@FXML
	private JFXTextField edittext_edition;
	@FXML
	private ComboBox<String> combobox_type;

	@FXML
	private void addDocument(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		if(isInputValid()) {
			
			livre = new Catalogue();
			livre.setTitre(edittext_titre.getText());
			livre.setAuteur(edittext_auteur.getText());
			livre.setEdition(edittext_edition.getText());
			livre.setType(combobox_type.getValue());
			livre.setEtat("Disponible");
			CatalogueDAO.addDocument(livre);
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
	}
	
	private boolean isInputValid() {
		String errorMessage = "", errorHeader = "";
		int cpt = 0;

		if (edittext_titre.getText() == null || edittext_titre.getText().length() == 0) {
			errorMessage += "Le titre du document n'est pas valide!\n";
			cpt ++;
		}
		if (edittext_auteur.getText() == null || edittext_auteur.getText().length() == 0) {
			errorMessage += "L'auteur du document n'est pas valide!\n";
			cpt ++;
		}
		if (edittext_edition.getText() == null || edittext_edition.getText().length() == 0) {
			errorMessage += "L'édition du document n'est pas valide!\n";
			cpt ++;
		}
		if (combobox_type.getValue() == null || combobox_type.getValue().length() == 0) {
			errorMessage += "Veuillez choisir le type du docment!\n";
			cpt ++;
		}
		
		if (errorMessage.length() == 0)
		    return true;
		else {
			if(cpt == 0)
				errorHeader = "Champ invalide";
			else if(cpt > 0)
				errorHeader = "Champs invalides";
		
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText(errorHeader);
			alert.setContentText(errorMessage);
			alert.showAndWait();

			return false;
		}
	}
	
	@FXML
	private void cancel(ActionEvent event) {
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		combobox_type.getItems().addAll("Livre", "Mémoire", "DVD de documentaires");
	}
}
