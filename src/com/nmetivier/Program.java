package com.nmetivier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
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
			System.out.println("[A]jouter un(e) étudiant(e).");
			System.out.println("[M]odifier un(e) étudiant(e).");
			System.out.println("[S]upprimer un(e) étudiant(e).");
			System.out.println("[V]oir les étudiants.");
			System.out.println("[F]aire l'appel des étudiants.");
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
					AttendanceRegister.checkPresence(Student.studentsList);
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
				System.out.println("Une erreur non gérée est survenue : ");
				error.printStackTrace();
			}
		}
	}
}
