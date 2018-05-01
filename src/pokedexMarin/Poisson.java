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
public class Poisson extends Pokemon {

    /**
     *
     * @param date
     * @param nomTrouver
     * @param quantiteTrouver
     */
    private SEXE sexe;

    public Poisson() {
    }

    public Poisson(String date, String nomTrouver, int quantiteTrouver, String nom,String couleur,int grandeur, SEXE sexe, TYPE_EAU typeEau) {
        super(date, nomTrouver, quantiteTrouver, nom, couleur, grandeur);
        this.sexe = sexe;
        this.typeEau = typeEau;
    }

    enum SEXE {
        MALE,
        FEMELLE;
    }

    public enum TYPE_EAU {
        EAU_DOUCE,
        EAU_SALEE;
    }
    private TYPE_EAU typeEau;

}
