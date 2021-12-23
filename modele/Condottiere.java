package modele;

import controleur.Interaction;

public class Condottiere extends Personnage{
    public Condottiere(){
        super("Condottiere", 8, Caracteristiques.CONDOTTIERE);
    }

    public void utiliserPouvoir(){

        System.out.print("Voulez vous utiliser votre pouvoir de destruction ? (o/n) ");
        boolean usePower = Interaction.lireOuiOuNon();

        if(usePower){
            
            System.out.println("Voici la liste des joueurs et le contenu de leurs quartier:");

            for(int i = 0; i < this.getPlateau().getNombreJoueurs(); i++){

                Joueur currentPlayer = this.getPlateau().getJoueur(i);

                System.out.print("\n" + (i+1) + " " + currentPlayer.getNom() + ": ");

                for(int j = 0; j < currentPlayer.nbQuartiersDansCite(); j++){
                    Quartier currentQuartier = currentPlayer.getCite()[j];

                    if(currentQuartier instanceof Quartier)
                        System.out.print((j+1) + ": " + currentQuartier.getNom() + " (cout " + currentQuartier.getCout() + "), ");
                }
               
            }

            System.out.println("\nPour votre information, vous possedez actuellement " + this.getJoueur().nbPieces() + " pièces dans votre trésor.");

            int selectedTarget;
            Boolean keepAsking = true;

            do{
                System.out.print("Quel joueur choisissez-vous ? (0 pour ne rien faire): ");
                selectedTarget = Interaction.lireUnEntier(0, this.getPlateau().getNombreJoueurs()+1);

                if(selectedTarget == 0){
                    keepAsking = false;
                } else {
                    Joueur targetPlayer = this.getPlateau().getJoueur(selectedTarget-1);

                    if(targetPlayer.getPersonnage() instanceof Eveque && !targetPlayer.getPersonnage().getAssassine()){
                        System.out.println("Vous ne pouvez pas choisir un évêque vivant.");
                    } else {
                        keepAsking = false;

                        int selectedQuartier;
                        Boolean keepAskingQuartier = true;

                        do{
                            System.out.print("Quel quartier choisissez vous ? ");
                            selectedQuartier = Interaction.lireUnEntier(1, targetPlayer.nbQuartiersDansCite()+1);
                            Quartier targetQuartier = targetPlayer.getCite()[selectedQuartier-1];

                            if(targetQuartier.getCout()-1 > this.getJoueur().nbPieces()){
                                System.out.println("Vous n'avez pas assez de pièces.");
                            } else {
                                keepAskingQuartier = false;
                                System.out.println(targetPlayer.nbQuartiersDansCite());
                                System.out.println("=> On retire " + targetQuartier.getNom() + " à " + targetPlayer.getNom());
                            }
                        } while (keepAskingQuartier);

                    }

                }

            } while (keepAsking);
        }

    }

    public void utiliserPouvoirAvatar(){}

    public void percevoirRessourcesSpecifiques(){

        for(Quartier quartier : this.getJoueur().getCite()){

            if(quartier instanceof Quartier && quartier.getType().equals(Quartier.TYPE_QUARTIERS[1]))
                this.getJoueur().ajouterPieces(1);

        }

    }
}
