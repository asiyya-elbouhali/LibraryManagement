package com.mylibrary.dao;

import com.mylibrary.db.DatabaseManager;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.Scanner;


public class EmprunteurImp implements EmprunteurDao {
    private int emprunteur_id;
    private String numero_membre;
    private String nom;

    @Override
    public void enregistrerEmprunteur() {
        Scanner input = new Scanner(System.in);
        System.out.println("Entez votre numero_membre:");
        this.numero_membre = input.nextLine();

        System.out.println("Entez votre nom:");
        this.nom = input.nextLine();

        System.out.println("Signup successful!");

        input.close();

        try {
            Connection connection = DatabaseManager.Connection();

            String insertQuery = "INSERT INTO emprunteur (numero_membre, nom) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, this.numero_membre);
            preparedStatement.setString(2, this.nom);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Emprunteur added to the database successfully.");
            }

            // Close resources
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  void allEmprunteurs(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            connection = DatabaseManager.Connection();
            String selectQuery="SELECT * FROM emprunteur";
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery(selectQuery);

            System.out.println("\t\t\t\tLISTE DES EMPRUNTEURS\n");
            System.out.println("Nom\t\tNuméro de member\t\t");

            while (resultSet.next()) {
                String numero_membre = resultSet.getString("numero_membre");
                String nom = resultSet.getString("nom");

                System.out.println(
                        nom + "\t\t" +numero_membre+"\t\t");
            }statement.close();
            resultSet.close();
            connection.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

 }
