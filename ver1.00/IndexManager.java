import java.awt.event.*;

public class IndexManager{
	public static void main(String args[]){
		MyFrame frame = new MyFrame("インデックスマネージャ");
		frame.setVisible(true);
		
		frame.btnHiduke.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						frame.readHiduke();
						String year = String.valueOf(frame.in_year);
						String month = frame.in_month;
						String day = frame.in_day;
						System.out.println("日付 = " + year + "/" + month + "/" + day);
						
						if(year == "0"|| month == null|| day == null){
							System.out.println("日付をちゃんと入力して");
						}

					}
				}
			);
		
		frame.btnWrite.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						int i = frame.readVal();
						if(i == -1){
							System.out.println("数値をちゃんと入力して");
							return;
						}else{
							String year = String.valueOf(frame.in_year);
							String month = frame.in_month;
							String day = frame.in_day;
							
							if(year == "0"|| month == null|| day == null){
								System.out.println("日付が設定されてないよ");
								return;
							}else{
								String sname = frame.in_Sisu;
								double h = frame.in_hajimarine;
								double t = frame.in_takane;
								double y = frame.in_yasune;
								double o = frame.in_owarine;
								double d = frame.in_dekidaka;
								
								System.out.println("インデックス = " + sname);
								System.out.println("始値 = " + h);
								System.out.println("高値 = " + t);
								System.out.println("安値 = " + y);
								System.out.println("終値 = " + o);
								System.out.println("出来高 = " + d);
								
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
							System.out.println("日付が設定されてないよ");
							return;
						}else{
							Sisu kakikomi2 = new Sisu("倍率", year, month, day);
							kakikomi2.writeBairitsu();
							System.out.println("倍率書き込みOK");
						}
					}
				}
			);
		
	}
	
}
