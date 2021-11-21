package modele;

public class PlateauDeJeu {
    private Personnage[] listePersonnages;
    private Joueur[] joueur;
    private Pioche pioche;
    private int nombrePersonnages;
    private int nombreJoueurs;

    public PlateauDeJeu() {
        this.listePersonnages = new Personnage[9];
        this.nombreJoueurs = 0;
        this.nombrePersonnages = 0;
        //En ce qui concerne la pioche, vous devrez cr´eer une instance de la pioche dans ce 
        //constructeur (lien de composition dans le diagramme de classe).
    }

    public void getNombrePersonnages(){
        
    }
}