package com.nmetivier;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author ARSI
 *
 */
public class AttendanceRegister {

	/**
	 * We check if students are present.
	 * @param studentsListCopy
	 */
	static void checkPresence(ArrayList<Object[]> studentsListCopy) {
		Object[][] attendanceRegister = new Object[studentsListCopy.size()][2];
		Scanner keyBoard = new Scanner(System.in);
		for(int index = 0; index < studentsListCopy.size(); index++) {
			Object student = studentsListCopy.get(index);
			attendanceRegister[index][0] = student;
			
			System.out.println("L'étudiant " + student);
			boolean isPresent = keyBoard.nextBoolean();
			
		}
	}
}
