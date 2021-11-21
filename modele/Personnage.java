package modele;

public abstract class Personnage{
    private String nom;
    private int rang;
    private String caracteristiques;
    private Joueur joueur;
    private boolean assassine;
    private boolean vole;

    public Personnage(String nom,int rang,String caracteristiques){
        joueur = null;
        assassine = false;
        vole = false;
    }

    public String getNom(){
        return this.nom;
    }

    public int getRang(){
        return this.rang;
    }

    public String getCaracteristiques(){
        return this.caracteristiques;
    }

    public Joueur getJoueur(){
        return this.joueur;
    }

    public boolean getAssassine(){
        return this.assassine;
    }

    public boolean getVole(){
        return this.vole;
    }

    public void setJoueur(Joueur j){
        this.joueur=j;
    }

    public void setVole(){
        this.vole=true;
    }

    public void setAssassine(){
        this.assassine=true;
    }

    public void ajouterPieces(){
        if(joueur!=null && assassine == false){
            joueur.ajouterPieces(2);
        }
    }

    public void ajouterQuartier(Quartier nouveau){
        if(joueur!=null && assassine == false){
            joueur.ajouterQuartierDansMain(nouveau);
        }
    }

    public void construire(Quartier nouveau){
        if(joueur!=null && assassine == false){
            joueur.ajouterQuartierDansCite(nouveau);
        }
    }

    public void percevoirRessourcesSpecifiques(){
        if(joueur!=null && assassine == false){
            System.out.println("aucune ressource spécifique ");
        }
    }

    public abstract void utiliserPouvoir();
    
    public void reinitialiser() {
        this.joueur = null;
        this.vole = false;
        this.assassine = false;
    }
}