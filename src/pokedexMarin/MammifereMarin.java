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
public class MammifereMarin extends Poisson {

    private ALIMENTATION alimentation;

    public MammifereMarin(String date, String nomTrouver, int quantiteTrouver, String nom, /*TYPE type,*/ SEXE sexe, TYPE_EAU typeEau, String cri, ALIMENTATION alimentation) {
        super(date, nomTrouver, quantiteTrouver, nom,/* type, */ sexe, typeEau);
        this.cri = cri;
        this.alimentation = alimentation;
    }

    MammifereMarin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public enum ALIMENTATION {
        CARNIVORE,
        VEGETARIEN;
    }

    private String cri = "";

}
