import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Emprunt {
    private int emprunt_id;
    private int emprunteur_id;
    private int book_id;
    private String date_emprunt;
    private String date_retour;



    // Add Emprunt to the database
    public void addEmpruntToDatabase(Connection connection) throws SQLException {
        PreparedStatement insertStatement = null;

        try {
             // Use the provided database connection to insert the Emprunt object into the "emprunt" table
            String insertQuery = "INSERT INTO emprunt (emprunteur_id, book_id, date_emprunt, date_retour) VALUES (?, ?, ?, ?)";
            insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, this.emprunteur_id);
            insertStatement.setInt(2, this.book_id);
            insertStatement.setString(3, this.date_emprunt);
            insertStatement.setString(4, this.date_retour);
            insertStatement.executeUpdate();
        } finally {
            // Close resources in a finally block to ensure they are always closed
            if (insertStatement != null) {
                insertStatement.close();
            }
        }
    }

    // Getter for emprunt_id
    public int getEmprunt_id() {
        return emprunt_id;
    }

    // Setter for emprunt_id
    public void setEmprunt_id(int emprunt_id) {
        this.emprunt_id = emprunt_id;
    }

    // Getter for emprunteur_id
    public int getEmprunteur_id() {
        return emprunteur_id;
    }

    // Setter for emprunteur_id
    public void setEmprunteur_id(int emprunteur_id) {
        this.emprunteur_id = emprunteur_id;
    }

    // Getter for book_id
    public int getBook_id() {
        return book_id;
    }

    // Setter for book_id
    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    // Getter for date_emprunt
    public String getDate_emprunt() {
        return date_emprunt;
    }

    // Setter for date_emprunt
    public void setDate_emprunt(String date_emprunt) {
        this.date_emprunt = date_emprunt;
    }

    // Getter for date_retour
    public String getDate_retour() {
        return date_retour;
    }

    // Setter for date_retour
    public void setDate_retour(String date_retour) {
        this.date_retour = date_retour;
    }

}
