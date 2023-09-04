import java.sql.*;
import java.util.Scanner;
public class Book {

    private int book_id;
    private int isbn;
    private String titre;
    private String auteur;
    private String etat;
    public static int count;
    Book theBooks[] = new Book[50];
    private Scanner input = new Scanner(System.in);
    public  static void  main(String args[])
    {
        Connection con;
        PreparedStatement pst;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanager", "root", "");
            System.out.println("Success");
        }catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }catch (SQLException ex) {
        ex.printStackTrace();
        }

    }

    public void addBook()
    {

        System.out.println("Entrez l'ISBN du livre:");
        this.isbn = input.nextInt();
        input.nextLine();

        System.out.println("Entrez le titre du livre:");
        this.titre = input.nextLine();

        System.out.println("Entez l'auteur du livre:");
        this.auteur = input.nextLine();

        this.etat = "disponible";

        String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // SQL query to insert Emprunteur into the table
            String insertQuery = "INSERT INTO book (isbn, titre, auteur, etat) VALUES (?, ?, ?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setInt(1, this.isbn);
            preparedStatement.setString(2, this.titre);
            preparedStatement.setString(3, this.auteur);
            preparedStatement.setString(4, this.etat);


            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Book added to the database successfully.");
            }

            // Close resources
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public Book searchBookByISBN(int isbnToSearch) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // SQL query to select a book by ISBN
            String selectQuery = "SELECT * FROM book WHERE isbn = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, isbnToSearch);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int isbn = resultSet.getInt("isbn");
                String titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                String etat = resultSet.getString("etat");

                // Create a new Book object with the retrieved information
                Book foundBook = new Book();
                foundBook.isbn = isbn;
                foundBook.titre = titre;
                foundBook.auteur = auteur;
                foundBook.etat = etat;

                // Close resources
                resultSet.close();
                preparedStatement.close();
                connection.close();

                return foundBook;
            } else {
                System.out.println("Book with ISBN " + isbnToSearch + " not found.");
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Book searchBookByTitle(String titleToSearch) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

             String selectQuery = "SELECT * FROM book WHERE titre = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, titleToSearch);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int isbn = resultSet.getInt("isbn");
                String titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                String etat = resultSet.getString("etat");

                // Create a new Book object with the retrieved information
                Book foundBook = new Book();
                foundBook.isbn = isbn;
                foundBook.titre = titre;
                foundBook.auteur = auteur;
                foundBook.etat = etat;

                // Close resources
                resultSet.close();
                preparedStatement.close();
                connection.close();

                return foundBook;
            } else {
                System.out.println("Le livre nommé " + titleToSearch + " n'existe pas!");
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Book searchBookByAuthor(String authorToSearch) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // SQL query to select a book by ISBN
            String selectQuery = "SELECT * FROM book WHERE auteur = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, authorToSearch);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int isbn = resultSet.getInt("isbn");
                String titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                String etat = resultSet.getString("etat");

                // Create a new Book object with the retrieved information
                Book foundBook = new Book();
                foundBook.isbn = isbn;
                foundBook.titre = titre;
                foundBook.auteur = auteur;
                foundBook.etat = etat;

                // Close resources
                resultSet.close();
                preparedStatement.close();
                connection.close();

                return foundBook;
            } else {
                System.out.println("Le livre de l'auteur " + authorToSearch + " n'existe pas.");
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Book searchBookByStatus(String statusToSearch) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // SQL query to select a book by ISBN
            String selectQuery = "SELECT * FROM book WHERE auteur = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, statusToSearch);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int isbn = resultSet.getInt("isbn");
                String titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                String etat = resultSet.getString("etat");

                // Create a new Book object with the retrieved information
                Book foundBook = new Book();
                foundBook.isbn = isbn;
                foundBook.titre = titre;
                foundBook.auteur = auteur;
                foundBook.etat = etat;

                // Close resources
                resultSet.close();
                preparedStatement.close();
                connection.close();

                return foundBook;
            } else {
                System.out.println("Il n'y a aucun livre " + statusToSearch );
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void showAllBooks() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // SQL query to select all books from the 'book' table
            String selectQuery = "SELECT * FROM book";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            System.out.println("\t\t\t\tSHOWING ALL BOOKS\n");
            System.out.println("ISBN\t\tTitre\t\tAuteur\t\tEtat");

            while (resultSet.next()) {
                int isbn = resultSet.getInt("isbn");
                String titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                String etat = resultSet.getString("etat");

                System.out.println(
                        isbn + "\t\t" + titre + "\t\t" + auteur + "\t\t" + etat);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // To create menu
    public void Menu()
    {

        // Displaying menu
        System.out.println(
                "----------------------------------------------------------------------------------------------------------");
        System.out.println("Appuyez sur 1 pour ajouter un livre.");
        System.out.println("Appuyez sur 0 pour quitter l'application.");
        System.out.println("Appuyez sur 2 pour afficher tous les livres.");
        System.out.println("Appuyez sur 3 pour chercher un livre.");
        System.out.println("Appuyez sur 4 pour afficher les livres disponibles");
        System.out.println("Appuyez sur 5 pour enregistrer un emprunteur.");
        System.out.println("Appuyez sur 6 pour afficher la liste des emprunteurs.");
        System.out.println("Appuyez sur 7 pour afficher les livres empruntés. ");
        System.out.println(
                "-------------------------------------------------------------------------------------------------------");
    }

    // Getter and Setter for book_id
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    // Getter and Setter for isbn
    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    // Getter and Setter for titre
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    // Getter and Setter for auteur
    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    // Getter and Setter for etat
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
