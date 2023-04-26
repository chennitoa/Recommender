package gui.edit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.Main;
import filetracker.FileInfo;
import filetracker.MetadataManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import search.LetterManager;
import util.FileHandler;
import util.FileSelector;

public class EditScreen {
	
	@FXML
	private TextArea editor;
	@FXML
	private Label info;
	
	private FileInfo fileInfo;
	
	private FileHandler fileHandler;
	private LetterManager letterManager;
	private Main main;
	
	public EditScreen() {
		fileHandler = FileHandler.getFileHandler();
		letterManager = LetterManager.getLetterManager();
	}
	
	public void initialize() {
		
	}
	
	@FXML
	public void open() {
		String pathToFile = FileSelector.getChooseFilePath(FileSelector.LETTER_FILTER);
		if (pathToFile != null) {
			try {
				main.openLetterWithFile(new File(pathToFile));
			}
			catch (FileNotFoundException e) {
				info.setText("Failed to open file");
			}
		}
	}
	
	@FXML
	public void save() {
		if (fileInfo.getPathToFile() == null) {
			saveAs();
		}
		else {
			List<String> textContent = getTextContents();
			String filePath = fileInfo.getPathToFile();
			info.setText(fileInfo.getPathToFile());
			
			//Write to file with metadata
			MetadataManager.addMetadata(textContent, fileInfo);
			try {
				fileHandler.writeToFile(filePath, textContent);
			}
			catch (IOException e) {
				info.setText("Failed to save file. Please try again.");
			}
		}
	}
	
	@FXML
	public void saveAs() {
		List<String> textContent = getTextContents();
		String filePath = FileSelector.getCreateFilePath(FileSelector.LETTER_FILTER,
				"letter_" + fileInfo.getFirstName() + fileInfo.getLastName() + fileInfo.getLetterYear() + ".recc");
		if (filePath == null) {
			return;
		}
		info.setText(filePath);
		fileInfo = new FileInfo(fileInfo);
		fileInfo.setPathToFile(filePath);
		
		letterManager.insertIfAbsent(fileInfo);
		
		//Write to file with metadata
		MetadataManager.addMetadata(textContent, fileInfo);
		try {
			fileHandler.writeToFile(filePath, textContent);
		}
		catch (IOException e) {
			info.setText("Failed to save file. Please try again.");
		}
	}
	
	@FXML
	public void export() {
		List<String> textContent = getTextContents();
		String filePath = FileSelector.getCreateFilePath(FileSelector.TEXT_FILTER,
				"letter_" + fileInfo.getFirstName() + fileInfo.getLastName() + fileInfo.getLetterYear() + ".txt");
		
		//Write to file
		try {
			fileHandler.writeToFile(filePath, textContent);
		}
		catch (IOException e) {
			info.setText("Failed to export file. Please try again.");
		}
	}
	
	@FXML
	public void undo() {
		editor.undo();
	}
	
	@FXML
	public void redo() {
		editor.redo();
	}
	
	public List<String> getTextContents() {
		List<String> textContent = new ArrayList<String>();
		for (CharSequence text : editor.getParagraphs()) {
			textContent.add(text.toString());
		}
		return textContent;
	}
	
	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
		if (fileInfo == null || fileInfo.getPathToFile() == null) {
			info.setText("Not Saved");
		}
		else {
			info.setText(fileInfo.getPathToFile());
		}
	}
	
	public void setText(List<String> textLines) {
		String text = "";
		for (String line : textLines) {
			if (!line.equals("%delete%")) {
				text += line + "\n";
			}
		}
		editor.setText(text);
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
}
