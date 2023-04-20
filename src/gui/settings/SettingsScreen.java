package gui.settings;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SettingsScreen {
	
	@FXML
	private Label personal;
	@FXML
	private Label prefill;
	@FXML
	private Label account;
	@FXML
	private ImageView back;
	@FXML
	private ScrollPane content;
	
	private FXMLLoader personalLoader;
	private VBox personalTab;
	
	private FXMLLoader presetLoader;
	private VBox presetTab;
	
	private FXMLLoader accountLoader;
	private VBox accountTab;
	private AccountTab accountController;
	
	private Main main;
	
	/*
	 * Loads the three different tabs for the settings screen
	 */
	public SettingsScreen() {
		try {
			personalLoader = new FXMLLoader(getClass().getResource("./PersonalTab.fxml"));
			personalTab = personalLoader.load();
			
			presetLoader = new FXMLLoader(getClass().getResource("./PresetTab.fxml"));
			presetTab = presetLoader.load();
			
			accountLoader = new FXMLLoader(getClass().getResource("./AccountTab.fxml"));
			accountTab = accountLoader.load();
			accountController = accountLoader.getController();
			accountController.setSettingsScreen(this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Display selected label with orange highlight if true, else no highlight
	 */
	public void setLabelStyleSelected(Label labelToStyle, boolean isSelected) {
		if (isSelected) {
			labelToStyle.setStyle("-fx-background-color: LIGHTSALMON;" +
					"-fx-text-fill: WHITE");
		}
		else {
			labelToStyle.setStyle("-fx-background-color: TRANSPARENT;" +
					"-fx-text-fill: BLACK");
		}
	}
	
	
	/*
	 * Opens various menus for various different choices
	 */
	public void initialize() {
		
		setLabelStyleSelected(personal, true);
		setLabelStyleSelected(prefill, false);
		setLabelStyleSelected(account, false);
		displayPersonalScreen();
		
		personal.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			setLabelStyleSelected(personal, true);
			setLabelStyleSelected(prefill, false);
			setLabelStyleSelected(account, false);
			displayPersonalScreen();
		});
		
		prefill.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			setLabelStyleSelected(personal, false);
			setLabelStyleSelected(prefill, true);
			setLabelStyleSelected(account, false);
			displayPrefillScreen();
		});
		
		account.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			setLabelStyleSelected(personal, false);
			setLabelStyleSelected(prefill, false);
			setLabelStyleSelected(account, true);
			displayAccountScreen();
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
	}
	
	/*
	 * Displays the personal tab
	 */
	public void displayPersonalScreen() {
		content.setContent(personalTab);
	}
	
	/*
	 * Displays the prefill tab
	 */
	public void displayPrefillScreen() {
		content.setContent(presetTab);
	}
	
	/*
	 * Displays the account tab
	 */
	public void displayAccountScreen() {
		content.setContent(accountTab);
	}
	
	/*
	 * Sends a log out signal to main
	 */
	public void sendLogoutSignal() {
		main.logout();
	}
	
}
