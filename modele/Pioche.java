package modele;

import java.util.ArrayList;
import java.util.Random;

public class Pioche {
    private ArrayList<Quartier> liste;
    public Pioche(){
        this.liste = new ArrayList<Quartier>();
    }

    public Quartier piocher(){
        return this.liste.size() > 0 ? this.liste.remove(0) : null;
    }

    public void ajouter(Quartier quartier){
        this.liste.add(quartier);
    }

    public int nombreElements(){
        return this.liste.size();
    }

    public void melanger(){
        Random generateur = new Random();
        int i = generateur.nextInt(this.nombreElements());
        int j = generateur.nextInt(this.nombreElements());
        Quartier tempQuartier;
        tempQuartier = liste.get(i);
        liste.set(i, liste.get(j));
        liste.set(j, tempQuartier);
    }
}