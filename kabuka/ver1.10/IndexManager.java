import java.awt.event.*;

public class IndexManager{
	public static void main(String args[]){
		Data data = new Data("�C���f�b�N�X�}�l�[�W��");
		data.frame.setVisible(true);
		
		data.frame.btnHiduke.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						data.readHiduke();
					}
				}
			);
		
		data.frame.btnWrite.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						data.writeSisu("�w��");	
					}
				}
				);
		
		data.frame.btnBairitsu.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						data.writeSisu("�{��");
					}
				}
				);
		
	}
	
}
