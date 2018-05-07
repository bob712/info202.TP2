/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedexMarin;

/**
 *
 * @author vbrod
 */
public class Animal extends Vivant {

    private SEXE sexe;

    public enum SEXE {
        MALE,
        FEMELLE;
    }
    public Animal() {
    }
    public Animal(String date, String codeAccesTrouver, int quantiteTrouver, String nom, String couleur, int grandeur, SEXE sexe, TYPE_EAU typeEau) {
        super(date, codeAccesTrouver, quantiteTrouver, nom, couleur, grandeur, typeEau);
        this.sexe = sexe;
    }

    

}
