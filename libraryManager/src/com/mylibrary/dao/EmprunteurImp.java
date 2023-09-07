package com.mylibrary.dao;

import com.mylibrary.db.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class EmprunteurImp implements EmprunteurDao {
    private int emprunteur_id;
    private String numero_membre;
    private String motdepasse;

    @Override
    public void signup() {
        Scanner input = new Scanner(System.in);
        System.out.println("Entez votre numero_membre:");
        this.numero_membre = input.nextLine();

        System.out.println("Entez votre motdepasse:");
        this.motdepasse = input.nextLine();

        System.out.println("Signup successful!");

        input.close();

        try {
            Connection connection = DatabaseManager.Connection();

            String insertQuery = "INSERT INTO emprunteur (numero_membre, motdepasse) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, this.numero_membre);
            preparedStatement.setString(2, this.motdepasse);

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



 }
