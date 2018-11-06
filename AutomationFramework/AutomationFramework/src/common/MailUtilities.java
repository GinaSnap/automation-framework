package common;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.ParseException;

public class MailUtilities {

    static String protocol="imaps";
    static String host = "imap.gmail.com";
    static String user="snapkitchen.tester.1";
    static String password="QAtestER";
    static String inbox = "INBOX";
    static String trash = "TRASH";
    static int port = -1;
    static boolean verbose = false;
    static boolean debug = false;
    static boolean showStructure = false;
    static boolean showMessage = false;
    static boolean showAlert = false;
    static boolean saveAttachments = false;
    static int attnum = 1;
    
    public String getMessage(String subject)
    {
    		Store store = null;
    		Folder folder = null;

    		try {
    			
    			store = getSession();
    			folder = getFolder(store, inbox);

    			int totalMessages = folder.getMessageCount();

    			if (totalMessages == 0) {
    				System.out.println("Empty folder");
    				folder.close(false);
    				store.close();
    				return "";
    			}

		Message[] msgs = folder.getMessages();

		// I need to understand this better.
		FetchProfile fp = new FetchProfile();
		fp.add(FetchProfile.Item.ENVELOPE);
		fp.add(FetchProfile.Item.FLAGS);
		fp.add("X-Mailer");
		folder.fetch(msgs, fp);

		for (int i = 0; i < msgs.length; i++) 
		{
		    if (msgs[i].getSubject().equals(subject)) {
		    		Multipart mp = (Multipart) msgs[i].getContent();
			    return (String) mp.getBodyPart(1).getContent();  //It may not always be the first element.  Not sure.
		    }
		}

	    folder.close(false);
	    store.close();
	} catch (Exception ex) {
	    System.out.println("Oops, got exception! " + ex.getMessage());
	    ex.printStackTrace();
	}
    		return "";
    }
    
    public String deleteMessage(String subject)
    {
    		Store store = null;
    		Folder inboxFolder = null;

    		try {
    			
    			store = getSession();
    			inboxFolder = getFolder(store, inbox);

		Message[] msgs = inboxFolder.getMessages();

		// I need to understand this better.
		FetchProfile fp = new FetchProfile();
		fp.add(FetchProfile.Item.ENVELOPE);
		fp.add(FetchProfile.Item.FLAGS);
		fp.add("X-Mailer");
		inboxFolder.fetch(msgs, fp);

		for (int i = 0; i < msgs.length; i++) 
		{
		    if (msgs[i].getSubject().equals(subject)) {
		    		msgs[i].setFlag(Flags.Flag.DELETED, true);
		    }
		}

	    inboxFolder.close(false);
	    store.close();
	} catch (Exception ex) {
	    System.out.println("Oops, got exception! " + ex.getMessage());
	    ex.printStackTrace();
	}
    		return "";
    }
   

    private Store getSession() {
		Store store = null;

    		try 
    		{
	    		// Get a Properties object
			Properties props = System.getProperties();
	
			// Get a Session object
			Session session = Session.getInstance(props, null);
			session.setDebug(debug);
	
			// Get a Store object
			store = session.getStore(protocol);
			
			// Connect
			store.connect(host, port, user, password);
		} 
    		catch (NoSuchProviderException e) 
    		{
			e.printStackTrace();
		} 
    		catch (MessagingException e) 
    		{
			e.printStackTrace();
		}
			
		return store;
    }
    
    private Folder getFolder(Store store, String mailbox)
    {
    		Folder folder=null;
		try 
		{
			folder = store.getDefaultFolder();
			folder = folder.getFolder(mailbox);
			
			if (folder != null) 
			{
			folder.open(Folder.READ_WRITE);
			}
		}
		catch (MessagingException ex) 
		{
			System.out.println(ex.getStackTrace());
		}
		return folder;
    }

    public static void dumpPart(Part p) throws Exception {
//	if (p instanceof Message)
//	    dumpEnvelope((Message)p);

	/** Dump input stream .. 

	InputStream is = p.getInputStream();
	// If "is" is not already buffered, wrap a BufferedInputStream
	// around it.
	if (!(is instanceof BufferedInputStream))
	    is = new BufferedInputStream(is);
	int c;
	while ((c = is.read()) != -1)
	    System.out.write(c);

	**/

	String ct = p.getContentType();
	try {
	    pr("CONTENT-TYPE: " + (new ContentType(ct)).toString());
	} catch (ParseException pex) {
	    pr("BAD CONTENT-TYPE: " + ct);
	}
	String filename = p.getFileName();
	if (filename != null)
	    pr("FILENAME: " + filename);

	/*
	 * Using isMimeType to determine the content type avoids
	 * fetching the actual content data until we need it.
	 */
	if (p.isMimeType("text/plain")) {
	    pr("This is plain text");
	    pr("---------------------------");
	    if (!showStructure && !saveAttachments)
		System.out.println((String)p.getContent());
//	} else if (p.isMimeType("multipart/*")) {
//	    pr("This is a Multipart");
//	    pr("---------------------------");
//	    Multipart mp = (Multipart)p.getContent();
//	    level++;
//	    int count = mp.getCount();
//	    for (int i = 0; i < count; i++)
//		dumpPart(mp.getBodyPart(i));
//	    level--;
//	} else if (p.isMimeType("message/rfc822")) {
//	    pr("This is a Nested Message");
//	    pr("---------------------------");
//	    level++;
//	    dumpPart((Part)p.getContent());
//	    level--;
//	} else {
//	    if (!showStructure && !saveAttachments) {
//		/*
//		 * If we actually want to see the data, and it's not a
//		 * MIME type we know, fetch it and check its Java type.
//		 */
//		Object o = p.getContent();
//		if (o instanceof String) {
//		    pr("This is a string");
//		    pr("---------------------------");
//		    System.out.println((String)o);
//		} else if (o instanceof InputStream) {
//		    pr("This is just an input stream");
//		    pr("---------------------------");
//		    InputStream is = (InputStream)o;
//		    int c;
//		    while ((c = is.read()) != -1)
//			System.out.write(c);
//		} else {
//		    pr("This is an unknown type");
//		    pr("---------------------------");
//		    pr(o.toString());
//		}
//	    } else {
//		// just a separator
//		pr("---------------------------");
//	    }
	}

	/*
	 * If we're saving attachments, write out anything that
	 * looks like an attachment into an appropriately named
	 * file.  Don't overwrite existing files to prevent
	 * mistakes.
	 */
//	if (saveAttachments && level != 0 && p instanceof MimeBodyPart &&
//		!p.isMimeType("multipart/*")) {
//	    String disp = p.getDisposition();
//	    // many mailers don't include a Content-Disposition
//	    if (disp == null || disp.equalsIgnoreCase(Part.ATTACHMENT)) {
//		if (filename == null)
//		    filename = "Attachment" + attnum++;
//		pr("Saving attachment to file " + filename);
//		try {
//		    File f = new File(filename);
//		    if (f.exists())
//			throw new IOException("file exists");
//		    ((MimeBodyPart)p).saveFile(f);
//		} catch (IOException ex) {
//		    pr("Failed to save attachment: " + ex);
//		}
//		pr("---------------------------");
//	    }
//	}
    }

    public static void dumpEnvelope(Message m) throws Exception {
	pr("This is the message envelope");
	pr("---------------------------");
	Address[] a;
	// FROM 
	if ((a = m.getFrom()) != null) {
	    for (int j = 0; j < a.length; j++)
		pr("FROM: " + a[j].toString());
	}

	// REPLY TO
	if ((a = m.getReplyTo()) != null) {
	    for (int j = 0; j < a.length; j++)
		pr("REPLY TO: " + a[j].toString());
	}

	// TO
	if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
	    for (int j = 0; j < a.length; j++) {
		pr("TO: " + a[j].toString());
		InternetAddress ia = (InternetAddress)a[j];
		if (ia.isGroup()) {
		    InternetAddress[] aa = ia.getGroup(false);
		    for (int k = 0; k < aa.length; k++)
			pr("  GROUP: " + aa[k].toString());
		}
	    }
	}

	// SUBJECT
	pr("SUBJECT: " + m.getSubject());

	// DATE
	Date d = m.getSentDate();
	pr("SendDate: " +
	    (d != null ? d.toString() : "UNKNOWN"));

//	// FLAGS
//	Flags flags = m.getFlags();
//	StringBuffer sb = new StringBuffer();
//	Flags.Flag[] sf = flags.getSystemFlags(); // get the system flags
//
//	boolean first = true;
//	for (int i = 0; i < sf.length; i++) {
//	    String s;
//	    Flags.Flag f = sf[i];
//	    if (f == Flags.Flag.ANSWERED)
//		s = "\\Answered";
//	    else if (f == Flags.Flag.DELETED)
//		s = "\\Deleted";
//	    else if (f == Flags.Flag.DRAFT)
//		s = "\\Draft";
//	    else if (f == Flags.Flag.FLAGGED)
//		s = "\\Flagged";
//	    else if (f == Flags.Flag.RECENT)
//		s = "\\Recent";
//	    else if (f == Flags.Flag.SEEN)
//		s = "\\Seen";
//	    else
//		continue;	// skip it
//	    if (first)
//		first = false;
//	    else
//		sb.append(' ');
//	    sb.append(s);
//	}
//
//	String[] uf = flags.getUserFlags(); // get the user flag strings
//	for (int i = 0; i < uf.length; i++) {
//	    if (first)
//		first = false;
//	    else
//		sb.append(' ');
//	    sb.append(uf[i]);
//	}
//	pr("FLAGS: " + sb.toString());

//	// X-MAILER
//	String[] hdrs = m.getHeader("X-Mailer");
//	if (hdrs != null)
//	    pr("X-Mailer: " + hdrs[0]);
//	else
//	    pr("X-Mailer NOT available");
    }

    static String indentStr = "                                               ";
    static int level = 0;

    /**
     * Print a, possibly indented, string.
     */
    public static void pr(String s) {
	if (showStructure)
	    System.out.print(indentStr.substring(0, level * 2));
	System.out.println(s);
    }
}

