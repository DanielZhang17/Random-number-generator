import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class control implements Initializable {
	@FXML
	Button generate;
	@FXML
	TextField t1;
	@FXML
	TextField t2;
	@FXML
	Label num;
	private int click;
	private void handleButtonAction(ActionEvent event) throws IOException{
		click++;
		BufferedWriter br = new BufferedWriter(new FileWriter("log.txt",true));//this is a log file writer that will record the input and will not overwrite the existing content
		 Date date = new Date();//this is to identify the time user accessed the quiz question
		 br.newLine();//start with a new line
		 br.write("created on:"+date);
		 br.newLine();
		 br.write("Max:"+t1.getText()+"\tMin:"+t2.getText());
		 br.newLine();

		double max = 0;
		double min=0;
		boolean vaild = true;
		String s1 = null;
		if(!t1.getText().isEmpty()&&!t2.getText().isEmpty()){
			if(isNumeric(t1.getText()))
				max = Double.parseDouble(t1.getText());
			else{
				vaild = false;
				num.setText("invaild input !");
			}
			if(isNumeric(t2.getText()))
				min = Double.parseDouble(t2.getText());
		}
		else if(!t1.getText().isEmpty()&&isNumeric(t1.getText()))
			max = Double.parseDouble(t1.getText());
		else{
			vaild = false;
			num.setText("0");
		}			
		if(max<min){
			vaild = false;
			num.setText("Range invaild!");
		}
		if(!isNumeric(t1.getText())||!isNumeric(t2.getText())){
			vaild = false;
			num.setText("invaild input !");
		}
		if(vaild){
			s1 = ""+(long)(Math.random()*((max-min)+1)+min);
			num.setText(s1);
		}
		br.write("Generated:"+num);
		br.newLine();
		br.close();
		//only if 5 times of click it upload the info
		if(click>=5){
			click=0;
			File log = new File("log.txt");
			//the following 2 lines of code creates a FTP client and upload the file into the server
			FTPC server = new FTPC ("185.28.20.77","u628180624","19980915", log);//this is creating a FTP client and login and passes the log file into the class
			server.upload();
		}
	}
	public void initialize(URL location, ResourceBundle resources) {
		generate.setOnAction(arg0 -> {
			try {
				handleButtonAction(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
	public boolean isNumeric(String s){
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
}
