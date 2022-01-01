package modele.test;

import modele.Joueur;
import modele.PlateauDeJeu;
import modele.Roi;
import modele.test.test_perso.Test;

public class TestPlateauDeJeu {

	public static void main(String[] args) {
		TestPlateauDeJeu testPlateau = new TestPlateauDeJeu();
		testPlateau.test1();
		testPlateau.test2();	
		testPlateau.test3();
		testPlateau.test4();
	}
	
	public void test1() {
		System.out.println("TEST DU CONSTRUCTEUR");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Test.test(plateau.getNombreJoueurs()== 0,"test du nombre de joueurs");
		Test.test(plateau.getNombrePersonnages()== 0,"test du nombre de personnages");
		Test.test(plateau.getPioche()!= null && 
				plateau.getPioche().nombreElements()==0,
				"test de l'existance de la pioche");
	}
	public void test2() {
		System.out.println("TEST DE L'AJOUT D'UN JOUEUR");
		PlateauDeJeu plateau = new PlateauDeJeu();
		System.out.println("PlateauDeJeu est instancié");
		Joueur joueur = new Joueur("Billy");
		System.out.println("Joueur instancié");
		plateau.ajouterJoueur(joueur);
		Test.test(plateau.getNombreJoueurs()== 1,"nombre de joueurs");
		Test.test(plateau.getJoueur(0)== joueur,
				"r�cup�ration de ce joueur depuis le tableau");
	}
	public void test3() {
		System.out.println("TEST DE L'AJOUT D'UN PERSONNAGE");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Test.test(plateau.getNombrePersonnages()== 1,"nombre de joueurs");
		Test.test(plateau.getPersonnage(0)== roi,
				"r�cup�ration du personnage depuis le tableau");
	}
	public void test4() {
		System.out.println("TEST DE L'ASSOCIATION DU PLATEAU AU PERSONNAGE");
		PlateauDeJeu plateau = new PlateauDeJeu();
		Roi roi = new Roi();
		plateau.ajouterPersonnage(roi);
		Test.test(roi.getPlateau()==plateau,
				"association du plateau au personnage");
	}
}
