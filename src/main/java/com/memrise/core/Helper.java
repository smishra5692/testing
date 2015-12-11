package com.memrise.core;

import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.text.DateFormat;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeMultipart;
public class Helper {
   
     @SuppressWarnings("unused")
	public static String getMail(String subject, String accountName ,String passWord) throws Exception{
            System.out.println("Searching for Email with subject:"+subject.trim());
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            props.setProperty("mail.imaps.host", "imap.gmail.com");
            props.setProperty("mail.imaps.port", "993");
            props.setProperty("mail.imaps.connectiontimeout", "5000");
            props.setProperty("mail.imaps.timeout", "5000");
            props.setProperty("mail.imaps.ssl.trust", "*");
            try {
                        store.connect("imap.gmail.com",accountName, passWord);                  
                        /*
                         * If the mail is found then the loop below terminates with the return statement
                         * If the mail is not in the in box then it pauses for few seconds and check again
                         * It repeats for 20 times before it confirms that there is not mail in the inbox
                         */
                        for(int count=0;count<20;count++){
                           
                                Folder inbox = store.getFolder("Inbox");
                           
                                inbox.open(Folder.READ_WRITE);
                           
                                Message messages[] = inbox.getMessages();
                                System.out.println(messages);
                                System.out.println(messages.length);
                                /*
                                 * this for loop Starts with the latest mails in the inbox and goes on.
                                 */
                                for (int i=messages.length;i>0; i--) {      
                                    //This if condition checks for the mail with the given subject
                                	i--;
                                  if(messages[i].getSubject().trim().contains(subject.trim())){
                                        System.out.println(i + ": " + messages[i].getFrom()[0]+ "\t" + messages[i].getSubject());
                                        String contentStr = null;
                                         ContentType ct = new ContentType(messages[i].getContentType() );
                                        if(ct.getPrimaryType().equals("multipart")) {
                                            contentStr = getMultiPart(messages[i]);
                                        } else {
                                            contentStr = messages[i].getContent().toString();
                                        }
                                       
                                        return messages[i].getSubject().toString()+ " " + contentStr;
                                    }
                                }
                        Thread.sleep(3000);
                        return "";
                    }
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                    return "";
                } catch (MessagingException e) {
                    e.printStackTrace();
                    return "";
                }
                catch(NullPointerException e)
                {
                    System.out.println("in null catch");
                }
                catch (Exception e) {
                    System.out.println("The error is " + e.toString());
                    return "";
                }
                finally{
                    store.close();
                }
                return "";
    }
      
    /*
    * @Desc: getMultiPart is used to get MultiPart content
    */
    public static String getMultiPart(Message m) {
    StringBuffer stringBuffer = new StringBuffer();
    try {
    MimeMultipart content = ( MimeMultipart )m.getContent();
    for( int i = 0; i < content.getCount(); i++ ) {
    BodyPart part = content.getBodyPart( i );
    stringBuffer.append(part.getContent());
    }
    }
    catch(Exception e) {
    e.printStackTrace();
    }
    return stringBuffer.toString();
    }
    /**
    * deleteAllEmail is used to delete all the emails.
    * @return
    * @throws Exception
    */
    public static String deleteAllEmail(String MailID,String Password) throws Exception{
    System.out.println("Deleting All Email:");
    Properties props = System.getProperties(); 
    props.setProperty("mail.store.protocol", "imaps");
    props.setProperty("mail.imaps.ssl.trust", "*");
    try {
    Session session = Session.getDefaultInstance(props, null);
    Store store = session.getStore("imaps");
    store.connect("imap.gmail.com", MailID,Password);
    Folder inbox = store.getFolder("Inbox");
    inbox.open(Folder.READ_WRITE);
    Message messages[] = inbox.getMessages();
    for (int i=0, n=messages.length; i<n; i++) {
    messages[i].setFlag(Flags.Flag.DELETED, true);
    }
    } catch (NoSuchProviderException e) {
    e.printStackTrace();
    System.out.println("Error");
    } catch (MessagingException e) {
    e.printStackTrace();
    System.out.println("Error");
    } catch (Exception e) {
    System.out.println("The error is " + e.toString());
    return "";
    }
    return "";
    }
    public static String deleteEmail(String subject,String accountName,String passWord) throws Exception{
    System.out.println("Deleting Email with subject:"+subject);
    Properties props = System.getProperties();
    props.setProperty("mail.store.protocol", "imaps");
    props.setProperty("mail.imaps.ssl.trust", "*");
    try {
    Session session = Session.getDefaultInstance(props, null);
    Store store = session.getStore("imaps");
    store.connect("imap.gmail.com",accountName.trim(),passWord.trim());
    Folder inbox = store.getFolder("Inbox");
    inbox.open(Folder.READ_WRITE);
    Message messages[] = inbox.getMessages();
    for (int i=0, n=messages.length; i<n; i++) {
    if(messages[i].getSubject().replace(" ","").contains(subject.replace(" ",""))){
          messages[i].setFlag(Flags.Flag.DELETED, true);
    }
    }
    } catch (NoSuchProviderException e) {
    e.printStackTrace();
    System.out.println("Error");
    } catch (MessagingException e) {
    e.printStackTrace();
    System.out.println("Error");
    } catch (Exception e) {
    System.out.println("The error is " + e.toString());
    return "";
    }
    return "";
    }

    public static String checkMail(String subject,String body[]) throws Exception{
    System.out.println("Validating Email:"+subject);
    Properties props = System.getProperties();
    props.setProperty("mail.store.protocol", "imaps");
    try {
    Session session = Session.getDefaultInstance(props, null);
    Store store = session.getStore("imaps");
    store.connect("imap.gmail.com", "hemanth@sunfra.com", "hemalatha_79");
    Folder inbox = store.getFolder("Inbox");
    inbox.open(Folder.READ_WRITE);
    Message messages[] = inbox.getMessages();
    for (int i=0, n=messages.length; i<n; i++) {
    if(messages[i].getSubject().contains(subject)){
          for (int j=0;j<body.length;j++){
             if(messages[i].getContent().toString().contains(body[j])){
             }
             else{
             System.out.println("ERROR: String Not Found:"+body[j]);
             }
          }
          BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
          String line = reader.readLine();
          if ("YES".equals(line)) {
             messages[i].setFlag(Flags.Flag.DELETED, true);

          }
    }
    }
    } catch (NoSuchProviderException e) {
    e.printStackTrace();
    System.out.println("Error");
    System.exit(1);
    } catch (MessagingException e) {
    e.printStackTrace();
    System.out.println("Error");
    System.exit(2);
    }
    return "";
    }
    public static String getMailNew(String subject,String mailId) throws Exception{
    System.out.println("Searching for Email with subject:"+subject);
    Properties props = System.getProperties();
    props.setProperty("mail.store.protocol", "imaps");
    Session session = Session.getDefaultInstance(props, null);
    Store store = session.getStore("imaps");
    String passWord = null;
    if(mailId.contains("testsharehoods001")){
    passWord = "sharehoods";
    } else if(mailId.contains("sunfratestaccounother")){
    passWord = "sunfra1234";
    } else if(mailId.contains("suraja.sunfra")){
    }
    try {
    store.connect("imap.gmail.com", mailId, passWord);
    for(int count=0;count<20;count++){
    Folder inbox = store.getFolder("Inbox");
    inbox.open(Folder.READ_WRITE);
    Message messages[] = inbox.getMessages();
    for (int i=0, n=messages.length; i<n; i++) {
    if(messages[i].getSubject().replace(" ","").contains(subject.replace(" ",""))){
    String contentStr = null;
    ContentType ct = new ContentType(messages[i].getContentType() );
                   if(ct.getPrimaryType().equals("multipart")) {
                   contentStr = getMultiPart(messages[i]);
                   } else {
                   contentStr = messages[i].getContent().toString();
                   }
           return messages[i].getSubject().toString()+ " " + contentStr;
    }
    }
    Thread.sleep(200);
    }
    } catch (NoSuchProviderException e) {
    e.printStackTrace();
    return "";
    } catch (MessagingException e) {
    e.printStackTrace();
    return "";
    } catch (Exception e) {
    System.out.println("The error is " + e.toString());
    return "";
    }
    finally{
    store.close();
    }
    return "";
    }
    public static String deleteAllEmailNew(String mailId,String password) throws Exception{
    System.out.println("Deleting All Email for account : "+mailId);
    Properties props = System.getProperties();
    props.setProperty("mail.store.protocol", "imaps");
    try {
    Session session = Session.getDefaultInstance(props, null);
    Store store = session.getStore("imaps");
    store.connect("imap.gmail.com", mailId, password);
    Folder inbox = store.getFolder("Inbox");
    inbox.open(Folder.READ_WRITE);
    Message messages[] = inbox.getMessages();
    for (int i=0, n=messages.length; i<n; i++) {
    messages[i].setFlag(Flags.Flag.DELETED, true);
    }
    } catch (NoSuchProviderException e) {
    e.printStackTrace();
    System.out.println("Error");
    } catch (MessagingException e) {
    e.printStackTrace();
    System.out.println("Error");
    } catch (Exception e) {
    System.out.println("The error is " + e.toString());
    return "";
    }
    return "";
    }
    /*
     * This method returns the link which matches the content form the html string
     */
    public static String getLinkWithContentInMail(String subject,String linkContent, String accountName ,String passWord) throws Exception{
         String mailContent= getMail( subject,  accountName , passWord);
         String s = mailContent.replaceAll("\"", " ");
            // separete input by spaces ( URLs don't have spaces )
            String [] parts = s.split("\\s");
	        // Attempt to convert each item into an URL.   
	        for( String item : parts )
	        try {
	            URL url = new URL(item);
	            // If possible then replace with anchor...
	            if(url.toString().contains(linkContent))
	             return url.toString();    
	        } catch (MalformedURLException e) {
	            // If there was an URL that was not it!...    
	        }
		 return null;
	}
	public static String uniqueStringwithDateandTime() throws ParseException
	{
		Date now = new Date();  
		DateFormat df = new SimpleDateFormat("ddmmyyHHmmss");
		String s = df.format(now); 
		return s; 	
	}
	public static String uniqueStringWithTime() throws Exception{
		Date now = new Date();  
		DateFormat df = new SimpleDateFormat("HHmmss");
		String s = df.format(now); 
		return s; 	
	}
	public static String uniqueStringWithDayInDate() throws Exception{
		Date now=new Date();
		DateFormat df=new SimpleDateFormat("dd");
		String s =df.format(now);
		return s;
	}

	public static int getNoOfDaysOfAMonth(int month) throws Exception{
		int noOfDaysInCurrMonth = 0;
		if(month ==4 || month == 6 || month ==9 || month == 11){
			noOfDaysInCurrMonth = 30;
		}else if(month == 2){
			noOfDaysInCurrMonth = 28;
		}else{
			noOfDaysInCurrMonth = 31;
		}
		return noOfDaysInCurrMonth;
	}
	public static void log(String msg) {
		System.out.println(msg);
		
	}
	}


