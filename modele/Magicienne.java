package modele;
/**
 * 
 * @author DAMKAM Milaine 01/12/2021
 *
 */

import java.util.ArrayList;

import controleur.Interaction;

public class Magicienne extends Personnage{
	public Magicienne() {
		super("Magicienne",3,Caracteristiques.MAGICIENNE);
	}
	public void utiliserPouvoir() {
		ArrayList<Quartier> copieTableau = new ArrayList<Quartier>(this.getJoueur().getMain());
		System.out.println("Voulez-vous ´echanger vos cartes avec celles d’un autre joueur ?(oui/non)");
		if(Interaction.lireOuiOuNon()) {
			for(int i=0;i<this.getPlateau().getNombreJoueurs();i++) {
				System.out.println("Le joueur"+this.getPlateau().getJoueur(i).getNom()+"possede"+this.getPlateau().getJoueur(i).nbQuartiersDansMain()+"cartes dans sa main");
			}
			int joueur=0;
			
			System.out.println("veuillez choisir un joueur en entrant son numero");
			joueur =Interaction.lireUnEntier(1,this.getPlateau().getNombreJoueurs());
		
		
			ArrayList<Quartier> copieMainMagicienne = new ArrayList<Quartier>(this.getJoueur().getMain());
			ArrayList<Quartier> copieMainJoueur= new ArrayList<Quartier>(this.getPlateau().getJoueur(joueur-1).getMain());
			int nM = this.getJoueur().nbQuartiersDansMain();
			int nJ = this.getPlateau().getJoueur(joueur-1).nbQuartiersDansMain();
			for(int i=0;i< nM;i++) {
				this.getJoueur().retirerQuartierDansMain();
			}
			for(int i=0;i< nJ;i++) {
				this.getPlateau().getJoueur(joueur-1).retirerQuartierDansMain();
			}
			for(Quartier quartier:copieMainMagicienne) {
				this.getPlateau().getJoueur(joueur-1).ajouterQuartierDansMain(quartier);
			}
			for(Quartier quartier:copieMainMagicienne) {
				this.getJoueur().ajouterQuartierDansMain(quartier);
			}
				
		}else
		{
			if(this.getJoueur().nbQuartiersDansMain()==0) {
				return;
		}else{
			System.out.println("Combien de cartes voulez-vous prendre dans la pioche ? (o/n)");
			int nb=Interaction.lireUnEntier(0,this.getJoueur().nbQuartiersDansMain()+1);
			if(nb==0) {
				return;
			}
			if(nb==this.getJoueur().nbQuartiersDansMain()) {
				int n = this.getJoueur().nbQuartiersDansMain();
				
				for(int i=0;i<n;i++) {
					this.getPlateau().getPioche().ajouter(this.getJoueur().retirerQuartierDansMain());
				}
				for (int i = 0; i < n; i++) {
					this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
					
				}
			}else {
				ArrayList<Quartier> copieMainMagicienne = new ArrayList<Quartier>(this.getJoueur().getMain());
				for (int i = 0; i < copieMainMagicienne.size(); i++) {
					System.out.println("Voici les cartes de votre main :");
					System.out.println((i + 1) + " " + copieMainMagicienne.get(i).getNom() + " - type : "
					+ copieMainMagicienne.get(i).getType() + " - pièces : "
					+ copieMainMagicienne.get(i).getCout());
					}
				for (int i = 0; i < nb; i++) {
					System.out.print("Quel est le numéro de la carte que vous voulez retirer ? ");
					int carte = Interaction.lireUnEntier(1, copieMainMagicienne.size() + 1);
					this.getPlateau().getPioche().ajouter(copieMainMagicienne.remove(carte-1));
					}
				for (int i = 0; i < nb; i++) {
					copieMainMagicienne.add(this.getPlateau().getPioche().piocher());
					}
				int n = this.getJoueur().nbQuartiersDansMain();
				for (int i = 0; i < n; i++) {
					this.getJoueur().retirerQuartierDansMain();
					}
				for (Quartier quartier : copieMainMagicienne) {
					this.getJoueur().ajouterQuartierDansMain(quartier);
					}
			}
				
			}
		}
	}

	public void utiliserPouvoirAvatar() {
	
	}
}
		


