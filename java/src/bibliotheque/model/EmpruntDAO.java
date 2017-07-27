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

public class EmpruntDAO {
	
	public static void addEmprunt(Emprunt emprunt) throws ClassNotFoundException {
	
		String req_user = "SELECT id FROM utilisateurs WHERE nom = '" +emprunt.getNomEmprunteur() +"';";
		String req_doc = "SELECT id FROM documents WHERE titre = '" +emprunt.getTitreDocument() +"';";
		int id_user = 0, id_doc = 0;
		
		try {
			ResultSet rs_user = DBUtil.dbExecuteQuery(req_user);
			while (rs_user.next()) {
				id_user = rs_user.getInt("id");
			}
				
			ResultSet rs_doc = DBUtil.dbExecuteQuery(req_doc);
			while (rs_doc.next()) {
				id_doc = rs_doc.getInt("id");
			}
				
			PreparedStatement requete = DBUtil.dbConnect().prepareStatement("INSERT INTO emprunts (id_emprunteur, id_document, dat_emprunt, dat_retour, rendu) VALUES (?, ?, ?, ?, ?)");
			requete.setInt(1, id_user);
			requete.setInt(2, id_doc);
			requete.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
			requete.setDate(4, Date.valueOf(emprunt.getDateRetour()));
			requete.setString(5, emprunt.getRendu());
			requete.execute();
			
			PreparedStatement update_etat_doc = DBUtil.dbConnect().prepareStatement("UPDATE documents SET etat = 'Emprunté' WHERE id = " +id_doc);
			update_etat_doc.executeUpdate();
			PreparedStatement update_etat_user= DBUtil.dbConnect().prepareStatement("UPDATE utilisateurs SET etat = 'Emprunteur' WHERE id = " +id_user);
			update_etat_user.executeUpdate();
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Opération réussie");
			alert.setHeaderText(null);
			alert.setContentText("L'emprunt a été effectué avec succès.");
			alert.showAndWait();
		
		} catch (SQLException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Echec de l'opération");
			alert.setHeaderText(ex.getLocalizedMessage());
			alert.setContentText("L'emprunt n'a pas pus être effectué. Veuillez réessyer.");
			alert.showAndWait();
		}
        }
	
	public static void reintegration(String user, String doc) throws ClassNotFoundException {
		
		String req_user = "UPDATE utilisateurs SET etat = 'Non Emprunteur' WHERE nom = '" +user +"';";
		String req_doc = "UPDATE documents SET etat = 'Disponible' WHERE titre = '" +doc +"';";
		String req_etat_emprunt = "UPDATE emprunts SET rendu = 'Oui' WHERE id_emprunteur = (SELECT id FROM utilisateurs WHERE nom = '" +user +"');";
		
		try {
			PreparedStatement requete_user = DBUtil.dbConnect().prepareStatement(req_user);
			requete_user.executeUpdate();
			PreparedStatement requete_doc = DBUtil.dbConnect().prepareStatement(req_doc);
			requete_doc.executeUpdate();
			PreparedStatement requete_etat_emprunt = DBUtil.dbConnect().prepareStatement(req_etat_emprunt);
			requete_etat_emprunt.executeUpdate();
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Opération réussie");
			alert.setHeaderText(null);
			alert.setContentText(doc +" a été réintégré avec succès à la bibliothèque");
			alert.showAndWait();
		
		} catch (SQLException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Echec de l'opération");
			alert.setHeaderText(ex.getLocalizedMessage());
			alert.setContentText(doc +" n'a pas été réintégré. Veuillez réessayer");
			alert.showAndWait();
		}
        }
	
	public ObservableList<Emprunt> getListEmprunts(String req) throws SQLException, ClassNotFoundException {
		
		ObservableList<Emprunt> list = FXCollections.observableArrayList();
		ResultSet rs = DBUtil.dbExecuteQuery(req);
		
		try {
			while (rs.next()) {
				Emprunt user = new Emprunt();
				String req_user = "SELECT nom FROM utilisateurs WHERE id = '" +rs.getInt("id_emprunteur") +"';";
				String req_doc = "SELECT titre FROM documents WHERE id = '" +rs.getInt("id_document") +"';";
				
				ResultSet rs_user = DBUtil.dbExecuteQuery(req_user);
				while (rs_user.next()) {
					user.setNomEmprunteur(rs_user.getString("nom"));
				}
				
				ResultSet rs_doc = DBUtil.dbExecuteQuery(req_doc);
				while (rs_doc.next()) {
					user.setTitreDocument(rs_doc.getString("titre"));
				}
				
				user.setDateEmprunt(rs.getDate("dat_emprunt").toLocalDate());
				user.setDateRetour(rs.getDate("dat_retour").toLocalDate());
				user.setRenduDocument(rs.getString("rendu"));
				list.add(user);
			}
		} catch(SQLException ex) {
			Logger.getLogger(CatalogueDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;
	}
}
