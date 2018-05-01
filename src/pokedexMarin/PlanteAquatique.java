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
public class PlanteAquatique extends Poisson {

    private FLOTAISON flotaison;

    public PlanteAquatique(String date, String nomTrouver, int quantiteTrouver, String nom/*,TYPE type*/, SEXE sexe, TYPE_EAU typeEau, FLOTAISON flotaison) {
        super(date, nomTrouver, quantiteTrouver, nom,/* type,*/ sexe, typeEau);
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
