import java.util.Scanner;
import java.sql.*;


public class Emprunteur {
    private int emprunteur_id;
    private String numero_membre;
    private String motdepasse;

    public void signup(){
        Scanner input = new Scanner(System.in);
        System.out.println("Entez votre numero_membre:");
        this.numero_membre = input.nextLine();

        System.out.println("Entez votre motdepasse:");
        this.motdepasse = input.nextLine();

        System.out.println("Signup successful!");

         input.close();

        String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

             String insertQuery = "INSERT INTO emprunteur (emprunteur_id, numero_membre, motdepasse) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setInt(1, this.emprunteur_id);
            preparedStatement.setString(2, this.numero_membre);
            preparedStatement.setString(3, this.motdepasse);

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


    // Getters and setters for emprunteur_id, numero_membre, and motdepasse
    public int getEmprunteur_id() {
        return emprunteur_id;
    }

    public void setEmprunteur_id(int emprunteur_id) {
        this.emprunteur_id = emprunteur_id;
    }

    public String getNumero_membre() {
        return numero_membre;
    }

    public void setNumero_membre(String numero_membre) {
        this.numero_membre = numero_membre;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }



    }


