// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println(
                "********************Bienvenue à la bibliothèque de Paris !********************");
        System.out.println(
                "                      Veuillez selectionner votre choix:                      ");
        System.out.println(
                "******************************************************************************");
        int choice;
        int searchChoice;
        Book book = new Book();
        Emprunteur emprunteur = new Emprunteur();

        do {

            book.Menu();
            choice = input.nextInt();

            switch (choice) {

                 case 1:
                    book.addBook();
                    break;

                 case 2:
                    book.showAllBooks();
                    break;

                 case 3:
                    //Search
                     System.out.println(
                             " Appuyez sur  1 pour chercher un livre par ISBN.");
                     System.out.println(
                             " Appuyez sur 2 pour chercher un livre par Auteur.");
                     System.out.println(
                             " Appuyez sur 3 pour chercher un livre par Titre.");
                     searchChoice = input.nextInt();
                     switch (searchChoice){
                         case 1:
                             System.out.println("Entrez l'ISBN du livre à rechercher :");
                             int isbnToSearch = input.nextInt();
                             Book foundBook = book.searchBookByISBN(isbnToSearch);
                             if (foundBook != null) {
                                 System.out.println("Livre trouvé :");
                                 System.out.println("ISBN : " + foundBook.getIsbn());
                                 System.out.println("Titre : " + foundBook.getTitre());
                                 System.out.println("Auteur : " + foundBook.getAuteur());
                                 System.out.println("État : " + foundBook.getEtat());
                             } else {
                                 System.out.println("Livre non trouvé.");
                             }
                             break;

                         // Case
                         case 2:
                             // Consume the newline character left in the buffer
                             input.nextLine();

                             // Prompt the user to enter the author's name
                             System.out.println("Entrez le nom de l'auteur à rechercher :");
                             String authorToSearch = input.nextLine(); // Read the author's name
                             Book foundBookByAuthor = book.searchBookByAuthor(authorToSearch);
                             if (foundBookByAuthor != null) {
                                 System.out.println("Livre trouvé :");
                                 System.out.println("ISBN : " + foundBookByAuthor.getIsbn());
                                 System.out.println("Titre : " + foundBookByAuthor.getTitre());
                                 System.out.println("Auteur : " + foundBookByAuthor.getAuteur());
                                 System.out.println("État : " + foundBookByAuthor.getEtat());
                             } else {
                                 System.out.println("Livre non trouvé.");
                             }
                             break;
                             // Case
                         case 3:
                             input.nextLine();

                             // Prompt the user to enter the book title
                             System.out.println("Entrez le titre du livre à rechercher :");
                             String titleToSearch = input.nextLine(); // Read the book title
                             Book foundBookByTitle = book.searchBookByTitle(titleToSearch);
                             if (foundBookByTitle != null){

                                 System.out.println("Livre trouvé :");
                                 System.out.println("ISBN : " + foundBookByTitle.getIsbn());
                                 System.out.println("Titre : " + foundBookByTitle.getTitre());
                                 System.out.println("Auteur : " + foundBookByTitle.getAuteur());
                                 System.out.println("État : " + foundBookByTitle.getEtat());
                             } else {
                                 System.out.println("Livre non trouvé.");
                             }
                             break;
                     }
                     break;

                 case 4:
                    //book.showAllAvailableBooks();
                    break;

                 case 5:
                    emprunteur.signup();
                    break;

                 case 6:
                    //emprunteur.allEmprunteurs();
                    break;

                 case 7:
                     //book.showAllBorrowedBooks();

                default:

                    // Print statement
                    System.out.println("Choisissez entre 0 et 7.");
            }

        }
        while (choice != 0);
    }


}

