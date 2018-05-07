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
public class MammifereMarin extends Animal {

    private ALIMENTATION alimentation;
    private String cri = "aucun cri précisé";

    public enum ALIMENTATION {
        CARNIVORE,
        VEGETARIEN;
    }

    public MammifereMarin() {
    }

    public MammifereMarin(String date, String nomTrouver, int quantiteTrouver, String nom, String couleur, int grandeur, SEXE sexe, TYPE_EAU typeEau, String cri, ALIMENTATION alimentation) {
        super(date, nomTrouver, quantiteTrouver, nom, couleur, grandeur, sexe, typeEau);
        this.cri = cri;
        this.alimentation = alimentation;
    }

}
