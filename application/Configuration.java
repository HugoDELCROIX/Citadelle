package application;

import modele.Architecte;
import modele.Assassin;
import modele.Condottiere;
import modele.Eveque;
import modele.Joueur;
import modele.Magicienne;
import modele.Marchande;
import modele.Personnage;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Roi;
import modele.Voleur;

public class Configuration {
    //Déclaration des Quartiers
    //Type : RELIGIEUX
    private static Quartier temple = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
    private static Quartier eglise = new Quartier("église", Quartier.TYPE_QUARTIERS[0], 2);
    private static Quartier monastere = new Quartier("monastère", Quartier.TYPE_QUARTIERS[0], 3);
    private static Quartier cathedrale = new Quartier("cathédrale", Quartier.TYPE_QUARTIERS[0], 5);
    //Type : MILITAIRE
    private static Quartier tour_guet = new Quartier("tour de guet", Quartier.TYPE_QUARTIERS[1], 1);
    private static Quartier prison = new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2);
    private static Quartier caserne = new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3);
    private static Quartier forteresse = new Quartier("forteresse", Quartier.TYPE_QUARTIERS[1], 5);
    //Type : NOBLE
    private static Quartier manoir = new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3);
    private static Quartier chateau = new Quartier("château", Quartier.TYPE_QUARTIERS[2], 4);
    private static Quartier palais = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5);
    //Type : COMMERCANT
    private static Quartier taverne = new Quartier("tarvene", Quartier.TYPE_QUARTIERS[3], 1);
    private static Quartier echope = new Quartier("échoppe", Quartier.TYPE_QUARTIERS[3], 2);
    private static Quartier marche = new Quartier("marché", Quartier.TYPE_QUARTIERS[3], 2);
    private static Quartier comptoir = new Quartier("comptoir", Quartier.TYPE_QUARTIERS[3], 3);
    private static Quartier port = new Quartier("port", Quartier.TYPE_QUARTIERS[3], 4);
    private static Quartier hotel_ville = new Quartier("hôtel de ville", Quartier.TYPE_QUARTIERS[3], 5);

    //Déclaration des Merveilles
    private static Quartier bibliotheque = new Quartier("Bibliothèque", Quartier.TYPE_QUARTIERS[4], 6);
    private static Quartier forge = new Quartier("Forge", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier carriere = new Quartier("Carrière", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier laboratoire = new Quartier("Laboratoire", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier cour_miracle = new Quartier("Cour des Miracles", Quartier.TYPE_QUARTIERS[4], 2);
    private static Quartier manufacture = new Quartier("Manufacture", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier donjon = new Quartier("Donjon", Quartier.TYPE_QUARTIERS[4], 3);
    private static Quartier salle_cartes = new Quartier("Salle des Cartes", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier dracoport = new Quartier("Dracoport", Quartier.TYPE_QUARTIERS[4], 6);
    private static Quartier statue_equestre = new Quartier("Statue Équestre", Quartier.TYPE_QUARTIERS[4], 3);
    private static Quartier ecole_magie = new Quartier("École de Magie", Quartier.TYPE_QUARTIERS[4], 6);
    private static Quartier tresor_imperial = new Quartier("Trésor Impérial", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier fontaine_souhaits = new Quartier("Fontaine aux Souhaits", Quartier.TYPE_QUARTIERS[4], 5);
    private static Quartier tripot = new Quartier("Tripot", Quartier.TYPE_QUARTIERS[4], 6);

    //Déclaration des Personnages
    private static Personnage assassin = new Assassin();
    private static Personnage architecte = new Architecte();
    private static Personnage condotierre = new Condottiere();
    private static Personnage eveque = new Eveque();
    private static Personnage magicienne = new Magicienne();
    private static Personnage marchande = new Marchande();
    private static Personnage roi = new Roi();
    private static Personnage voleur = new Voleur();

    //Déclaration des Joueurs
    private static Joueur j1 = new Joueur("Bob");
    private static Joueur j2 = new Joueur("bot1");
    private static Joueur j3 = new Joueur("bot2");
    private static Joueur j4 = new Joueur("bot3");

    public static Pioche nouvellePioche() {
        Pioche pioche = new Pioche();
        //Ajout des Quartiers en fonction de leur quantité à la pioche
        for (int i = 0; i < 2; i++) {
            pioche.ajouter(cathedrale);
            pioche.ajouter(forteresse);
            pioche.ajouter(hotel_ville);
        }
        for (int i = 0; i < 3; i++) {
            pioche.ajouter(monastere);
            pioche.ajouter(eglise);
            pioche.ajouter(temple);
            pioche.ajouter(tour_guet);
            pioche.ajouter(prison);
            pioche.ajouter(caserne);
            pioche.ajouter(palais);
            pioche.ajouter(echope);
            pioche.ajouter(comptoir);
            pioche.ajouter(port);
        }
        for (int i = 0; i < 4; i++) {
            pioche.ajouter(marche);
            pioche.ajouter(chateau);
        }
        for (int i = 0; i < 5; i++) {
            pioche.ajouter(taverne);
            pioche.ajouter(manoir);
        }

        pioche.melanger();

        return pioche;
    }


    public static PlateauDeJeu configurationDeBase(Pioche pioche) {
        PlateauDeJeu plateau = new PlateauDeJeu();
        //Ajout des Personnages au plateau de jeu
        plateau.ajouterPersonnage(architecte);
        plateau.ajouterPersonnage(assassin);
        plateau.ajouterPersonnage(condotierre);
        plateau.ajouterPersonnage(eveque);
        plateau.ajouterPersonnage(magicienne);
        plateau.ajouterPersonnage(marchande);
        plateau.ajouterPersonnage(roi);
        plateau.ajouterPersonnage(voleur);
        //Ajout des Joueurs au plateau de jeu
        plateau.ajouterJoueur(j1);
        plateau.ajouterJoueur(j2);
        plateau.ajouterJoueur(j3);
        plateau.ajouterJoueur(j4);

        //Ajout des Merveilles à la pioche
        pioche.ajouter(bibliotheque);
        pioche.ajouter(forge);
        pioche.ajouter(carriere);
        pioche.ajouter(laboratoire);
        pioche.ajouter(cour_miracle);
        pioche.ajouter(manufacture);
        pioche.ajouter(donjon);
        pioche.ajouter(salle_cartes);
        pioche.ajouter(dracoport);
        pioche.ajouter(statue_equestre);
        pioche.ajouter(ecole_magie);
        pioche.ajouter(tresor_imperial);
        pioche.ajouter(fontaine_souhaits);
        pioche.ajouter(tripot);

        pioche.melanger();

        plateau.setPioche(pioche);

        return plateau;
    }
}