package modele;

/**
 * 
 * @author DEIRI Sana 10/12/2021
 *
 */

public class Marchande extends Personnage {
	public Marchande() {
		super("Marchande",6,Caracteristiques.MARCHANDE);
	}
	
	
	public void utiliserPouvoir() {  
        
    }
	
	public void percevoirRessourcesSpecifiques() {
		Quartier[] cite = this.getJoueur().getCite();
        int nbQuartiers = this.getJoueur().nbQuartiersDansCite();

        for(int i=0;i<nbQuartiers;i++){
            if(cite[i]!=null && cite[i].getType().equals(Quartier.TYPE_QUARTIERS[3])){
                getJoueur().ajouterPieces(1);
            }
        }
	}
}
	


