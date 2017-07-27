package bibliotheque.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class BibliothequeController implements Initializable {

	public static AnchorPane containerP;
	
	@FXML
	private JFXDrawer drawer;
	@FXML
	private JFXHamburger hamburger;
	@FXML
	private AnchorPane container;
    
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		containerP = container;

		try {
			VBox box = FXMLLoader.load(getClass().getResource("/bibliotheque/view/SidePanelView.fxml"));
			drawer.setSidePane(box);
			drawer.open();
		} catch (IOException ex) {
			Logger.getLogger(BibliothequeController.class.getName()).log(Level.SEVERE, null, ex);
		}

		HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
		transition.setRate(1);
		
		hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
			transition.setRate(transition.getRate()*-1);
			transition.play();

			if(drawer.isShown())
				drawer.close();
			else
				drawer.open();
		});
       }
}
