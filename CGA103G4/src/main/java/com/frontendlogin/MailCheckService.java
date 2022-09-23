//package com.frontendlogin;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import java.util.Properties;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import javax.swing.JOptionPane;
//
//
//
//@WebServlet("/front-end/member/mailcheckservice")
//public class MailCheckService extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//
//protected boolean allowUser(String email) {
//		
//	
//		if ("tomcat".equals(email))
//			return true;
//		else
//			return false;
//	}
//
//
//
//
//	
//	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
//	public void sendMail(String to, String subject, String messageText) {
//			
//	   try {
//		   // 設定使用SSL連線至 Gmail smtp Server
//		   Properties props = new Properties();
//		   props.put("mail.smtp.host", "smtp.gmail.com");
//		   props.put("mail.smtp.socketFactory.port", "465");
//		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//		   props.put("mail.smtp.auth", "true");
//		   props.put("mail.smtp.port", "465");
//
//       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
//       // ●1) 登入你的Gmail的: 
//       // ●2) 點選【管理你的 Google 帳戶】
//       // ●3) 點選左側的【安全性】
//       
//       // ●4) 完成【兩步驟驗證】的所有要求如下:
//       //     ●4-1) (請自行依照步驟要求操作之.....)
//       
//       // ●5) 完成【應用程式密碼】的所有要求如下:
//       //     ●5-1) 下拉式選單【選取應用程式】--> 選取【郵件】
//       //     ●5-2) 下拉式選單【選取裝置】--> 選取【Windows 電腦】
//       //     ●5-3) 最後按【產生】密碼
////	     final String myGmail = "ixlogic.wu@gmail.com";
//	     final String myGmail = "sosohung20@gmail.com";
////	     final String myGmail_password = "ddjomltcnypgcstn";
//	     final String myGmail_password = "cvtektutlgxsnqer";
//		   Session session = Session.getInstance(props, new Authenticator() {
//			   protected PasswordAuthentication getPasswordAuthentication() {
//				   return new PasswordAuthentication(myGmail, myGmail_password);
//			   }
//		   });
//
//		   Message message = new MimeMessage(session);
//		   message.setFrom(new InternetAddress(myGmail));
//		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
//		  
//		   //設定信中的主旨  
//		   message.setSubject(subject);
//		   //設定信中的內容 
//		   message.setText(messageText);
//
//		   Transport.send(message);
//		   System.out.println("傳送成功!");
//     }catch (MessagingException e){
//	     System.out.println("傳送失敗!");
//	     e.printStackTrace();
//     }
//   }
//	 
//	 public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//			req.setCharacterEncoding("UTF-8");
//			res.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = res.getWriter();
//
//			// 【取得使用者 帳號(account)】
//			String email = req.getParameter("email");
//	
//			      String to = "sosohung20@gmail.com";
//			      
//			      String subject = "驗證碼";
//			      String vcode = genAuthCode(); 
//
//			      String messageText = "Hello! \n" + " 驗證碼為 : "+ vcode  ; 
//			       
//			      MailCheckService mailService = new MailCheckService();
//			      mailService.sendMail(to, subject, messageText);
//
//				
//				res.sendRedirect(req.getContextPath() + "/front-end/member/addMember.jsp"); // *工作3: (-->如無來源網頁:則重導至login_success.jsp)
//				Object[] options = { "繼續"}; 
//				JOptionPane.showOptionDialog(null, "驗證碼已寄到信箱，請按繼續重新加入會員", "警告",  JOptionPane.DEFAULT_OPTION, 
//						JOptionPane.WARNING_MESSAGE,  null, options, options[0]); 
//			
//		}
//	 
//		public static String genAuthCode() {
//			String vcode="";
//			for (int j = 1; j <=4; j++) {
//				int i = (int) (Math.random() * 62);
//				if (i < 10) {
//					vcode=vcode+i;
//				} else if (i >36) {
//					char s = (char) (i + 28);
//					vcode=vcode+s;
//				} else {
//					char s = (char) (i + 87);
//					vcode=vcode+s;
//				}
//			}
//			return vcode;
//		}
//
//}


