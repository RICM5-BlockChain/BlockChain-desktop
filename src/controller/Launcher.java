package controller;

import javax.swing.JOptionPane;

import model.Config;
import model.Mailer;
import view.MainWindow;

public class Launcher {

	public static Config config;

	
	public static void main(String[] args) {
		config = Config.getConfig();
		if(!Mailer.init()){
			JOptionPane.showMessageDialog(null,"Probleme dans la configuration du mailer, merci de contacter un administrateur");
			System.exit(-1);
		}
	
		
		@SuppressWarnings("unused")
		MainWindow mainWindow = new MainWindow("UGA Chain Desktop", 680, 480);
	}

}
