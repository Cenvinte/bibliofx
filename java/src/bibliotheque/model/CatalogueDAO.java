package bibliotheque.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class CatalogueDAO {
	
	public static void addDocument(Catalogue livre) throws ClassNotFoundException, SQLException {
		
		String req = "INSERT INTO documents (titre, auteur, edition, type, etat) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement requete = DBUtil.dbConnect().prepareStatement(req);
			requete.setString(1, livre.getTitre());
			requete.setString(2, livre.getAuteur());
			requete.setString(3, livre.getEdition());
			requete.setString(4, livre.getType());
			requete.setString(5, livre.getEtat());
			requete.execute();
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Opération réussie");
			alert.setHeaderText(null);
			alert.setContentText(livre.getTitre() +" a été ajouté avec succès à la bibliothèque");
			alert.showAndWait();
		} catch (SQLException ex) {
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Echec de l'opération");
			alert.setHeaderText(ex.getLocalizedMessage());
			alert.setContentText(livre.getTitre() +" n'a pas été enregistré. Veuillez réessayer");
			alert.showAndWait();
		}
        }
	
	public static void deleteDocument(String titre) throws ClassNotFoundException {
		
		String req = "DELETE FROM documents WHERE titre = '" +titre +"'";
		try {
			PreparedStatement requete = DBUtil.dbConnect().prepareStatement(req);
			requete.executeUpdate();
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Opération réussie");
			alert.setHeaderText(null);
			alert.setContentText(titre +" a été supprimé avec succès de la bibliothèque");
			alert.showAndWait();
		
		} catch (SQLException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Echec de l'opération");
			alert.setHeaderText(ex.getLocalizedMessage());
			alert.setContentText(titre +" n'a pas été supprimé. Veuillez réessayer");
			alert.showAndWait();
		}
        }
	
	public ObservableList<Catalogue> getListDocuments(String req) throws SQLException, ClassNotFoundException {
		ObservableList<Catalogue> list = FXCollections.observableArrayList();
		ResultSet rs = DBUtil.dbExecuteQuery(req);
		try {
			while (rs.next()) {
				Catalogue doc = new Catalogue();
				doc.setTitre(rs.getString("titre"));
				doc.setAuteur(rs.getString("auteur"));
				doc.setEdition(rs.getString("edition"));
				doc.setType(rs.getString("type"));
				doc.setEtat(rs.getString("etat"));
				list.add(doc);
			}
			rs.close();
		} catch(SQLException ex) {
			Logger.getLogger(CatalogueDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;
	}
}
