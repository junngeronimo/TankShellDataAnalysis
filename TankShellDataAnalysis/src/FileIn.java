import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIn {
	
	public static void parseData() {
		
	}
	
	/*
	 * Read all data in and store into tankDataList data structure
	 * Stores by rows
	 */
	public static void readData(String contents, tankDataList data) {
		
		String[] contentsArray = contents.split(";");
		ArrayList<String> row = new ArrayList<String>();
		ArrayList<String> row2 = new ArrayList<String>();
		boolean rowSwitch = false;
		for(int i = 0; i < contentsArray.length; i++) {
			
			// Reaches end of line which should be split into two, add first to row
			if (contentsArray[i].contains("\n")) {
				String[] endOfLine = contentsArray[i].split("\n");
//				System.out.println(endOfLine.length);
// 				for (String str : endOfLine) {
// //					System.out.print(str + "\n");
// 				}
				

				// First row finished, add to data immediately, clear row for next iteration
				if (!rowSwitch) {
					row.add(endOfLine[0]);
					data.addRow(row);
					row = new ArrayList<String>();
					rowSwitch = true; // When true, it's time to create row2.
									  // False = create row
				} else {
					row2.add(endOfLine[0]);
					data.addRow(row2);
					row2 = new ArrayList<String>();
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
		
		
/*		if (rowSwitch) {
			System.out.println("Adding to row 2");
			data.addRow(row2);
		}
		else {
			System.out.println("Adding to row 1");
			data.addRow(row);
		}*/
		
	}
	
	// Function to read in data
	public static String readFile(String file) {
		
		// Var init.
		String fileLine = "";
		File input = new File(file);
		
		// Get file name
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			//DEBUG
//			System.out.println(Paths.get(file));
//			System.out.println("exists? - " + input.exists());
//			System.out.println("directory? - " +input.isDirectory());
//			System.out.println("can read? - " +input.canRead());
			
			for (String line; (line = br.readLine()) != null;) {
				fileLine += line;
				fileLine += "\n";
			}
		} catch (IOException e) {
			
			e.printStackTrace();
			

		}

		
		return fileLine;
	}

}
