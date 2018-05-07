/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedexMarin;

import pokedexMarin.Vivant.TYPE_EAU;

/**
 *
 * @author 1764812
 */
public class PlanteAquatique extends Specimen {

    private FLOTAISON flotaison;

    public enum FLOTAISON {
        FLOTTANTE,
        IMMERGEE;
    }

    public PlanteAquatique() {
    }

    public PlanteAquatique(String date, String nomTrouver, int quantiteTrouver, String nom, String couleur, int grandeur, TYPE_EAU typeEau, FLOTAISON flotaison) {
        super(date, nomTrouver, quantiteTrouver, nom, couleur, grandeur);
        this.flotaison = flotaison;
    }
}
