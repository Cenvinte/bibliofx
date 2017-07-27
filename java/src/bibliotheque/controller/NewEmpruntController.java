package bibliotheque.controller;

import bibliotheque.model.DBUtil;
import bibliotheque.model.Emprunt;
import bibliotheque.model.EmpruntDAO;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class NewEmpruntController implements Initializable {

	Emprunt emprun;
	
	@FXML
	private ComboBox<String> combobox_user;
	@FXML
	private ComboBox<String> combobox_titre;
	@FXML
	private DatePicker date_emprunt;
	@FXML
	private JFXDatePicker date_retour;

	@FXML
	private void actionEmprunt(ActionEvent event) throws SQLException, ClassNotFoundException {
		
		if(isInputValid()) {
			emprun = new Emprunt();
			emprun.setNomEmprunteur(combobox_user.getValue());
			emprun.setTitreDocument(combobox_titre.getValue());
			emprun.setDateEmprunt(date_emprunt.getValue());
			emprun.setDateRetour(date_retour.getValue());
			emprun.setRenduDocument("Non");
			EmpruntDAO.addEmprunt(emprun);
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
	}
	
	@FXML
	private void cancel(ActionEvent event) {
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		String selectStmt1 = "SELECT nom FROM utilisateurs WHERE etat = 'Non Emprunteur'";
		String selectStmt2 = "SELECT titre FROM documents WHERE etat = 'Disponible'";
		
		try {
			ResultSet rs1 = DBUtil.dbExecuteQuery(selectStmt1);
			ResultSet rs2 = DBUtil.dbExecuteQuery(selectStmt2);
			
			while(rs1.next())
				combobox_user.getItems().add(rs1.getString("nom"));
			while(rs2.next())
				combobox_titre.getItems().add(rs2.getString("titre"));
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(NewEmpruntController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private boolean isInputValid() {
		String errorMessage = "", errorHeader = "";
		int cpt = 0;

		if (combobox_user.getValue() == null || combobox_user.getValue().length() == 0) {
			errorMessage += "Veuillez choisir un utilisateur!\n";
			cpt ++;
		}
		if (combobox_titre.getValue() == null || combobox_titre.getValue().length() == 0) {
			errorMessage += "Veuillez choisir un document!\n";
			cpt ++;
		}
		if (date_emprunt.getValue() == null || date_emprunt.getValue().toString().length() == 0) {
			errorMessage += "La date de l'emprunt n'est pas valide!\n";
			cpt ++;
		}
		if (date_retour.getValue() == null || date_retour.getValue().toString().length() == 0) {
			errorMessage += "La date du retour de l'emprunt n'est pas valide!\n";
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
