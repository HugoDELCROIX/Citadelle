package modele;

public class PlateauDeJeu {
    protected Personnage[] listePersonnages;
    private Joueur[] listeJoueurs;
    private Pioche pioche;
    private int nombrePersonnages;
    private int nombreJoueurs;

    public PlateauDeJeu() { //Constructeur
        this.listePersonnages = new Personnage[9];
        this.listeJoueurs = new Joueur[9];
        this.nombreJoueurs = 0;
        this.nombrePersonnages = 0;
        this.pioche = new Pioche();
    }

    public int getNombrePersonnages() { //Retourne le nombre de personnages sur le plateau
        return this.nombrePersonnages;
    }

    public int getNombreJoueurs() { //Retourne le nombre de joueurs sur le plateau
        return this.nombreJoueurs;
    }

    public Pioche getPioche() { //Retourne la pioche 
        return this.pioche;
    }

    public void setPioche(Pioche pioche){
        this.pioche = pioche;
    }

    public Personnage getPersonnage(int i) { //Retourne un personnage parmis la liste des personnages
        if (0 <= i && i <= 8) {
            return listePersonnages[i];
        } else {
            return null;
        }
    }

    public Joueur getJoueur(int i) { //Retourne un personnage parmis la liste des joueurs
        if (0 <= i && i <= 8) {
            return listeJoueurs[i];
        } else {
            return null;
        }
    }

    public void ajouterPersonnage(Personnage personnage) { //Ajouter un nouveau personnage dans le plateau de jeu
        if (personnage != null && this.getNombrePersonnages() != this.listePersonnages.length) {
            this.listePersonnages[this.getNombrePersonnages()] = personnage;
            personnage.setPlateau(this);
            this.nombrePersonnages += 1;
        }
    }

    public void ajouterJoueur(Joueur joueur) { //Ajouter un nouveau joueur dans le plateau de jeu
        if (joueur != null && this.getNombreJoueurs() != this.listeJoueurs.length) {
            this.listeJoueurs[this.getNombreJoueurs()] = joueur;
            this.nombreJoueurs += 1;
        }
    }

}