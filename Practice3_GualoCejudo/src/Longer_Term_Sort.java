import java.util.Comparator;
/*********************************************************************
*
* Class Name: Longer_Term_Sort
* Author/s name: Jose Carlos Gualo Cejudo
* Release/Creation date: 20/04/2020
* Class description: This class implements the interface "Comparator", and is used to sort and arrayList of fellowships by longer-term
*
**********************************************************************
*/
public class Longer_Term_Sort implements Comparator<Fellowship> {
	
	/*********************************************************************
	*
	* Method name: compare
	* 
	* Description of the Method: this method is used to compare two objects of the type fellowship passed as parameters, and will be used
	* to sort an arrayList of this type of elements 
	*
	* Calling arguments: We pass two objects of the type fellowship called "a" and "b"
	*
	* Return value: Here we are returning an integer 1 or -1, depending on which of the two compared elements is greater
	*
	* Required Files: This method does not require files
	*
	* This method does not check any exception
	*
	*********************************************************************/
	public int compare(Fellowship a, Fellowship b) {
		return Double.compare(b.get_duration(), a.get_duration());
	}
}
