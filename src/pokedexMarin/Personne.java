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
public class Personne {

    private String nom;
    private String codeAcces;
    private String motDePasse;
    private int age;

    public Personne(String codeAcces, String motDePasse, String nom, int age) {
        this.nom = nom;
        this.codeAcces = codeAcces;
        this.motDePasse = motDePasse;
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public String getCodeAcces() {
        return codeAcces;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public int getAge() {
        return age;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCodeAcces(String codeAcces) {
        this.codeAcces = codeAcces;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Personne{" + "nom=" + nom + ", codeAcces=" + codeAcces + ", motDePasse=" + motDePasse + ", age=" + age + '}';
    }

    
}
