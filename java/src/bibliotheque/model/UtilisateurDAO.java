package bibliotheque.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


public class UtilisateurDAO {
	
	public static void addUser(Utilisateur user) throws ClassNotFoundException, SQLException {
	
		try {
			PreparedStatement requete = DBUtil.dbConnect().prepareStatement("INSERT INTO utilisateurs (nom, prenoms, borndat, sex, address, profession, telephone, etat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			requete.setString(1, user.getNom());
			requete.setString(2, user.getPrenom());
			requete.setDate(3, Date.valueOf(user.getDateNaissance()));
			requete.setString(4, user.getSexe());
			requete.setString(5, user.getAddress());
			requete.setString(6, user.getProfession());
			requete.setString(7, user.getTelephone());
			requete.setString(8, "Non Emprunteur");
			requete.execute();
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Opération réussie");
			alert.setHeaderText(null);
			alert.setContentText(user.getNom() +" " +user.getPrenom() +" a été enrégistré avec succès.");
			alert.showAndWait();
		
		} catch (SQLException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Echec de l'opération");
			alert.setHeaderText(null);
			alert.setContentText(user.getNom() +" " +user.getPrenom() +" n'a pas été enrégistré. Veuillez réessyer.");
			alert.showAndWait();
		}
        }
	
	public static void deleteUser(String user) throws ClassNotFoundException {
		
		String req = "DELETE FROM utilisateurs WHERE nom = '" +user +"'";
		try {
			PreparedStatement requete = DBUtil.dbConnect().prepareStatement(req);
			requete.executeUpdate();
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Opération réussie");
			alert.setHeaderText(null);
			alert.setContentText(user +" a été supprimé avec succès de la bibliothèque");
			alert.showAndWait();
		
		} catch (SQLException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Echec de l'opération");
			alert.setHeaderText(ex.getLocalizedMessage());
			alert.setContentText(user +" n'a pas été supprimé. Veuillez réessayer");
			alert.showAndWait();
		}
        }
	
	public ObservableList<Utilisateur> getListUsers(String req) throws SQLException, ClassNotFoundException {
		ObservableList<Utilisateur> list = FXCollections.observableArrayList();
		ResultSet rs;
		rs = DBUtil.dbExecuteQuery(req);
		
		try {
			while (rs.next()) {
				Utilisateur user = new Utilisateur();
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenoms"));
				user.setDateNaissance(rs.getDate("borndat").toLocalDate());
				user.setSexe(rs.getString("sex"));
				user.setProfession(rs.getString("profession"));
				user.setAddress(rs.getString("address"));
				user.setTelephone(rs.getString("telephone"));
				list.add(user);
			}
		} catch(SQLException ex) {
			Logger.getLogger(CatalogueDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;
	}
}
