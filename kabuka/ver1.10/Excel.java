//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.*;


class Excel{
// ********************  �����o�ϐ�  **********************
	
	private String filename;
	private String nameSisu;
	private Workbook wb;
	private Sheet sheet;
	private Row row;
	private DataFormat format;
	
// ******************  �R���X�g���N�^  ********************
	
	Excel(String newSisu, int newYear){
		nameSisu = newSisu;
		filename = newYear + "�w������.xlsx";
		wb = null;
		sheet = null;
		row = null;
		format = null;
	}
	
// **************  �t�@�C���̓ǂݍ��݃��\�b�h  ****************
	
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
	
	
// **************  �t�@�C���ւ̏����o�����\�b�h  ****************
	
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
		
	
// **************  �Z���̒l�ݒ�(value-double)���\�b�h  ****************
	
	public void setValue(int cellNo, double value, String form){
		CellStyle style = wb.createCellStyle();
		style.setDataFormat(format.getFormat(form));
		
		Cell cell = row.createCell(cellNo);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}
	
// **************  �Z���̒l�ݒ�(value-int)���\�b�h  ****************
	
	public void setValue(int cellNo, int value, String form){
		CellStyle style = wb.createCellStyle();
		style.setDataFormat(format.getFormat(form));
		
		Cell cell = row.createCell(cellNo);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}
	
// **************  �Z���̒l�ݒ�(value-String)���\�b�h  ****************
		
	public void setValue(int cellNo, String value, String form){
		CellStyle style = wb.createCellStyle();
		style.setDataFormat(format.getFormat(form));
		
		Cell cell = row.createCell(cellNo);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}
	
	
// **************  �Z���̒l�ݒ�(formula)���\�b�h  ****************
	
	public void setFormula(int cellNo, String value, String form){
		CellStyle style = wb.createCellStyle();
		style.setDataFormat(format.getFormat(form));
		
		Cell cell = row.createCell(cellNo);
		cell.setCellFormula(value);
		cell.setCellStyle(style);
	}


//**************  �ŐV�f�[�^�̓��t�Ƃ̔�r���\�b�h  ****************

	public int compareDate(String date){
	// �t�@�C���A�V�[�g�̓ǂݍ���
		this.readFile();
		sheet = wb.getSheet(nameSisu);
		row = sheet.getRow(1);
		Cell cell = row.getCell(0);
		
		if(date.equals(cell.getStringCellValue()))
			return -1;
		
		return 1;
	}
	
	
// **************  �e�탁�\�b�h�@�@*********************
	
	public void getSheet(String name){sheet = wb.getSheet(name);}
	public void shiftRows(int i, int j, int k){sheet.shiftRows(i, j, k);}
	public void createRow(int i){row = sheet.createRow(i);}
	public void createDataFormat(){format = wb.createDataFormat();}

}
