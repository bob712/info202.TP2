/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedexMarin;

/**
 *
 * @author 1764812
 */
public class Pokemon {

    private static int noCreation = 1;
    private String date;//changer le type de la variable?
    private String codeAccesTrouver;//nom de l'utilisateur qui l'a entre
    private int quantiteTrouver;
    private int numero;
    private String nom;
    private String couleur;
    private int grandeur;//grandeur en cm

    public Pokemon() {
    }

    public Pokemon(String date, String codeAccesTrouver, int quantiteTrouver, String nom, String couleur, int grandeur) {
        this.date = date;
        this.codeAccesTrouver = codeAccesTrouver;
        this.quantiteTrouver = quantiteTrouver;
        this.numero = Pokemon.noCreation++;
        this.nom = nom;
        this.couleur = couleur;
        this.grandeur = grandeur;
    }

    @Override
    public String toString() {
        return nom;
    }

    public void setQuantiteTrouver(int quantiteTrouver) {
        this.quantiteTrouver = quantiteTrouver;
    }

    public int getQuantiteTrouver() {
        return quantiteTrouver;
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        return date;
    }

    public String getCodeAccesTrouver() {
        return codeAccesTrouver;
    }

}
