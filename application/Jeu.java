package application;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import controleur.Interaction;
import modele.Joueur;
import modele.Personnage;
import modele.PlateauDeJeu;
import modele.Quartier;

public class Jeu {
	private PlateauDeJeu plateauDeJeu;
	private int numeroConfiguration;
	private Random generateur;
	private int joueurPersoRangMax = 0;
	
	/**
	 *
	 */
	public Jeu() {
		this.plateauDeJeu = new PlateauDeJeu();
		this.numeroConfiguration = 0;
		this.generateur = new Random();
	}
	
	public void jouer() {
		int response = 0;
		afficherBienvenue();
		do {
			afficherMenu();
			response = Interaction.lireUnEntier(1, 4);
			if (response == 1) {
				jouerPartie();
			}else if(response == 2) {
				afficherLesRegles();
			}
		}while(response != 3);
		quitter();
	}
	
	private void afficherBienvenue() {
		System.out.println("Bienvenue dans le jeu Citadelles");
	}
	
	private void afficherMenu() {
		System.out.println("Veuillez entrer le chiffre correspondant à  votre choix!!!");
		System.out.println("	1 : 	Jouer une nouvelle partie. ");
		System.out.println("	2 : 	Afficher les règles du jeu. ");
		System.out.println("	3 : 	Quitter l'application. ");
	}
	
	private void afficherLesRegles() {
		
	}
	
	private void quitter() {
		System.out.println("Au revoir et à  très bientôt !!!");
		System.exit(0);
	}
	
	private void jouerPartie() {
		initialisation();
		do {
			tourDeJeu();
			gestionCouronne();
			reinitialisationPersonnages();
		} while (!partieFinie());
		calculDesPoints();
	}
	
	private void initialisation() {
		//ici on initialise le plateau avec la configuration de base du projet
		this.plateauDeJeu = Configuration.configurationDeBase(Configuration.nouvellePioche());
		//on ajoute deux pièces à  tous les joueurs
		for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++) {
			this.plateauDeJeu.getJoueur(i).ajouterPieces(2);
			for (int j = 0; j < 4; j++) {
				//on ajoute à  la main de chaque joueur un quatier qu'il a pioché dans la pioche
				this.plateauDeJeu.getJoueur(i).ajouterQuartierDansMain(this.plateauDeJeu.getPioche().piocher());
			}
		}
		//ici on attribue la couronne à  un jooueur aléatoire
		this.plateauDeJeu.getJoueur(this.generateur.nextInt(this.plateauDeJeu.getNombreJoueurs())).setPossedeCouronne(true);
	}
	
	private void gestionCouronne() {
		//on détermine le joueur qui possède la couronne et on lui enlève la couronne
		for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++) {
			if(this.plateauDeJeu.getJoueur(i).getPossedeCouronne())
				this.plateauDeJeu.getJoueur(i).setPossedeCouronne(false);
		}
		//on attribue la couronne au joueur qui possède le peesonnage Roi
		for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++) {
			if(this.plateauDeJeu.getJoueur(i).getPersonnage().getNom().equals("Roi")) {
				System.out.println(this.plateauDeJeu.getJoueur(i).getNom()+" possède la couronne");
				this.plateauDeJeu.getJoueur(i).setPossedeCouronne(true);
			}
		}
	}
	
	private void reinitialisationPersonnages() {
		//on réinitialise tous les personnage
		for (int i = 0; i < this.plateauDeJeu.getNombrePersonnages(); i++) {
			if(this.plateauDeJeu.getPersonnage(i).getJoueur() != null)
				this.plateauDeJeu.getPersonnage(i).reinitialiser();
		}
	}
	
	private boolean partieFinie() {
		//on parcoure tous les joueurs pour vérifier si un possède une cité complète
		for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++) {
			if (this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7) {
				System.out.println("La partie est terminé : "+this.plateauDeJeu.getJoueur(i).getNom()+" possède une cité complète");
				//on affecte l'attribut isPremier de joueur à  true pour dire qu'il est le premier à  avoir une cité complète
				this.plateauDeJeu.getJoueur(i).setPremier(true);
				this.plateauDeJeu.getJoueur(joueurPersoRangMax).setRangPlusEleve();
				return true;
			}
		}
		return false;
	}
	
	private void tourDeJeu() {
		//on effectue le choix des personnages
		choixPersonnages();
		for (int personnage = 0; personnage <this.plateauDeJeu.getNombrePersonnages(); personnage++) {
			System.out.println("Le jeu appelle " + this.plateauDeJeu.getPersonnage(personnage).getNom());
			//on vérifie si le personnage est associé à un joueur
			if (this.plateauDeJeu.getPersonnage(personnage).getJoueur() != null) {
				//on vérifie si le joueur associé au personnage n'est pas simulé par l'ordinateur en testant son attribut simule de la classe joueur
				if(!this.plateauDeJeu.getPersonnage(personnage).getJoueur().isSimule()) {
					//on affiche la main du joueur
					System.out.println("Voici votre Main :");
					for (int i = 0; i < this.plateauDeJeu.getPersonnage(personnage).getJoueur().nbQuartiersDansMain(); i++) {
						System.out.println((i + 1) + " " + this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain().get(i).getNom() + " - type : "
								+  this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain().get(i).getType() + " - pièces : "
								+  this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain().get(i).getCout());
					}
				}
				//on affiche la cité du joueur
				System.out.println("Voici votre Cité :");
				if(this.plateauDeJeu.getPersonnage(personnage).getJoueur().nbQuartiersDansCite()  > 0) {
					for (int i = 0; i < this.plateauDeJeu.getPersonnage(personnage).getJoueur().nbQuartiersDansCite(); i++) {
						System.out.println((i + 1) + " " +  this.plateauDeJeu.getPersonnage(personnage).getJoueur().getCite()[i].getNom() + " - type : "
								+ this.plateauDeJeu.getPersonnage(personnage).getJoueur().getCite()[i].getType() + " - pièces : "
								+ this.plateauDeJeu.getPersonnage(personnage).getJoueur().getCite()[i].getCout());
					}
				}else{
					System.out.println("Votre cité est vide !");
				}
				System.out.println("Vous possédez "+this.plateauDeJeu.getPersonnage(personnage).getJoueur().nbPieces()+" pièces");
				//on vérifie si le personnage n'est pas assassiné
				if (!this.plateauDeJeu.getPersonnage(personnage).getAssassine()) {
					//on vérifie si le personnage n'est pas volé
					if (this.plateauDeJeu.getPersonnage(personnage).getVole()) {
						System.out.println("Le " + this.plateauDeJeu.getPersonnage(personnage).getNom() + " est volé !");
						System.out.println("Le " + this.plateauDeJeu.getPersonnage(personnage).getNom() + " donne toutes ses pièces au voleur !");
						int nbPieces = this.plateauDeJeu.getPersonnage(personnage).getJoueur().nbPieces();
						//si le personnage est volé il donne toutes ses pièces au voleur
						this.plateauDeJeu.getPersonnage(personnage).getJoueur().retirerPieces(nbPieces);
						for (int i = 0; i < this.plateauDeJeu.getNombrePersonnages(); i++) {
							if (this.plateauDeJeu.getJoueur(i).getNom().equals("Voleur"))
								this.plateauDeJeu.getJoueur(i).ajouterPieces(nbPieces);
						}
					} else {
						//le personnage percoit les ressources (cartes ou pièces d'or)
						percevoirRessource(personnage);
						this.plateauDeJeu.getPersonnage(personnage).percevoirRessourcesSpecifiques();
						System.out.println("Voulez vous utiliser votre pouvoir ?");
						boolean res = false;
						//on teste si le personnage n'est pas simulé par l'ordinateur
						if (!this.plateauDeJeu.getPersonnage(personnage).getJoueur().isSimule())
							res = Interaction.lireOuiOuNon();
						else {
							//sinon on génère un nombre aléatoire qui correspond au choix de l'ordinateur
							res = this.generateur.nextInt(2) == 1;
						}
						if (res) {
							System.out.println("Le " + this.plateauDeJeu.getPersonnage(personnage).getNom() + " utilise son pouvoir !");
							if (!this.plateauDeJeu.getPersonnage(personnage).getJoueur().isSimule())
								this.plateauDeJeu.getPersonnage(personnage).utiliserPouvoir();
							else
								this.plateauDeJeu.getPersonnage(personnage).utiliserPouvoirAvatar();
						}
						System.out.println("Voulez vous construire ?");
						if (!this.plateauDeJeu.getPersonnage(personnage).getJoueur().isSimule())
							res = Interaction.lireOuiOuNon();
						else {
							res = this.generateur.nextInt(2) == 1;
						}
						if (res) {
							boolean peutConstruire = false;
							int carte = 0;
							do {
								System.out.println("Quel cartier voulez vous construire ?");
								int i = 1;
								//on affiche la main du joueur
								for (Quartier quartier : this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain()) {
									System.out.println((i) + " " + quartier.getNom() + " - type : " + quartier.getType()
											+ " - pièces : " + quartier.getCout());
									i++;
								}
								System.out.println("0 : Annuler la construction");
								if (!this.plateauDeJeu.getPersonnage(personnage).getJoueur().isSimule())
									carte = Interaction.lireUnEntier(0,
											this.plateauDeJeu.getPersonnage(personnage).getJoueur().nbQuartiersDansMain() + 1);
								else {
									carte = this.generateur
											.nextInt(this.plateauDeJeu.getPersonnage(personnage).getJoueur().nbQuartiersDansMain() + 1);
								}
								if (carte == 0)
									break;
								
								//on vérifie si le joueur peut construire la carte choisie
								if (this.plateauDeJeu.getPersonnage(personnage).getJoueur().nbPieces() >= this.plateauDeJeu.getPersonnage(personnage)
										.getJoueur().getMain().get(carte - 1).getCout()
										&& !this.plateauDeJeu.getPersonnage(personnage).getJoueur().quartierPresentDansCite(this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain().get(carte - 1).getNom()))
									peutConstruire = true;
								else
									System.out.println(
											"Vous ne pouvez pas construire ce quartier. Veuillez choisir un autre quartier !");
							} while (!peutConstruire);
							if (carte != 0) {
								//on constuit le quartier choisit
								System.out.println("Le " + this.plateauDeJeu.getPersonnage(personnage).getNom()  + " a construit le "+this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain().get(carte-1).getNom());
								this.plateauDeJeu.getPersonnage(personnage)
										.construire(this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain().get(carte - 1));
							}
						}
					}
				} else {
					System.out.println("Le " + this.plateauDeJeu.getPersonnage(personnage).getNom() + " est assasiné !");
				}
			} else {
				System.out.println("Aucun joueur ne possède le " + this.plateauDeJeu.getPersonnage(personnage).getNom());
			}
		}
		joueurPersoRangMax = this.plateauDeJeu.getJoueur(0).getPersonnage().getRang();
		//on détermine l'id du joueur qui possède le personnage de rang le plus élévé à la fin de la partie
		for (int j = 0; j < this.plateauDeJeu.getNombreJoueurs(); j++) {
			if(this.plateauDeJeu.getJoueur(j).getPersonnage().getRang() > joueurPersoRangMax)
				joueurPersoRangMax = j;
		}
	}
	
	private void choixPersonnages() {
		ArrayList<Joueur> joueurs = new ArrayList<>();
		Personnage persoFaceVisible1 = null;
		Personnage persoFaceVisible2 = null;
		Personnage persoFaceCache = null;
		int response = 0;
		for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++) {
			joueurs.add(this.plateauDeJeu.getJoueur(i));
		}
		do {
			persoFaceVisible1 = this.plateauDeJeu.getPersonnage(this.generateur.nextInt(this.plateauDeJeu.getNombrePersonnages()));
			persoFaceVisible2 = this.plateauDeJeu.getPersonnage(this.generateur.nextInt(this.plateauDeJeu.getNombrePersonnages()));
			persoFaceCache = this.plateauDeJeu.getPersonnage(this.generateur.nextInt(this.plateauDeJeu.getNombrePersonnages()));
			
		}while(persoFaceVisible1.getRang() == 4 || persoFaceVisible2.getRang() == 4 || persoFaceCache.getNom().equals(persoFaceVisible1.getNom()) || persoFaceCache.getNom().equals(persoFaceVisible2.getNom()) || persoFaceVisible1.getNom().equals(persoFaceVisible2.getNom()));
		int n = joueurs.size();
		for (int i = 0; i < n; i++) {
			if(joueurs.get(i).getPossedeCouronne()) {
				joueurs.add(0, joueurs.remove(i));
			}
		}
		do {
			System.out.println("Choix des personnages :");
			System.out.println("Le personnage \""+persoFaceVisible1.getNom()+"\" est écarté face visible");
			System.out.println("Le personnage \""+persoFaceVisible2.getNom()+"\" est écarté face visible");
			System.out.println("Un personnage est écarté face cachée");
			if(joueurs.get(0).getPossedeCouronne())
				System.out.println("Vous avez la couronne ! ");
			for (int i = 0; i < this.plateauDeJeu.getNombrePersonnages(); i++) {
				if(this.plateauDeJeu.getPersonnage(i).getJoueur() == null && !this.plateauDeJeu.getPersonnage(i).getNom().equals(persoFaceCache.getNom()) && !this.plateauDeJeu.getPersonnage(i).getNom().equals(persoFaceVisible1.getNom()) && !this.plateauDeJeu.getPersonnage(i).getNom().equals(persoFaceVisible2.getNom())) {
					System.out.println((i+1)+" "+this.plateauDeJeu.getPersonnage(i).getNom());
				}
			}
			if(!joueurs.get(0).isSimule()) {
				do {
					System.out.println("Quel personnage choisissez vous ?");
					response = Interaction.lireUnEntier(1, this.plateauDeJeu.getNombrePersonnages()+1);
				}while(this.plateauDeJeu.getPersonnage(response-1).getJoueur() != null || this.plateauDeJeu.getPersonnage(response-1).getNom().equals(persoFaceCache.getNom()) || this.plateauDeJeu.getPersonnage(response-1).getNom().equals(persoFaceVisible1.getNom()) || this.plateauDeJeu.getPersonnage(response-1).getNom().equals(persoFaceVisible2.getNom()));
				this.plateauDeJeu.getPersonnage(response-1).setJoueur(joueurs.get(0));
			}else {
				int perso = 0;
				do {
					perso = this.generateur.nextInt(this.plateauDeJeu.getNombrePersonnages());
				}while(this.plateauDeJeu.getPersonnage(perso).getJoueur() != null || this.plateauDeJeu.getPersonnage(perso).getNom().equals(persoFaceCache.getNom()) || this.plateauDeJeu.getPersonnage(perso).getNom().equals(persoFaceVisible1.getNom()) || this.plateauDeJeu.getPersonnage(perso).getNom().equals(persoFaceVisible2.getNom()));
				this.plateauDeJeu.getPersonnage(perso).setJoueur(joueurs.get(0));
			}
			joueurs.remove(0);
		}while(joueurs.size() > 0);
		
	}
	
	private void percevoirRessource(int personnage) 
	{
		System.out.println("Voulez vous percevoir des cartes ou des pièces d'or ?");
		System.out.println("1 : deux cartes");
		System.out.println("2 : deux pièces d'or");
		int response = 0;
		if (!this.plateauDeJeu.getPersonnage(personnage).getJoueur().isSimule())
			response = Interaction.lireUnEntier(1, 3);
		else {
			do {
				response = this.generateur.nextInt(3);
			} while (response == 0);
		}
		if (response == 1) {
		
			System.out.println("Vous avez pioché deux cartes");

			Quartier[] quartiers = new Quartier[2];
			for (int i = 0; i < quartiers.length; i++) 
			{
				quartiers[i] = this.plateauDeJeu.getPioche().piocher();
			}
			System.out.println("Voici les cartes que vous avez pioché : ");
			for (int i = 0; i < quartiers.length; i++) 
			{
				System.out.println((i + 1) + " " + quartiers[i].getNom() + " - type : "
				+ quartiers[i].getType() + " - pièces : " + quartiers[i].getCout());
			}
			System.out.println("Quelle carte voulez vous gardez ? : ");
			int carte = 0;
			if (!plateauDeJeu.getPersonnage(personnage).getJoueur().isSimule())
				carte = Interaction.lireUnEntier(1, 3);
			else {
				do {
					carte = this.generateur.nextInt(3);
				} while (carte == 0);
			}
			plateauDeJeu.getPersonnage(personnage).ajouterQuartier(quartiers[carte-1]);
			for (int i = 0; i < quartiers.length; i++) {
				if (!quartiers[carte-1].getNom().equals(quartiers[i].getNom())) {
					plateauDeJeu.getPioche().ajouter(quartiers[i]);
					break;
				}
			}
		} else {
			System.out.println("Vous avez perçu deux pièces d'or");
			this.plateauDeJeu.getPersonnage(personnage).ajouterPieces();
		}
	}
	
	private void calculDesPoints() {
		int[][] scores = new int[this.plateauDeJeu.getNombreJoueurs()][1];
		int score = 0;
		for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++) {
			ArrayList<String> quartiers = new ArrayList<>();
			for (int j = 0; j < this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++) {
				score += this.plateauDeJeu.getJoueur(i).getCite()[j].getCout();
				if(!quartiers.contains(this.plateauDeJeu.getJoueur(i).getCite()[j].getType()))
					quartiers.add(this.plateauDeJeu.getJoueur(i).getCite()[j].getType());
			}
			if(quartiers.size() >= 5)
				score += 3;
			if(this.plateauDeJeu.getJoueur(i).isPremier())
				score += 4;
			else {
				if(this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7)
					score += 2;
			}
			scores[i][0] = score;
			System.out.println( this.plateauDeJeu.getJoueur(i).getNom()+" a un total de "+score+" points !");
		}
		int joueurMax = 0;
		int scoreMax = scores[0][0];
		for (int i = 0; i < scores.length; i++) {
			if(scores[i][0] >= scoreMax) {
				if(scores[i][0]  == scoreMax) {
					if(this.plateauDeJeu.getJoueur(i).isRangPlusEleve()) {
						scoreMax = scores[i][0];
						joueurMax = i;
					}
				}else {
					scoreMax = scores[i][0];
					joueurMax = i;
				}
			}
		}
		System.out.println(this.plateauDeJeu.getJoueur(joueurMax).getNom()+" a remporté la partie avec un total de "+scoreMax+" points.");
		System.out.println("Félicitation "+this.plateauDeJeu.getJoueur(joueurMax).getNom()+"!!!");
	}
	
}