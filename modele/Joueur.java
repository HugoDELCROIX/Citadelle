package modele;


import java.util.ArrayList;
import java.util.Random;

public class Joueur {
	private String nom;
	private int tresor;
    private Quartier cite[];
    private ArrayList<Quartier> main = new ArrayList<>();
	private int nbQuartier;
	private boolean possedeCouronne;
	protected Personnage monPersonnage;
	private boolean simule;
	private boolean premier;
	private boolean rangPlusEleve;

	/**
	 * @param nom
	 */
	public Joueur(String nom) {
		// TODO Auto-generated constructor stub
		this.nom = nom;
		this.tresor = 0;
		this.nbQuartier = 0;
		this.possedeCouronne = false;
		this.cite = new Quartier[8];
	    this.main = new ArrayList<Quartier>();
	    this.monPersonnage = null;
	    this.simule = false;
	    this.premier = false;
	    this.rangPlusEleve = false;
	    
	}
	
	public String getNom() {
		return nom;
	}
	
	/**
	 * @return the monPersonnage
	 */
	public Personnage getPersonnage() {
		return monPersonnage;
	}

	public int nbPieces() {
		return tresor;
	}
	
	public int nbQuartiersDansCite() {
		return nbQuartier;
	}
	
	public Quartier[] getCite() {

		return cite;
	}
	
	public ArrayList<Quartier> getMain() {
		return main;
	}
	
	public int nbQuartiersDansMain() {
			
		return main.size();

	}
	
	/**
	 * @return the rangPlusEleve
	 */
	public boolean isRangPlusEleve() {
		return rangPlusEleve;
	}

	/**
	 * @param rangPlusEleve the rangPlusEleve to set
	 */
	public void setRangPlusEleve() {
		this.rangPlusEleve = true;
	}

	/**
	 * @return the simule
	 */
	public boolean isSimule() {
		return simule;
	}

	/**
	 * @param simule the simule to set
	 */
	public void setSimule(boolean simule) {
		this.simule = simule;
	}

	/**
	 * @return the premier
	 */
	public boolean isPremier() {
		return premier;
	}

	/**
	 * @param premier the premier to set
	 */
	public void setPremier(boolean premier) {
		this.premier = premier;
	}

	public Boolean getPossedeCouronne() {
		return possedeCouronne;
	}
	
	public void setPossedeCouronne(boolean b) {
		this.possedeCouronne = b;
	}
	
	public void ajouterPieces(int nbPieces ) {
		if(nbPieces >= 0) {
			tresor = tresor + nbPieces;
		}
	}
	
	public void retirerPieces(int nbPieces) {
		
		if (nbPieces > 0) {
			
		if ( (tresor - nbPieces) >= 0) {
			
			tresor = tresor - nbPieces;
		}
	}
		
	}
	
	public void ajouterQuartierDansCite(Quartier quartier) {
		
		if (nbQuartier < 8) {
			this.cite[nbQuartier] = quartier;
			nbQuartier++;
		}
		
	}
	
	public boolean quartierPresentDansCite(String nom) {
		for (int i=0; i< nbQuartier - 1; i++) {
			
			if (cite[i].getNom().equals(nom)) {
				
			    return true;
				
			}
		}
		return false;
		
	}
	
	public Quartier retirerQuartierDansCite(String nom) {
		
		Quartier quartSupprime = null;
		
		for (int i=0; i< nbQuartier; i++) {
			if (cite[i].getNom().equals(nom)) {
				quartSupprime = cite[i];
				cite[i] = null;
				nbQuartier--;
			}
		}
		return quartSupprime;
		
	}
	
	public void ajouterQuartierDansMain(Quartier quartier) {
		
		main.add(quartier);
		
	}
	
	public Quartier retirerQuartierDansMain() {
		
		if (main.size() != 0) {
			
			Random generateur = new Random();
			int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
			return main.remove(numeroHasard);
			
		}
		return null;

	}
	
	public void reinitialiser() {
		this.tresor = 0;
		this.cite = new Quartier[8];
	    this.main = new ArrayList<Quartier>();
	    this.nbQuartier = 0;
	}	

}
