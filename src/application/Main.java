package application;
	
import gui.LoginPage;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			MoveTo startPoint = new MoveTo(108, 71);
			LineTo line1 = new LineTo(321,161);
			LineTo line2 = new LineTo(126,232);
			LineTo line3 = new LineTo(232,52);
			LineTo line4 = new LineTo(269,250);
			LineTo line5 = new LineTo(108,71);
			
			Path path = new Path();
			path.getElements().addAll(startPoint, line1, line2, line3, line4, line5);
			Button sceneChangeButton = new Button("Change Scene");
			
			Group root = new Group(path, sceneChangeButton);
			Scene scene = new Scene(root, 400, 300);
			
			LoginPage loginPage = new LoginPage();
			Scene loginScene = loginPage.getLoginPage();
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			sceneChangeButton.setOnAction(event -> {
				primaryStage.setScene(loginScene);
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
