/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedexMarin;

import pokedexMarin.Poisson.TYPE_EAU;

/**
 *
 * @author 1764812
 */
public class PlanteAquatique extends Pokemon {

    private FLOTAISON flotaison;

    public PlanteAquatique(String date, String nomTrouver, int quantiteTrouver, String nom, String couleur, int grandeur, TYPE_EAU typeEau, FLOTAISON flotaison) {
        super(date, nomTrouver, quantiteTrouver, nom, couleur, grandeur);
        this.flotaison = flotaison;
    }

    PlanteAquatique() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public enum FLOTAISON {
        FLOTTANTE,
        IMMERGEE;
    }

}
