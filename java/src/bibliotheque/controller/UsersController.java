package bibliotheque.controller;

import bibliotheque.model.Utilisateur;
import bibliotheque.model.UtilisateurDAO;
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

public class UsersController implements Initializable {

	UtilisateurDAO userDAO;
	ObservableList<Utilisateur> user;
	private Stage primaryStage;
	String prete = "SELECT * FROM utilisateurs WHERE id = (SELECT id_emprunteur FROM emprunts WHERE rendu = 'Non');";
	
	@FXML
	private  TableView<Utilisateur> table_users;
	@FXML
	private TableColumn<Utilisateur, String> user_lastname;
	@FXML
	private TableColumn<Utilisateur, String> user_firstname;
	@FXML
	private TableColumn<Utilisateur, String> user_borndat;
	@FXML
	private TableColumn<Utilisateur, String> user_sex;
	@FXML
	private TableColumn<Utilisateur, String> user_address;
	@FXML
	private TableColumn<Utilisateur, String> user_profession;
	@FXML
	private TableColumn<Utilisateur, String> user_telephone;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		all();
	}	

	@FXML
	private void addUser(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/bibliotheque/view/NewUserView.fxml"));
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
		primaryStage.setTitle("NOUVEAU ABONNÉ");
		primaryStage.show();
	}

	@FXML
	private void deleteUser(ActionEvent event) throws ClassNotFoundException {
		
		int selectedIndex = table_users.getSelectionModel().getSelectedIndex();
		
		if (selectedIndex >= 0) {
			String nom = table_users.getSelectionModel().getSelectedItem().getNom();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(null);
			alert.setContentText("Voulez-vous vraiment supprimer " +nom +" ?");
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get() == ButtonType.OK) {
				UtilisateurDAO.deleteUser(nom);
				table_users.getItems().remove(selectedIndex);
			}
			else
			    alert.close();			
		}
		else {
		    Alert alert = new Alert(Alert.AlertType.WARNING);
		    alert.setTitle("Opération non éffectuée");
		    alert.setHeaderText("Aucune personne choisie");
		    alert.setContentText("Veuillez choisir la personne à supprimer");
		    alert.showAndWait();
		}
	}

	@FXML
	private void listeHomme(ActionEvent event) {
		
		userDAO = new UtilisateurDAO();
		String req = "SELECT * FROM utilisateurs WHERE sex = 'Homme';";
		
		try {
			user = userDAO.getListUsers(req);
			user_lastname.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
			user_firstname.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
			user_borndat.setCellValueFactory(cellData -> cellData.getValue().dateNaissanceProperty());
			user_sex.setCellValueFactory(cellData -> cellData.getValue().sexeProperty());
			user_address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
			user_profession.setCellValueFactory(cellData -> cellData.getValue().professionProperty());
			user_telephone.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty());
			table_users.setItems(user);
		} catch (SQLException | ClassNotFoundException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERREUR");
			alert.setHeaderText(null);
			alert.setContentText(ex.getLocalizedMessage());
			alert.showAndWait();
			Logger.getLogger(CatalogueController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void listeFemme(ActionEvent event) {
		
		userDAO = new UtilisateurDAO();
		String req = "SELECT * FROM utilisateurs WHERE sex = 'Femme';";
		
		try {
			user = userDAO.getListUsers(req);
			user_lastname.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
			user_firstname.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
			user_borndat.setCellValueFactory(cellData -> cellData.getValue().dateNaissanceProperty());
			user_sex.setCellValueFactory(cellData -> cellData.getValue().sexeProperty());
			user_address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
			user_profession.setCellValueFactory(cellData -> cellData.getValue().professionProperty());
			user_telephone.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty());
			table_users.setItems(user);
		} catch (SQLException | ClassNotFoundException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERREUR");
			alert.setHeaderText(null);
			alert.setContentText(ex.getLocalizedMessage());
			alert.showAndWait();
			Logger.getLogger(CatalogueController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void listePretes(ActionEvent event) {
		
		userDAO = new UtilisateurDAO();
		String req = "SELECT * FROM utilisateurs WHERE etat = 'Emprunteur';";
		
		try {
			user = userDAO.getListUsers(req);
			user_lastname.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
			user_firstname.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
			user_borndat.setCellValueFactory(cellData -> cellData.getValue().dateNaissanceProperty());
			user_sex.setCellValueFactory(cellData -> cellData.getValue().sexeProperty());
			user_address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
			user_profession.setCellValueFactory(cellData -> cellData.getValue().professionProperty());
			user_telephone.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty());
			table_users.setItems(user);
		} catch (SQLException | ClassNotFoundException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERREUR");
			alert.setHeaderText(null);
			alert.setContentText(ex.getLocalizedMessage());
			alert.showAndWait();
			Logger.getLogger(CatalogueController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void listeNonPretes(ActionEvent event) throws SQLException, ClassNotFoundException {
		
		userDAO = new UtilisateurDAO();
		String req = "SELECT * FROM utilisateurs WHERE etat = 'Non Emprunteur';";
		
		try {
			user = userDAO.getListUsers(req);
			user_lastname.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
			user_firstname.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
			user_borndat.setCellValueFactory(cellData -> cellData.getValue().dateNaissanceProperty());
			user_sex.setCellValueFactory(cellData -> cellData.getValue().sexeProperty());
			user_address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
			user_profession.setCellValueFactory(cellData -> cellData.getValue().professionProperty());
			user_telephone.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty());
			table_users.setItems(user);
		} catch (SQLException | ClassNotFoundException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERREUR");
			alert.setHeaderText(null);
			alert.setContentText(ex.getLocalizedMessage());
			alert.showAndWait();
			Logger.getLogger(CatalogueController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void listAll(ActionEvent event) {
		all();
	}
	
	private void all() {
		
		userDAO = new UtilisateurDAO();
		String req = "SELECT * FROM utilisateurs;";
		
		try {
			user = userDAO.getListUsers(req);
			user_lastname.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
			user_firstname.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
			user_borndat.setCellValueFactory(cellData -> cellData.getValue().dateNaissanceProperty());
			user_sex.setCellValueFactory(cellData -> cellData.getValue().sexeProperty());
			user_address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
			user_profession.setCellValueFactory(cellData -> cellData.getValue().professionProperty());
			user_telephone.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty());
			table_users.setItems(user);
		} catch (SQLException | ClassNotFoundException ex) {
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERREUR");
			alert.setHeaderText(null);
			alert.setContentText(ex.getLocalizedMessage());
			alert.showAndWait();
			Logger.getLogger(CatalogueController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
