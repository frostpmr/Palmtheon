
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class Email {

	
//	 public static void printParaCount(String document) {
////	        String lineBreakCharacters = "\r\n";
////	        StringTokenizer st = new StringTokenizer(
////	                    document, lineBreakCharacters);
////	        System.out.println("ParaCount: " + st.countTokens());
//		 int count = 0;
//		 Pattern whitespace = Pattern.compile("\\s\\s");
//		 Matcher matcher;
//		 matcher = whitespace.matcher(document);
//		 while (matcher.find()){ 
//			 count++;
//		 }
//		 System.out.println(count);
//	    }
	public Set dafuq(String dir) throws IOException{
		Set<String> sendee = new HashSet<String>();
	File directory = new File(dir);
		  File[] directoryListing = directory.listFiles();
	File[] mailFiles = directory.listFiles();
	  String host = "host.com";
	  java.util.Properties properties = System.getProperties();
	  properties.setProperty("mail.smtp.host", host);
	  Session session = Session.getDefaultInstance(properties);

	  
	  for (File tmpFile : mailFiles) {
	     MimeMessage email = null;
	     
	     
	     
	     try {
	        FileInputStream fis = new FileInputStream(tmpFile);
	        email = new MimeMessage(session, fis);
            
	        sendee.add(email.getFrom()[0].toString());
//	        System.out.println("\nsender: " + email.getFrom()[0].toString());
//	        System.out.println("\nsubject: " + email.getSubject());
	        
	        Object content = email.getContent();  
	        if (content instanceof String)  
	        {  
	            String body = (String)content;  
	              
	        }  
	        else if (content instanceof Multipart)  
	        {  
	            Multipart mp = (Multipart)content;  
	             
	        }  
	        String body = (String) content;
	     
	        System.out.println( "sender " + email.getFrom()[0].toString());
//	        System.out.println("\nrecipients: " + Arrays.asList(email.getRecipients(Message.RecipientType.TO))); 
	     } catch (MessagingException e) {
	        throw new IllegalStateException("illegal state issue", e);
	     } catch (FileNotFoundException e) {
	        throw new IllegalStateException("file not found issue issue: " + tmpFile.getAbsolutePath() , e); 
	     }
	  }
	  return sendee;	
}
}
