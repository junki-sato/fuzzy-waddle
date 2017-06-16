//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.*;


class Excel{
// ********************  メンバ変数  **********************
	
	private String filename;
	private String nameSisu;
	private Workbook wb;
	private Sheet sheet;
	private Row row;
	private DataFormat format;
	
// ******************  コンストラクタ  ********************
	
	Excel(String newSisu, int newYear){
		nameSisu = newSisu;
		filename = newYear + "指数日足.xlsx";
		wb = null;
		sheet = null;
		row = null;
		format = null;
	}
	
// **************  ファイルの読み込みメソッド  ****************
	
	public void readFile(){
		FileInputStream in = null;
		try{
			in = new FileInputStream(filename);
			wb = WorkbookFactory.create(in);
		}catch(IOException e){
			System.out.println(e.toString());
		}catch(InvalidFormatException e){
			System.out.println(e.toString());
		}finally{
			try{
				in.close();
			}catch(IOException e){
				System.out.println(e.toString());
			}
		}
	}
	
	
// **************  ファイルへの書き出しメソッド  ****************
	
	public void writeFile(){
		FileOutputStream out = null;
		try{
			out = new FileOutputStream(filename);
			wb.write(out);
		}catch(IOException e){
			System.out.println(e.toString());
		}finally{
			try{
				out.close();
			}catch(IOException e){
				System.out.println(e.toString());
			}
		}	
	}
		
	
// **************  セルの値設定(value-double)メソッド  ****************
	
	public void setValue(int cellNo, double value, String form){
		CellStyle style = wb.createCellStyle();
		style.setDataFormat(format.getFormat(form));
		
		Cell cell = row.createCell(cellNo);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}
	
// **************  セルの値設定(value-int)メソッド  ****************
	
	public void setValue(int cellNo, int value, String form){
		CellStyle style = wb.createCellStyle();
		style.setDataFormat(format.getFormat(form));
		
		Cell cell = row.createCell(cellNo);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}
	
// **************  セルの値設定(value-String)メソッド  ****************
		
	public void setValue(int cellNo, String value, String form){
		CellStyle style = wb.createCellStyle();
		style.setDataFormat(format.getFormat(form));
		
		Cell cell = row.createCell(cellNo);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}
	
	
// **************  セルの値設定(formula)メソッド  ****************
	
	public void setFormula(int cellNo, String value, String form){
		CellStyle style = wb.createCellStyle();
		style.setDataFormat(format.getFormat(form));
		
		Cell cell = row.createCell(cellNo);
		cell.setCellFormula(value);
		cell.setCellStyle(style);
	}


//**************  最新データの日付との比較メソッド  ****************

	public int compareDate(String date){
	// ファイル、シートの読み込み
		this.readFile();
		sheet = wb.getSheet(nameSisu);
		row = sheet.getRow(1);
		Cell cell = row.getCell(0);
		
		if(date.equals(cell.getStringCellValue()))
			return -1;
		
		return 1;
	}
	
	
// **************  各種メソッド　　*********************
	
	public void getSheet(String name){sheet = wb.getSheet(name);}
	public void shiftRows(int i, int j, int k){sheet.shiftRows(i, j, k);}
	public void createRow(int i){row = sheet.createRow(i);}
	public void createDataFormat(){format = wb.createDataFormat();}

}
