import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIn {
	
	public static void main(String[] args) {

		String file = "";
		tankDataList data = new tankDataList();
		
		// Ask user for input
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter file name: ");
		file = reader.next();
		
		// Get raw data from text file
		String fileContents = readFile(file);
//		System.out.println(fileContents);
		
		// Separate values into nested arrays
		parseData(fileContents, data);
		
//		data.printData();

	}
	
	
	
	public static void parseData(String contents, tankDataList data) {
		
		String[] contentsArray = contents.split(";");
		ArrayList<String> row = new ArrayList<>();
		for(int i = 0; i < contentsArray.length; i++) {
//			System.out.println("[" + i + "] - " +contentsArray[i]);
//			System.out.println(contentsArray[i]);
			if (contentsArray[i].contains("\n")) {
//				row.add(contentsArray[i].split("\n")[0]);
//				row.add(contentsArray[i].split("\n")[1]);
				System.out.println(contentsArray[i] + "\n");
				System.out.println("boom" + contentsArray[i].split("\\s")[0]);
				data.addRow(row);
				row = new ArrayList<>();
			}
			row.add(contentsArray[i]);
			
		}
		
//		data.addRow(row);
				
		
	}
	
	// Function to read in data
	public static String readFile(String file) {
		
		// Var init.
		String fileLine = "";

		// Get file name
		try {
			for (String line : Files.readAllLines(Paths.get(file))) {
				fileLine += line;
				fileLine += "\n";
			}
		} catch (IOException e) {
			// File was not found
			System.out.println("File was not found. Try again.");
			Scanner reader = new Scanner(System.in);
			file = reader.next();

		}
		
		// Test if file was read in properly
//		System.out.println(fileLine);
		
		return fileLine;
	}

}
