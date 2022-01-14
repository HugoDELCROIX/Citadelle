package modele;

public class Quartier {
    private String nom;
    private String type;
    private int coutConstruction;
    private String caracteristiques;
    public static final String[] TYPE_QUARTIERS = {
        "RELIGIEUX",
        "MILITAIRE",
        "NOBLE",
        "COMMERCANT",
        "MERVEILLE"
    };

    public Quartier() { //Constructeur
        super();
        this.nom = "";
        this.type = "";
        this.coutConstruction = 0;
        this.caracteristiques = "";
    }

    public Quartier(String nom, String type, int coutConstruction, String caracteristiques) { //Constructeur
        super();
        this.nom = nom;
        this.type = type;
        this.coutConstruction = coutConstruction;
        this.caracteristiques = caracteristiques;
    }
    public Quartier(String nom, String type, int coutConstruction) { //Constructeur
        super();
        this.nom = nom;
        this.type = type;
        this.coutConstruction = coutConstruction;
        this.caracteristiques = "";
    }

    public String getNom() { //Retourne le nom du quartier
        return this.nom;
    }
    public void setNom(String nom) { //Défini le nom du quartier
        this.nom = nom;
    }

    public String getType() { //Retourne le type du quartier
        return this.type;
    }
    public void setType(String type) { //Défini le type du quartier
        for (String typeQt: Quartier.TYPE_QUARTIERS) {
            if (typeQt == type) {
                this.type = type;
                break;
            } else {
                this.type = "";
            }
        }
    }

    public int getCout() { //Retourne le coût du quartier
        return this.coutConstruction;
    }
    public void setCout(int coutConstruction) { //Défini le coût d'un quartier
        if (1 <= coutConstruction && coutConstruction <= 6) {
            this.coutConstruction = coutConstruction;
        } else {
            coutConstruction = 0;
        }
    }

    public String getCaracteristiques() { //Retourne les caractéristiques du quartier
        return caracteristiques;
    }
    public void setCaracteristiques(String caracteristiques) { //Défini les caractéristiques du quartier
        this.caracteristiques = caracteristiques;
    }
}