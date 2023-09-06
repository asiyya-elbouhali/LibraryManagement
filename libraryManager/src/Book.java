import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

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

    public int searchBookIdByISBN(String isbnToSearch) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
        String username = "root";
        String password = "";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int bookId = -1; // Initialize to -1 or an appropriate error value

        try {
            // Establish the database connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // SQL query to retrieve book_id based on ISBN
            String query = "SELECT book_id FROM book WHERE isbn = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbnToSearch);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Check if a result was found
            if (resultSet.next()) {
                bookId = resultSet.getInt("book_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close resources
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

        return bookId;
    }

    public void deleteBookByISBN(String isbnToDelete) {
        int bookIdToDelete = searchBookIdByISBN(isbnToDelete);

        if (bookIdToDelete != -1) {
            String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
            String username = "root";
            String password = "";

            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

                // SQL query to delete a book by book_id
                String deleteQuery = "DELETE FROM book WHERE book_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setInt(1, bookIdToDelete);

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Book with ISBN " + isbnToDelete + " deleted from the database.");
                } else {
                    System.out.println("No book found with ISBN " + isbnToDelete + ".");
                }

                // Close resources
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No book found with ISBN " + isbnToDelete + ".");
        }
    }

    public Book searchBookByISBN(String isbnToSearch) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // SQL query to select a book by ISBN
            String selectQuery = "SELECT * FROM book WHERE isbn = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            // Try to parse the input as an integer
            int parsedIsbn = -1;
            try {
                parsedIsbn = Integer.parseInt(isbnToSearch);
                preparedStatement.setInt(1, parsedIsbn);
            } catch (NumberFormatException e) {
                // ISBN is not a valid integer, search by the string value
                preparedStatement.setString(1, isbnToSearch);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int isbn = resultSet.getInt("isbn");
                String titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                String etat = resultSet.getString("etat");
                int bookId = resultSet.getInt("book_id");

                // Create a new Book object with the retrieved information
                Book foundBook = new Book();
                foundBook.isbn = isbn;
                foundBook.titre = titre;
                foundBook.auteur = auteur;
                foundBook.etat = etat;
                foundBook.setBook_id(bookId);

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
            String selectQuery = "SELECT * FROM book WHERE etat = ?";
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
        System.out.println("Appuyez sur 00 pour emprunté  un livre.");
        System.out.println("Appuyez sur 1 pour ajouter un livre à la bibliothéque.");
        System.out.println("Appuyez sur 0 pour quitter l'application.");
        System.out.println("Appuyez sur 2 pour afficher tous les livres.");
        System.out.println("Appuyez sur 3 pour chercher un livre.");
        System.out.println("Appuyez sur 4 pour afficher les livres disponibles");
        System.out.println("Appuyez sur 5 pour enregistrer un emprunteur.");
        System.out.println("Appuyez sur 6 pour afficher la liste des emprunteurs.");
        System.out.println("Appuyez sur 7 pour afficher les livres empruntés. ");
        System.out.println("Appuyez sur 8 pour supprimer un livre. ");
        System.out.println("Appuyez sur 9 pour modifier un livre. ");
        System.out.println("Appuyez sur 10 pour retourner  un livre.");

        System.out.println(
                "-------------------------------------------------------------------------------------------------------");
    }


    // Borrow a book by ISBN
    public void borrowBookByISBN(String isbnToBorrow) {
        // Search for the book by ISBN
        Book foundBook = searchBookByISBN(isbnToBorrow);

        if (foundBook != null) {
            if (foundBook.getEtat().equals("disponible")) {
                // Set the new status to "emprunté"
                foundBook.setEtat("emprunté");

                // Update the book's status in the database
                updateBookStatusInDatabase(foundBook);

                Emprunt emprunt = new Emprunt();

                // Set the book_id in the Emprunt object
                emprunt.setBook_id(foundBook.getBook_id());

                // Set the date_emprunt and date_retour as needed
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date currentDate = new Date();
                String dateEmprunt = dateFormat.format(currentDate);
                // Calculate the return date (e.g., 2 weeks from today)
                Date returnDate = new Date(currentDate.getTime() + (14 * 24 * 60 * 60 * 1000)); // 14 days
                String dateRetour = dateFormat.format(returnDate);
                emprunt.setDate_emprunt(dateEmprunt);
                emprunt.setDate_retour(dateRetour);

                // Set the emprunteur_id based on the provided numero_member
                Scanner input = new Scanner(System.in);
                System.out.println("Entrez le numero_member de l'emprunteur :");
                String numeroMember = input.nextLine();
                // Call the lookup method to get the emprunteur_id
                int emprunteurId = lookupEmprunteurIdByNumeroMember(numeroMember);

                if (emprunteurId != -1) {
                    emprunt.setEmprunteur_id(emprunteurId);

                    // Get the database connection
                    String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
                    String username = "root";
                    String password = "";

                    Connection connection = null;
                    try {
                        // Establish the database connection
                        connection = DriverManager.getConnection(jdbcUrl, username, password);

                        // Insert the Emprunt object into the database
                        emprunt.addEmpruntToDatabase(connection);

                        System.out.println("Livre emprunté avec succès.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (connection != null) {
                                connection.close();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("Aucun emprunteur trouvé avec le numero_member spécifié.");
                }
            } else if (foundBook.getEtat().equals("emprunté")) {
                System.out.println("Le livre est déjà emprunté.");
            }
        } else {
            System.out.println("Aucun livre trouvé avec l'ISBN spécifié.");
        }
    }

    // Add this method to update the book's status in the database
    private void updateBookStatusInDatabase(Book book) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
        String username = "root";
        String password = "";

        Connection connection = null;
        try {
            // Establish the database connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Update the book's status
            String updateQuery = "UPDATE book SET etat = ? WHERE book_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, book.getEtat());
            preparedStatement.setInt(2, book.getBook_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void returnBookByISBN(String isbnToReturn) {
        // Search for the book by ISBN
        Book foundBook = searchBookByISBN(isbnToReturn);

        if (foundBook != null) {
            if (foundBook.getEtat().equals("emprunté")) {
                // Set the new status to "disponible"
                foundBook.setEtat("disponible");

                // Update the book's status in the database
                updateBookStatusInDatabase(foundBook);

                // Get the current date for return date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date currentDate = new Date();
                String dateRetour = dateFormat.format(currentDate);

                // Set the return date in the Emprunt object
                Emprunt emprunt = new Emprunt();
                emprunt.setBook_id(foundBook.getBook_id());
                emprunt.setDate_retour(dateRetour);

                // Set the emprunteur_id based on the provided numero_member
                Scanner input = new Scanner(System.in);
                System.out.println("Entrez le numero_member de l'emprunteur :");
                String numeroMember = input.nextLine();
                // Call the lookup method to get the emprunteur_id
                int emprunteurId = lookupEmprunteurIdByNumeroMember(numeroMember);

                if (emprunteurId != -1) {
                    emprunt.setEmprunteur_id(emprunteurId);

                    // Get the database connection
                    String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
                    String username = "root";
                    String password = "";

                    Connection connection = null;
                    try {
                        // Establish the database connection
                        connection = DriverManager.getConnection(jdbcUrl, username, password);

                        // Update the return date in the Emprunt object in the database
                        updateEmpruntReturnDateInDatabase(emprunt, connection);

                        System.out.println("Livre retourné avec succès.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (connection != null) {
                                connection.close();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("Aucun emprunteur trouvé avec le numero_member spécifié.");
                }
            } else {
                System.out.println("Le livre n'est pas emprunté.");
            }
        } else {
            System.out.println("Aucun livre trouvé avec l'ISBN spécifié.");
        }
    }

    // Add this method to update the return date in the Emprunt object in the database
    private void updateEmpruntReturnDateInDatabase(Emprunt emprunt, Connection connection) {
        try {
            // Update the return date in the Emprunt object
            String updateQuery = "UPDATE emprunt SET date_retour = ? WHERE book_id = ? AND emprunteur_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, emprunt.getDate_retour());
            preparedStatement.setInt(2, emprunt.getBook_id());
            preparedStatement.setInt(3, emprunt.getEmprunteur_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add this method in your class or a dedicated database utility class
    public int lookupEmprunteurIdByNumeroMember(String numeroMember) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
        String username = "root";
        String password = "";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int emprunteurId = -1; // Initialize to -1 or an appropriate error value

        try {
            // Establish the database connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // SQL query to retrieve emprunteur_id based on numero_member
            String query = "SELECT emprunteur_id FROM emprunteur WHERE numero_membre = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, numeroMember);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Check if a result was found
            if (resultSet.next()) {
                emprunteurId = resultSet.getInt("emprunteur_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close resources
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

        return emprunteurId;
    }

    public void updateBookInformationByISBN(String isbnToUpdate) {
        // Search for the book by ISBN
        Book foundBook = searchBookByISBN(isbnToUpdate);

        if (foundBook != null) {
            // Display current book details
            System.out.println("Livre trouvé :");
            System.out.println("ISBN : " + foundBook.getIsbn());
            System.out.println("Titre actuel : " + foundBook.getTitre());
            System.out.println("Auteur actuel : " + foundBook.getAuteur());

            // Prompt for updates
            Scanner input = new Scanner(System.in);

            System.out.println("Voulez-vous mettre à jour le titre ? (Oui/Non) :");
            String updateTitleChoice = input.next().toLowerCase();
            input.nextLine(); // Consume the newline character

            String newTitle = foundBook.getTitre();

            if (updateTitleChoice.equals("oui")) {
                System.out.println("Entrez le nouveau titre :");
                newTitle = input.nextLine();
            }

            System.out.println("Voulez-vous mettre à jour l'auteur ? (Oui/Non) :");
            String updateAuthorChoice = input.next().toLowerCase();
            input.nextLine(); // Consume the newline character

            String newAuthor = foundBook.getAuteur();

            if (updateAuthorChoice.equals("oui")) {
                System.out.println("Entrez le nouvel auteur :");
                newAuthor = input.nextLine();
            }

            // Update the book in the database
            String jdbcUrl = "jdbc:mysql://localhost:3306/librarymanager";
            String username = "root";
            String password = "";

            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                String updateQuery = "UPDATE book SET titre = ?, auteur = ? WHERE isbn = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, newTitle);
                preparedStatement.setString(2, newAuthor);
                preparedStatement.setString(3, isbnToUpdate);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Informations du livre mises à jour avec succès.");
                } else {
                    System.out.println("Aucun livre trouvé avec l'ISBN spécifié.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun livre trouvé avec l'ISBN spécifié.");
        }
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
