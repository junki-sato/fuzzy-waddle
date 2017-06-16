import java.awt.event.*;

public class IndexManager{
	public static void main(String args[]){
		MyFrame frame = new MyFrame("�C���f�b�N�X�}�l�[�W��");
		frame.setVisible(true);
		
		frame.btnHiduke.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						frame.readHiduke();
						String year = String.valueOf(frame.in_year);
						String month = frame.in_month;
						String day = frame.in_day;
						System.out.println("���t = " + year + "/" + month + "/" + day);
						
						if(year == "0"|| month == null|| day == null){
							System.out.println("���t�������Ɠ��͂���");
						}

					}
				}
			);
		
		frame.btnWrite.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						int i = frame.readVal();
						if(i == -1){
							System.out.println("���l�������Ɠ��͂���");
							return;
						}else{
							String year = String.valueOf(frame.in_year);
							String month = frame.in_month;
							String day = frame.in_day;
							
							if(year == "0"|| month == null|| day == null){
								System.out.println("���t���ݒ肳��ĂȂ���");
								return;
							}else{
								String sname = frame.in_Sisu;
								double h = frame.in_hajimarine;
								double t = frame.in_takane;
								double y = frame.in_yasune;
								double o = frame.in_owarine;
								double d = frame.in_dekidaka;
								
								System.out.println("�C���f�b�N�X = " + sname);
								System.out.println("�n�l = " + h);
								System.out.println("���l = " + t);
								System.out.println("���l = " + y);
								System.out.println("�I�l = " + o);
								System.out.println("�o���� = " + d);
								
								Sisu kakikomi = new Sisu(sname, year, month, day);
								kakikomi.setSisu(h, t, y, o, d);
								kakikomi.writeSisu();
							}
						}
					}
				}
			);
		
		frame.btnBairitsu.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						String year = String.valueOf(frame.in_year);
						String month = frame.in_month;
						String day = frame.in_day;
			
						if(year == "0"|| month == null|| day == null){
							System.out.println("���t���ݒ肳��ĂȂ���");
							return;
						}else{
							Sisu kakikomi2 = new Sisu("�{��", year, month, day);
							kakikomi2.writeBairitsu();
							System.out.println("�{����������OK");
						}
					}
				}
			);
		
	}
	
}
