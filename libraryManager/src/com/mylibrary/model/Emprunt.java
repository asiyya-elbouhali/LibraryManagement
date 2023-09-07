package com.mylibrary.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Emprunt {
    private int emprunt_id;
    private int emprunteur_id;
    private int book_id;
    private String date_emprunt;
    private String date_retour;


    public Emprunt() {
        //le constructeur
     }

    public Emprunt(int emprunteur_id, int book_id, String date_emprunt, String date_retour) {
        this.emprunteur_id = emprunteur_id;
        this.book_id = book_id;
        this.date_emprunt = date_emprunt;
        this.date_retour = date_retour;
    }

    public int getEmprunt_id() {
        return emprunt_id;
    }

    public void setEmprunt_id(int emprunt_id) {
        this.emprunt_id = emprunt_id;
    }

    public int getEmprunteur_id() {
        return emprunteur_id;
    }

    public void setEmprunteur_id(int emprunteur_id) {
        this.emprunteur_id = emprunteur_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getDate_emprunt() {
        return date_emprunt;
    }

    public void setDate_emprunt(String date_emprunt) {
        this.date_emprunt = date_emprunt;
    }

    public String getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(String date_retour) {
        this.date_retour = date_retour;
    }

    public void addEmpruntToDatabase(Connection connection) throws SQLException {
        PreparedStatement insertStatement = null;

        try {
            String insertQuery = "INSERT INTO emprunt (emprunteur_id, book_id, date_emprunt, date_retour) VALUES (?, ?, ?, ?)";
            insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, this.emprunteur_id);
            insertStatement.setInt(2, this.book_id);
            insertStatement.setString(3, this.date_emprunt);
            insertStatement.setString(4, this.date_retour);
            insertStatement.executeUpdate();
        } finally {
            if (insertStatement != null) {
                insertStatement.close();
            }
        }
    }

}
