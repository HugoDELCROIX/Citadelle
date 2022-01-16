package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Magicienne extends Personnage {

	public Magicienne() {
		super("Magicienne", 3, Caracteristiques.MAGICIENNE);
	}

	public void utiliserPouvoir() {
		ArrayList < Quartier > copieMainMagicienne = null;
		ArrayList < Quartier > copieMainJoueur = null;
		System.out.println("Voulez �changer vos cartes avec celle d'un autre joueur ? (oui/non) ");
		if (Interaction.lireOuiOuNon()) {
			for (int i = 0; i < this.getPlateau().getNombreJoueurs(); i++) {
				if (!this.getPlateau().getJoueur(i).monPersonnage.getNom().equals(this.getNom())) {
					System.out.println((i + 1) + " : Le joueur " + this.getPlateau().getJoueur(i).getNom() + " poss�de " +
						this.getPlateau().getJoueur(i).nbQuartiersDansMain() + " cartes dans sa main");
				}
			}
			System.out.println("Veuillez choisir un joueur en entrant son num�ro ");
			int joueur = Interaction.lireUnEntier(1, this.getPlateau().getNombreJoueurs() + 1);
			copieMainMagicienne = new ArrayList < Quartier > (this.getJoueur().getMain());
			copieMainJoueur = new ArrayList < Quartier > (
				this.getPlateau().getJoueur(joueur - 1).getMain());
			int nbQuartiersDansMainMagicienne = this.getJoueur().nbQuartiersDansMain();
			int nbQuartiersDansMainJoueur = this.getPlateau().getJoueur(joueur - 1).nbQuartiersDansMain();
			for (int i = 0; i < nbQuartiersDansMainMagicienne; i++) {
				this.getJoueur().retirerQuartierDansMain();
			}
			for (int i = 0; i < nbQuartiersDansMainJoueur; i++) {
				this.getPlateau().getJoueur(joueur - 1).retirerQuartierDansMain();
			}
			for (Quartier quartier: copieMainMagicienne) {
				this.getPlateau().getJoueur(joueur - 1).ajouterQuartierDansMain(quartier);
			}
			for (Quartier quartier: copieMainJoueur) {
				this.getJoueur().ajouterQuartierDansMain(quartier);
			}
		} else {
			if (this.getJoueur().nbQuartiersDansMain() == 0) {
				return;
			} else {
				System.out.println("Combien de cartes voulez-vous prendre dans la pioche ? ");
				int nb = Interaction.lireUnEntier(0, this.getJoueur().nbQuartiersDansMain() + 1);
				if (nb == 0) {
					return;
				}
				if (nb == this.getJoueur().nbQuartiersDansMain()) {
					int n = this.getJoueur().nbQuartiersDansMain();
					for (int i = 0; i < n; i++) {
						this.getPlateau().getPioche().ajouter(this.getJoueur().retirerQuartierDansMain());
					}
					for (int i = 0; i < n; i++) {
						this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
					}
				} else {
					copieMainMagicienne = new ArrayList < Quartier > (this.getJoueur().getMain());
					for (int i = 0; i < copieMainMagicienne.size(); i++) {
						System.out.println("Voici les cartes de votre main :");
						System.out.println((i + 1) + " " + copieMainMagicienne.get(i).getNom() + " - type : " +
							copieMainMagicienne.get(i).getType() + " - pi�ces : " +
							copieMainMagicienne.get(i).getCout());
					}
					for (int i = 0; i < nb; i++) {
						System.out.println("Quel est le num�ro de la carte que vous voulez retirer ? ");
						int carte = Interaction.lireUnEntier(1, copieMainMagicienne.size() + 1);
						this.getPlateau().getPioche().ajouter(copieMainMagicienne.remove(carte - 1));
					}
					for (int i = 0; i < nb; i++) {
						copieMainMagicienne.add(this.getPlateau().getPioche().piocher());
					}
					int n = this.getJoueur().nbQuartiersDansMain();
					for (int i = 0; i < n; i++) {
						this.getJoueur().retirerQuartierDansMain();
					}
					for (Quartier quartier: copieMainMagicienne) {
						this.getJoueur().ajouterQuartierDansMain(quartier);
					}
				}

			}
		}

	}

	public void utiliserPouvoirAvatar() {
		Random random = new Random();
		ArrayList < Quartier > copieMainMagicienne = null;
		ArrayList < Quartier > copieMainJoueur = null;
		int response = 0;
		boolean res = false;
		System.out.println("Voulez �changer vos cartes avec celle d'un autre joueur ? (oui/non) ");
		res = random.nextInt(2) == 1;
		if (res) {
			for (int i = 0; i < this.getPlateau().getNombreJoueurs(); i++) {
				if (!this.getPlateau().getJoueur(i).monPersonnage.getNom().equals(this.getNom())) {
					System.out.println((i + 1) + " : Le joueur " + this.getPlateau().getJoueur(i).getNom() + " poss�de " +
						this.getPlateau().getJoueur(i).nbQuartiersDansMain() + " cartes dans sa main");
				}
			}
			System.out.println("Veuillez choisir un joueur en entrant son num�ro ");
			do
				response = random
				.nextInt(this.getPlateau().getNombreJoueurs() + 1);
			while (response == 0);
			copieMainMagicienne = new ArrayList < Quartier > (this.getJoueur().getMain());
			copieMainJoueur = new ArrayList < Quartier > (
				this.getPlateau().getJoueur(response - 1).getMain());
			int nbQuartiersDansMainMagicienne = this.getJoueur().nbQuartiersDansMain();
			int nbQuartiersDansMainJoueur = this.getPlateau().getJoueur(response - 1).nbQuartiersDansMain();
			for (int i = 0; i < nbQuartiersDansMainMagicienne; i++) {
				this.getJoueur().retirerQuartierDansMain();
			}
			for (int i = 0; i < nbQuartiersDansMainJoueur; i++) {
				this.getPlateau().getJoueur(response - 1).retirerQuartierDansMain();
			}
			for (Quartier quartier: copieMainMagicienne) {
				this.getPlateau().getJoueur(response - 1).ajouterQuartierDansMain(quartier);
			}
			for (Quartier quartier: copieMainJoueur) {
				this.getJoueur().ajouterQuartierDansMain(quartier);
			}
		} else {
			if (this.getJoueur().nbQuartiersDansMain() == 0) {
				return;
			} else {
				System.out.println("Combien de cartes voulez-vous prendre dans la pioche ? ");
				int nb = random
					.nextInt(this.getPlateau().getNombreJoueurs() + 1);
				if (nb == 0) {
					return;
				}
				if (nb == this.getJoueur().nbQuartiersDansMain()) {
					int n = this.getJoueur().nbQuartiersDansMain();
					for (int i = 0; i < n; i++) {
						this.getPlateau().getPioche().ajouter(this.getJoueur().retirerQuartierDansMain());
					}
					for (int i = 0; i < n; i++) {
						this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
					}
				} else {
					copieMainMagicienne = new ArrayList < Quartier > (this.getJoueur().getMain());
					for (int i = 0; i < copieMainMagicienne.size(); i++) {
						System.out.println("Voici les cartes de votre main :");
						System.out.println((i + 1) + " " + copieMainMagicienne.get(i).getNom() + " - type : " +
							copieMainMagicienne.get(i).getType() + " - pi�ces : " +
							copieMainMagicienne.get(i).getCout());
					}
					for (int i = 0; i < nb; i++) {
						System.out.println("Quel est le num�ro de la carte que vous voulez retirer ? ");
						int carte = 0;
						do
							carte = random
							.nextInt(copieMainMagicienne.size() + 1);
						while (carte == 0);
						this.getPlateau().getPioche().ajouter(copieMainMagicienne.remove(carte - 1));
					}
					for (int i = 0; i < nb; i++) {
						copieMainMagicienne.add(this.getPlateau().getPioche().piocher());
					}
					int n = this.getJoueur().nbQuartiersDansMain();
					for (int i = 0; i < n; i++) {
						this.getJoueur().retirerQuartierDansMain();
					}
					for (Quartier quartier: copieMainMagicienne) {
						this.getJoueur().ajouterQuartierDansMain(quartier);
					}
				}

			}
		}
	}

}