package bibliotheque;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Authentification");
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/LoginView.fxml"));
			AnchorPane login = (AnchorPane) loader.load();
			Scene scene = new Scene(login);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(IOException e) {
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
