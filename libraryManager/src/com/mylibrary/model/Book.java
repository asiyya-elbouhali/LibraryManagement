package com.mylibrary.model;

public class Book {
    private int book_id;
    private String isbn;
    private String titre;
    private String auteur;
    private String etat;



     public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

     public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String  isbn) {
        this.isbn = isbn;
    }

     public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

     public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

     public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }



}
