package model;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;	
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import controller.Launcher;
import controller.OSValidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Mailer{

	/**
	 * Read in secret_config/configMailer.csv
	 */
	private static String SMTP_HOST1 = "";
	private static String LOGIN_SMTP1 = "";
	private static String IMAP_ACCOUNT1 = "";
	private static String PASSWORD_SMTP1 = "";

	public static boolean init(){
		String filePath;
		if(OSValidator.isWindows()){
			filePath = (System.getProperty("user.dir")+"/secret_config/configMailer.csv").replace('/','\\');	
		}
		else{
			filePath = (System.getProperty("user.dir")+"/secret_config/configMailer.csv").replace('\\','/');
		}
		
		File file = new File(filePath);
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String sCurrentLine;
			sCurrentLine = br.readLine();
			String[] s = sCurrentLine.split(",");
			SMTP_HOST1=s[0];
			LOGIN_SMTP1=s[1];
			IMAP_ACCOUNT1=s[2];
			PASSWORD_SMTP1=s[3];
			Launcher.config.setIp(s[4]);
			br.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean send(String address,String subject,String content) {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.host", SMTP_HOST1);
		properties.setProperty("mail.smtp.user", LOGIN_SMTP1);
		properties.setProperty("mail.from", IMAP_ACCOUNT1);
		Session session = Session.getInstance(properties);

		// 2 -> Creation du message
		MimeMessage message = new MimeMessage(session);
		try {
			message.setText(content);
			message.setSubject(subject);
			message.addRecipients(Message.RecipientType.TO, address);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		// 3 -> Envoi du message
		Transport transport = null;
		try {
			transport = session.getTransport("smtp");
			transport.connect(LOGIN_SMTP1, PASSWORD_SMTP1);
			transport.sendMessage(message, new Address[] { new InternetAddress(address) });
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
			try {
				if (transport != null) {
					transport.close();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static boolean send_attached(String address,String subject,String content,String path) {
		// 1 -> Creation de la session
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.auth", "true");      
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.host", SMTP_HOST1);
		properties.setProperty("mail.smtp.user", LOGIN_SMTP1);
		properties.setProperty("mail.from", IMAP_ACCOUNT1);
		Session session = Session.getInstance(properties);

		// 2 -> Creation du message avec piece jointe
		File file = new File(path);
		FileDataSource datasource1 = new FileDataSource(file);
		DataHandler handler1 = new DataHandler(datasource1);
		MimeBodyPart autruche = new MimeBodyPart();
		try {
			autruche.setDataHandler(handler1);
			autruche.setFileName(datasource1.getName());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		MimeBodyPart body = new MimeBodyPart();
		try {
			body.setContent(content, "text/plain");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		MimeMultipart mimeMultipart = new MimeMultipart();
		try {
			mimeMultipart.addBodyPart(body);
			mimeMultipart.addBodyPart(autruche);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		MimeMessage message = new MimeMessage(session);
		try {
			message.setContent(mimeMultipart);
			message.setSubject(subject);
			message.addRecipients(Message.RecipientType.TO, address);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		// 3 -> Envoi du message
		Transport transport = null;
		try {
			transport = session.getTransport("smtp");
			transport.connect(LOGIN_SMTP1, PASSWORD_SMTP1);
			transport.sendMessage(message, new Address[] { new InternetAddress(address) });
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
			try {
				if (transport != null) {
					transport.close();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
