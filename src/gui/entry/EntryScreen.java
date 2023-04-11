package gui.entry;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class EntryScreen {
	
	@FXML
	private ScrollPane content;
	
	
	private FXMLLoader loginLoader;
	private FXMLLoader resetLoader;
	
	private AnchorPane loginTab;
	private AnchorPane resetTab;
	
	private LoginTab loginController;
	private ResetTab resetController;
	
	private Main main;
	
	/*
	 * Loads the login tab and reset tab
	 */
	public EntryScreen() {
		try {
			loginLoader = new FXMLLoader(getClass().getResource("./LoginTab.fxml"));
			loginTab = loginLoader.load();
			loginController = loginLoader.getController();
			
			resetLoader = new FXMLLoader(getClass().getResource("./ResetTab.fxml"));
			resetTab = resetLoader.load();
			resetController = resetLoader.getController();
			
			loginController.setEntryScreen(this);
			resetController.setEntryScreen(this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initialize() {
		this.displayLoginScreen();
	}
	
	/*
	 * Displays the menu screen
	 */
	public void displayMenuScreen() {
		main.displayMenuScene(true);
	}
	
	/*
	 * Displays the login screen
	 */
	public void displayLoginScreen() {
		content.setContent(loginTab);
	}
	
	/*
	 * Displays the reset screen
	 */
	public void displayResetScreen() {
		content.setContent(resetTab);
	}
	
	/*
	 * Sets main for navigation
	 */
	public void setMain(Main main) {
		this.main = main;
	}
}
