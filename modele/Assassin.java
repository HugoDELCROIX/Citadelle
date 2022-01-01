package modele;

import controleur.Interaction;

public class Assassin extends Personnage{
	
	
	public Assassin() {
		super("Assassin", 1, Caracteristiques.ASSASSIN);
	}

	public void utiliserPouvoir() {
		Personnage[]listePersonnages = this.getPlateau().listePersonnages;
		System.out.println("Quel personnage voulez-vous assassiner ?");
		for(int i=0;i<this.getPlateau().getNombrePersonnages();i++) {
			
			System.out.println((i+1)+"  "+listePersonnages[i].getNom());
		}
		boolean continu=true;
		do {
			int c= Interaction.lireUnEntier()-1;
			System.out.println("Votre choix : "+c);
			if(listePersonnages[c].getNom().equals("Assassin")) {
				System.out.println("Vous ne pouvez pas vous assasiner.");
			}
			else {
				listePersonnages[c].setAssassine();
				continu=false;
			}
		}
		while(continu);
	}


	public void utiliserPouvoirAvatar() {
		
	}

}
