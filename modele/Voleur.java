package modele;
import controleur.Interaction; 

/**
 * 
 * @author DEIRI Sana 13/12/2021
 *
 */

public class Voleur extends Personnage {
	public Voleur () {
		super("Voleur",2,Caracteristiques.VOLEUR);
	}
	
	public void utiliserPouvoir() {  
		PlateauDeJeu plateau = this.getPlateau();
		int nbPersonnages = plateau.getNombrePersonnages();
		System.out.println("Quel personnage voulez-vous voler?");
		for (int i = 0; i < nbPersonnages; i++) {
			Personnage p = plateau.getPersonnage(i);
			System.out.println((i+1) + ":" + p.getNom());
		}
		
		boolean continu = true;
		int choix = 0;
		int piecesVolees = 0;
		do {
			try {
				System.out.println("Vous avez choisi :");
				choix = Interaction.lireUnEntier() -1;
				Personnage personnageChoisi = plateau.getPersonnage(choix);
				Joueur joueurChoisi = personnageChoisi.getJoueur();
				if(personnageChoisi == this) 
					throw new ExceptionMeChoisir ();
				else if (personnageChoisi.getRang() == 1)
					throw new ExceptionMauvaisRang ();
				else if (joueurChoisi.nbPieces() == 0)
					throw new ExceptionPasArgentSuffisant ();
				
				personnageChoisi.setVole();
				piecesVolees = joueurChoisi.nbPieces();
				joueurChoisi.retirerPieces(piecesVolees);
				this.getJoueur().ajouterPieces(piecesVolees);
				continu = false;
			}
			catch (ExceptionMeChoisir e) {
				System.out.println ("Vous ne pouvez pas vous choisir !");
			}
			catch (ExceptionMauvaisRang e ) {
				System.out.println ("Vous ne pouvez pas voler un personnage de rang 1.");
			}
			catch (ExceptionPasArgentSuffisant e) {
				System.out.println("Vous ne pouvez pas voler un personnages qui n'a pas de pieces");
			}
		} while(continu);	
	}
}