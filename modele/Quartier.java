package modele;

public class Quartier {
    private String nom;
    private String type;
    private int coutConstruction;
    private String caractéristiques;
    public static final String[] TYPES_QUARTIERS = {
        "RELIGIEUX",
        "MILITAIRE",
        "NOBLE",
        "COMMERCANT",
        "MERVEILLE"
    };

    public String getNom() {
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        for(String typeQt : Quartier.TYPES_QUARTIERS){
            if(typeQt == type){
                this.type = type;
                break;
            } else {
                this.type = "";
            }
        }

        /*
        for (int i = 0; i <= 4; i++) {
            if (type == TYPES_QUARTIERS[i])
                return type;
        }
        return type = "";
        */
    }

    public int getCout() {
        return this.coutConstruction;
    }
    public void setCout(int coutConstruction) {
        
    }
}