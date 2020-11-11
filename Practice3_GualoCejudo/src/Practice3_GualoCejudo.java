import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

/*********************************************************************
 *
 * Class Name: Practice3_GualoCejudo Author/s name: Jose Carlos Gualo Cejudo
 * Release/Creation date: 20/04/2020 Class description: In this class we have
 * the main method, auxiliary methods, and the greedy method to perform a greedy
 * approach to the solution
 *
 **********************************************************************
 */

public class Practice3_GualoCejudo {
	final static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String fichero1 = "dataset8.txt";
		String fichero2 = "dataset100.txt";
		ArrayList<Fellowship> dataset8 = new ArrayList<Fellowship>();
		ArrayList<Fellowship> dataset100 = new ArrayList<Fellowship>();
		ArrayList<Fellowship> solution = new ArrayList<Fellowship>();
		int opt1 = 0, opt2 = 0;
		double benefit = 0;
		boolean seguir = true;

		do {
			try {

				seguir = true;
				opt1 = showMenuDataset();
				switch (opt1) {
				case 1:
					do {
						dataset8 = ReadFile.leerFichero(fichero1);
						opt2 = showMenuShorting();
						if (opt2 == 5) {
							seguir = false;
						} else {

							switch (opt2) {
							case 1:
								dataset8.sort(new Longer_Term_Sort());
								break;
							case 2:
								dataset8.sort(new Shorter_Term_Sort());
								break;
							case 3:
								dataset8.sort(new Monthly_Salary_Sort());
								break;
							case 4:
								dataset8.sort(new Total_Salary_Sort());
								break;
							}
							System.out.println("\nDataset shorted in the following order: ");
							showLists(dataset8);
							solution = greedy(dataset8);
							benefit = calculateBenefit(solution);
							System.out.println("\nThe fellowships selected are: ");
							showLists(solution);
							showBenefit(benefit);

						}
					} while ((seguir == true) || (opt2 < 1 || opt2 > 5));
					break;
				case 2:
					do {
						dataset100 = ReadFile.leerFichero(fichero2);
						opt2 = showMenuShorting();
						if (opt2 == 5) {
							seguir = false;
						} else {

							switch (opt2) {
							case 1:
								dataset100.sort(new Longer_Term_Sort());
								break;
							case 2:
								dataset100.sort(new Shorter_Term_Sort());
								break;
							case 3:
								dataset100.sort(new Monthly_Salary_Sort());
								break;
							case 4:
								dataset100.sort(new Total_Salary_Sort());
								break;
							}
							System.out.println("\nDataset shorted in the following order: ");
							showLists(dataset100);
							solution = greedy(dataset100);
							benefit = calculateBenefit(solution);
							System.out.println("\nThe fellowships selected are: ");
							showLists(solution);
							showBenefit(benefit);

						}
					} while ((seguir == true) || (opt2 < 1 || opt2 > 5));
					break;
				case 3:
					System.exit(1); // End of the program when introduced value is 3
					break;
				default:
					System.out.println("Option not valid");

				}
			} catch (NumberFormatException exception) {
				System.out.println("This is not a numerical value, please introduce one");
			}
		} while (true);
	}

	/*********************************************************************
	 *
	 * Method name: showLists
	 *
	 * Description of the Method: This method show the information about the
	 * fellowships in an arrayList passed as argument
	 *
	 * Calling arguments: An arrayList of the type fellowship called "fellowship"
	 *
	 * Return value: This method does not return any value because it is void
	 *
	 * Required Files: This method does not require any file
	 *
	 * This method does not check any exception
	 *
	 *********************************************************************/
	public static void showLists(ArrayList<Fellowship> fellowship) {
		Iterator<Fellowship> iterator = fellowship.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
	}

	/*********************************************************************
	 *
	 * Method name: calculateBenefit
	 *
	 * Description of the Method: This method is used to calculate the benefit of
	 * the solution arrayList passed as argument called "fellowship"
	 *
	 * Calling arguments: An arrayList of the type fellowship called "fellowship"
	 *
	 * Return value: This method returns a double with the value benefit that has
	 * calculates
	 *
	 * Required Files: This method does not require any file
	 *
	 * This method does not check any exception
	 *
	 *********************************************************************/
	public static double calculateBenefit(ArrayList<Fellowship> fellowship) {
		double benefit = 0;
		Fellowship actual_fellowship = new Fellowship();
		Iterator<Fellowship> iterator = fellowship.iterator();
		while (iterator.hasNext()) {
			actual_fellowship = iterator.next();
			benefit += actual_fellowship.get_duration() * actual_fellowship.getMonthly_salary();
		}

		return benefit;
	}

	/*********************************************************************
	 *
	 * Method name: greedy
	 *
	 * Description of the Method: This method is used to give a greedy solution to
	 * our problem according to a sorted arrayList passed as argument
	 *
	 * Calling arguments: An arrayList of the type fellowship called "fellowship"
	 *
	 * Return value: This method returns an arrayList of the type fellowship, which
	 * contains the fellowships that are part of the solution by a certain order
	 *
	 * Required Files: This method does not require any file
	 *
	 * This method does not check any exception
	 *
	 *********************************************************************/
	public static ArrayList<Fellowship> greedy(ArrayList<Fellowship> fellowship) {
		ArrayList<Fellowship> solution = new ArrayList<Fellowship>();
		int[] months = new int[12];

		while (fellowship.isEmpty() == false) {
			if (checkMonthArray(fellowship.get(0), months)) {
				fillMonthArray(fellowship.get(0), months);
				solution.add(fellowship.get(0));
			}
			fellowship.remove(0);
		}
		return solution;
	}

	/*********************************************************************
	 *
	 * Method name: checkMonthArray
	 *
	 * Description of the Method: This method is used to check the compatibility of
	 * a given fellowship with all the rest in the solution we already have
	 *
	 * Calling arguments: We pass as parameters: an object of the type fellowship
	 * which is the one that we are going to check, and a array of integers,(0 ,1),
	 * which contains the information about the available months
	 *
	 * Return value: This method returns a boolean value, true if it is compatible,
	 * or false if not
	 *
	 * Required Files: This method does not require any file
	 *
	 * This method does not check any exception
	 *
	 *********************************************************************/
	public static boolean checkMonthArray(Fellowship fellowship, int[] months) {
		boolean solution = false;
		for (int i = fellowship.getStart_month() - 1; i <= fellowship.getFinish_month() - 1; i++) {
			if (months[i] == 0) {
				solution = true;
			} else {
				solution = false;
				return solution;
			}
		}
		return solution;
	}

	/*********************************************************************
	 *
	 * Method name: fillMonthArray
	 *
	 * Description of the Method: This method is used to introduce 1s into the array
	 * which contains the free months (0 if there is not any fellowship in that
	 * method, 1 if there is)
	 *
	 * Calling arguments: An object of the type fellowship from which we will pick
	 * the months the fellowship starts and ends, and the array of integers which
	 * contains the free months
	 *
	 * Return value: This method does not return any value because it is void
	 *
	 * Required Files: This method does not require any file
	 *
	 * This method does not check any exception
	 *
	 *********************************************************************/
	public static void fillMonthArray(Fellowship fellowship, int[] months) {
		for (int i = fellowship.getStart_month() - 1; i <= fellowship.getFinish_month() - 1; i++) {
			months[i] = 1;
		}
	}

	/*********************************************************************
	 *
	 * Method name: showMenuDataset
	 *
	 * Description of the Method: This method shows a menu about which dataset can
	 * select the user, and also ask for the option, checks if it is valid, and
	 * returns it
	 *
	 * Calling arguments: This method has no arguments
	 *
	 * Return value: This method returns an integer which represents the option
	 * selected by the user
	 *
	 * Required Files: This method does not require any file
	 *
	 * This method does not check any exception
	 *
	 *********************************************************************/
	public static int showMenuDataset() {
		String option_str = "";
		int option_int = 0;

		do {
			System.out.println(
					"\nChoose a dataset:\n(1)\tDataset of 8 elements.\n(2)\tDataset of 100 elements.\n(3)\tExit.");
			option_str = keyboard.nextLine();
			option_int = Integer.parseInt(option_str);
			if ((option_int < 1) || (option_int > 3)) {
				System.out.println("The number introduced is not valid");
			}
		} while ((isNumeric(option_str) == false) || ((option_int < 1) || (option_int > 3)));

		return option_int;
	}

	/*********************************************************************
	 *
	 * Method name: showMenuShorting
	 *
	 * Description of the Method: This method shows a menu about which sorting
	 * method can select the user, and also ask for the option, checks if it is
	 * valid, and returns it
	 *
	 * Calling arguments: This method has no arguments
	 *
	 * Return value: This method returns an integer which represents the option
	 * selected by the user
	 *
	 * Required Files: This method does not require any file
	 *
	 * This method does not check any exception
	 *
	 *********************************************************************/
	public static int showMenuShorting() {
		String option_str = "";
		int option_int = 0;

		do {
			System.out.println(
					"\nChoose a shorting method:\n(1)\tLonger-term shorting.\n(2)\tShorter-term shorting.\n(3)\tHighest monthly salary.\n(4)\tHighest total salary.\n(5)\tBack.");
			option_str = keyboard.nextLine();
			option_int = Integer.parseInt(option_str);
			if ((option_int < 1) || (option_int > 5)) {
				System.out.println("The number introduced is not valid");
			}
		} while ((isNumeric(option_str) == false) || ((option_int < 1) || (option_int > 5)));

		return option_int;
	}

	/*********************************************************************
	 *
	 * Method name: showBenefit
	 *
	 * Description of the Method: This method shows a message with the total benefit
	 * of the solution
	 *
	 * Calling arguments: It is passed a double value with represents the benefit
	 * which will be shown
	 *
	 * Return value: This method does not return any value because it is void
	 *
	 * Required Files: This method does not require any file
	 *
	 * This method does not check any exception
	 *
	 *********************************************************************/
	public static void showBenefit(double benefit) {
		System.out.println("This method has a benefit of: " + benefit + "€");
	}

	/*********************************************************************
	 *
	 * Method name: isNumeric
	 *
	 * Description of the Method: This method is used to check that the option
	 * introduced by kwyboard is a numeric value, so if not, it will manage the
	 * exception NumberFormatException
	 *
	 * Calling arguments: A string called "string", which will be checked if it is a
	 * numeric value or not
	 *
	 * Return value: A boolean with the final conclusion about if the string is or
	 * is not a numeric value
	 *
	 * Required Files: This method does not require any file
	 *
	 * This method handles NumberFormatException
	 *
	 *********************************************************************/
	public static boolean isNumeric(String string) {
		boolean numeric;

		try {
			Integer.parseInt(string);
			numeric = true;
		} catch (NumberFormatException exception) {
			numeric = false;
		}

		return numeric;
	}

}
