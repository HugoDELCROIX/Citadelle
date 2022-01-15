package modele;

public class Eveque extends Personnage {

    public Eveque() {
        super("Eveque", 5, Caracteristiques.EVEQUE);
    }

    public void utiliserPouvoir() {}

    public void percevoirRessourcesSpecifiques() {
        Quartier[] citeJ = this.getJoueur().getCite();
        int nbQuartiers = this.getJoueur().nbQuartiersDansCite();

        for (int i = 0; i < nbQuartiers; i++) {
            if (citeJ[i] != null && citeJ[i].getType().equals(Quartier.TYPE_QUARTIERS[0])) {
                getJoueur().ajouterPieces(1);
            }
        }
    }

    public void utiliserPouvoirAvatar() {}

}