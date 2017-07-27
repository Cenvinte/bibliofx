package bibliotheque.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class SidePanelController implements Initializable {

	
	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}    

	@FXML
	private void exit(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	private void catalogue(ActionEvent event) throws IOException {
		AnchorPane catalog = FXMLLoader.load(getClass().getResource("/bibliotheque/view/CatalogueView.fxml"));
		BibliothequeController.containerP.getChildren().add(catalog);
	}

	@FXML
	private void users(ActionEvent event) throws IOException {
		AnchorPane user = FXMLLoader.load(getClass().getResource("/bibliotheque/view/UsersView.fxml"));
		BibliothequeController.containerP.getChildren().add(user);
	}

	@FXML
	private void emprunts(ActionEvent event) throws IOException {
		AnchorPane emprunt = FXMLLoader.load(getClass().getResource("/bibliotheque/view/EmpruntsView.fxml"));
		BibliothequeController.containerP.getChildren().add(emprunt);
	}

	@FXML
	private void about(ActionEvent event) throws IOException {
		AnchorPane about = FXMLLoader.load(getClass().getResource("/bibliotheque/view/AboutView.fxml"));
		BibliothequeController.containerP.getChildren().add(about);
	}
	
}
