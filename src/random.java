import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class random extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		stage.getIcons().add(new Image(random.class.getResourceAsStream("unnamed.png")));
		// TODO Auto-generated method stub
		try{
		FXMLLoader loader =new  FXMLLoader(getClass().getResource("document.fxml"));
	    Pane root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Random Number Generator");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		Application.launch(args);
	}

}

