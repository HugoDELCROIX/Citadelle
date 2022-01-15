package modele;

import java.util.Random;

import controleur.Interaction;

public class Assassin extends Personnage {

	public Assassin() {
		super("Assassin", 1, Caracteristiques.ASSASSIN);
	}

	public void utiliserPouvoir() {
		Personnage[] listePersonnages = this.getPlateau().listePersonnages;
		System.out.println("Quel personnage voulez-vous assassiner ?");
		for (int i = 0; i < this.getPlateau().getNombrePersonnages(); i++) {

			System.out.println((i + 1) + "  " + listePersonnages[i].getNom());
		}
		boolean continu = true;
		do {
			int c = Interaction.lireUnEntier() - 1;
			System.out.println("Votre choix : " + c);
			if (listePersonnages[c].getNom().equals("Assassin")) {
				System.out.println("Vous ne pouvez pas vous assasiner.");
			} else {
				listePersonnages[c].setAssassine();
				continu = false;
			}
		}
		while (continu);
	}
	
	public void utiliserPouvoirAvatar() {
		Random rand = new Random();

            int listeJoueur = rand.nextInt(this.getPlateau().getNombreJoueurs()-1);
            Personnage personnageChoisi = getPlateau().getPersonnage(listeJoueur+1);

            while (personnageChoisi.getNom().equals("Assassin")) {
                listeJoueur = rand.nextInt(this.getPlateau().getNombreJoueurs()+1);
                personnageChoisi = getPlateau().getPersonnage(listeJoueur-1);
            }

			personnageChoisi.setAssassine();
    }

	public static void main(String[] args) {
		PlateauDeJeu plateau = new PlateauDeJeu();

		Joueur j1 = new Joueur("Hugo");
		Roi roi = new Roi();
		roi.setJoueur(j1);
		plateau.ajouterJoueur(j1);
		plateau.ajouterPersonnage(roi);

		Joueur j2 = new Joueur("Nathan");
		Assassin assassin = new Assassin();
		assassin.setJoueur(j2);
		plateau.ajouterJoueur(j2);
		plateau.ajouterPersonnage(assassin);

		Joueur j3 = new Joueur("Steph");
		Eveque eveque = new Eveque();
		eveque.setJoueur(j3);
		plateau.ajouterJoueur(j3);
		plateau.ajouterPersonnage(eveque);

		System.out.println("1. "+eveque.getAssassine());
		System.out.println("\n2. "+roi.getAssassine());
		System.out.println("\n3. "+assassin.getAssassine());

		System.out.println("Utilisation du pouvoir Avatar");

		System.out.println("Nombre de Joueurs : "+plateau.getNombreJoueurs()+"\nNombre de Personnages : "+plateau.getNombrePersonnages());
		assassin.utiliserPouvoir();

		System.out.println("1. "+eveque.getAssassine());
		System.out.println("\n2. "+roi.getAssassine());
		System.out.println("\n3. "+assassin.getAssassine());

	}

}