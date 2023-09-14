package com.mylibrary.model;


public class Emprunteur {
    private int emprunteur_id;
    private String numero_membre;
    private String nom;


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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
