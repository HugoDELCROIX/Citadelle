package modele;

public class Quartier {
    private String nom;
    private String type;
    private int coutConstruction;
    private String caractéristiques;
    public static final String[] TYPE_QUARTIERS = {
        "RELIGIEUX",
        "MILITAIRE",
        "NOBLE",
        "COMMERCANT",
        "MERVEILLE"
    };

    public Quartier() {
        super();
        this.nom ="";
        this.type = "";
        this.coutConstruction = 0;
        this.caractéristiques = "";
        }
        
        public Quartier(String nom, String type, int coutConstruction, String caracteristiques) {
        super();
        this.nom = nom;
        this.type = type;
        this.coutConstruction = coutConstruction;
        this.caractéristiques = caracteristiques;
        }
        public Quartier(String nom, String type, int coutConstruction) {
        super();
        this.nom = nom;
        this.type = type;
        this.coutConstruction = coutConstruction;
        this.caractéristiques = "";
        }

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
        for(String typeQt : Quartier.TYPE_QUARTIERS){
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
        if(1<=coutConstruction && coutConstruction<=6){
            this.coutConstruction=coutConstruction;
        }
        else {
            coutConstruction=0;
        }
    }

    public String getCaracteristiques(){
        return caractéristiques;
    }
    public void setCaracteristiques(String caractéristiques){
        this.caractéristiques=caractéristiques;
    }
}