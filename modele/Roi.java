package modele;

public class Roi extends Personnage{
    
    public Roi(){
        super("Roi",4,Caracteristiques.ROI);
    }

    public void utiliserPouvoir(){
        System.out.println("Je prends la couronne");
        Joueur.possedeCouronne = true;
    }

    public void percevoirRessourcesSpecifiques(){
        
    }
}
