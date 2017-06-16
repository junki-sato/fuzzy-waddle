import javax.swing.*;
import java.awt.*;
import java.util.Calendar;


class MyFrame extends JFrame{
// ************  �����o�ϐ�  ******************
	public JButton btnWrite;
	public JButton btnBairitsu;
	public JButton btnHiduke;

	private JComboBox selectSisu;
	private JTextField t_year;
	private JComboBox selectMonth;
	private JComboBox selectDay;
	private JTextField t_hajimarine;
	private JTextField t_takane;
	private JTextField t_yasune;
	private JTextField t_owarine;
	private JTextField t_dekidaka;

	public String in_Sisu;
	public int in_year;
	public String in_month;
	public String in_day;
	public double in_hajimarine;
	public double in_takane;
	public double in_yasune;
	public double in_owarine;
	public double in_dekidaka;

// ************  �R���X�g���N�^  ****************
	MyFrame(String title){

	// �t���[���T�C�Y�ƃ��C�A�E�g
		setTitle(title);
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		FlowLayout layout = new FlowLayout();
		setLayout(layout);


	// �p�l���̐ݒ�
		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(170,250));
		p1.setLayout(layout);

	// �R���|�[�l���g�̍쐬
		String[] nameIndex = {"���o����", "TOPIX", "JPX400", "2��", "�}�U�[�Y", "JASDAQ"};
		selectSisu = new JComboBox(nameIndex);
		selectSisu.setPreferredSize(new Dimension(150,20));


		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);

		String[] nameMonth = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		String[] nameDay = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		t_year = new JTextField(4);
		t_year.setText(String.valueOf(year));
		selectMonth = new JComboBox(nameMonth);
		selectMonth.setPreferredSize(new Dimension(40,20));
		selectMonth.setSelectedIndex(month - 1);
		selectDay = new JComboBox(nameDay);
		selectDay.setPreferredSize(new Dimension(40,20));
		selectDay.setSelectedIndex(day - 1);


		JLabel l_hajimarine = new JLabel("�n�@�l");
		JLabel l_takane = new JLabel("���@�l");
		JLabel l_yasune = new JLabel("���@�l");
		JLabel l_owarine = new JLabel("�I�@�l");
		JLabel l_dekidaka = new JLabel("�o����");

		t_hajimarine = new JTextField(10);
		t_takane = new JTextField(10);
		t_yasune = new JTextField(10);
		t_owarine = new JTextField(10);
		t_dekidaka = new JTextField(10);
		btnWrite = new JButton("��������");
		btnBairitsu = new JButton("�{��");
		btnHiduke = new JButton("���t�̐ݒ�");
		

	// �R���|�[�l���g���p�l���ɑ}��
		p1.add(t_year);
		p1.add(selectMonth);
		p1.add(selectDay);
		p1.add(btnHiduke);
		p1.add(selectSisu);
		p1.add(l_owarine);
		p1.add(t_owarine);
		p1.add(l_dekidaka);
		p1.add(t_dekidaka);
		p1.add(l_hajimarine);
		p1.add(t_hajimarine);
		p1.add(l_takane);
		p1.add(t_takane);
		p1.add(l_yasune);
		p1.add(t_yasune);
		p1.add(btnWrite);
		p1.add(btnBairitsu);

		Container contentPane = getContentPane();
		contentPane.add(p1);
	}

// **********  ���t��ǂݍ��ރ��\�b�h  **************
	void readHiduke(){
		try{
			in_year = Integer.parseInt(t_year.getText());
			in_month = (String)selectMonth.getSelectedItem();
			in_day = (String)selectDay.getSelectedItem();
		}catch(NumberFormatException e){
			JLabel msg = new JLabel("���t�������Ɛݒ肳��Ă��܂���");
			JOptionPane.showMessageDialog(this, msg);
			return;
		}
	}
	
	
// **********  ���͒l��ǂݍ��ރ��\�b�h  **************
	 int readVal(){
		try{
			in_Sisu = (String)selectSisu.getSelectedItem();
			in_hajimarine = Double.parseDouble(t_hajimarine.getText());
			in_takane = Double.parseDouble(t_takane.getText());
			in_yasune = Double.parseDouble(t_yasune.getText());
			in_owarine = Double.parseDouble(t_owarine.getText());
			in_dekidaka = Double.parseDouble(t_dekidaka.getText());
		}catch(NumberFormatException e){
			JLabel msg = new JLabel("���l�����͂���Ă��Ȃ��A�܂��͐��l�łȂ��l�����͂���Ă��܂�");
			JOptionPane.showMessageDialog(this, msg);
			return -1;
		}

		return 1;
	}

}
