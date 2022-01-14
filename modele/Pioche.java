package modele;

import java.util.ArrayList;
import java.util.Random;

public class Pioche {
    private ArrayList < Quartier > liste;

    public Pioche() { //Constructeur
        this.liste = new ArrayList < Quartier > ();
    }

    public Quartier piocher() { //Piocher la carte au sommet de la pioche
        return this.liste.size() > 0 ? this.liste.remove(0) : null;
    }

    public void ajouter(Quartier quartier) { //Ajouter un quartier en bas de la pioche
        this.liste.add(quartier);
    }

    public int nombreElements() { //Retourne le nombre de carte dans la pioche 
        return this.liste.size();
    }

    public void melanger() { //Mélanger la pioche
        Random generateur = new Random();
        int i = generateur.nextInt(this.nombreElements());
        int j = generateur.nextInt(this.nombreElements());
        Quartier tempQuartier;
        tempQuartier = liste.get(i);
        liste.set(i, liste.get(j));
        liste.set(j, tempQuartier);
    }
}