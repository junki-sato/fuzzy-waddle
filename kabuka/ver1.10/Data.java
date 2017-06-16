class Data{
	// ************  メンバ変数  ******************
	private String nameSisu;
	private int year;
	private String month;
	private String day;
	private double hajimarine;
	private double takane;
	private double yasune;
	private double owarine;
	private int dekidaka;
	private String[] nameIndex = {"日経平均", "TOPIX", "JPX400", "2部", "マザーズ", "JASDAQ"};
	
	public MyFrame frame;
	private Excel excel;
	
// ************  コンストラクタ  ****************
	Data(String title){
		frame = new MyFrame(title);
	}
	
// **********  日付を読み込むメソッド  **************
	public void readHiduke(){
		try{
			year = Integer.parseInt(frame.t_year.getText());
			month = (String)frame.selectMonth.getSelectedItem();
			day = (String)frame.selectDay.getSelectedItem();
		}catch(NumberFormatException e){
			frame.showErrorMsg("日付がちゃんと設定されていません");
			System.out.println("日付 をちゃんと設定して");
			return;
		}
		System.out.println("日付 = " + year + "/" + month + "/" + day);
	}


// ************** 書き出しメソッド  ****************

	public void writeSisu(String select){
	// 日付設定確認
		if(year == 0|| month == null|| day == null){
			System.out.println("日付が設定されてないよ");
			return;
		}

	// nameSisuの読み取り
		if(select.equals("指数")){
			nameSisu = (String)frame.selectSisu.getSelectedItem();
		}else if(select.equals("倍率")){
			nameSisu = "倍率";
		}else{
			return;
		}

	// Excelオブジェクト生成
		excel = new Excel(nameSisu, year);

	// 二重書き込みチェック
		int i = excel.compareDate(year + "/" + month + "/" + day);
		if (i == -1){
			System.out.println("その日の" + nameSisu + "のデータはもうあるよ");
			return;
		}

	// 指数の読み取り
		if(select.equals("指数"))
			if(readVal() == -1) return;
		
	// 倍率書き込み前の指数チェック
		if(select.equals("倍率")){
			for(int j = 0; j < 6; j++){
				Excel check = new Excel(nameIndex[j], year);
				if(check.compareDate(year + "/" + month + "/" + day) == 1){
					System.out.println(nameIndex[j] + "がまだ書き込まれてない");
					return;
				}
					
			}
		}
			

	// ファイルの読み込み
		excel.readFile();

	// シートを開く＆行を挿入&セルの作成
		excel.getSheet(nameSisu);
		excel.shiftRows(1, 365, 1);
		excel.createRow(1);
		excel.createDataFormat();
		excel.setValue(0, year + "/" + month + "/" + day, "yyyy/mm/dd");
		this.setSisuFormat(nameSisu);
		
	// ファイルへ書き込み
		excel.writeFile();

	// 入力値の表示
		if(select.equals("指数")){
			System.out.println("インデックス = " + nameSisu);
			System.out.println("始値 = " + hajimarine);
			System.out.println("高値 = " + takane);
			System.out.println("安値 = " + yasune);
			System.out.println("終値 = " + owarine);
			System.out.println("出来高 = " + dekidaka);
		}else if(select.equals("倍率")){
			System.out.println("倍率書き込みOK");
		}else{return;}

	}


// **********  入力値を読み込むメソッド  **************
	
	private int readVal(){
		try{
			hajimarine = Double.parseDouble(frame.t_hajimarine.getText());
			takane = Double.parseDouble(frame.t_takane.getText());
			yasune = Double.parseDouble(frame.t_yasune.getText());
			owarine = Double.parseDouble(frame.t_owarine.getText());
			dekidaka = Integer.parseInt(frame.t_dekidaka.getText());
		}catch(NumberFormatException e){
			frame.showErrorMsg("数値が入力されていない、または数値でない値が入力されています");
			System.out.println("数値をちゃんと入力して");
			return -1;
		}
		
		if(takane < hajimarine || takane < yasune || takane < owarine){
			System.out.println("高値が一番高いはず");
			return -1;
		}
		if(yasune > hajimarine || yasune > takane || yasune > owarine){
			System.out.println("安値が一番安いはず");
			return -1;
		}
		
		return 1;
	}


// **************  書き出しフォーマット設定メソッド  ****************

	private void setSisuFormat(String name){
		if(name.equals("倍率")){
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
