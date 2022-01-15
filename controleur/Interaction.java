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
                sc.next(); // passe l'entier pour éviter de boucler
            }
        } while (continu);
        return i;
    }

    // renvoie un entier lu au clavier compris dans l'intervalle
    //     [borneMin, borneMax[
    public static int lireUnEntier(int borneMin, int borneMax) {
        int i = 0;
        boolean continu = true;
        do {
            try {
                i = sc.nextInt();
                if (i >= borneMin && i < borneMax)
                    continu = false;
            } catch (InputMismatchException e) {
                continu = true;
                System.out.print("Veuillez rentrer un chiffre compris entre: " + borneMin + " et " + borneMax);
                sc.next(); // passe l'entier pour éviter de boucler
            }
        } while (continu);
        return i;
    }

    // lit les réponses "oui", "non", "o" ou "n" et renvoie un booléen
    public static boolean lireOuiOuNon() {
        boolean retour;
        String valeur;
        boolean i = false;

        valeur = sc.next();
        while (i == false) {
            if ("oui".equals(valeur) || "o".equals(valeur) || "non".equals(valeur) || "n".equals(valeur)) {
                i = true;
            } else {
                System.out.println("Veuillez entrer 'oui','o'/'non','n'");
                valeur = sc.next();
            }
        }
        if ("oui".equals(valeur) || "o".equals(valeur)) {
            retour = true;
        } else {
            retour = false;
        }

        return retour;

    }

    // renvoie une chaîne de caractère lue au clavier:
    public static String lireUneChaine() {
        String retour = "";

        retour = sc.next();

        return retour;
    }

}