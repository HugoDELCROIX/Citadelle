package application;

import modele.Pioche;
import modele.Quartier;

public class Configuration {

    //Type : RELIGIEUX
    private static Quartier temple = new Quartier("temple",Quartier.TYPE_QUARTIERS[0],1);
    private static Quartier eglise = new Quartier("église",Quartier.TYPE_QUARTIERS[0],2);
    private static Quartier monastere = new Quartier("monastère",Quartier.TYPE_QUARTIERS[0],3);
    private static Quartier cathedrale = new Quartier("cathédrale",Quartier.TYPE_QUARTIERS[0],5);
    //Type : MILITAIRE
    private static Quartier tour_guet = new Quartier("tour de guet",Quartier.TYPE_QUARTIERS[1],1);
    private static Quartier prison = new Quartier("prison",Quartier.TYPE_QUARTIERS[1],2);
    private static Quartier caserne = new Quartier("caserne",Quartier.TYPE_QUARTIERS[1],3);
    private static Quartier forteresse = new Quartier("forteresse",Quartier.TYPE_QUARTIERS[1],5);
    //Type : NOBLE
    private static Quartier manoir = new Quartier("manoir",Quartier.TYPE_QUARTIERS[2],3);
    private static Quartier chateau = new Quartier("château",Quartier.TYPE_QUARTIERS[2],4);
    private static Quartier palais = new Quartier("palais",Quartier.TYPE_QUARTIERS[2],5);
    //Type : COMMERCANT
    private static Quartier taverne = new Quartier("tarvene",Quartier.TYPE_QUARTIERS[3],1);
    private static Quartier echope = new Quartier("échoppe",Quartier.TYPE_QUARTIERS[3],2);
    private static Quartier marche = new Quartier("marché",Quartier.TYPE_QUARTIERS[3],2);
    private static Quartier comptoir = new Quartier("comptoir",Quartier.TYPE_QUARTIERS[3],3);
    private static Quartier port = new Quartier("port",Quartier.TYPE_QUARTIERS[3],4);
    private static Quartier hotel_ville = new Quartier("hôtel de ville",Quartier.TYPE_QUARTIERS[3],5);

    public static Pioche nouvellePioche(Pioche pioche){
        
        //Ajout des Quartiers en fonctions de leur quantité
        for(int i = 0;i<2;i++){
            pioche.ajouter(cathedrale);
            pioche.ajouter(forteresse);
            pioche.ajouter(hotel_ville);
        }
        for(int i = 0;i<3;i++){
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
        for(int i = 0;i<4;i++){
            pioche.ajouter(marche);
            pioche.ajouter(chateau);
        }
        for(int i = 0;i<5;i++){
            pioche.ajouter(taverne);
            pioche.ajouter(manoir);
        }

        pioche = new Pioche();

        return pioche;
    } 
    

    public static void configurationDeBase(Pioche p){
        
    }
}
