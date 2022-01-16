package application;

import java.util.ArrayList;
import java.util.Random;
import controleur.Interaction;
import modele.Joueur;
import modele.Personnage;
import modele.PlateauDeJeu;
import modele.Quartier;

public class Jeu {

    private int joueurPersoRangMax = 0;
    private PlateauDeJeu plateauDeJeu;
    private Random generateur;

    public Jeu() {
        this.plateauDeJeu = new PlateauDeJeu();
        this.generateur = new Random();
    }

    public void jouer() {
        int choix = 0;
        afficherBienvenue();
        do {
            afficherMenu();
            choix = Interaction.lireUnEntier(1, 4);
            if (choix == 1) {
                jouerPartie();
            } else if (choix == 2) {
                afficherLesRegles();
            }
        } while (choix != 3);
        quitterPartie();
    }

    private void afficherLesRegles() {
        System.out.println("Regles");
    }

    private void jouerPartie() {
        initialisation();
        do {
            tourDeJeu();
            gestionCouronne();
            reinitialisationPersonnages();
        } while (partieFinie() == false);
        calculDesPoints();
    }

    private void initialisation() {
        //Initialisation du plateau de jeu
        this.plateauDeJeu = Configuration.configurationDeBase(Configuration.nouvellePioche());
        //On ajoute 2 pièces au trésor de tous les joueurs
        for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++) {
            Joueur joueur = this.plateauDeJeu.getJoueur(i);
            joueur.ajouterPieces(2);
            //On ajoute 4 quartiers au trésor de tous les joueurs
            for (int j = 0; j < 4; j++) {
                joueur.ajouterQuartierDansMain(this.plateauDeJeu.getPioche().piocher());
            }
            //On attribut à chaque joueur s'il est simulé ou pas
            if (joueur.getNom().contains("bot")) {
                joueur.setSimule(true);
            }
        }
        //On attribut aléatoirement la couronne à un joueur
        this.plateauDeJeu.getJoueur(this.generateur.nextInt(this.plateauDeJeu.getNombreJoueurs())).setPossedeCouronne(true);
    }

    private void gestionCouronne() {
        //on détermine le joueur qui possède la couronne et on lui enlève la couronne
        for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++) {
            if (this.plateauDeJeu.getJoueur(i).getPossedeCouronne())
                this.plateauDeJeu.getJoueur(i).setPossedeCouronne(false);
        }
        //on attribue la couronne au joueur qui possède le peesonnage Roi
        for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++) {
            if (this.plateauDeJeu.getJoueur(i).getPersonnage().getNom().equals("Roi")) {
                System.out.println("\t\n" + this.plateauDeJeu.getJoueur(i).getNom() + " possède la couronne\n");
                this.plateauDeJeu.getJoueur(i).setPossedeCouronne(true);
            }
        }
    }

    private void reinitialisationPersonnages() {
        //on réinitialise tous les personnage
        for (int i = 0; i < this.plateauDeJeu.getNombrePersonnages(); i++) {
            if (this.plateauDeJeu.getPersonnage(i).getJoueur() != null)
                this.plateauDeJeu.getPersonnage(i).reinitialiser();
        }
    }

    private boolean partieFinie() {
        //on parcoure tous les joueurs pour vérifier si un possède une cité complète
        for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++) {
            if (this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7) {
                System.out.println("\t\nLa partie est terminé : " + this.plateauDeJeu.getJoueur(i).getNom() + " possède une cité complète\n");
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
        for (int personnage = 0; personnage < this.plateauDeJeu.getNombrePersonnages(); personnage++) {
            System.out.println("----------------------------------------------\n" + "Le jeu appelle " + this.plateauDeJeu.getPersonnage(personnage).getNom() + "\t");
            //on vérifie si le personnage est associé à un joueur
            if (this.plateauDeJeu.getPersonnage(personnage).getJoueur() != null) {
                //on vérifie si le joueur associé au personnage n'est pas simulé par l'ordinateur en testant son attribut simule de la classe joueur
                if (!this.plateauDeJeu.getPersonnage(personnage).getJoueur().isSimule()) {
                    //on affiche la main du joueur
                    System.out.println("\n\tVoici votre Main :\n");
                    for (int i = 0; i < this.plateauDeJeu.getPersonnage(personnage).getJoueur().nbQuartiersDansMain(); i++) {
                        if (this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain().get(i) != null) {
                            System.out.println((i + 1) + " : " + this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain().get(i).getNom() + " - type : " +
                                this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain().get(i).getType() + " - pièces : " +
                                this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain().get(i).getCout());
                        }
                    }
                }
                //on affiche la cité du joueur
                System.out.println("\n\tVoici votre Cité :\n");
                if (this.plateauDeJeu.getPersonnage(personnage).getJoueur().nbQuartiersDansCite() > 0) {
                    for (int i = 0; i < this.plateauDeJeu.getPersonnage(personnage).getJoueur().nbQuartiersDansCite(); i++) {
                        System.out.println((i + 1) + " : " + this.plateauDeJeu.getPersonnage(personnage).getJoueur().getCite()[i].getNom() + " - type : " +
                            this.plateauDeJeu.getPersonnage(personnage).getJoueur().getCite()[i].getType() + " - pièces : " +
                            this.plateauDeJeu.getPersonnage(personnage).getJoueur().getCite()[i].getCout());
                    }
                } else {
                    System.out.println("\nVotre cité est vide !");
                }
                System.out.println("\tVous possédez " + this.plateauDeJeu.getPersonnage(personnage).getJoueur().nbPieces() + " pièces\n");
                //on vérifie si le personnage n'est pas assassiné
                if (!this.plateauDeJeu.getPersonnage(personnage).getAssassine()) {
                    //on vérifie si le personnage n'est pas volé
                    if (this.plateauDeJeu.getPersonnage(personnage).getVole()) {
                        System.out.println("\tLe " + this.plateauDeJeu.getPersonnage(personnage).getNom() + " est volé !\n");
                        System.out.println("\tLe " + this.plateauDeJeu.getPersonnage(personnage).getNom() + " donne toutes ses pièces au voleur !\n");
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
                        System.out.println("\n\tVoulez vous utiliser votre pouvoir ? (oui/non)");
                        boolean res = false;
                        //on teste si le personnage n'est pas simulé par l'ordinateur
                        if (!this.plateauDeJeu.getPersonnage(personnage).getJoueur().isSimule())
                            res = Interaction.lireOuiOuNon();
                        else {
                            //sinon on génère un nombre aléatoire qui correspond au choix de l'ordinateur
                            res = this.generateur.nextInt(2) == 1;
                        }
                        if (res) {
                            System.out.println("\n\tLe " + this.plateauDeJeu.getPersonnage(personnage).getNom() + " utilise son pouvoir !\n");
                            if (!this.plateauDeJeu.getPersonnage(personnage).getJoueur().isSimule())
                                this.plateauDeJeu.getPersonnage(personnage).utiliserPouvoir();
                            else
                                this.plateauDeJeu.getPersonnage(personnage).utiliserPouvoirAvatar();
                        }
                        System.out.println("\n\tVoulez vous construire ? (oui/non)");
                        if (!this.plateauDeJeu.getPersonnage(personnage).getJoueur().isSimule())
                            res = Interaction.lireOuiOuNon();
                        else {
                            res = this.generateur.nextInt(2) == 1;
                        }
                        if (res) {
                            boolean peutConstruire = false;
                            int carte = 0;
                            do {
                                System.out.println("\tQuel quartier voulez vous construire ?\n");
                                int i = 1;
                                //on affiche la main du joueur
                                for (Quartier quartier: this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain()) {
                                    System.out.println((i) + " : " + quartier.getNom() + " - type : " + quartier.getType() +
                                        " - pièces : " + quartier.getCout());
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
                                    .getJoueur().getMain().get(carte - 1).getCout() &&
                                    !this.plateauDeJeu.getPersonnage(personnage).getJoueur().quartierPresentDansCite(this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain().get(carte - 1).getNom()))
                                    peutConstruire = true;
                                else
                                    System.out.println(
                                        "\tVous ne pouvez pas construire ce quartier. \nVeuillez choisir un autre quartier !\n");
                            } while (!peutConstruire);
                            if (carte != 0) {
                                //on constuit le quartier choisit
                                System.out.println("\t" + this.plateauDeJeu.getPersonnage(personnage).getNom() + " a construit le " + this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain().get(carte - 1).getNom() + "\n");
                                this.plateauDeJeu.getPersonnage(personnage)
                                    .construire(this.plateauDeJeu.getPersonnage(personnage).getJoueur().getMain().get(carte - 1));
                            }
                        }
                    }
                } else {
                    System.out.println("\tLe " + this.plateauDeJeu.getPersonnage(personnage).getNom() + " est assasiné !\n");
                }
            } else {
                System.out.println("\tAucun joueur ne possède le " + this.plateauDeJeu.getPersonnage(personnage).getNom() + "\n");
            }
        }
        joueurPersoRangMax = this.plateauDeJeu.getJoueur(0).getPersonnage().getRang();
        //on détermine l'id du joueur qui possède le personnage de rang le plus élévé à la fin de la partie
        for (int j = 0; j < this.plateauDeJeu.getNombreJoueurs(); j++) {
            if (this.plateauDeJeu.getJoueur(j).getPersonnage().getRang() > joueurPersoRangMax)
                joueurPersoRangMax = j;
        }
        System.out.println("\n\tFin du tour de jeu\n");
    }

    private void choixPersonnages() {
        System.out.println("\tChoix des personnages :\n");

        int pFaceVisible1 = 0;
        int pFaceVisible2 = 0;
        int pFaceCachee = 0;

        do {
            pFaceVisible1 = generateur.nextInt(this.plateauDeJeu.getNombrePersonnages());
            pFaceVisible2 = generateur.nextInt(this.plateauDeJeu.getNombrePersonnages());
            pFaceCachee = generateur.nextInt(this.plateauDeJeu.getNombrePersonnages());
        } while (pFaceVisible1 == pFaceVisible2 || pFaceVisible2 == pFaceCachee || pFaceVisible1 == pFaceCachee);

        System.out.println("Les personnages " + this.plateauDeJeu.getPersonnage(pFaceVisible1).getNom() + " et " + this.plateauDeJeu.getPersonnage(pFaceVisible2).getNom() + " sont écartés face visible.");
        System.out.println("Un personnage est écarté face caché");

        Personnage[] listePerso = new Personnage[9];
        for (int i = 0; i < this.plateauDeJeu.getNombrePersonnages(); i++) {
            listePerso[i] = this.plateauDeJeu.getPersonnage(i);
        }
        listePerso[pFaceVisible1] = null;
        listePerso[pFaceVisible2] = null;
        listePerso[pFaceCachee] = null;

        int joueurCouronne = 0;

        for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++)
            joueurCouronne = this.plateauDeJeu.getJoueur(i).getPossedeCouronne() ? i : joueurCouronne;

        int playerIteration = 0;

        do {
            int currentPlayer = (joueurCouronne + playerIteration) % this.plateauDeJeu.getNombreJoueurs();

            System.out.println(
                "\n----------------------------------------------\n" +
                "\t\t  " + this.plateauDeJeu.getJoueur(currentPlayer).getNom() + " " + (this.plateauDeJeu.getJoueur(currentPlayer).getPossedeCouronne() == true ? "\n\t   (Joueur couronné)" : "") + "\n" +
                "     Quel personnage choisissez vous ?\n" +
                "                                              \n"
            );

            System.out.print("Liste des personnages :\n");
            for (int i = 0; i < this.plateauDeJeu.getNombrePersonnages(); i++) {
                if (listePerso[i] instanceof Personnage)
                    System.out.println("\t" + i + " - " + this.plateauDeJeu.getPersonnage(i).getNom());
            }

            int choix;
            do {
                System.out.print("\nVotre choix : ");
                if (this.plateauDeJeu.getJoueur(currentPlayer).getNom().contains("bot"))
                    choix = generateur.nextInt(this.plateauDeJeu.getNombrePersonnages());
                else
                    choix = Interaction.lireUnEntier(0, this.plateauDeJeu.getNombrePersonnages());


                if (!(listePerso[choix] instanceof Personnage))
                    System.out.println("\tImpossible de faire ce choix !");

            } while (!(listePerso[choix] instanceof Personnage));

            this.plateauDeJeu.getPersonnage(choix).setJoueur(this.plateauDeJeu.getJoueur(currentPlayer));
            listePerso[choix] = null;

            playerIteration++;

        } while (playerIteration <= this.plateauDeJeu.getNombreJoueurs() - 1);

        System.out.println(
            "\n\tLe choix des Personnages est terminé.\n" +
            "\tLe tour commence : \n" +
            "----------------------------------------------\n"
        );
    }

    private void percevoirRessource(int personnage) {
        System.out.println("\tVoulez vous percevoir des cartes ou des pièces d'or ?\t");
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

            System.out.println("\tVous avez pioché deux cartes\n");

            Quartier[] quartiers = new Quartier[2];
            for (int i = 0; i < quartiers.length; i++) {
                quartiers[i] = this.plateauDeJeu.getPioche().piocher();
            }
            System.out.println("\tVoici les cartes que vous avez pioché : ");
            for (int i = 0; i < quartiers.length; i++) {
                System.out.println((i + 1) + " : " + quartiers[i].getNom() + " - type : " +
                    quartiers[i].getType() + " - pièces : " + quartiers[i].getCout());
            }
            System.out.println("\tQuelle carte voulez-vous garder ? : ");
            int carte = 0;
            if (!plateauDeJeu.getPersonnage(personnage).getJoueur().isSimule())
                carte = Interaction.lireUnEntier(1, 3);
            else {
                do {
                    carte = this.generateur.nextInt(3);
                } while (carte == 0);
            }
            plateauDeJeu.getPersonnage(personnage).ajouterQuartier(quartiers[carte - 1]);
            for (int i = 0; i < quartiers.length; i++) {
                if (!quartiers[carte - 1].getNom().equals(quartiers[i].getNom())) {
                    plateauDeJeu.getPioche().ajouter(quartiers[i]);
                    break;
                }
            }
        } else {
            System.out.println("\tVous avez perçu deux pièces d'or\n");
            this.plateauDeJeu.getPersonnage(personnage).ajouterPieces();
        }
    }

    private void calculDesPoints() {
        int[][] scores = new int[this.plateauDeJeu.getNombreJoueurs()][1];
        int score = 0;
        for (int i = 0; i < this.plateauDeJeu.getNombreJoueurs(); i++) {
            ArrayList < String > quartiers = new ArrayList < > ();
            for (int j = 0; j < this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite(); j++) {
                score += this.plateauDeJeu.getJoueur(i).getCite()[j].getCout();
                if (!quartiers.contains(this.plateauDeJeu.getJoueur(i).getCite()[j].getType()))
                    quartiers.add(this.plateauDeJeu.getJoueur(i).getCite()[j].getType());
            }
            if (quartiers.size() >= 5)
                score += 3;
            if (this.plateauDeJeu.getJoueur(i).isPremier())
                score += 4;
            else {
                if (this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite() >= 7)
                    score += 2;
            }
            scores[i][0] = score;
            System.out.println("\t"+this.plateauDeJeu.getJoueur(i).getNom() + " a un total de " + score + " points !\n");
        }
        int joueurMax = 0;
        int scoreMax = scores[0][0];
        for (int i = 0; i < scores.length; i++) {
            if (scores[i][0] >= scoreMax) {
                if (scores[i][0] == scoreMax) {
                    if (this.plateauDeJeu.getJoueur(i).isRangPlusEleve()) {
                        scoreMax = scores[i][0];
                        joueurMax = i;
                    }
                } else {
                    scoreMax = scores[i][0];
                    joueurMax = i;
                }
            }
        }
        System.out.println("\t"+this.plateauDeJeu.getJoueur(joueurMax).getNom() + " a remporté la partie avec un total de " + scoreMax + " points.\n");
        System.out.println("\tFélicitation " + this.plateauDeJeu.getJoueur(joueurMax).getNom() + "!!!\n");
    }

    private void afficherBienvenue() {
        System.out.println("----------------------------------------------\n"+"\tBienvenue dans Citadelles\n"+"----------------------------------------------\n");
    } 

    private void afficherMenu() {
        System.out.println("Veuillez entrer le chiffre correspondant à  votre choix !");
        System.out.println("	1 : 	Jouer une nouvelle partie. ");
        System.out.println("	2 : 	Afficher les règles du jeu. ");
        System.out.println("	3 : 	Quitter l'application. ");
    }

    private void quitterPartie() {
        System.out.println("\tÀ bientôt dans Citadelles !");
        System.exit(0);
    }
}