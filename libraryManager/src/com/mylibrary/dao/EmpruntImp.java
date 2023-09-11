package com.mylibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mylibrary.model.Emprunt; // Import the Emprunt class
import com.mylibrary.db.DatabaseManager;

public class EmpruntImp implements EmpruntDao {
    private Emprunt emprunt;

    public EmpruntImp(Emprunt emprunt) {
        this.emprunt = emprunt;
    }

    @Override
    public void addEmpruntToDatabase() {
        Connection connection = null;
        PreparedStatement insertStatement = null;

        try {
            connection = DatabaseManager.Connection();
            String insertQuery = "INSERT INTO emprunt (emprunteur_id, book_id, date_emprunt, date_retour) VALUES (?, ?, ?, ?)";
            insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, emprunt.getEmprunteur_id());
            insertStatement.setInt(2, emprunt.getBook_id());
            insertStatement.setString(3, emprunt.getDate_emprunt());
            insertStatement.setString(4, emprunt.getDate_retour());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (insertStatement != null) {
                    insertStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteEmpruntToDatabase(int emprunt_id){

        Connection connection = null;
        PreparedStatement insertStatement = null;

        try {
            connection = DatabaseManager.Connection();
            String deleteQuery = "DELETE FROM emprunt WHERE emprunt_id=?";
            insertStatement = connection.prepareStatement(deleteQuery);
            insertStatement.setInt(1,emprunt_id);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (insertStatement != null) {
                    insertStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
