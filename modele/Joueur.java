package modele;
import java.util.ArrayList;
import java.util.Random;

public class Joueur {
    private String nom;
    private int tresor;
    private int nbQuartiers;
    private boolean possedeCouronne;
    private Quartier[] cite;
    private ArrayList < Quartier > main;

    public Joueur(String nom) {
        this.nom = nom;
        this.tresor = 0;
        this.nbQuartiers = 0;
        this.possedeCouronne = false;
        this.cite = new Quartier[8];
        this.main = new ArrayList < Quartier > ();
    }

    public String getNom() {
        return this.nom;
    }

    public int nbPieces() {
        return this.tresor;
    }

    public int nbQuartiersDansCite() {
        return this.nbQuartiers;
    }

    public Quartier[] getCite() {   
        return this.cite;
    }

    public ArrayList < Quartier > getMain() {
        return this.main;
    }

    public int nbQuartiersDansMain() {
        return this.main.size();
    }

    public boolean getPossedeCouronne() {
        return this.possedeCouronne;
    }

    public void setPossedeCouronne(boolean b) {
        this.possedeCouronne = b;
    }

    public void ajouterPieces(int piece) {
        if (piece > 0) {
            tresor = tresor + piece;
        } else {
            System.out.println("Le nombre de pieces ne peut pas etre negatif");
        }
    }

    public void retirerPieces(int piece) {
        if (piece <= tresor) {
            if (piece > 0) {
                tresor = tresor - piece;
            } else {
                System.out.println("Le nombre de pieces ne peut pas etre negatif");
            }
        } else {
            System.out.println("Vous ne pouvez pas retirer plus de pièces que le trésor");
        }
    }

    public void ajouterQuartierDansCite(Quartier quartier) {
        if (nbQuartiers < 8) {
            cite[nbQuartiers] = quartier;
            nbQuartiers++;
        }
    }

    public boolean quartierPresentDansCite(String quartier) {
        for (int i = 0; i < nbQuartiers; i++) {
            if (cite[i] != null && cite[i].getNom().equals(quartier)) {
                return true;
            }
        }
        return false;
    }

    public Quartier retirerQuartierDansCite(String quartier) {
        Quartier quartSupprime = null;
        for (int i = 0; i < nbQuartiers; i++) {
            if (cite[i] != null && cite[i].getNom().equals(quartier)) {
                quartSupprime = cite[i];
                cite[i] = null;
                nbQuartiers--;
            }
        }
        return quartSupprime;
    }

    public void ajouterQuartierDansMain(Quartier nom){
        main.add(nom);
    }

    public Quartier retirerQuartierDansMain(){
        Random generateur = new Random();
        int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
        Quartier retour=main.get(numeroHasard);
        main.remove(numeroHasard);
        return retour;
    }

    public void reinitialiser() {
        this.tresor = 0;
        this.main =  new ArrayList<Quartier>();
        this.cite = new Quartier[8];
        this.nbQuartiers = 0;
    }
}