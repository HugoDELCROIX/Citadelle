package modele;
import java.util.ArrayList;

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



}