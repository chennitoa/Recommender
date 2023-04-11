package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SettingsScreen implements ApplicationScreen {
	
	@FXML
	private Label personal;
	@FXML
	private Label prefill;
	@FXML
	private Label account;
	@FXML
	private Label back;
	@FXML
	private ScrollPane content;
	
	private FXMLLoader personalLoader;
	private FXMLLoader prefillLoader;
	private FXMLLoader accountLoader;
	
	private VBox personalTab;
	private VBox prefillTab;
	private VBox accountTab;
	
	private AccountTab accountController;
	
	private Main main;
	
	/*
	 * Loads the three different tabs for the settings screen
	 */
	public SettingsScreen() {
		try {
			personalLoader = new FXMLLoader(getClass().getResource("../gui/PersonalTab.fxml"));
			personalTab = personalLoader.load();
			
			prefillLoader = new FXMLLoader(getClass().getResource("../gui/PrefillTab.fxml"));
			prefillTab = prefillLoader.load();
			
			accountLoader = new FXMLLoader(getClass().getResource("../gui/AccountTab.fxml"));
			accountTab = accountLoader.load();
			accountController = accountLoader.getController();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Opens various menus for various different choices
	 */
	public void initialize() {
		
		content.setContent(personalTab);
		content.setVisible(true);
		
		personal.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			content.setContent(personalTab);
		});
		
		prefill.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			content.setContent(prefillTab);
		});
		
		account.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			content.setContent(accountTab);
		});
		
		back.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			main.displayMenuScene(false);
		});
		
	}
	
	/*
	 * Sets main for nativation
	 */
	public void setMain(Main main) {
		this.main = main;
		accountController.setMain(main);
	}
	
}
