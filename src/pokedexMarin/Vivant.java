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
public class Vivant extends Specimen {

    private TYPE_EAU typeEau;

    public enum TYPE_EAU {
        EAU_DOUCE,
        EAU_SALEE;
    }

    public Vivant() {
    }

    public Vivant(String date, String codeAccesTrouver, int quantiteTrouver, String nom, String couleur, int grandeur, TYPE_EAU typeEau) {
        super(date, codeAccesTrouver, quantiteTrouver, nom, couleur, grandeur);
        this.typeEau = typeEau;
    }

}
