import java.util.ArrayList;

public class tankDataList {

		// Nested ArrayList<ArrayList<String>>
		/* 
		 * 1
		 * [
		 * 	[] [] [] [] [] [] [] []
		 * ]
		 * 
		 * 2
		 * [
		 * 	[] [] [] [] [] [] [] []
		 * ]
		 * 
		 * 
		 */
		
		ArrayList<ArrayList<String>> rows;
		
		// Construct the data class
		public tankDataList(){
			rows = new ArrayList<ArrayList<String>>();
		}
		
		/* 
		 * 
		 * 
		 * Setters and Getters 
		 * 
		 * 
		 * */
		public void addRow(ArrayList<String> list) {
			rows.add(list);
		}
		
		
		// Also shows how many columns in the data
		public int size() {
			return rows.size();
		}
		
		// Consider that last 3 columns are totals and first column is height
		public int rowSize(int row) {
			return getRow(row).size();
		}
		
		// Traverses each nested row item and prints it out, separated by rows of data
		public void printData() {
			for (int i = 0; i < rows.size(); i++) {
				int j = 0;
				System.out.print("[");
				for (String entry : getRow(i)) {
//					System.out.println(j + " - " + entry);
					System.out.print(entry + ", ");
					j++;
				}
				System.out.print("]\n");
			}
		}
		
		public String getDataPoint(int row, int cellIndex) {
			ArrayList<String> rowNumber = getRow(row);
			return rowNumber.get(cellIndex);
		}
		
		public ArrayList<String> getRow(int row) {
			return rows.get(row);
		}
		
		// Gets column by number loc in row 
		public ArrayList<String> getColumn(int col) {
			int targetColumn = col;
			ArrayList<String> values = new ArrayList<String>();
			
			for (int i = 0; i < rows.size(); i++) {
				values.add(getRow(i).get(targetColumn));
			}
			
			return values;
		}
		
		/*
		 * 
		 * Getters for Specific data sets
		 * 
		 * */
		
		public ArrayList<String> getShellAngle() {
			ArrayList<String> angles = new ArrayList<String>(rows.get(0).subList(1, rows.get(0).size()));
			
			return angles;
		}
		
		public ArrayList<String> getArcLength() {
			ArrayList<String> arcLength = new ArrayList<String>(rows.get(1).subList(1, rows.get(1).size()-3));
			
			return arcLength;
		}
		
		public ArrayList<String> getArcHeight() {
			ArrayList<String> firstColumn = getColumn(0);
			ArrayList<String> arcHeight = new ArrayList<String>(firstColumn.subList(2, firstColumn.size()));
			
			return arcHeight;
		}
		
		public tankDataList getShellData() {
			tankDataList shell = new tankDataList();
			ArrayList<String> row;
			
			// start at row 2 after angle row and arc length row
			for (int i = 2; i< rows.size(); i++) {
				row = new ArrayList<String>(rows.get(i).subList(1, rows.get(i).size()-3));
				shell.addRow(row);
			}
						
			return shell;
		}
		
		
}
