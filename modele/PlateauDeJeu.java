package modele;

public class PlateauDeJeu {
    private Personnage[] listePersonnages;
    private Joueur[] listeJoueurs;
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

    public int getNombrePersonnages(){
        return nombrePersonnages;
    }

    public int getNombreJoueurs(){
        return nombreJoueurs;
    }

    public Pioche getPioche(){
        return pioche;
    }

    public Personnage getPersonnages(int i){
        if(0<=i && i<=8){
            return listePersonnages[i];
        } else {
            return null;
        }
    }

    public Joueur getJoueur(int i){
        if(0<=i && i<=8){
            return listeJoueurs[i];
        } else {
            return null;
        }
    }

    public void ajouterPersonnage(){
        
    }

    public void ajouterJoueur(){
        
    }

}