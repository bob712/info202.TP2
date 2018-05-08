/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedexMarin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import pokedexMarin.Animal.SEXE;
import pokedexMarin.Vivant.TYPE_EAU;
import pokedexMarin.MammifereMarin.ALIMENTATION;
import pokedexMarin.PlanteAquatique.FLOTAISON;

/**
 *
 * @author vbrod
 */
public class Pokedex {

    public Scanner clavier = new Scanner(System.in);
    private Encryption cryptage;
    private int personneIndex = -1;
    private ArrayList<Personne> personnes;
    private ArrayList<Specimen> pokemons;

    public Pokedex() {
        this.personnes = new ArrayList();
        this.pokemons = new ArrayList();
        try {
            this.cryptage = new Encryption();
        } catch (Exception ex) {
            Logger.getLogger(Pokedex.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Bienvenu à ce Pokedex");
        if (ouvrirPersonnes()) {//peut etre enlever si on fait une autre façon de connecter l'utilisateur
            personneIndex = connexionPersonne();
        }
    }

    public int getPersonneIndex() {//effacer?
        return personneIndex;
    }

    private boolean ouvrirPersonnes() {
        boolean fichierOuvert = false;
        String[] infoPersonneTemp;
        String ligne;
        Personne personneActuelle;
        try {
            // extraire les informations du fichier
            BufferedReader lire = new BufferedReader(new FileReader("Personnes.txt"));
            while ((ligne = lire.readLine()) != null) {
                infoPersonneTemp = ligne.split(";");
                personneActuelle = new Personne(infoPersonneTemp[0], infoPersonneTemp[1], infoPersonneTemp[2], Integer.parseInt(infoPersonneTemp[3]));
                personnes.add(personneActuelle);
            }
            lire.close();
            fichierOuvert = true;

        } catch (FileNotFoundException fnfe) {
            System.out.println("Veuillez poser le fichier Personnes.txt dans le fichier du projet.");
        } catch (NumberFormatException nfe) {
            System.out.println("La dernière position de chaque ligne du fichier Personnes.txt doit contenir un nombre entier représentat l'âge de l'utilisateur.");
        } catch (IOException ioe) {
            System.out.println("Une erreure s'est produite lors de l'ouverture du fichier Personnes.txt.\nVeuillez vérifier la présence, le contenu et le type du fichier.");
        }
        return fichierOuvert;
    }

    public int connexionPersonne() {
        String codeAcces;
        String motDePasse;
        boolean utilisateurTrouve = false;
        boolean motDePasseValide = false;
        int ligne;
        System.out.println("Veuillez entrer votre nom d'usager.");
        codeAcces = clavier.nextLine();

        ligne = -1;
        while (ligne < this.personnes.size() && !utilisateurTrouve) {
            ligne++;
            if ((codeAcces).equals(this.personnes.get(ligne).getCodeAcces())) {
                utilisateurTrouve = true;
                //cheker mot de passe
                int nbEssais = 0;
                System.out.println("Veuillez entrer votre mot de passe.");
                while (nbEssais < 3 && !motDePasseValide) {
                    motDePasse = clavier.nextLine();
                    if (motDePasse.length() % 8 == 0) {
                        if (motDePasse.equals(cryptage.decrypt(this.personnes.get(ligne).getMotDePasse()))) {
                            motDePasseValide = true;
                        } else {
                            nbEssais++;//augmenter le nombre d'essais seulement quand le mot de passe est mauvais, pas quand c'est une autre condition
                            System.out.println("Ce mot de passe n'est pas le votre, veuillez entrer votre mot de passe. (essai " + nbEssais + "/3)");
                        }
                    } else {
                        nbEssais++;
                        System.out.println("Veuillez entrer un mot de passe dont le nombre de caractères est un multiple de 8 (essai " + nbEssais + "/3)");
                    }
                }
                if (nbEssais == 3) {
                    System.out.println("Vous avez raté tous vos essais, À la prochaine!");
                }
                if (motDePasseValide) {

                    System.out.println("Vous êtes connectés à votre compte, " + this.personnes.get(ligne).getNom() + ".");
                } else {
                }
            }

        }
        if (!utilisateurTrouve) {
            System.out.println("Ce nom d'utilisateur n'existe pas. Voulez-vous quitter le Pokedex Marin? (Oui/Non) ");
            switch (clavier.nextLine().toLowerCase()) {
                case "non":
                    return connexionPersonne();
                //break;
                case "oui":
                    ligne = -1;
                    System.out.println("À la prochaine!");
                    break;
                default:
                    ligne = -1;
                    System.out.println("Cette réponse n'est pas reconnue.\nÀ la prochaine!");
                    break;
            }
        }
        return ligne;
    }

    public void menu() {
        boolean quitter = false;
        System.out.println(
                "\nVeuillez entrer le numéro de l'action que vous désirer effectuer\n"
                + "1-Consulter les specimens déjà saisis.\n"
                + "2-Saisir un nouveau spécimen.\n"
                + "3-Modifier un specimen.\n"
                + "4-Statistiques.\n"
                + "5-Quitter");
        switch (clavier.nextLine()) {
            case "1":
                if (pokemons.isEmpty()) {
                    System.out.println("Il n'y a présentement aucun specimen enregistrés");
                } else {
                    consulter();
                }
                break;
            case "2":
                ajoutSpecimen();
                break;
            case "3":
                if (pokemons.isEmpty()) {
                    System.out.println("Il n'y a présentement aucun specimen enregistrés");
                } else {
                    modifierSpecimen();
                }
                break;
            case "4":
                Statistiques();
                break;
            case "5":
                sauvegarde();
                quitter = true;
                break;
        }
        if (!quitter) {//recommencer tant que l'utilisateur ne veut pas quitter;
            menu();
        }
    }

    private void consulter() {
        for (int index = 0; index < pokemons.size(); index++) {
            System.out.println(pokemons.get(index).getNom());
        }
    }

    private void ajoutSpecimen() {
        String type;
        String date;
        String nom;
        String couleur;
        int quantiteTrouve;
        int grandeur;
        SEXE sexe;
        TYPE_EAU type_eau = null;

        type = typeAjout();
        nom = nomAjout();
        date = dateAjout();
        quantiteTrouve = quantiteAjout();
        couleur = couleurAjout();
        grandeur = grandeurAjout();
        switch (type) {
            case "1":
                pokemons.add(new Poisson(date,
                        personnes.get(personneIndex).getCodeAcces(),
                        quantiteTrouve, nom, couleur, grandeur, sexeAjout(), type_eauAjout()));
                break;
            case "2":
                pokemons.add(new MammifereMarin(date, personnes.get(personneIndex).getCodeAcces(), quantiteTrouve, nom, couleur, grandeur, sexeAjout(), type_eauAjout(), criAjout(), alimentationAjout()));
                break;
            case "3":
                pokemons.add(new PlanteAquatique(date, personnes.get(personneIndex).getCodeAcces(), quantiteTrouve, nom, couleur, grandeur, type_eauAjout(), flotaisonAjout()));
                break;
            case "4":
                pokemons.add(new Mineral(date, personnes.get(personneIndex).getCodeAcces(), quantiteTrouve, nom, couleur, grandeur));
                break;
            case "5":
                pokemons.add(new AutreAnimal(date, personnes.get(personneIndex).getCodeAcces(), quantiteTrouve, nom, couleur, grandeur, sexeAjout(), type_eauAjout()));
                break;
            default:
                System.out.println("Vous êtes redirigés vers le menu");
                break;
        }
    }

    private String typeAjout() {
        System.out.println("Quel type de specimen avez-vous observé?\n"
                + "1-Poisson\n"
                + "2-Mammifère marin\n"
                + "3-Plante aquatique\n"
                + "4-Minéral\n"
                + "5-Autre animal marin\n"
                + "autre-Je me suis trompé, je ne désire pas ajouter de créature.");
        return clavier.nextLine();
    }

    private String nomAjout() {
        System.out.println("Comment voulez-vous nommer ce spécimen?");
        return clavier.nextLine();
    }

    private String dateAjout() {
        String date;
        boolean dateCorrecte;
        System.out.println("À quelle date avez-vous observé ce specimen(entrer la date sous le format AAAAMMJJ sans espaces)");
        do {
            dateCorrecte = true;
            date = clavier.nextLine();
            if (date.length() != 8) {
                dateCorrecte = false;
                System.out.println("Veuillez entrer 8 nombres représentant la date en format AAAAMMJJ.");
            }
            //if ya des caracteres qui sont pas des chiffres
        } while (!dateCorrecte);
        return date;
    }

    private int quantiteAjout() {
        int quantiteTrouve = 0;
        boolean entreeValide;
        System.out.println("Combien en avez-vous observé?");
        do {
            try {
                quantiteTrouve = Integer.parseInt(clavier.nextLine());
                entreeValide = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Veuillez entrer un nombre entier");
                entreeValide = false;
            }
        } while (!entreeValide);
        return quantiteTrouve;
    }

    private String couleurAjout() {
        System.out.println("Veuillez entrer la couleur qui corresponds le mieux à la couleur du spécimen.");
        return clavier.nextLine();
    }

    private int grandeurAjout() {
        int grandeur = 0;
        boolean entreeValide;
        System.out.println("De quelle taille étaient-ils? (entrez leur taille en cm)");
        do {
            try {
                grandeur = Integer.parseInt(clavier.nextLine());
                entreeValide = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Veuillez entrer un nombre entier");
                entreeValide = false;
            }
        } while (!entreeValide);
        return grandeur;
    }

    private SEXE sexeAjout() {
        SEXE sexe = null;
        boolean entreeCorrecte;
        System.out.println("De quel sexe etaient-ils? \n1-Mâle\n2-Femelle");
        do {
            switch (clavier.nextLine()) {
                case "1":
                    entreeCorrecte = true;
                    sexe = SEXE.MALE;
                    break;
                case "2":
                    entreeCorrecte = true;
                    sexe = SEXE.FEMELLE;
                    break;
                default:
                    entreeCorrecte = false;
                    System.out.println("Cette reponse n'est pas vailde, veuillez réentrer votre choix.");
                    break;
            }
        } while (!entreeCorrecte);
        return sexe;
    }

    private TYPE_EAU type_eauAjout() {
        boolean entreeCorrecte;
        TYPE_EAU type_eau = null;
        System.out.println("Dans quels type d'eau vivent-ils?\n1-Eau douce\n2-Eau salée");
        do {
            switch (clavier.nextLine()) {
                case "1":
                    entreeCorrecte = true;
                    type_eau = TYPE_EAU.EAU_DOUCE;
                    break;
                case "2":
                    entreeCorrecte = true;
                    type_eau = TYPE_EAU.EAU_SALEE;
                    break;
                default:
                    entreeCorrecte = false;
                    System.out.println("Cette reponse n'est pas vailde, veuillez réentrer votre choix.");
                    break;
            }
        } while (!entreeCorrecte);
        return type_eau;
    }

    private String criAjout() {
        System.out.println("Veuillez entrer le cri de cet animal");
        return clavier.nextLine();
    }

    private ALIMENTATION alimentationAjout() {
        ALIMENTATION alimentation = null;
        boolean entreeCorrecte;
        System.out.println("Quel est le type d'alimentation de cet animal?\n1-Carnivore\n2-Végétarien");
        do {
            switch (clavier.nextLine()) {
                case "1":
                    entreeCorrecte = true;
                    alimentation = ALIMENTATION.CARNIVORE;
                    break;
                case "2":
                    entreeCorrecte = true;
                    alimentation = ALIMENTATION.VEGETARIEN;
                    break;
                default:
                    entreeCorrecte = false;
                    System.out.println("Cette reponse n'est pas vailde, veuillez réentrer votre choix.");
                    break;
            }
        } while (!entreeCorrecte);
        return alimentation;
    }

    private FLOTAISON flotaisonAjout() {
        FLOTAISON flotaison = null;
        Boolean entreeCorrecte;
        System.out.println("La plante est-elle un flottante ou immergée?\n1-Flottante\n2-Immergée");
        do {
            switch (clavier.nextLine()) {
                case "1":
                    entreeCorrecte = true;
                    flotaison = FLOTAISON.FLOTTANTE;
                    break;
                case "2":
                    entreeCorrecte = true;
                    flotaison = FLOTAISON.IMMERGEE;
                    break;
                default:
                    entreeCorrecte = false;
                    System.out.println("Cette reponse n'est pas vailde, veuillez réentrer votre choix.");
                    break;
            }
        } while (!entreeCorrecte);
        return flotaison;
    }

    private void modifierSpecimen() {
        System.out.println("Choisissez la modification que vous voulez effectuer.\n"
                + "1-Supprimer un specimen.\n"
                + "2-Modifier la quantité appercue d'un specimen donné.\n"
                + "3-Retourner au menu principal");
        switch (clavier.nextLine()) {
            case "1":
                supprimerSpecimen();
                break;
            case "2":
                modifierQuantiteSpecimen();
                break;
            case "3":
                break;
            default:
                System.out.println("Cette option n'est pas valide. Vous êtes redirigés vers le menu principal.");
                break;
        }
    }

    private void supprimerSpecimen() {
        int indexSpecimen;
        System.out.println("De quel type est le specimen que vous voules supprimer?");
        afficherInstancesType(choixType());
        System.out.println("Parmis ceux-cis, lequel voulez- vous supprimer?");
        indexSpecimen = trouverIndexSpecimen();
        System.out.println("Le specimen " + pokemons.get(indexSpecimen) + " va être supprimé, désirez-vous continuer?\n1-Oui\n2-Non");
        switch (clavier.nextLine().toLowerCase()) {
            case "1":
            case "oui":
                pokemons.remove(pokemons.get(indexSpecimen));
                break;
            case "2":
            case "non":
                System.out.println("La suppression a été annulée. Vous êtes redirigés vers BRELEBESHRE");
                break;
        }
    }

    private void modifierQuantiteSpecimen() {
        int indexSpecimen;
        boolean entreeValide = false;
        System.out.println("De quel type est le specimen dont vous voulez modifier le nombre?");
        afficherInstancesType(choixType());
        System.out.println("Parmis ceux-cis, pour lequel voulez-vous modifier la quantité observée? ");
        indexSpecimen = trouverIndexSpecimen();
        System.out.println("La quantité de " + pokemons.get(indexSpecimen).getNom() + " observé est présentement de " + pokemons.get(indexSpecimen).getQuantiteTrouver()
                + ".\n Entrer la nouvelle valeure pour cette quantité observée.");
        while (!entreeValide) {
            try {
                pokemons.get(indexSpecimen).setQuantiteTrouver(Integer.parseInt(clavier.nextLine()));
                entreeValide = true;
                System.out.println("La quantité de " + pokemons.get(indexSpecimen).getNom() + " observé est maintenant de " + pokemons.get(indexSpecimen).getQuantiteTrouver() + ".");
            } catch (NumberFormatException nfe) {
                System.out.println("Veuillez entrer un nombre entier.");
                entreeValide = false;
            }
        }
    }

    private Specimen choixType() {
        Specimen type;
        System.out.println(""
                + "1-Poisson\n"
                + "2-Mammifère Marin\n"
                + "3-Plante Aquatique\n"
                + "4-Mineral\n"
                + "5-Autre animal marin\n"
                + "Autre-Je ne connais pas le type du specimen que je cherche.");
        switch (clavier.nextLine()) {
            case "1":
                type = new Poisson();
                break;
            case "2":
                type = new MammifereMarin();
                break;
            case "3":
                type = new PlanteAquatique();
                break;
            case "4":
                type = new Mineral();
                break;
            case "5":
                type = new AutreAnimal();
                break;
            default:
                type = new Specimen();
                break;
        }
        return type;
    }

    private void afficherInstancesType(Specimen type) {
        System.out.println("Voici l'ensemble des specimens de ce type.\n");
        for (int indexSpecimen = 0; indexSpecimen < pokemons.size(); indexSpecimen++) {
            if (pokemons.get(indexSpecimen).getClass() == type.getClass()) {
                System.out.println(pokemons.get(indexSpecimen).getNom());
            }
        }
    }

    private int trouverIndexSpecimen() {
        boolean indexTrouve = false;
        String nom;
        System.out.println("Entrer le nom du specimen (sans tenir compte des majuscules)");
        int indexSpecimen = 0;
        while (!indexTrouve) {
            nom = clavier.nextLine();
            while (indexSpecimen < pokemons.size() && !(pokemons.get(indexSpecimen).getNom().equalsIgnoreCase(nom))) {
                indexSpecimen++;
            }
            if ((pokemons.get(indexSpecimen).getNom().equalsIgnoreCase(nom))) {
                indexTrouve = true;
            } else {
                System.out.println("Ce specimen ne fait pas partie de la liste, veuillez réentrer le nom d'un specimen.");
                indexTrouve = false;
            }
        }
        return indexSpecimen;
    }

    private void Statistiques() {
        afficherNbEntreesType();
        afficherNbEntreesPersonne();
        afficherInformationsPersonnes();
    }

    private void afficherNbEntreesType() {
        int nbPoisson = 0;
        int nbMammifere = 0;
        int nbPlante = 0;
        int nbMineral = 0;
        for (int index = 0; index < pokemons.size(); index++) {
            if (pokemons.get(index) instanceof Poisson) {
                nbPoisson++;
            } else if (pokemons.get(index) instanceof MammifereMarin) {
                nbMammifere++;
            } else if (pokemons.get(index) instanceof PlanteAquatique) {
                nbPlante++;
            } else if (pokemons.get(index) instanceof Mineral) {
                nbMineral++;
            }
        }
        System.out.println("Il y a \n"
                + nbPoisson + " entrées qui sont des poisson,\n"
                + nbMammifere + " entrées qui sont de Mammifères Marins,\n"
                + nbPlante + " entrées qui sont des Plantes Aquatiques et\n"
                + nbMineral + " entrées qui sont des Minéraux");
    }

    private void afficherNbEntreesPersonne() {
        ArrayList<Integer> nbEntreesPersonnes = new ArrayList();

        for (int indexPersonne = 0; indexPersonne < personnes.size(); indexPersonne++) {
            nbEntreesPersonnes.add(0);
        }

        for (int indexPokemon = 0; indexPokemon < pokemons.size(); indexPokemon++) {
            for (int indexPersonne = 0; indexPersonne < personnes.size(); indexPersonne++) {
                if ((pokemons.get(indexPokemon).getCodeAccesTrouver()).equals(personnes.get(indexPersonne).getCodeAcces())) {
                    nbEntreesPersonnes.set(indexPersonne, (nbEntreesPersonnes.get(indexPersonne)) + 1);
                }
            }
        }
        for (int indexPersonne = 0; indexPersonne < personnes.size(); indexPersonne++) {
            System.out.println(personnes.get(indexPersonne) + " a enregistre " + nbEntreesPersonnes.get(indexPersonne).toString() + " specimens.");
        }

    }

    private void afficherInformationsPersonnes() {
    }

    private void sauvegarde() {
        try {
            ObjectOutputStream ecrire = new ObjectOutputStream(new FileOutputStream("pokedex.bin"));
            ecrire.writeObject(pokemons);
            ecrire.flush();
            ecrire.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pokedex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pokedex.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void ouvertureBinaire() {
        try {
            ObjectInputStream lire = new ObjectInputStream(new FileInputStream("pokedex.bin"));
            pokemons = (ArrayList<Specimen>) lire.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pokedex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pokedex.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pokedex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
