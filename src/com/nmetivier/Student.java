package com.nmetivier;

import java.awt.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

/**
 * Classe qui va repr�senter les �tudiants.
 * 
 * @author ARSI
 *
 */
public class Student {
	
	//region D�claration des variables.
	/**
	 * Liste des �tudiants.
	 */
	static ArrayList<Object[]> studentsList = new ArrayList<Object[]>();

	/**
	 * Date d'aujourd'hui.
	 */
	private static final Date CURRENT_DATE = new Date(System.currentTimeMillis());

	/**
	 * Compteur d'�tudiants cr��s (utilis� pour identifi� les �tudiants).
	 */
	private static short studentCreated;

	/**
	 * Repr�sente le clavier.
	 */
	private static Scanner keyBoard;

	//endregion
	
	
	//region D�laration des fonctions.
	/**
	 * Permet de calculer l'age de l'�tudiant(e).
	 * @param birthDay Date d'anniversaire.
	 * @return Age de l'�tudiant(e).
	 */
	private static short setAge(Date birthDay) {
		long difference = CURRENT_DATE.getTime() - birthDay.getTime();
		double millisecondes = (1000 * 60 * 60 * 24 * 365);
		return (short) Math.round(difference / millisecondes);
	}

	/**
	 * Permet de d�finir la date de naissance d'un(e) �tudiant(e).
	 * @param security Flag forcant � rentrer une valeur non nulle au clavier.
	 * @return
	 */
	private static Date setBirthDay(boolean security) {
		boolean checker = false;
		String birthDayAsString = null;
		keyBoard = new Scanner(System.in);
		Date finalDate = null;

		do {
			System.out.print("Date de naissance de l'�tudiant(e) (JJ/MM/AAAA) : ");

			// Je demande � l'utilisateur de saisir son pr�nom.
			birthDayAsString = keyBoard.nextLine();

			// Si le pr�nom est compos� d'au moins 3 lettres...
			if (birthDayAsString.length() == 10) {
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
				try {
					finalDate = format.parse(birthDayAsString);
					return finalDate;
				} catch (ParseException error) {
					if (security == false) {
						return null;
					}
					System.out.println("Le format ou la date de naissance n'est pas correct.");
				}
			} else {
				if (security == false) {
					return null;
				}
			}

		} while (!checker);
		return null;
	}

	/**
	 * Premet de d�finir le nom d'un(e) �tudiant(e).
	 * @param security Flag forcant � rentrer une valeur non nulle au clavier.
	 * @return Nom de l'�tudiant(e).
	 */
	private static String setLastName(boolean security) {
		boolean checker = false;
		String lastName = null;
		keyBoard = new Scanner(System.in);

		do {
			System.out.print("Nom de l'�tudiant(e) : ");

			// Je demande � l'utilisateur de saisir son pr�nom.
			lastName = keyBoard.nextLine();

			// Si le pr�nom est compos� d'au moins 3 lettres...
			if (lastName.length() >= 3) {
				// On v�rifie que le pr�nom ne contient que des lettres.
				checker = lastName.chars().allMatch(Character::isLetter);
			} else if (lastName.length() == 0) {
				if (security == false) {
					return null;
				}
			}

		} while (!checker);
		return lastName;
	}

	/**
	 * Permet de d�finir le pr�nom d'un(e) �tudiant(e).
	 * @param security Flag forcant � rentrer une valeur non nulle au clavier.
	 * @return Pr�nom de l'�tudiant(e).
	 */
	private static String setFirstName(boolean security) {
		boolean checker = false;
		String firstName = null;
		keyBoard = new Scanner(System.in);

		do {
			System.out.print("Pr�nom de l'�tudiant(e) : ");

			// Je demande � l'utilisateur de saisir son pr�nom.
			firstName = keyBoard.nextLine();

			// Si le pr�nom est compos� d'au moins 3 lettres...
			if (firstName.length() >= 3) {
				// On v�rifie que le pr�nom ne contient que des lettres.
				checker = firstName.chars().allMatch(Character::isLetter);
			} else if (firstName.length() == 0) {
				if (security == false) {
					return null;
				}
			}

		} while (!checker);
		return firstName;
	}

	/**
	 * Permet d'ajouter un(e) nouvel(le) �tudiant(e).
	 */
	static void addNewStudent() {
		// Pr�nom de l'�tudiant.
		String firstName = setFirstName(true);

		// Nom de l'�tudiant.
		String lastName = setLastName(true);

		// Date de naissance de l'�tudiant.
		Date birthDay = setBirthDay(true);

		// Age de l'�tudiant.
		short age = setAge(birthDay);

		studentCreated++;
		studentsList.add(new Object[] { studentCreated, lastName.toUpperCase(), firstName, birthDay, age });

	}

	/**
	 * Permet de modifier un(e) �tudiant(e).
	 */
	static void modifyStudent() {
		showStudents();

		int idStudentToModify;
		Object[] studentToModify = null;

		if (studentsList.size() > 0) {
			do {
				System.out.print("ID de l'�tudiant(e) � modifier : ");

				idStudentToModify = keyBoard.nextInt();
				if (idStudentToModify > 0) {
					try {
						studentToModify = studentsList.get(idStudentToModify - 1);
					} catch (IndexOutOfBoundsException e) {
						System.out.println("Attention ! L'index rentr� ne correspond � aucun ID d'�tudiant(e).");
						System.out.println("Vous vouliez peut-�tre modifier le (la) dernier(e) �tudiant(e) ?");
						showStudent(studentsList.size() - 1);
						System.out.print("Votre r�ponse [O/N] : ");
					}
					System.out.println("Confirmez-vous que vous voulez modifier l'�tudiant(e) : ");
					showStudent(idStudentToModify - 1);
					do {
						System.out.print("Votre choix [O/N] : ");
						String userChoice = keyBoard.nextLine();
						if (userChoice.toUpperCase().equals("O")) {
							int index = 0;
							for (Object studentInfo : studentToModify) {
								String newValue;
								switch (index) {
								case 1:
									System.out.print("Nom (" + studentInfo.toString() + ") : ");
									newValue = setLastName(false);
									try {
										if (!newValue.isEmpty()) {
											studentToModify[1] = newValue;
										}
									} catch (NullPointerException e) {

									}

									break;
								case 2:
									System.out.print("Pr�nom (" + studentInfo.toString() + ") : ");
									newValue = setFirstName(false);

									try {
										if (!newValue.isEmpty()) {
											studentToModify[2] = newValue;
										}
									} catch (NullPointerException e) {

									}
									break;
								case 3:
									System.out.print("Date de Naissance (" + studentInfo.toString() + ") : ");
									Date newDate = setBirthDay(false);

									try {
										if (!newDate.toString().equals(null)) {
											studentToModify[3] = newDate;
										}
									} catch (NullPointerException e) {

									}
									break;
								case 4:
									System.out.print("Age (" + studentInfo.toString() + ") : ");
									newValue = setLastName(false);

									try {
										if (!newValue.isEmpty()) {
											studentToModify[4] = newValue;
										}
									} catch (NullPointerException e) {

									}
									break;
								}
								index++;
							}
							studentsList.set(idStudentToModify - 1, studentToModify);
							return;
						} else if (userChoice.toUpperCase().equals("N")) {
							return;
						}
					} while (true);

				} else {

				}
				break;

			} while (true);
		}
	}

	/**
	 * Permet d'afficher tout les �tudiants.
	 */
	static void showStudents() {
		if (studentsList.size() > 0) {
			Iterator<Object[]> iteraorForStudentList = studentsList.iterator();
			int data = 0;
			while (iteraorForStudentList.hasNext()) {
				Object[] etudiant = iteraorForStudentList.next();
				for (Object info : etudiant) {
					System.out.println(info);
				}
				
				
				
				
				data = 0;
				for (Object studentData : iteraorForStudentList.next()) {
					switch (data) {
					case 0:
						System.out.print("[" + studentData + "]\t");
						break;
					case 3:
						System.out.print("- " + studentData + " - ");
						break;
					case 4:
						System.out.print("(" + studentData + " ans)\n");
						break;

					default:
						System.out.print(studentData + " ");
						break;
					}
					data++;
				}
			}
		} else {
			System.out.println("La liste est vide.");
		}

	}

	/**
	 * Permet d'afficher un(e) �tudiant(e) sp�cifique.
	 * @param index	ID de l'�tudiant(e) � afficher.
	 */
	private static void showStudent(int index) {
		Object[] student = null;
		try {
			student = studentsList.get(index);
		} catch (IndexOutOfBoundsException errorLevel1) {
			try {
				student = studentsList.get(index-1);
			} catch (IndexOutOfBoundsException errorLevel2) {
				System.out.println("Cet ID d'�tudiant n'existe pas.");
				short i = Short.parseShort("1f");
			}
		}
		System.out.println(String.format("[%s]\t%s %s - %s - (%s ans)", student[0], student[1], student[2], student[3],
				student[4]));
	}

	/**
	 * Permet de supprimer un �tudiant.
	 */
	public static void deleteStudent() {
		showStudents();
		int idStudentToModify;
		Object[] studentToModify = null;
		if (studentsList.size() > 0) {
			do {
				System.out.print("ID de l'�tudiant(e) � modifier : ");
				idStudentToModify = keyBoard.nextInt();
				if (idStudentToModify > 0) {
					try {
						studentToModify = studentsList.get(idStudentToModify - 1);
					} catch (IndexOutOfBoundsException e) {
						System.out.println("Attention ! L'index rentr� ne correspond � aucun ID d'�tudiant(e).");
						System.out.println("Vous vouliez peut-�tre modifier le (la) dernier(e) �tudiant(e) ?");
						showStudent(studentsList.size() - 1);
						System.out.print("Votre r�ponse [O/N] : ");
					}
					System.out.println("Confirmez-vous que vous voulez supprimer l'�tudiant(e) : ");
					showStudent(idStudentToModify - 1);
					do {
						System.out.print("Votre choix [O/N] : ");
						String userChoice = keyBoard.nextLine();
						if (userChoice.toUpperCase().equals("O")) {
							studentsList.remove(idStudentToModify - 1);
							System.out.println("L'�tudiant(e) a �t� suprim�(e).");
							return;
						} else if (userChoice.toUpperCase().equals("N")) {
							break;
						}
						
					} while (true);
				}
			} while (true);
		}
		else {
			System.out.println("La liste est vide.");
		}
		
	}
	//endregion

}
