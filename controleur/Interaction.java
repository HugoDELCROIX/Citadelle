package controleur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction {
	private static Scanner sc = new Scanner(System.in);

	public static int lireUnEntier() {
		int i = 0;
		boolean continu = true;
		do {
			try {
				i = sc.nextInt();
				continu = false;
			} catch (InputMismatchException e) {
				System.out.print("Veuillez rentrer un chiffre : ");
				sc.next(); // passe l'entier pour �viter de boucler
			}
		} while(continu);
		return i;
	}

	// renvoie un entier lu au clavier compris dans l'intervalle
	//     [borneMin, borneMax[
        public static int lireUnEntier(int borneMin, int borneMax) {
            int i = 0;
            Scanner s = new Scanner(System.in);
        i = s.nextInt();
        while(i<borneMin || i>=borneMax){
            System.out.println("Entier non compris entre "+borneMin+" et "+borneMax);
            i = s.nextInt();
        } 
        s.close();
        return i;
	}

	// lit les r�ponses "oui", "non", "o" ou "n" et renvoie un bool�en
	public static boolean lireOuiOuNon() {
		boolean retour;
        String valeur;
        boolean i = false;

		Scanner s = new Scanner(System.in);
        valeur = s.next();
        while(i==false){
            if("oui".equals(valeur)||"o".equals(valeur)||"non".equals(valeur)||"n".equals(valeur)){
                i=true;
            }else{
            System.out.println("Veuillez entrer 'oui','o'/'non','n'");
            valeur = s.next();
            }
        } 
        if("oui".equals(valeur)||"o".equals(valeur)){
            retour = true;
        } else {
            retour = false;
        }
        s.close();
        return retour;
        
	}

	// renvoie une cha�ne de caract�re lue au clavier:
	public static String lireUneChaine() {
		String retour = "";
        Scanner s = new Scanner(System.in);
        retour = s.next();
        s.close();
		return retour;
	}

    public static void main(String[] args) {
        //System.out.println(lireUnEntier(1,4));
        //lireOuiOuNon();
        //lireUneChaine();
    }
	
}