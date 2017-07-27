package bibliotheque.controller;

import bibliotheque.model.CatalogueDAO;
import bibliotheque.model.Catalogue;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CatalogueController implements Initializable {

	private CatalogueDAO docDAO;
	private ObservableList<Catalogue> collec;
	private Stage primaryStage;
	
	@FXML
	private  TableView<Catalogue> table_doc;
	@FXML
	private TableColumn<Catalogue, String> doc_titre;
	@FXML
	private TableColumn<Catalogue, String> doc_type;
	@FXML
	private TableColumn<Catalogue, String> doc_auteur;
	@FXML
	private TableColumn<Catalogue, String> doc_edition;
	@FXML
	private TableColumn<Catalogue, String> doc_etat;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		all();
	}

	@FXML
	private void addDocument(ActionEvent event) {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/bibliotheque/view/NewDocumentView.fxml"));
		try {
			loader.load();
		} catch (IOException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERREUR");
			alert.setHeaderText(null);
			alert.setContentText(ex.getLocalizedMessage());
			alert.showAndWait();
		}
		
		primaryStage=new Stage();
		Parent parent = loader.getRoot();
		Scene adminPanelScene = new Scene(parent);
		primaryStage.setScene(adminPanelScene);
		primaryStage.setTitle("NOUVEAU DOCUMENT");
		primaryStage.show();
	}

	@FXML
	private void deleteDocument(ActionEvent event) throws ClassNotFoundException {

		int selectedIndex = table_doc.getSelectionModel().getSelectedIndex();
		System.out.println(selectedIndex);
		
		if (selectedIndex >= 0) {
			String titre = table_doc.getSelectionModel().getSelectedItem().getTitre();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(null);
			alert.setContentText("Voulez-vous vraiment supprimer " +titre +" ?");
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get() == ButtonType.OK) {
				CatalogueDAO.deleteDocument(titre);
				table_doc.getItems().remove(selectedIndex);
			}
			else
			    alert.close();
		}
		else {
		    Alert alert = new Alert(AlertType.WARNING);
		    alert.setTitle("Opération non éffectuée");
		    alert.setHeaderText("Aucun document choisi");
		    alert.setContentText("Veuillez choisir le document à supprimer");
		    alert.showAndWait();
		}
	}

	@FXML
	private void barrowDocument(ActionEvent event) {
		docDAO = new CatalogueDAO();
		String req = "SELECT * FROM documents WHERE etat = 'Emprunté';";
		
		try {
			collec = docDAO.getListDocuments(req);
			doc_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
			doc_auteur.setCellValueFactory(cellData -> cellData.getValue().auteurProperty());
			doc_edition.setCellValueFactory(cellData -> cellData.getValue().editionProperty());
			doc_type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
			doc_etat.setCellValueFactory(cellData -> cellData.getValue().etatProperty());
			table_doc.setItems(collec);
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
	private void availableDocument(ActionEvent event) {
		docDAO = new CatalogueDAO();
		String req = "SELECT * FROM documents WHERE etat = 'Disponible';";
		
		try {
			collec = docDAO.getListDocuments(req);
			doc_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
			doc_auteur.setCellValueFactory(cellData -> cellData.getValue().auteurProperty());
			doc_edition.setCellValueFactory(cellData -> cellData.getValue().editionProperty());
			doc_type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
			doc_etat.setCellValueFactory(cellData -> cellData.getValue().etatProperty());
			table_doc.setItems(collec);
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
	private void allDocument(ActionEvent event) {
		all();
	}

	@FXML
	private void showBooks(ActionEvent event) {
		docDAO = new CatalogueDAO();
		String req = "SELECT * FROM documents WHERE type = 'Livre';";
		
		try {
			collec = docDAO.getListDocuments(req);
			doc_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
			doc_auteur.setCellValueFactory(cellData -> cellData.getValue().auteurProperty());
			doc_edition.setCellValueFactory(cellData -> cellData.getValue().editionProperty());
			doc_type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
			doc_etat.setCellValueFactory(cellData -> cellData.getValue().etatProperty());
			table_doc.setItems(collec);
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
	private void showMemoires(ActionEvent event) {
		docDAO = new CatalogueDAO();
		String req = "SELECT * FROM documents WHERE type = 'Mémoire';";
		
		try {
			collec = docDAO.getListDocuments(req);
			doc_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
			doc_auteur.setCellValueFactory(cellData -> cellData.getValue().auteurProperty());
			doc_edition.setCellValueFactory(cellData -> cellData.getValue().editionProperty());
			doc_type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
			doc_etat.setCellValueFactory(cellData -> cellData.getValue().etatProperty());
			table_doc.setItems(collec);
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
	private void showDVD(ActionEvent event) {
		docDAO = new CatalogueDAO();
		String req = "SELECT * FROM documents WHERE type = 'DVD de documentaire';";
		
		try {
			collec = docDAO.getListDocuments(req);
			doc_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
			doc_auteur.setCellValueFactory(cellData -> cellData.getValue().auteurProperty());
			doc_edition.setCellValueFactory(cellData -> cellData.getValue().editionProperty());
			doc_type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
			doc_etat.setCellValueFactory(cellData -> cellData.getValue().etatProperty());
			table_doc.setItems(collec);
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
		
		docDAO = new CatalogueDAO();
		String req = "SELECT * FROM documents;";
		
		try {
			collec = docDAO.getListDocuments(req);
			doc_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
			doc_auteur.setCellValueFactory(cellData -> cellData.getValue().auteurProperty());
			doc_edition.setCellValueFactory(cellData -> cellData.getValue().editionProperty());
			doc_type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
			doc_etat.setCellValueFactory(cellData -> cellData.getValue().etatProperty());
			table_doc.setItems(collec);
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
