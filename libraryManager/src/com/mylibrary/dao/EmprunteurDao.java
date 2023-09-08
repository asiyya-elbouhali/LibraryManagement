package com.mylibrary.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;

public interface EmprunteurDao {

    void enregistrerEmprunteur();

    void allEmprunteurs();

}
