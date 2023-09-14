package com.mylibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import  com.mylibrary.db.DatabaseManager;
public class Statistics {

    public void displayStatistics() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseManager.Connection();

             String statisticsQuery = "SELECT " +
                    "(SELECT COUNT(*) FROM book) AS total_books, " +
                    "(SELECT COUNT(*) FROM emprunt) AS total_emprunts, " +
                    "(SELECT COUNT(*) FROM book WHERE etat = 'perdu') AS booksLosts, " +
                    "(SELECT COUNT(*) FROM book WHERE etat = 'disponible') AS booksAvailable " +
                    "FROM dual";

            preparedStatement = connection.prepareStatement(statisticsQuery);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int totalBooks = resultSet.getInt("total_books");
                int totalEmprunts = resultSet.getInt("total_emprunts");
                int bookLosts = resultSet.getInt("booksLosts");
                 int booksAvailable = resultSet.getInt("booksAvailable");


                System.out.println("Statistiques de la bibliothèque :");
                System.out.println("Nombre total de livres : " + totalBooks);
                System.out.println("Nombre total d'emprunts : " + totalEmprunts);
                System.out.println("Nombre des livres perdus : " + bookLosts);
                 System.out.println("Nombre des livres disponibles : " + booksAvailable);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
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
