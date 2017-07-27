package bibliotheque.controller;

import bibliotheque.model.Emprunt;
import bibliotheque.model.EmpruntDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class EmpruntsController implements Initializable {

	private EmpruntDAO empruntDAO;
	private ObservableList<Emprunt> emprunt;
	private Stage primaryStage;
	
	@FXML
	private TableView<Emprunt> table_emprunts;
	@FXML
	private TableColumn<Emprunt, String> username;
	@FXML
	private TableColumn<Emprunt, String> titre;
	@FXML
	private TableColumn<Emprunt, String> date_emprunt;
	@FXML
	private TableColumn<Emprunt, String> date_retour;
	@FXML
	private TableColumn<Emprunt, String> rendu;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		all();
	}	

	@FXML
	private void newEmprunt(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/bibliotheque/view/NewEmpruntView.fxml"));
		try {
			loader.load();
		} catch (IOException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERREUR");
			alert.setHeaderText(null);
			alert.setContentText(ex.getLocalizedMessage());
			alert.showAndWait();
		}
		
		primaryStage = new Stage();
		Parent parent = loader.getRoot();
		Scene adminPanelScene = new Scene(parent);
		primaryStage.setScene(adminPanelScene);
		primaryStage.setTitle("NOUVEL EMPRUNT");
		primaryStage.show();
	}

	@FXML
	private void reintegration(ActionEvent event) throws ClassNotFoundException {
		
		int selectedIndex = table_emprunts.getSelectionModel().getSelectedIndex();
			
		if (selectedIndex >= 0) {
			
			String user = table_emprunts.getSelectionModel().getSelectedItem().getNomEmprunteur();
			String doc = table_emprunts.getSelectionModel().getSelectedItem().getTitreDocument();
			String rendu = table_emprunts.getSelectionModel().getSelectedItem().getRendu();
			
			if(rendu.equals("Non")) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation");
				alert.setHeaderText(null);
				alert.setContentText("Voulez-vous vraiment réintégrer " +doc +" ?");
				Optional<ButtonType> result = alert.showAndWait();
				
				if (result.get() == ButtonType.OK) {
					EmpruntDAO.reintegration(user, doc);
					table_emprunts.getItems().remove(selectedIndex);
				}
				else
				    alert.close();
			}
			else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Opération non éffectuée");
				alert.setHeaderText(null);
				alert.setContentText("Ce document est déjà réintégré");
				alert.showAndWait();
			}
		}
		else {
		    Alert alert = new Alert(AlertType.WARNING);
		    alert.setTitle("Opération non éffectuée");
		    alert.setHeaderText("Aucun document choisi");
		    alert.setContentText("Veuillez choisir le document à réintégrer");
		    alert.showAndWait();
		}
	}

	@FXML
	private void allEmprunts(ActionEvent event) {
		all();
	}

	@FXML
	private void empruntsNonRendus(ActionEvent event) {
		
		empruntDAO = new EmpruntDAO();
		String req = "SELECT * FROM emprunts WHERE rendu = 'Non';";
		
		try {
			emprunt = empruntDAO.getListEmprunts(req);
			username.setCellValueFactory(cellData -> cellData.getValue().nomEmprunteurProperty());
			titre.setCellValueFactory(cellData -> cellData.getValue().titreDocumentProperty());
			date_emprunt.setCellValueFactory(cellData -> cellData.getValue().dateEmpruntProperty());
			date_retour.setCellValueFactory(cellData -> cellData.getValue().dateRetourProperty());
			rendu.setCellValueFactory(cellData -> cellData.getValue().renduProperty());
			table_emprunts.setItems(emprunt);
		} catch (SQLException | ClassNotFoundException ex) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERREUR");
			alert.setHeaderText(null);
			alert.setContentText(ex.getLocalizedMessage());
			alert.showAndWait();
			Logger.getLogger(CatalogueController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void empruntsRendus(ActionEvent event) {
		
		empruntDAO = new EmpruntDAO();
		String req = "SELECT * FROM emprunts WHERE rendu = 'Oui';";
		
		try {
			emprunt = empruntDAO.getListEmprunts(req);
			username.setCellValueFactory(cellData -> cellData.getValue().nomEmprunteurProperty());
			titre.setCellValueFactory(cellData -> cellData.getValue().titreDocumentProperty());
			date_emprunt.setCellValueFactory(cellData -> cellData.getValue().dateEmpruntProperty());
			date_retour.setCellValueFactory(cellData -> cellData.getValue().dateRetourProperty());
			rendu.setCellValueFactory(cellData -> cellData.getValue().renduProperty());
			table_emprunts.setItems(emprunt);
		} catch (SQLException | ClassNotFoundException ex) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERREUR");
			alert.setHeaderText(null);
			alert.setContentText(ex.getLocalizedMessage());
			alert.showAndWait();
			Logger.getLogger(CatalogueController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private void all() {
		
		empruntDAO = new EmpruntDAO();
		String req = "SELECT * FROM emprunts;";
		
		try {
			emprunt = empruntDAO.getListEmprunts(req);
			username.setCellValueFactory(cellData -> cellData.getValue().nomEmprunteurProperty());
			titre.setCellValueFactory(cellData -> cellData.getValue().titreDocumentProperty());
			date_emprunt.setCellValueFactory(cellData -> cellData.getValue().dateEmpruntProperty());
			date_retour.setCellValueFactory(cellData -> cellData.getValue().dateRetourProperty());
			rendu.setCellValueFactory(cellData -> cellData.getValue().renduProperty());
			table_emprunts.setItems(emprunt);
		} catch (SQLException | ClassNotFoundException ex) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERREUR");
			alert.setHeaderText(null);
			alert.setContentText(ex.getLocalizedMessage());
			alert.showAndWait();
			Logger.getLogger(CatalogueController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
