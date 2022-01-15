package modele;

public class Roi extends Personnage {

    public Roi() {
        super("Roi", 4, Caracteristiques.ROI);
    }


    public void percevoirRessourcesSpecifiques() {
        if (this.getJoueur() instanceof Joueur && !this.getAssassine()) {
            int sumNoble = 0;
            for (Quartier quartier: this.getJoueur().getCite()) {

                if (quartier instanceof Quartier && quartier.getType() == Quartier.TYPE_QUARTIERS[2]) {
                    sumNoble += 1;
                }

            }

            this.getJoueur().ajouterPieces(sumNoble);
            System.out.println(sumNoble + " pièces ajoutés.");
        }
    }

    public void utiliserPouvoir() {
        if (this.getJoueur() instanceof Joueur && !this.getAssassine()) {
            this.getJoueur().setPossedeCouronne(true);
            System.out.println("Je prends la couronne.");
        }
    }

    public void utiliserPouvoirAvatar() {
        this.utiliserPouvoir();
    }

}