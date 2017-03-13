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
		
		public int size() {
			return rows.size();
		}
		
		public int rowSize(int row) {
			return getRow(row).size();
		}
		
		public void printData() {
			for (int i = 0; i < rows.size(); i++) {
				int j = 0;
				for (String entry : getRow(i)) {
					System.out.println(j + " - " + entry);
					j++;
				}
			}
		}
		
		
}
