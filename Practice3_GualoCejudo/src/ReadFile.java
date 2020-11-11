import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;
import java.util.StringTokenizer;
/*********************************************************************
*
* Class Name: ReadFile
* Author/s name: Jose Carlos Gualo Cejudo
* Release/Creation date: 20/04/2020
* Class description: This class is used to read a txt file
*
**********************************************************************
*/
public class ReadFile {
	
	/*********************************************************************
	 *
	 * Method name: leerFichero
	 *
	 * Description of the Method: This method is used to take different pieces of
	 * information from a file
	 *
	 * Calling arguments: In the method we pass one argument of the type String
	 * called fichero, which is the path to the file to be read
	 *
	 * Return value: This method returns an arrayList of fellowships, which parameter of each object are the information read from the file 
	 *
	 * Required Files: It needs one file to work correctly. This file will be passed
	 * as an argument saved in the variable fichero
	 *
	 * This method can throw FileNotFoundException which is thrown when the path of
	 * the file does not match with the given one, and IOException.
	 *
	 *********************************************************************/
	public static ArrayList<Fellowship> leerFichero(String fichero) throws FileNotFoundException, IOException {
		String linea;
		int id, start_month, finish_month;
		double monthly_salary;
		ArrayList<Fellowship> list = new ArrayList<Fellowship>();
		int i = 0;

		try {
			InputStream is = ReadFile.class.getResourceAsStream(fichero);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			while ((linea = br.readLine()) != null) {

				try {
					StringTokenizer st = new StringTokenizer(linea, "\t");
					id = Integer.parseInt(st.nextToken());
					start_month = Integer.parseInt(st.nextToken());
					finish_month = Integer.parseInt(st.nextToken());
					monthly_salary = Double.parseDouble(st.nextToken());

					Fellowship fellowship = new Fellowship(id, start_month, finish_month, monthly_salary);
					list.add(fellowship);
					i++;

				} catch (Exception e) {
					System.out.println("Error closing the file");
					System.exit(1);
				}

			}
			is.close();
		} catch (Exception e) {
			System.out.println("Error, file not found");
			System.exit(1);
		}
		return list;

	}
}
