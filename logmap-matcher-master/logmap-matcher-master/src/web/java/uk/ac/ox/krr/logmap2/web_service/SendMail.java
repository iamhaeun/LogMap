/*******************************************************************************
 * Copyright 2012 by the Department of Computer Science (University of Oxford)
 * 
 *    This file is part of LogMap.
 * 
 *    LogMap is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 * 
 *    LogMap is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 * 
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with LogMap.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package uk.ac.ox.krr.logmap2.web_service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


 
public class SendMail {
	
	//setting email and passwd at web.xml
	final String username;
	final String password;
	
	
	
	public SendMail(String tomail, String subject, String text_mail, String email, String passwd_email, String smtp_host){
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2"); //add		
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		
		this.username = email;
		this.password = passwd_email;
		
		System.out.println(email);
		System.out.println(passwd_email);
		System.out.println(tomail);
		System.out.println(smtp_host);
		
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			
			//From
			message.setFrom(new InternetAddress("logmap.om.tool@gmail.com", "LogMap Team", "utf-8"));
			
			//To
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(tomail));
			
			/*
			 * //BCC message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(
			 * "ernesto.jimenez.ruiz@gmail.com,logmap.om.tool@gmail.com"));
			 * 
			 * //Reply to message.setReplyTo(InternetAddress.parse(
			 * "ernesto.jimenez.ruiz@gmail.com,logmap.om.tool@gmail.com"));
			 * 
			 */
			message.setSubject(subject);
			message.setText(text_mail);
 
		
			Transport.send(message);
 
			
			//System.out.println("Done");
 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		
		 
		
	}

	
	
	
}
