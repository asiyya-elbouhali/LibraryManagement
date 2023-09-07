package com.mylibrary.model;


public class Emprunteur {
    private int emprunteur_id;
    private String numero_membre;
    private String motdepasse;


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
