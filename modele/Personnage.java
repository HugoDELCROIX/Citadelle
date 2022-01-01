package modele;

public abstract class Personnage {
    protected String nom;
    protected int rang;
    protected String caracteristiques;
    protected Joueur joueur;
    protected boolean assassine;
    protected boolean vole;
    protected PlateauDeJeu plateau;

    public Personnage(String nom, int rang, String caracteristiques) {
        this.nom = nom;
        this.rang = rang;
        this.caracteristiques = caracteristiques;
        joueur = null;
        assassine = false;
        vole = false;
    }

    public String getNom() {
        return this.nom;
    }

    public int getRang() {
        return this.rang;
    }

    public String getCaracteristiques() {
        return this.caracteristiques;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public boolean getAssassine() {
        return this.assassine;
    }

    public boolean getVole() {
        return this.vole;
    }

    public PlateauDeJeu getPlateau() {
        return this.plateau;
    }

    public void setPlateau(PlateauDeJeu plateau) {
        this.plateau = plateau;
    }

    public void setJoueur(Joueur j) {
        this.joueur=j;
        this.joueur.monPersonnage = this;
    }

    public void setVole() {
        this.vole = true;
    }

    public void setAssassine() {
        this.assassine = true;
    }

    public void ajouterPieces() {
        if (joueur != null && assassine == false) {
            joueur.ajouterPieces(2);
        }
    }

    public void ajouterQuartier(Quartier nouveau) {
        if (joueur != null && assassine == false) {
            joueur.ajouterQuartierDansMain(nouveau);
        }
    }

    public void construire(Quartier nouveau) {
        if (joueur != null && assassine == false) {
            joueur.ajouterQuartierDansCite(nouveau);
        }
    }

    public void percevoirRessourcesSpecifiques() {
        if (joueur != null && assassine == false) {
            System.out.println("aucune ressource spécifique ");
        }
    }

    public abstract void utiliserPouvoir();

    public void reinitialiser() {
        if (this.joueur != null) {
            this.joueur.monPersonnage = null;
        }
        this.joueur = null;
        this.vole = false;
        this.assassine = false;
        
    }

    public abstract void utiliserPouvoirAvatar();
}