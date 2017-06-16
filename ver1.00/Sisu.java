//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.*;


class Sisu{
// ********************  �����o�ϐ�  **********************
	private String filename;
	private Workbook wb;	
	private Sheet sheet;
	private Row row;
	private DataFormat format;
	
	private String nameSisu;
	private String year;
	private String month;
	private String day;
	
	private double hajimarine;
	private double takane;
	private double yasune;
	private double owarine;
	private double dekidaka;
	
	
// ******************  �R���X�g���N�^  ********************
	
	Sisu(String newSisu, String newYear, String newMonth, String newDay){
		nameSisu = newSisu;
		
		year = newYear;
		month = newMonth;
		day = newDay;
		
		filename = newYear + "�w������.xlsx";
		wb = null;
	}

// ****************  �w���̐ݒ胁�\�b�h  ******************
	
	public void setSisu(double h, double t, double y, double o, double d){
		hajimarine = h;
		takane = t;
		yasune = y;
		owarine = o;
		dekidaka = d;
	}
	
	
// **************  �w���̏����o�����\�b�h  ****************
	
	public void writeSisu(){
	// �t�@�C���̓ǂݍ���
		this.readFile(filename);
		
	// �V�[�g���J�����s��}��
		sheet = wb.getSheet(nameSisu);
		sheet.shiftRows(1, 365, 1);
		row = sheet.createRow(1);

	// �Z���̍쐬
		format = wb.createDataFormat();
		
		this.setValue(0, year + "/" + month + "/" + day, "yyyy/mm/dd");
		this.setValue(1, hajimarine, "0.00");
		this.setValue(2, takane, "0.00");
		this.setValue(3, yasune, "0.00");
		this.setValue(4, owarine, "0.00");
		this.setValue(5, dekidaka, "0");
		this.setFormula(7, "E2-E3", "0.00;[RED]-0.00");
		this.setFormula(8, "H2/E3*100", "0.00;[RED]-0.00");
		
	// �t�@�C���֏�������
		this.writeFile(filename);
	}
	
	
// **************  �{���̏����o�����\�b�h  ****************
	
	public void writeBairitsu(){
	// �t�@�C���̓ǂݍ���
		this.readFile(filename);
		
	// �V�[�g���J�����s��}��
		sheet = wb.getSheet(nameSisu);
		sheet.shiftRows(1, 365, 1);
		row = sheet.createRow(1);
		
	// �Z���̍쐬
		format = wb.createDataFormat();
		String[] nameIndex = {"���o����", "TOPIX", "JPX400", "2��", "�}�U�[�Y", "JASDAQ"};
		
		this.setValue(0, year + "/" + month + "/" + day, "yyyy/mm/dd");
		for(int i = 0; i < 6; i++){
			this.setFormula(i+1, "\'" + nameIndex[i] + "\'" + "!E2", "0.00");
		}
		this.setFormula(8, "B2/C2", "0.00");
		this.setFormula(9, "B2/D2", "0.00");
		this.setFormula(10, "B2/E2", "0.00");
		this.setFormula(11, "B2/F2", "0.00");
		this.setFormula(12, "B2/G2", "0.00");
		
	// �t�@�C���֏�������
		this.writeFile(filename);
	}
	
	
// **************  �t�@�C���̓ǂݍ��݃��\�b�h  ****************
	
	private void readFile(String newfilename){
		FileInputStream in = null;
		try{
			in = new FileInputStream(newfilename);
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
	
	private void writeFile(String newfilename){
		FileOutputStream out = null;
		try{
			out = new FileOutputStream(newfilename);
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
	
	private void setValue(int cellNo, double value, String form){
		CellStyle style = wb.createCellStyle();
		style.setDataFormat(format.getFormat(form));
		
		Cell cell = row.createCell(cellNo);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}
	
	
// **************  �Z���̒l�ݒ�(value-String)���\�b�h  ****************
		
	private void setValue(int cellNo, String value, String form){
		CellStyle style = wb.createCellStyle();
		style.setDataFormat(format.getFormat(form));
		
		Cell cell = row.createCell(cellNo);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}
	
	
// **************  �Z���̒l�ݒ�(formula)���\�b�h  ****************
	
	private void setFormula(int cellNo, String value, String form){
		CellStyle style = wb.createCellStyle();
		style.setDataFormat(format.getFormat(form));
		
		Cell cell = row.createCell(cellNo);
		cell.setCellFormula(value);
		cell.setCellStyle(style);
	}
}
