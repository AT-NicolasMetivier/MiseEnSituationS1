package com.nmetivier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Premi�re classe du programme. TP qui reprend : L'initialisation des variables
 * (tout type) L'utilisation de tableaux La d�claration de m�thodes (fonctions)
 * (avec arguments) L'extraction de code pour avoir des m�thodes. (Selection de
 * code + Refactor/Extract Method) L'utilisation du clavier Les conditions Les
 * boucles
 * 
 * Pour g�n�rer le programme en ex�cutable : File/Export Runnable JAR File
 * Launch Application = Classe contenant le point d'entr�e (public stativ void
 * main(String[] args) {) Export Destination = Dossier ou va etre cr��
 * l'ex�cutable + nom de l'�x�cutable.
 *
 * Pour �x�cuter le fichier g�n�r� : Sous MICROSOFT Windows: Ouvrir l'invite de
 * commande. ([Win]+[R]) Ecrire "cmd" Appuyer sur Entr�e Ecrire "java -jar "
 * S�lectionner
 * 
 * @author METIVIER Nocolas
 * @version 0.0.1
 *
 */
public class Program {

	public static void main(String[] args) {
		showMenu();
	}

	/**
	 * Permet d'afficher le menu.
	 */
	private static void showMenu() {
		// Screen screen = new Screen(width, height)
		while (true) {
			System.out.println("ACTIVE CONSULTING ING");
			System.out.println("=====================\n");
			System.out.println("[A]jouter un(e) �tudiant(e).");
			System.out.println("[M]odifier un(e) �tudiant(e).");
			System.out.println("[S]upprimer un(e) �tudiant(e).");
			System.out.println("[V]oir les �tudiants.");
			System.out.println("[F]aire l'appel des �tudiants.");
			System.out.println("[Q]uitter le programme.\n");

			System.out.print("Votre choix : ");

			Scanner keyBoard = new Scanner(System.in);
			String userChoiceMenu = keyBoard.nextLine();
			try {
				switch (userChoiceMenu.toUpperCase()) {
				case "A":
					Student.addNewStudent();
					break;
				case "M":
					Student.modifyStudent();
					break;

				case "S":
					Student.deleteStudent();
					break;

				case "F":

					break;

				case "V":
					Student.showStudents();
					break;

				case "Q":
					System.exit(0);
					break;
				}

			} catch (NullPointerException error) {
				System.out.println("[ERREUR] Votre choix n'est pas correct.");
			} catch (Exception error) {
				System.out.println("Une erreur non g�r�e est survenue : ");
				error.printStackTrace();
			}
		}
	}
}
