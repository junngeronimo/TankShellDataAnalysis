import java.util.Scanner;

public class Driver {
	
	/** END GOAL: Process data into color-coded printable data sheet with verticality and plumbness calculations  **/

	public static void main(String[] args) {
		
		String file = "";
		tankDataList data = new tankDataList();
		
		// Ask user for input
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter file name: ");
		file = reader.next();
		
		// Get raw data from text file
		String fileContents = FileIn.readFile(file);
//		System.out.println(fileContents);
		
		// Separate values into nested arrays
		FileIn.readData(fileContents, data);
		
		data.printData();
		System.out.println(data.getRow(1));

	}

}
