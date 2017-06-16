import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

class MyFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	// ************  �����o�ϐ�  ******************
	public JButton btnWrite;
	public JButton btnBairitsu;
	public JButton btnHiduke;

	public JComboBox<String> selectSisu;
	public JTextField t_year;
	public JComboBox<String> selectMonth;
	public JComboBox<String> selectDay;
	public JTextField t_hajimarine;
	public JTextField t_takane;
	public JTextField t_yasune;
	public JTextField t_owarine;
	public JTextField t_dekidaka;

// ************  �R���X�g���N�^  ****************
	MyFrame(String title){
		
	// �t���[���T�C�Y�ƃ��C�A�E�g
		setTitle(title);
		setSize(300, 330);
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
		selectSisu = new JComboBox<String>(nameIndex);
		selectSisu.setPreferredSize(new Dimension(150,20));

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);

		String[] nameMonth = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		String[] nameDay = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		t_year = new JTextField(4);
		t_year.setText(String.valueOf(year));
		selectMonth = new JComboBox<String>(nameMonth);
		selectMonth.setPreferredSize(new Dimension(40,20));
		selectMonth.setSelectedIndex(month - 1);
		selectDay = new JComboBox<String>(nameDay);
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
		p1.add(t_year); p1.add(selectMonth); p1.add(selectDay);
		p1.add(btnHiduke);
		p1.add(selectSisu);
		p1.add(l_owarine); p1.add(t_owarine);
		p1.add(l_dekidaka); p1.add(t_dekidaka);
		p1.add(l_hajimarine); p1.add(t_hajimarine);
		p1.add(l_takane); p1.add(t_takane);
		p1.add(l_yasune); p1.add(t_yasune);
		p1.add(btnWrite);
		p1.add(btnBairitsu);

		Container contentPane = getContentPane();
		contentPane.add(p1);
	}
	
	public void showErrorMsg(String errorMsg){
		JLabel msg = new JLabel(errorMsg);
		JOptionPane.showMessageDialog(this, msg);
	}

}
