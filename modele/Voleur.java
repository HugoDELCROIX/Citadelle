package modele;

import controleur.Interaction;
import java.util.Random;

public class Voleur extends Personnage {

    public Voleur() {
        super("Voleur", 2, Caracteristiques.VOLEUR);
    }


    public void utiliserPouvoir() {
        boolean personnageVole = false;

        do {
            System.out.println("Voici la liste des personnages");
            for (int i = 0; i < getPlateau().getNombreJoueurs(); i++) {
                Personnage personnage = getPlateau().getPersonnage(i);
                System.out.println("\n" + (i + 1) + " " + personnage.getNom() + " : ");
            }
            System.out.println("Choisissez un Personnage : ");
            int listePerso = Interaction.lireUnEntier(1, getPlateau().getNombreJoueurs() + 1);

            Personnage personnageChoisi = this.getPlateau().getPersonnage(listePerso - 1);

            if (personnageChoisi.getJoueur().equals(null)) {
                personnageVole = true;
            } else if (personnageChoisi.getRang() == 1) {
                System.out.println("Vous ne pouvez pas voler un personnage de rang 1");
                personnageVole = false;
            } else if (personnageChoisi.getNom().equals("Voleur")) {
                System.out.println("Vous ne pouvez pas vous voler vous même");
                personnageVole = false;
            } else {
                personnageChoisi.setVole();
                personnageVole = true;
            }
        } while (personnageVole == false);
    }

    public void utiliserPouvoirAvatar() {

        Random rand = new Random();
        int listeperso = rand.ints(0, getPlateau().getNombreJoueurs() + 1).findFirst().getAsInt();
        Personnage personnageChoisi = this.getPlateau().getPersonnage(listeperso - 1);
        boolean personnageVole = false;

        while (personnageChoisi.getRang() == 1 || personnageChoisi.getNom().equals("Voleur")) {
            listeperso = rand.ints(0, getPlateau().getNombreJoueurs() + 1).findFirst().getAsInt();
            personnageChoisi = this.getPlateau().getPersonnage(listeperso - 1);
        }

        do {
            if (personnageChoisi.getJoueur().equals(null)) {
                personnageVole = true;
            } else {
                personnageChoisi.setVole();
                personnageVole = true;
            }
        } while (personnageVole == false);
    }
}