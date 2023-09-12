package com.mylibrary;
import com.mylibrary.model.Book;
import com.mylibrary.dao.BookImp;
import com.mylibrary.dao.EmprunteurImp;
import com.mylibrary.dao.Statistics;

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
        BookImp bookImp = new BookImp(book);
        Statistics statistics= new Statistics();
        bookImp.updateBookStatusToLostIfReturnDateExceeded();
        EmprunteurImp emprunteur = new EmprunteurImp();

        do {

            bookImp.Menu();
            choice = input.nextInt();

            switch (choice) {
                case 00:
                    System.out.println("Entrez l'ISBN du livre à emprunter :");
                    String isbnToBorrow = input.next();
                    input.nextLine();
                    bookImp.borrowBookByISBN(isbnToBorrow);
                    break;

                case 1:
                    bookImp.addBook();
                    break;

                case 2:
                    bookImp.showAllBooks();
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
                            String isbnToSearch = input.next();
                            Book foundBook = bookImp.searchBookByISBN(isbnToSearch);
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
                             input.nextLine();

                             System.out.println("Entrez le nom de l'auteur à rechercher :");
                            String authorToSearch = input.nextLine(); // Read the author's name
                            Book foundBookByAuthor = bookImp.searchBookByAuthor(authorToSearch);
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

                             System.out.println("Entrez le titre du livre à rechercher :");
                            String titleToSearch = input.nextLine(); // Read the book title
                            Book foundBookByTitle = bookImp.searchBookByTitle(titleToSearch);
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
                    bookImp.showAllAvailableBooks();
                    break;
                case 5:
                    emprunteur.enregistrerEmprunteur();
                    break;

                case 6:
                    emprunteur.allEmprunteurs();
                    break;

                case 7:
                    bookImp.showAllBorrowedBooks();
                    break;
                case 8:
                    System.out.println("Entrez l'ISBN du livre à supprimer :");
                    String isbnToDelete = input.next();

                     bookImp.deleteBookByISBN(isbnToDelete);
                     break;
                case 9:
                    System.out.println("Entrez l'ISBN du livre à modifier :");
                    String isbnToUpdate = input.next();
                     bookImp.updateBookInformationByISBN(isbnToUpdate);
                    break;
                case 10:
                    System.out.println("Entrez l'ISBN du livre à retourner :");
                    String isbnToReturn = input.next();
                    bookImp.returnBookByISBN(isbnToReturn);
                    break;
                case 11:
                    statistics.displayStatistics();
                    break;
                default:
                     System.out.println("Choisissez entre 0 et 11.");
            }
        }
        while (choice != 0);

    }
}