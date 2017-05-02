package Email;

public class MainClass {

	
	public void sendEmailRecommend(String name,String friendemail,String message) {
		// TODO Auto-generated method stub
		String body =name+" recommended you to try Researchers Exchange Participation saying-"+message;//Body of the mail
		String from ="nbad.rimplekarishma@gmail.com"; // email of sender
		String sub ="Recommended-Researchers Exchange Participation";// subject of the mail
		String id = "nbad.rimplekarishma@gmail.com"; // email of the sender
		String pass = "nbad1234"; // password of the sender's email
		String to = friendemail;// email of the reciever//pass=nbad1234
		GMailSender sender = new GMailSender(id, pass);
		
		try {
			sender.sendMail(sub, body, from, to);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
        
        	public void sendEmailContact(String name,String contactemail,String message) {
		// TODO Auto-generated method stub
		String body ="Hi I am "+name+" .Contact me back on "+contactemail+"--"+message;//Body of the mail
		String from ="nbad.rimplekarishma@gmail.com"; // email of sender
		String sub ="Contact-Researchers Exchange Participation";// subject of the mail
		String id = "nbad.rimplekarishma@gmail.com"; // email of the sender
		String pass = "nbad1234"; // password of the sender's email
		String to ="nbad.rimplekarishma@gmail.com" ;// email of the reciever//pass=nbad1234
		GMailSender sender = new GMailSender(id, pass);
		
		try {
			sender.sendMail(sub, body, from, to);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//return;
	}
        

}
