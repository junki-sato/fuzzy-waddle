import java.awt.event.*;

public class IndexManager{
	public static void main(String args[]){
		Data data = new Data("インデックスマネージャ");
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
						data.writeSisu("指数");	
					}
				}
				);
		
		data.frame.btnBairitsu.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						data.writeSisu("倍率");
					}
				}
				);
		
	}
	
}
