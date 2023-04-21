package HTMLEditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class HtmlEditorExample extends Application {
	
	private final String INITIAL_TEXT = "<html><body> Please delete me and start typing. </body></html>" ;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setHtmlText(INITIAL_TEXT);
        
        VBox vBox = new VBox(htmlEditor);
        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("HTML Editor");

        primaryStage.show();
    }
}