package modele;

public abstract class Personnage {
    protected String nom;
    protected int rang;
    protected String caracteristiques;
    protected Joueur joueur;
    protected boolean assassine;
    protected boolean vole;
    protected PlateauDeJeu plateau;

    public Personnage(String nom, int rang, String caracteristiques) { //Constructeur
        this.nom = nom;
        this.rang = rang;
        this.caracteristiques = caracteristiques;
        joueur = null;
        assassine = false;
        vole = false;
    }

    public String getNom() { //Retourne le nom du personnage
        return this.nom;
    }

    public int getRang() { //retourne le rang du personnage
        return this.rang;
    }

    public String getCaracteristiques() { //Retourne les caractéristiques du personnage
        return this.caracteristiques;
    }

    public Joueur getJoueur() { //Retourne le joueur associé au personnage
        return this.joueur;
    }

    public boolean getAssassine() { //Retourne si le personnage est assassiné
        return this.assassine;
    }

    public boolean getVole() { //Retourne si le personnage est volé
        return this.vole;
    }

    public PlateauDeJeu getPlateau() { //Retourne le plateau de Jeu
        return this.plateau;
    }

    public void setPlateau(PlateauDeJeu plateau) { //Défini le plateau de jeu
        this.plateau = plateau;
    }

    public void setJoueur(Joueur j) { //Défini le joueur associé au personnage
        this.joueur = j;
        this.joueur.monPersonnage = this;
    }

    public void setVole() { //Défini que le personnage est volé
        this.vole = true;
    }

    public void setAssassine() { //Défini que le personnage est assassiné
        this.assassine = true;
    }

    public void ajouterPieces() { //Ajouter 2 pièces au joueur auquel le personnage est associé
        if (joueur != null && assassine == false) {
            joueur.ajouterPieces(2);
        }
    }

    public void ajouterQuartier(Quartier nouveau) { //Ajouter un quartier dans la main du joueur associé au personnage
        if (joueur != null && assassine == false) {
            joueur.ajouterQuartierDansMain(nouveau);
        }
    }

    public void construire(Quartier nouveau) { //Construire un quartier dans la cité du joueur associé au personnage
        if (joueur != null && assassine == false) {
            joueur.ajouterQuartierDansCite(nouveau);
        }
    }

    public void percevoirRessourcesSpecifiques() { //Perception des ressources spécifiques du personnage
        if (joueur != null && assassine == false) {
            System.out.println("aucune ressource spécifique ");
        }
    }

    public abstract void utiliserPouvoir(); //Utiliser le pouvoir spécifique d'un personnage

    public void reinitialiser() { //Réinitialiser les données du personnage
        if (this.joueur != null) {
            this.joueur.monPersonnage = null;
        }
        this.joueur = null;
        this.vole = false;
        this.assassine = false;

    }

    public abstract void utiliserPouvoirAvatar(); //Utilisation du pouvoir spécifique d'un personnage aléatoirement
}