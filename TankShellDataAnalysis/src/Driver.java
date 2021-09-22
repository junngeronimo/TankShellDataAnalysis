import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.Region;

import org.apache.poi.hssf.record.CFRuleBase.ComparisonOperator;
import org.apache.poi.hssf.usermodel.HSSFConditionalFormattingRule;
import org.apache.poi.hssf.usermodel.HSSFPatternFormatting;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderFormatting;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ColorScaleFormatting;
import org.apache.poi.ss.usermodel.ConditionFilterData;
import org.apache.poi.ss.usermodel.ConditionFilterType;
import org.apache.poi.ss.usermodel.ConditionType;
import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.DataBarFormatting;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FontFormatting;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Driver {
	
	/** END GOAL: Process data into color-coded printable data sheet with verticality and plumbness calculations  **/

	public static void main(String[] args) {
		
		String file = "";
		tankDataList data = new tankDataList();
		
		//Establish Current directory
		String workingDir = System.getProperty("user.dir");
		System.out.println("Current directory: " + workingDir);

		// Ask user for input
		Scanner reader = new Scanner(args[0]);
		System.out.print("Enter file name: ");
		file = reader.next();
		System.out.println("input: " + file);
		System.out.println();
		
		// Get raw data from text file
		String fileContents = FileIn.readFile(file);
		
		// Separate values into nested arrays
		FileIn.readData(fileContents, data);
		
//		System.out.println("Testing: Printing out all content.");
//		data.printData();
		
//		System.out.println();
//		System.out.println("Testing: Grabbing row 1:");
//		System.out.println(data.getRow(1));
//		System.out.println();
		
		
//		System.out.println("Testing: Get only shell data");
//		tankDataList shellData = data.shellData();
//		shellData.printData();
		
//		System.out.println("Testing: Get only angles");
//		System.out.println(data.getShellAngle());
//		System.out.println();
//		System.out.println("Testing: Get only arc lengths");
//		System.out.println(data.getArcLength());
//		System.out.println();
//		System.out.println("Testing: Get only arc heights");
//		System.out.println(data.getArcHeight());
		
		
		/**
		 * 
		 * START OF EXCEL PROCESSING
		 * 
		 */
		
		//
		String newWorkbookLocation = "E:/InspectionData/Dropbox (Powers EI)/3D Scan/3D Data Automation/Test files/new.xlsx";
		
		
		/**
		 * 
		 * READ EXISTING EXCEL FILE
		 * 
		 **/
		
		// Get file
		FileInputStream newWorkbook = null;
		try {
			newWorkbook = new FileInputStream(new File(newWorkbookLocation));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Create workbook
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(newWorkbook);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/**
		 * 
		 * WRITE EXISTING EXCEL FILE
		 * 
		 **/
		
		/* setup */
//		workbook.setSheetName(0, "Shell Distortion Criteria");
		workbook.createSheet("Shell Summary");
		workbook.createSheet("Shell Data");
		workbook.createSheet("Verticality Plot");
		workbook.createSheet("Shell Roundness Plot 1");
		workbook.createSheet("Shell Roundness Plot 2");
		
		int shellDataSheet = 2;
		Sheet sheet = workbook.getSheetAt(shellDataSheet); // grab "Shell Data"
		workbook.setActiveSheet(shellDataSheet);
//		sheet.setColumnWidth(0, 6000);
//		sheet.setColumnWidth(1, 4000);
		
		/* Header styles */
		
		CellStyle headerStyle = workbook.createCellStyle();
		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		headerStyle.setFont(font);
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); // Headers set to Grey	
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
//		font.setFontHeightInPoints((short) 16);
//		font.setBold(true);

		 
		/* Header content */
		Row header = sheet.createRow(0);
		Cell headerCell = header.createCell(0);
		int sheetLength = data.getRow(0).size();
		int sheetHeight = data.getColumn(0).size();
		
		/* data cell styles */
		
		CellStyle dataStyle = workbook.createCellStyle();
		dataStyle.setFont(font);
		
		// TEST CONDITIONAL FORMATTING - IN A FUNCTION TO MINIMIZE
		class CondFormat{
			
		/**
		 * 
		//============================================================
		//TESTING OUT CONDITIONAL FORMATTING
		 // Define a Conditional Formatting rule, which triggers formatting
		 // when cell's value is greater or equal than 100.0 and
		 // applies patternFormatting defined below.
		 ConditionalFormattingRule rule = sheet.createConditionalFormattingRule(
		     ComparisonOperator.GE,
		     "0", // 1st formula
		     null     // 2nd formula is not used for comparison operator GE
		 );
 
		 // Create pattern with red background
		 PatternFormatting patternFmt = rule.createPatternFormatting();
		 PatternFormatting.setFillBackgroundColor(IndexedColor.RED.getIndex());

		 // Define a region containing first column
		 Region [] regions =
		 {
		     new Region(1,(short)1,-1,(short)1)
		 };

		 // Apply Conditional Formatting rule defined above to the regions
		 sheet.addConditionalFormatting(regions, rule);
		
		//===========================================================
		
		 ////
		ConditionalFormattingRule distortion = new ConditionalFormattingRule() {
			
			@Override
			public int getStripeSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public boolean getStopIfTrue() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public int getPriority() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public PatternFormatting getPatternFormatting() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ExcelNumberFormat getNumberFormat() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public IconMultiStateFormatting getMultiStateFormatting() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getFormula2() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getFormula1() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public FontFormatting getFontFormatting() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ConditionFilterData getFilterConfiguration() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public DataBarFormatting getDataBarFormatting() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ConditionType getConditionType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ConditionFilterType getConditionFilterType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public byte getComparisonOperation() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public ColorScaleFormatting getColorScaleFormatting() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public BorderFormatting getBorderFormatting() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public PatternFormatting createPatternFormatting() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public FontFormatting createFontFormatting() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public BorderFormatting createBorderFormatting() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		////
		 * 
		 * 
		**/
			
		}
		
		
		/* initiate data rows and columns */
		
		for (int i = 0; i < sheetHeight; i++) {
			header = sheet.createRow(i);
			for (int j = 0; j < sheetLength; j++) {
				headerCell = header.createCell(j);
			}
		}
		
		// ADD ALL DATA TO SHELL DATA TAB BASICALLY
		int rowNumber = 0;
		int cellNumber;
		for (Row row : sheet) {
		    cellNumber = 0;
		    for (Cell cell : row) {
		    	// 2nd column for height from laser (ft.)
		    	if (cell.getColumnIndex() == 1) {
		    		cell.setCellStyle(headerStyle);
		    		continue;
		    	} else if (cell.getColumnIndex() == 0){
		    		cell.setCellStyle(headerStyle);
		    		continue;
		    	}
		    	
		    	// Once it reaches the size of the row it will continue outer loop
		    	int currentRowSize = data.getRow(rowNumber).size();
		    	
		    	// if it reaches the end of a row
		    	if (rowNumber == 0 && currentRowSize == cellNumber) { 
		    		cellNumber = 0;
		    		break;
		    		
		    	} 
		    	
		    	// if it reaches the start of the summary data at end of row
		    	else if (rowNumber > 0 && currentRowSize-3 == cellNumber) { 
			    	cellNumber = 0;
		    		break;
		    	} 
		    	
		    	// Adding to the shell data sheet as is or as a float and set styles
		    	else {	
		    		// if it's a header
		    		if (!data.getDataPoint(rowNumber, cellNumber).matches("-?\\d+(\\.\\d+)?") || data.getDataPoint(rowNumber, cellNumber).contains("\u00B0")) {
		    			cell.setCellValue(data.getDataPoint(rowNumber, cellNumber));
		    			cell.setCellStyle(headerStyle);
		
		    		} 
		    		// adds normal shell data as floats to the cell
		    		else {
		    			cell.setCellValue(Float.parseFloat(data.getDataPoint(rowNumber, cellNumber)));
		    			// accounting for if it's a header
		    			if (cellNumber == 0 || rowNumber == 1) cell.setCellStyle(headerStyle);
		    			// apply regular cell data - TODO add conditional formatting here
		    			else {
		    				cell.setCellStyle(dataStyle);
		    			}
		    		}
		    	}
			    sheet.autoSizeColumn(cellNumber);
		    	cellNumber++;
		    }
		    rowNumber++;
		}
		

		
		// Create a new row for header to convert arc length/height --> ft.
		sheet.shiftRows(2, 3, 1);
		sheet.createRow(2);
		// initialize the cells created for headers
		for (int i = 0; i < sheetLength; i++) {
			sheet.getRow(2).createCell(i);
			sheet.getRow(2).getCell(i).setCellStyle(headerStyle);
			
		}
		
		// Set header label
		sheet.getRow(2).getCell(0).setCellValue("Height from btm (ft)");
		sheet.getRow(2).getCell(1).setCellValue("Height from scanner (ft)");
		
		// Create a new arc length header that converts in. to ft.
		Cell aboveCell;
		int prevRow = 1;
		int prevColumn = 3;
		for (Cell cell : sheet.getRow(2)) {
			
			aboveCell = sheet.getRow(prevRow).getCell(prevColumn);
			
			// header label
			if (cell.getColumnIndex() == 2) {
				cell.setCellValue("arc length/height (ft.)");
			} 
			
			// Convert arc lengths into ft
			else if (cell.getColumnIndex() > 2) {
				cell.setCellFormula(aboveCell.getNumericCellValue() + "/12");
				prevColumn++;
			}
		}
		
		sheet.getRow(2).getCell(2).setCellValue("arc length/height (ft.)");

		
		
		for (Row row : sheet) {
			// check if it's after the 3 header rows
			if (row.getCell(0).getRowIndex() > 2) {
				// Fills in values to convert arc height into feet column 1
				for (Cell cell : row) {
					
					if (cell.getColumnIndex() == 1) {
			    		cell.setCellFormula(row.getCell(cell.getColumnIndex()+1).toString() + "/12");
					}
					
//					if (cell.getStringCellValue().isEmpty()) break;

				}
				// Fills in values for Height from btm
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// First value on Height from btm (ft) = 2
						if (cell.getRowIndex() == 3) cell.setCellValue(0);
						
						if (cell.getRowIndex() > 3) {
							cell.setCellFormula(
								sheet.getRow(cell.getRowIndex()-1).getCell(0).getAddress() + // get the cell directly above
								"+ABS(" +
								(row.getCell(cell.getColumnIndex()+1).getAddress().toString()) + // gets the cell 1 column to the right
								"-" +
								(sheet.getRow(cell.getRowIndex()-1).getCell(cell.getColumnIndex()+1).getAddress().toString()) + // get the cell 1 above, 1 right
								")"
								);
						}
					}
					
//					if (cell.getNumericCellValue() == 0) break;
				}
				
			}
//			if (row.getCell(2).toString().isEmpty()) break;
		}
		
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		
		
		
//		for (Row row: sheet) {
//			row.createCell(0);
//		}
		
//		headerCell.setCellStyle(headerStyle);
//		 
//		headerCell = header.createCell(1);
//		headerCell.setCellValue("Age");
//		headerCell.setCellStyle(headerStyle);
		
		/* cell styles */
		
//		CellStyle style = workbook.createCellStyle();
//		style.setWrapText(true);
		 
//		Row row = sheet.createRow(2);
//		Cell cell = row.createCell(0);
//		cell.setCellValue("John Smith");
//		cell.setCellStyle(style);
//		 
//		cell = row.createCell(1);
//		cell.setCellValue(20);
//		cell.setCellStyle(style);
		
		
		/**
		 * TODO: for Shell data
		 * -Add in Height from btm (ft)
		 */
		
		
		
		
		/* close and save */
		
//		File currDir = new File("E:/InspectionData/Dropbox (Powers EI)/3D Scan/3D Data Automation/Test files/ ");
		File currDir = new File("C:/Users/Junn/git/TankShellDataAnalysis/ ");
		String path = currDir.getAbsolutePath();
		String fileLocation1 = path.substring(0, path.length() - 1) + "temp.xlsx";
		 
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(fileLocation1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.write(outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
