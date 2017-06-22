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
			rows = new ArrayList<>();
		}
		
		// Setters and Getters
		public void addRow(ArrayList<String> list) {
			rows.add(list);
		}
		
		public ArrayList<String> getRow(int row) {
			return rows.get(row);
		}
		
		// Gets column by number loc in row 
		public ArrayList<String> getColumn(int col) {
			int targetColumn = col;
			ArrayList<String> values = new ArrayList<>();
			
			for (int i = 0; i < rows.size(); i++) {
				values.add(getRow(i).get(targetColumn));
			}
			
			return values;
		}
		
		// Also is how many columns in the data
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
		
		
}
