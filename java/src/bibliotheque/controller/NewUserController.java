package bibliotheque.controller;

import bibliotheque.model.Utilisateur;
import bibliotheque.model.UtilisateurDAO;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;

public class NewUserController implements Initializable {

	Utilisateur user;
	String sex = null;
	
	@FXML
	private JFXDatePicker birthdat;
	@FXML
	private JFXTextField edittext_nom;
	@FXML
	private JFXTextField edittext_prenom;
	@FXML
	private JFXTextField edittext_address;
	@FXML
	private JFXRadioButton radiobutton_h;
	@FXML
	private JFXRadioButton radiobutton_f;
	@FXML
	private JFXTextField edittext_metier;
	@FXML
	private JFXTextField edittext_tel;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		ToggleGroup se = new ToggleGroup();
		radiobutton_f.setToggleGroup(se);
		radiobutton_h.setToggleGroup(se);
		radiobutton_h.setSelected(true);
	}	

	@FXML
	private void newUser(ActionEvent event) throws ClassNotFoundException, SQLException {
		if(isInputValid()) {
			
			if(radiobutton_f.isSelected())
				sex = "Femme";
			else if(radiobutton_h.isSelected())
				sex = "Homme";
			
			user = new Utilisateur();
			user.setNom(edittext_nom.getText());
			user.setPrenom(edittext_prenom.getText());
			user.setAddress(edittext_address.getText());
			user.setDateNaissance(birthdat.getValue());
			user.setSexe(sex);
			user.setProfession(edittext_metier.getText());
			user.setTelephone(edittext_tel.getText());
			UtilisateurDAO.addUser(user);
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
	}

	@FXML
	private void cancel(ActionEvent event) {
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	private boolean isInputValid() {
		String errorMessage = "", errorHeader = "";
		int cpt = 0;

		if (edittext_nom.getText() == null || edittext_nom.getText().length() == 0) {
			errorMessage += "Le nom de l'utilisateur n'est pas valide!\n";
			cpt ++;
		}
		if (edittext_prenom.getText() == null || edittext_prenom.getText().length() == 0) {
			errorMessage += "Le prénom de l'utilisateur n'est pas valide!\n";
			cpt ++;
		}
		if (edittext_address.getText() == null || edittext_address.getText().length() == 0) {
			errorMessage += "L'adresse de l'utilisateur n'est pas valide!\n";
			cpt ++;
		}
		if (edittext_metier.getText() == null || edittext_metier.getText().length() == 0) {
			errorMessage += "La profession de l'utilisateur n'est pas valide!\n";
			cpt ++;
		}
		if (edittext_tel.getText() == null || edittext_tel.getText().length() == 0) {
			errorMessage += "Le numero de téléphone de l'utilisateur n'est pas valide!\n";
			cpt ++;
		}
		if (birthdat.getValue() == null || birthdat.getValue().toString().length() == 0) {
			errorMessage += "La date de naissance de l'utilisateur n'est pas valide!\n";
			cpt ++;
		}
		
		if (errorMessage.length() == 0)
		    return true;
		else {
			if(cpt == 0)
				errorHeader = "Champ invalide";
			else if(cpt > 0)
				errorHeader = "Champs invalides";
		
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText(errorHeader);
			alert.setContentText(errorMessage);
			alert.showAndWait();

			return false;
		}
	}
}
