class Data{
	// ************  �����o�ϐ�  ******************
	private String nameSisu;
	private int year;
	private String month;
	private String day;
	private double hajimarine;
	private double takane;
	private double yasune;
	private double owarine;
	private int dekidaka;
	private String[] nameIndex = {"���o����", "TOPIX", "JPX400", "2��", "�}�U�[�Y", "JASDAQ"};
	
	public MyFrame frame;
	private Excel excel;
	
// ************  �R���X�g���N�^  ****************
	Data(String title){
		frame = new MyFrame(title);
	}
	
// **********  ���t��ǂݍ��ރ��\�b�h  **************
	public void readHiduke(){
		try{
			year = Integer.parseInt(frame.t_year.getText());
			month = (String)frame.selectMonth.getSelectedItem();
			day = (String)frame.selectDay.getSelectedItem();
		}catch(NumberFormatException e){
			frame.showErrorMsg("���t�������Ɛݒ肳��Ă��܂���");
			System.out.println("���t �������Ɛݒ肵��");
			return;
		}
		System.out.println("���t = " + year + "/" + month + "/" + day);
	}


// ************** �����o�����\�b�h  ****************

	public void writeSisu(String select){
	// ���t�ݒ�m�F
		if(year == 0|| month == null|| day == null){
			System.out.println("���t���ݒ肳��ĂȂ���");
			return;
		}

	// nameSisu�̓ǂݎ��
		if(select.equals("�w��")){
			nameSisu = (String)frame.selectSisu.getSelectedItem();
		}else if(select.equals("�{��")){
			nameSisu = "�{��";
		}else{
			return;
		}

	// Excel�I�u�W�F�N�g����
		excel = new Excel(nameSisu, year);

	// ��d�������݃`�F�b�N
		int i = excel.compareDate(year + "/" + month + "/" + day);
		if (i == -1){
			System.out.println("���̓���" + nameSisu + "�̃f�[�^�͂��������");
			return;
		}

	// �w���̓ǂݎ��
		if(select.equals("�w��"))
			if(readVal() == -1) return;
		
	// �{���������ݑO�̎w���`�F�b�N
		if(select.equals("�{��")){
			for(int j = 0; j < 6; j++){
				Excel check = new Excel(nameIndex[j], year);
				if(check.compareDate(year + "/" + month + "/" + day) == 1){
					System.out.println(nameIndex[j] + "���܂��������܂�ĂȂ�");
					return;
				}
					
			}
		}
			

	// �t�@�C���̓ǂݍ���
		excel.readFile();

	// �V�[�g���J�����s��}��&�Z���̍쐬
		excel.getSheet(nameSisu);
		excel.shiftRows(1, 365, 1);
		excel.createRow(1);
		excel.createDataFormat();
		excel.setValue(0, year + "/" + month + "/" + day, "yyyy/mm/dd");
		this.setSisuFormat(nameSisu);
		
	// �t�@�C���֏�������
		excel.writeFile();

	// ���͒l�̕\��
		if(select.equals("�w��")){
			System.out.println("�C���f�b�N�X = " + nameSisu);
			System.out.println("�n�l = " + hajimarine);
			System.out.println("���l = " + takane);
			System.out.println("���l = " + yasune);
			System.out.println("�I�l = " + owarine);
			System.out.println("�o���� = " + dekidaka);
		}else if(select.equals("�{��")){
			System.out.println("�{����������OK");
		}else{return;}

	}


// **********  ���͒l��ǂݍ��ރ��\�b�h  **************
	
	private int readVal(){
		try{
			hajimarine = Double.parseDouble(frame.t_hajimarine.getText());
			takane = Double.parseDouble(frame.t_takane.getText());
			yasune = Double.parseDouble(frame.t_yasune.getText());
			owarine = Double.parseDouble(frame.t_owarine.getText());
			dekidaka = Integer.parseInt(frame.t_dekidaka.getText());
		}catch(NumberFormatException e){
			frame.showErrorMsg("���l�����͂���Ă��Ȃ��A�܂��͐��l�łȂ��l�����͂���Ă��܂�");
			System.out.println("���l�������Ɠ��͂���");
			return -1;
		}
		
		if(takane < hajimarine || takane < yasune || takane < owarine){
			System.out.println("���l����ԍ����͂�");
			return -1;
		}
		if(yasune > hajimarine || yasune > takane || yasune > owarine){
			System.out.println("���l����Ԉ����͂�");
			return -1;
		}
		
		return 1;
	}


// **************  �����o���t�H�[�}�b�g�ݒ胁�\�b�h  ****************

	private void setSisuFormat(String name){
		if(name.equals("�{��")){
			for(int i = 0; i < 6; i++)
				excel.setFormula(i+1, "\'" + nameIndex[i] + "\'" + "!E2", "0.00");
			excel.setFormula(8, "B2/C2", "0.00");
			excel.setFormula(9, "B2/D2", "0.00");
			excel.setFormula(10, "B2/E2", "0.00");
			excel.setFormula(11, "B2/F2", "0.00");
			excel.setFormula(12, "B2/G2", "0.00");		
		}else{
			excel.setValue(1, hajimarine, "0.00");
			excel.setValue(2, takane, "0.00");
			excel.setValue(3, yasune, "0.00");
			excel.setValue(4, owarine, "0.00");
			excel.setValue(5, dekidaka, "0");
			excel.setFormula(7, "E2-E3", "0.00;[RED]-0.00");
			excel.setFormula(8, "H2/E3*100", "0.00;[RED]-0.00");			
		}
	}
}
