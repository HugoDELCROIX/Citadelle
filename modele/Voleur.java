package modele;

import controleur.Interaction;

public class Voleur extends Personnage{

    public Voleur(){
        super("Voleur", 2, Caracteristiques.VOLEUR);
    }


    public void utiliserPouvoir() {
        boolean personnageVole = false;
        
        do {
            System.out.println("Voici la liste des personnages");
            for(int i=0;i<getPlateau().getNombreJoueurs();i++){
                Personnage personnage = getPlateau().getPersonnage(i);
                System.out.println("\n"+(i+1)+" "+personnage.getNom()+" : ");
            }
            System.out.println("Choisissez un Personnage : ");
            int listePerso = Interaction.lireUnEntier(1, getPlateau().getNombreJoueurs()+1);

            Personnage personnageChoisi = this.getPlateau().getPersonnage(listePerso-1);

            if(personnageChoisi.getJoueur().equals(null)){
                personnageVole = true;
            } else if(personnageChoisi.getRang()==1){ 
                System.out.println("Vous ne pouvez pas voler un personnage de rang 1");
                personnageVole = false;
            } else if(personnageChoisi.getNom().equals("Voleur")){
                System.out.println("Vous ne pouvez pas vous voler vous même");
                personnageVole = false;
            } else {
                personnageChoisi.setVole();
                personnageVole = true;
            }
        } while (personnageVole == false);
    }

    public void utiliserPouvoirAvatar() {

        //int i;
        //Personnage personnageChoisi = this.getPlateau().getPersonnage();
    }
    
}