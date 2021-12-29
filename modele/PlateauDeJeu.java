package modele;

public class PlateauDeJeu {
    protected Personnage[] listePersonnages;
    private Joueur[] listeJoueurs;
    private Pioche pioche;
    private int nombrePersonnages;
    private int nombreJoueurs;

    public PlateauDeJeu() {
        this.listePersonnages = new Personnage[9];
        this.listeJoueurs = new Joueur[9];
        this.nombreJoueurs = 0;
        this.nombrePersonnages = 0;
        this.pioche = new Pioche();
    }

    public int getNombrePersonnages(){
        return this.nombrePersonnages;
    }

    public int getNombreJoueurs(){
        return this.nombreJoueurs;
    }

    public Pioche getPioche(){
        return this.pioche;
    }

    public Personnage getPersonnage(int i){
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

    public void ajouterPersonnage(Personnage personnage){
        if(personnage != null && this.getNombrePersonnages() != this.listePersonnages.length){
            this.listePersonnages[this.getNombrePersonnages()] = personnage;
            personnage.setPlateau(this);
            this.nombrePersonnages += 1;
        }
    }

    public void ajouterJoueur(Joueur joueur){
        if(joueur != null && this.getNombreJoueurs() != this.listeJoueurs.length){
            this.listeJoueurs[this.getNombreJoueurs()] = joueur;
            this.nombreJoueurs += 1;
        }
    }

}