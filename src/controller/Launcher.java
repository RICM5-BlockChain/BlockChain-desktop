package controller;

import model.Config;
import view.MainWindow;

public class Launcher {

	public static Config config;

	
	public static void main(String[] args) {
		config = Config.getConfig();
	
		
		@SuppressWarnings("unused")
		MainWindow mainWindow = new MainWindow("UGA Chain Desktop", 680, 480);
	}

}
