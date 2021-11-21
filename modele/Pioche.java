package modele;

import java.util.ArrayList;
import java.util.Random;

public class Pioche {

    private ArrayList < Quartier > liste = new ArrayList<Quartier>();

    public Pioche() {
        ArrayList < Quartier > liste = new ArrayList<Quartier>();
    }

    public ArrayList < Quartier > getListe() {
        return liste;
    }

    public void setListe(ArrayList < Quartier > liste) {
        this.liste = liste;
    }

    public Quartier piocher() {
        Quartier q = new Quartier();
        q = null;
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i) != null) {
                q = liste.get(i);
                break;
            }
        }
        return q;
    }

    public void ajouter(Quartier nouveau) {
        liste.add(nouveau);
    }

    public int nombreElements() {
        int i;
        i = liste.size();
        return i;
    }

    public void melanger() {
        Random generateur = new Random();
        for (int i = 0; i < liste.size(); i++) {
            int k = generateur.nextInt();
            int j = generateur.nextInt();
        }
    }
}