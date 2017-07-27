package bibliotheque.controller;

import bibliotheque.model.DBUtil;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	private Stage primaryStage;
	@FXML
	private JFXTextField edittext_username;
	@FXML
	private JFXPasswordField edittext_password;

	@FXML
	private void login(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

		String selectStmt = "SELECT * FROM bibliothecaires WHERE username = '" +edittext_username.getText() +"'AND password = '" +edittext_password.getText() +"'";

		try {
			ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
			if(rs.next()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Connexion établie");
				alert.setHeaderText(null);
				alert.setContentText(edittext_username.getText() +" vous êtes connecté au système de gestion de la bibliothèque");
				alert.showAndWait();

				((Node)(actionEvent.getSource())).getScene().getWindow().hide();

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/bibliotheque/view/BibliothequeView.fxml"));
				try {
					loader.load();
				} catch (IOException ex) {
					Alert aler = new Alert(AlertType.ERROR);
					aler.setTitle("ERREUR");
					aler.setHeaderText(null);
					aler.setContentText(ex.getLocalizedMessage());
					aler.showAndWait();
				}

				primaryStage=new Stage();
				Parent parent = loader.getRoot();
				Scene adminPanelScene = new Scene(parent);
				primaryStage.setScene(adminPanelScene);
				primaryStage.setTitle("BIBLIOTHÈQUE LECTURE POUR TOUS");
				primaryStage.show();
			}
			else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Echec de la connexion");
				alert.setHeaderText(null);
				alert.setContentText("Désolé! Utilisateur inexistant. Veuillez réessayer");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERREUR");
			alert.setHeaderText(null);
			alert.setContentText(e.toString());
			alert.showAndWait();
			throw e;
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {}   
}
