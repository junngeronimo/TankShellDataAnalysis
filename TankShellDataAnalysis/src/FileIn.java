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
		
		data.printData();

	}
	
	
	
	
	public static void parseData(String contents, tankDataList data) {
		
		String[] contentsArray = contents.split(";");
		ArrayList<String> row = new ArrayList<>();
		ArrayList<String> row2 = new ArrayList<>();
		boolean rowSwitch = false;
		for(int i = 0; i < contentsArray.length; i++) {
//			System.out.println("[" + i + "] - " +contentsArray[i]);
//			System.out.println(contentsArray[i]);
			

			// Reaches end of line which should be split into two, add first to row
			if (contentsArray[i].contains("\n")) {
				String[] endOfLine = contentsArray[i].split("\n");
				System.out.println(endOfLine.length);
				for (String str : endOfLine) {
					System.out.print(str + "\n");
				}
				

					
				
				// First row finished, add to data immediately, clear row for next iteration
				if (!rowSwitch) {
					row.add(endOfLine[0]);
					data.addRow(row);
					row = new ArrayList<>();
					rowSwitch = true; // When true, it's time to create row2.
									  // False = create row
				} else {
					row2.add(endOfLine[0]);
					data.addRow(row2);
					row2 = new ArrayList<>();
					rowSwitch = false;
				}

				// Start next row
				if (endOfLine.length > 1 && rowSwitch) 
					row2.add(endOfLine[1]);
				else if (endOfLine.length > 1 && !rowSwitch) {
					row.add(endOfLine[1]);
				} else if (rowSwitch) {
					row2.add(endOfLine[0]);
				} else
					row.add(endOfLine[0]);
				

				continue;

			}
			
			if (rowSwitch)
				row2.add(contentsArray[i]);
			else
				row.add(contentsArray[i]);
			
		}
		
		if (rowSwitch)
			data.addRow(row2);
		else
			data.addRow(row);
				
		
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
