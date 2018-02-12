package model;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import controller.Launcher;
import controller.OSValidator;

public class PDF {
	public static String exportAsPdf(Student s){
		PdfWriter writer;
		try {
			String filePath;
			if(OSValidator.isWindows()){
				filePath = (System.getProperty("user.dir")+"/PDF/"+s.name+".pdf").replace('/','\\');	
			}
			else{
				filePath = (System.getProperty("user.dir")+"/PDF/"+s.name+".pdf").replace('\\','/');
			}
			writer = new PdfWriter(filePath);
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf);
			Paragraph p = new Paragraph();
			try {
				Image logo = new Image(ImageDataFactory.create(Launcher.config.getLogo()));
				p.add(logo);
				p.add("\n"+s.finalPresentation());
				p.add("\n" + Launcher.config.SignatureQuote());
				Image Signature = new Image(ImageDataFactory.create(Launcher.config.getSignature()));
				p.add(Signature);
				document.add(p);
				document.close();
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath;
		if(controller.OSValidator.isWindows()){
			filePath = (System.getProperty("user.dir")+"/PDF/"+s.name+".pdf").replace('/','\\');	
		}
		else{
			filePath = (System.getProperty("user.dir")+"/PDF/"+s.name+".pdf").replace('\\','/');
		}
		
		String hash = Hash.SHA256.checksum(new File(filePath));
		try {
			
			System.out.println(hash);
			FileWriter fw  = new FileWriter(new File(filePath+".hash"));
			fw.write(hash, 0, hash.length());
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hash;
		
	}
}
