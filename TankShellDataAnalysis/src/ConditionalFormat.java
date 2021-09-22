import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

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
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ConditionalFormat {
	
	ConditionalFormatting d = new ConditionalFormatting() {
		
		@Override
		public void setRule(int arg0, ConditionalFormattingRule arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setFormattingRanges(CellRangeAddress[] arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public ConditionalFormattingRule getRule(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getNumberOfRules() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public CellRangeAddress[] getFormattingRanges() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void addRule(ConditionalFormattingRule arg0) {
			// TODO Auto-generated method stub
			
		}
	};

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
	
}
