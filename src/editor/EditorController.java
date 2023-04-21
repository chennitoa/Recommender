package editor;

import java.io.File;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;

public class EditorController {
	
	@FXML
	private TextArea areaText;
	
	private TextFile currentTextFile;
	
	private EditorModel model;
	
	public EditorController (EditorModel model) {
		this.model = model;
	}
	
	@FXML
	private void onSave() {
		TextFile textFile = new TextFile(currentTextFile.getFile(), Arrays.asList(areaText.getText().split("\n")));
		model.save(textFile);
	}
	

	@FXML
	private void onLoad() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File ("./"));
		File file = fileChooser.showOpenDialog(null);
		if (file != null)
		{
			IOResult <TextFile> io = model.load(file.toPath() );
			
			if (io.isOk() && io.hasData())
			{	
				currentTextFile= io.getData();
				areaText.clear();
				currentTextFile  .getContent().forEach(line -> areaText.appendText(line + "\n"));
			}
			else
			{
				System.out.println ("Failed");
			}
			
			
		}
		
	}
	@FXML
	private void onClose() {
		model.close();
		
	}
	@FXML
	private void onDelete() {
		
	}
	@FXML
	private void onAbout() {
		Alert alert = new Alert (Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("About");
		alert.setContentText("Simple Text Editor");
		alert.show();
		
	}


	@FXML 
	public void onHtmlEditor(ActionEvent event) 
	{
        HTMLEditor htmlEditor = new HTMLEditor();
        VBox vBox = new VBox(htmlEditor);
        Scene scene = new Scene(vBox);
        
        Stage primaryStage = new Stage();
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("HTML Editor");

        primaryStage.show();
       
	}
	
}










