package modele;
import java.util.ArrayList;
import java.util.Random;

public class Joueur {
    private String nom;
    private int tresor;
    private int nbQuartiers;
    private boolean possedeCouronne;
    protected Quartier[] cite;
    private ArrayList < Quartier > main;
    protected Personnage monPersonnage = null;
	private boolean simule;
	private boolean premier;
	private boolean rangPlusEleve;

    public Joueur(String nom) { //Constructeur
        this.nom = nom;
        this.tresor = 0;
        this.nbQuartiers = 0;
        this.possedeCouronne = false;
        this.cite = new Quartier[8];
        this.main = new ArrayList < Quartier > ();
		this.simule = false;
	    this.premier = false;
	    this.rangPlusEleve = false;
    }

    public Personnage getPersonnage() { //Retourne le personnage choisi par le joueur
        return this.monPersonnage;
    }

    public String getNom() { //Retourne le nom du joueur
        return this.nom;
    }

    public int nbPieces() { //Retourne le trésor du joueur
        return this.tresor;
    }

    public int nbQuartiersDansCite() { //Retourne le nombre de quartiers dans la cité du joueur
        return this.nbQuartiers;
    }

    public Quartier[] getCite() { //Retourne la cité du joueur
        return this.cite;
    }

    public ArrayList < Quartier > getMain() { //Retourne la main du joueur
        return this.main;
    }

	public boolean isRangPlusEleve() {
		return rangPlusEleve;
	}
	
	public void setRangPlusEleve() {
		this.rangPlusEleve = true;
	}
	
	public boolean isSimule() {
		return simule;
	}
	
	public void setSimule(boolean simule) {
		this.simule = simule;
	}
	
	public boolean isPremier() {
		return premier;
	}
	
	public void setPremier(boolean premier) {
		this.premier = premier;
	}

    public int nbQuartiersDansMain() { //Retourne du nombre de quartiers dans la main du joueur
        return this.main.size();
    }

    public boolean getPossedeCouronne() { //Retourne si le joueur posséde la couronne
        return this.possedeCouronne;
    }

    public void setPossedeCouronne(boolean b) { //Défini si le joueur posséde la couronne
        this.possedeCouronne = b;
    }

    public void ajouterPieces(int piece) { //Ajouter des pièces au trésor du joueur
        if (piece > 0) {
            tresor = tresor + piece;
        } else {
            System.out.println("Le nombre de pieces ne peut pas etre negatif");
        }
    }

    public void retirerPieces(int piece) { //Retirer des pièces au trésor du joueur
        if (piece <= tresor) {
            if (piece > 0) {
                tresor = tresor - piece;
            } else {
                System.out.println("Le nombre de pieces ne peut pas etre negatif");
            }
        } else {
            System.out.println("Vous ne pouvez pas retirer plus de pièces que le trésor");
        }
    }

    public void ajouterQuartierDansCite(Quartier quartier) { //Ajouter un quartier dans la cité du joueur
        if (nbQuartiers < 8) {
            cite[nbQuartiers] = quartier;
            nbQuartiers++;
        }
    }

    public boolean quartierPresentDansCite(String quartier) { //Retourne si un quartier est présent dans la cité du joueur
        for (int i = 0; i < nbQuartiers; i++) {
            if (cite[i] != null && cite[i].getNom().equals(quartier)) {
                return true;
            }
        }
        return false;
    }

    public Quartier retirerQuartierDansCite(String quartier) { //Retirer un quartier de la cité du joueur
        Quartier quartSupprime = null;
        for (int i = 1; i < this.cite.length; i++) {
            if (this.cite[i - 1] != null && this.cite[i - 1].getNom().equals(quartier)) {
                quartSupprime = this.cite[i - 1];
                this.cite[i - 1] = null;
            }
            if (this.cite[i - 1] == null) {
                this.cite[i - 1] = this.cite[i];
                this.cite[i] = null;
            }
        }
        this.nbQuartiers--;
        return quartSupprime;
    }

    public void ajouterQuartierDansMain(Quartier quartier) { //Ajouter un quartier dans la main du joueur
        main.add(quartier);
    }

    public Quartier retirerQuartierDansMain() { //Retirer un quartier de la main du joueur
        Random generateur = new Random();
        int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
        Quartier retour = main.get(numeroHasard);
        main.remove(numeroHasard);
        return retour;
    }

    public void reinitialiser() { //Reinitialiser les données du joueur 
        this.tresor = 0;
        this.main = new ArrayList < Quartier > ();
        this.cite = new Quartier[8];
        this.nbQuartiers = 0;
    }
}


	