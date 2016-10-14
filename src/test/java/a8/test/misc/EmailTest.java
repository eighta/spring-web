package a8.test.misc;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.junit.Ignore;
import org.junit.Test;

public class EmailTest {

	@Test
	@Ignore
	public void sendPlainTextMail() throws MessagingException{
		
		//CONTENT
		Multipart plainMultiPart = new MimeMultipart();
		BodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent("CONTENIDO DEL MENSAJE", "text/plain");
		plainMultiPart.addBodyPart(bodyPart,0);
		
		this.sendEmail(plainMultiPart);
	}
	
	@Test
	public void sendHtmlMail() throws MessagingException, IOException{
		
		Multipart htmlMultipart = this.getHtmlMultipart();
		this.sendEmail(htmlMultipart);
	}
	
	private Multipart getHtmlMultipart() throws MessagingException, IOException{
		String content = this.getHtmlContent();
		
		MimeMultipart mimeMultipart = new MimeMultipart("related");
		
		//BODY-PART
		BodyPart messageBodyPart = new MimeBodyPart();				
        messageBodyPart.setContent(content, "text/html");
        mimeMultipart.addBodyPart(messageBodyPart);
        
        //IMAGES
        MimeBodyPart imagePart = new MimeBodyPart();
        imagePart.setHeader("Content-ID", "CONTENT_IMAGE");
        imagePart.setDisposition(MimeBodyPart.INLINE);
        imagePart.attachFile("/p_wrk1/2016/TICKETS&MANTIS/T811931/SALUDO_BIENVENIDA_2.png");
        mimeMultipart.addBodyPart(imagePart);
        
        return mimeMultipart;
	}
	
	private String getHtmlContent() {
		String htmlContent =
				"<html style='height: 100%; display: table;  margin: auto;'>"
				+ "<body style=' height: 100%;  display: table-cell;  vertical-align: middle;'>"
				+ "	<img src='cid:CONTENT_IMAGE' width=950/>"
				+ "</body>"
				+ "</html>";
		
		
		return htmlContent;
	}

	public void sendEmail(Multipart multipart) throws MessagingException{
		
		String smtpHost = "10.0.0.212";
		
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", smtpHost);
		
		Session session = Session.getDefaultInstance(props,null);
		session.getProperties().setProperty("mail.smtp.host", smtpHost);
		session.setDebug(Boolean.TRUE);
		
		Message msg = new MimeMessage(session);
		
		//ORIGEN
		Address fromAdress = new InternetAddress("me@myself.com");
		msg.setFrom(fromAdress);
		
		//DESTINO
		Address toAdress = new InternetAddress("mochoa_tch@jaimetorres.net");
		msg.setRecipient(Message.RecipientType.TO, toAdress);
		
		//TITLE
		msg.setSubject("TEST");
		
		//HEADERS
		msg.setHeader("X-Mailer", "msgsend");
		
		//fecha de envio?
		msg.setSentDate(new Date());
		
		//CONTENIDO DEL MAIL
		msg.setContent(multipart);
		
		msg.saveChanges();
		
		//SEND
		Transport.send(msg);
	}
	
	/* CONSOLE
	
DEBUG: setDebug: JavaMail version 1.5.0-b01
DEBUG: getProvider() returning javax.mail.Provider[TRANSPORT,smtp,com.sun.mail.smtp.SMTPTransport,Oracle]
DEBUG SMTP: useEhlo true, useAuth false
DEBUG SMTP: trying to connect to host "10.0.0.212", port 25, isSSL false
220 mail.jaimetorres.net ESMTP Postfix
DEBUG SMTP: connected to host "10.0.0.212", port: 25

EHLO Des62.jaimetorres.net
250-mail.jaimetorres.net
250-PIPELINING
250-SIZE 20848640
250-VRFY
250-ETRN
250-STARTTLS
250-ENHANCEDSTATUSCODES
250-8BITMIME
250 DSN
DEBUG SMTP: Found extension "PIPELINING", arg ""
DEBUG SMTP: Found extension "SIZE", arg "20848640"
DEBUG SMTP: Found extension "VRFY", arg ""
DEBUG SMTP: Found extension "ETRN", arg ""
DEBUG SMTP: Found extension "STARTTLS", arg ""
DEBUG SMTP: Found extension "ENHANCEDSTATUSCODES", arg ""
DEBUG SMTP: Found extension "8BITMIME", arg ""
DEBUG SMTP: Found extension "DSN", arg ""
DEBUG SMTP: use8bit false
MAIL FROM:<ate_cliente@enlace-apb.com>
250 2.1.0 Ok
RCPT TO:<mochoa_tch@jaimetorres.net>
250 2.1.5 Ok
DEBUG SMTP: Verified Addresses
DEBUG SMTP:   mochoa_tch@jaimetorres.net
DATA
354 End data with <CR><LF>.<CR><LF>
Date: Thu, 13 Oct 2016 10:50:41 -0500 (COT)
From: ate_cliente@enlace-apb.com
To: mochoa_tch@jaimetorres.net
Message-ID: <801197928.2.1476373841127.JavaMail.des62@Des62.jaimetorres.net>
Subject: Prueba de correo!
MIME-Version: 1.0
Content-Type: multipart/mixed; 
	boundary="----=_Part_0_1769597131.1476373841032"
X-Mailer: msgsend

------=_Part_0_1769597131.1476373841032
Content-Type: text/plain; charset=us-ascii
Content-Transfer-Encoding: 7bit

CONTENIDO DEL MENSAJE
------=_Part_0_1769597131.1476373841032--
.
250 2.0.0 Ok: queued as 9EEB91640C12
QUIT
221 2.0.0 Bye
	 */
}
