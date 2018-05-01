/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedexMarin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1764812
 */
public class GestionPokedex {

    private Scanner clavier = new Scanner(System.in);
    private ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
    private Personne utilisateur;

    private Encryption encrypter;

    public GestionPokedex() {
        identification();
    }

    private void identification() {
        try {
            DataInputStream lire = new DataInputStream(new FileInputStream("utilisateurs.bin"));
            System.out.println("Entrez votre code d'acces");
            //search pour utilisateur
        } catch (FileNotFoundException fnfe) {
            try {
                DataOutputStream fichierSortie = new DataOutputStream(new FileOutputStream("utilisateurs.bin", true));
                nouvelUtilisateur("allo");//mettre le bon code dacces
            } catch (IOException ioe) {

            }
        }
        
    }

    private void nouvelUtilisateur(String codeAcces) {
        try {
            encrypter = new Encryption();
        } catch (Exception ex) {
            Logger.getLogger(GestionPokedex.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean entreeCorrecte;
        System.out.println("Entrez votre nom.");
        utilisateur.setNom(clavier.nextLine());
        utilisateur.setCodeAcces(codeAcces);
        System.out.println("Entrez votre mot de passe.");//verification mot de passe
        utilisateur.setMotDePasse(encrypter.encrypt(clavier.nextLine()));
        System.out.println("Entrez votre âge.");
        do {
            try {
                utilisateur.setAge(Integer.parseInt(clavier.nextLine()));
                entreeCorrecte = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Entrez votre âge en nombre.");
                entreeCorrecte = false;
            }
        } while (utilisateur.getAge() == 0);
        //save utilisateur dans fichier utilisateurS
    }

}
