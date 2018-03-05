package controller;

import model.Mailer;
import model.PDF;

public class mailTester {
	
	public static void main(String[] args) {
		Mailer.init();
		Mailer.send_attached("charles-marchand@hotmail.fr", "coucou", "coucou", PDF.getPathTo("Marchand CharlesR"));
	}
}
