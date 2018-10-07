import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class calcu extends Application {
	
	/** text中显示所有信息 */
	Text text = new Text("");
 
	public void start(Stage pr) {
		text.setFill(Color.BLACK);
		text.setFont(Font.font("黑体", 30));
		
		/** 展示的数字 */
		String[] number = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		
		Button[] bt = new Button[10];
		String[] operate= {"/","X","-","+","="};
		Button[] o=new Button[5];
		GridPane gdpane = new GridPane();
		for(int i=0;i<5;i++) {
			o[i]=new Button(operate[i]);
			o[i].setPrefSize(80, 40);
			String s=operate[i].toString();     //字符串数组转字符串
			o[i].setOnAction(e->add(s));
		}
		
		Button zc = new Button("%");
		zc.setOnAction(e->add("%"));
		
		Button ce = new Button("AC");
		ce.setOnAction(e->add("AC"));
		
		Button yes = new Button("√");
		yes.setOnAction(e->add("√"));
		
		Button x2 = new Button("x²");
		x2.setOnAction(e->add("x2"));
		
		Button x1 = new Button("1/x");
		x1.setOnAction(e->add("1/x"));
		
		Button c = new Button("C");
		c.setOnAction(e->add("AC"));
		
		Button del = new Button("←");
		del.setOnAction(e->add("←"));
		
		Button sin = new Button("sin");
		sin.setOnAction(e->add("sin"));
		
		Button cos = new Button("cos");
		cos.setOnAction(e->add("cos"));
		
		Button tan = new Button("tan");
		tan.setOnAction(e->add("tan"));
		
		Button log = new Button("log");
		log.setOnAction(e->add("log"));
		
		Button ln = new Button("ln");
		ln.setOnAction(e->add("ln"));
		
		Button addandmin = new Button("±");
		addandmin.setOnAction(e->add("±"));
		
		Button point = new Button(".");
		point.setOnAction(e->add("."));
		
		for (int i = 0; i < 10; i++) {
			bt[i] = new Button(number[i]);
			bt[i].setPrefSize(80, 40);
			bt[i].setStyle("-fx-base:white");
		}
 
		sin.setPrefSize(80, 40);
		cos.setPrefSize(80, 40);
		tan.setPrefSize(80, 40);
		log.setPrefSize(80, 40);
		ln.setPrefSize(80, 40);
		ce.setPrefSize(80, 40);
		zc.setPrefSize(80, 40);
		yes.setPrefSize(80, 40);
		x2.setPrefSize(80, 40);
		x1.setPrefSize(80, 40);
		c.setPrefSize(80, 40);
		del.setPrefSize(80, 40);
		addandmin.setPrefSize(80, 40);
		point.setPrefSize(80, 40);
 
		o[4].setStyle("-fx-base: LIGHTBLUE");
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(8, 8, 8, 8));
 
		HBox hbox = new HBox();
		BorderPane pane1 = new BorderPane();
		pane1.setRight(text);
		pane1.setBottom(hbox);
		pane.setTop(pane1);
 
		gdpane.add(sin, 0, 1);
		gdpane.add(cos, 0, 2);
		gdpane.add(tan, 0, 3);
		gdpane.add(log, 0, 4);
		gdpane.add(ln, 0, 5);
		gdpane.add(zc, 1, 0);
		gdpane.add(yes, 0, 0);
		gdpane.add(x2, 2, 0);
		gdpane.add(x1, 3, 0);
		gdpane.add(ce, 1, 1);
		gdpane.add(c, 2, 1);
		gdpane.add(del, 3, 1);
		o[4].setPrefSize(80, 80);
		gdpane.add(o[4], 4, 4, 1, 2);
		for(int i=0;i<4;i++){
			gdpane.add(o[i], 4, i);
		}
		
		for (int i = 2, count = 7; i < 5; i++, count = count - 3){
			for (int j = 0; j < 3; j++){
				gdpane.add(bt[count + j], j + 1, i);
			}
		}
		gdpane.add(bt[0], 2, 5);
		gdpane.add(addandmin, 1, 5);
		gdpane.add(point, 3, 5);
	
		pane.setCenter(gdpane);
		for (int i = 0; i < 10; i++) {
			String carriage = String.valueOf(i);
			bt[i].setOnAction(e -> add(carriage));
		}
		Scene scene = new Scene(pane);
		pr.setTitle("calculate");
		pr.setScene(scene);
		pr.show();
 
	}
 
	/**
	 * 检查text字符串前一个是否是+-x/.运算，保证不重复显示
	 * @return
	 */
	private boolean checkTheRight(){
		String t = text.getText();
		if(t.length() < 2){
			return true;
		}
		char lastChar = t.charAt(t.length() - 2);
		switch(lastChar){
			case '+':
			case '-':
			case 'X':
			case '/':
				return false;
		}
		return true;
	}
	
	/**
	 * 主方法 进行按钮选择运算
	 * @param s
	 */
	private void add(String s) {
		String out = "";
		if ((text.getText().equals("0") && text.getText().length() > 1) || text.getText().equals("404"))
			text.setText("");
		switch (s) {
		case "AC":
			out = "0";
			break;
		case "±":
			try {
				out = text.getText();
				float num = Float.parseFloat(text.getText()) * (-1);
				if(num % 1.0 == 0){
					out = String.valueOf((int) num);
				} else {
					out = String.valueOf(num);
				}
			} catch (NumberFormatException e) {
			}
		break;
		case "+":
		case "-":
		case "X":		//这里的乘是大写字母X
		case "/":
			if(checkTheRight()) {
				out = text.getText() + " " + s + " ";
			} else {
				out = text.getText();
			}
			break;
		case "%":
			try {
				Float getNum = Float.parseFloat(text.getText());
				float setNum = getNum / 100;
				if (setNum % 1 == 0)
					out = String.valueOf((int) setNum);
				else
					out = String.valueOf(setNum);
			} catch (NumberFormatException e) {
				System.err.println("Wrong Input");
				out = text.getText();
			}
			break;
		case "√":
			try {
				out = text.getText();
				float num = (float) Math.sqrt(Float.parseFloat(out));
				if(num % 1.0 == 0){
					out = String.valueOf((int) num);
				} else {
					out = String.valueOf(num);
				}
			} catch (NumberFormatException e) {
				System.err.println("Wrong Input");
				out = text.getText();
			}
			break;
		case "1/x":
			try {
				out = text.getText();
				float num = 1 / Float.parseFloat(out);
				if(num % 1.0 == 0){
					out = String.valueOf((int) num);
				} else {
					out = String.valueOf(num);
				}
			} catch (NumberFormatException e) {
				System.err.println("Wrong Input");
				out = text.getText();
			}
			break;
		case "x2":
			try {
				Float getNum = Float.parseFloat(text.getText());
				float setNum = (float) Math.pow(getNum, 2d);
				if (setNum % 1 == 0)
					out = String.valueOf((int) setNum);
				else
					out = String.valueOf(setNum);
			} catch (NumberFormatException e) {
				System.err.println("Wrong Input");
				out = text.getText();
			}
			break;
		case "←":
			out = text.getText();
			out = out.substring(0,out.length() - 1);
			break;
		case "=":
			if(text.getText() == ""){
				out = "0";
			} else {
				out = getResult();
			}
			break;
		case ".":
			if(checkTheRight()){
				out = text.getText() + s;
			} else {
				out = text.getText();
			}
			break;
		case "sin":
			try {
				Float getNum = Float.parseFloat(text.getText());
				float setNum = (float) Math.sin(getNum * Math.PI / 180);
				if (setNum % 1 == 0)
					out = String.valueOf((int) setNum);
				else
					out = String.valueOf(setNum);
			} catch (NumberFormatException e) {
				System.err.println("Wrong Input");
				out = text.getText();
			}
			break;
		case "cos":
			try {
				Float getNum = Float.parseFloat(text.getText());
				float setNum = (float) Math.cos(getNum * Math.PI / 180);
				if (setNum % 1 == 0)
					out = String.valueOf((int) setNum);
				else
					out = String.valueOf(setNum);
			} catch (NumberFormatException e) {
				System.err.println("Wrong Input");
				out = text.getText();
			}
			break;
		case "tan":
			try {
				Float getNum = Float.parseFloat(text.getText());
				float setNum = (float) Math.tan(getNum * Math.PI / 180);
				if (setNum % 1 == 0)
					out = String.valueOf((int) setNum);
				else
					out = String.valueOf(setNum);
			} catch (NumberFormatException e) {
				System.err.println("Wrong Input");
				out = text.getText();
			}
			break;
		case "log":
			try {
				Float getNum = Float.parseFloat(text.getText());
				float setNum = (float) Math.log10(getNum);
				if (setNum % 1 == 0)
					out = String.valueOf((int) setNum);
				else
					out = String.valueOf(setNum);
			} catch (NumberFormatException e) {
				System.err.println("Wrong Input");
				out = text.getText();
			}
			break;
		case "ln":
			try {
				Float getNum = Float.parseFloat(text.getText());
				float setNum = (float) Math.log(getNum);
				if (setNum % 1 == 0)
					out = String.valueOf((int) setNum);
				else
					out = String.valueOf(setNum);
			} catch (NumberFormatException e) {
				System.err.println("Wrong Input");
				out = text.getText();
			}
			break;
		default:
			if(text.getText().equals("0")){
				out = s;
			} else {
				out = text.getText() + s;
			}
			break;
		}
		text.setText(out);
	}
	
	/**
	 * 获取结果
	 * @return
	 */
	private String getResult() {
		try {
			String[] textbox = text.getText().split(" ");
			float result = Float.parseFloat(textbox[0]);
			for (int i = 2; i < textbox.length; i += 2) {
				float num = Float.parseFloat(textbox[i]);
				switch (textbox[i - 1]) {
				case "+":
					result += num;
					break;
				case "-":
					result -= num;
					break;
				case "/":
					result = Float.parseFloat(textbox[i - 2]) / num;
					break;
				case "X":
					result *= num;
					break;
				}
			}
			if (result % 1 == 0)
				return String.valueOf((int) result);
			else
				return String.valueOf(result);
		} catch (NumberFormatException e) {
			System.err.println("wrong input || null String");
			return "404";
		}
	}
 
	public static void main(String[] args) {
		Application.launch(args);
	}
 
}