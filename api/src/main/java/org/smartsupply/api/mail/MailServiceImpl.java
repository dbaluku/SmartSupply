//package org.smartsupply.api.mail;
//
////import org.fcit.rms.model.Staff;
//import org.smartsupply.model.Comment;
//import org.smartsupply.model.Complaint;
//import org.springframework.mail.MailException;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
//
//public class MailServiceImpl implements MailService {
//
//	private MailSender mailSender;
//	private SimpleMailMessage templateMessage;
//	//private Student student;
//
//
//	public void setMailSender(MailSender mailSender) {
//		this.mailSender = mailSender;
//	}
//
//	public SimpleMailMessage getTemplateMessage() {
//		return templateMessage;
//	}
//
//
//	public void setTemplateMessage(SimpleMailMessage templateMessage) {
//		this.templateMessage = templateMessage;
//	}
//
//
//	public MailSender getMailSender() {
//		return mailSender;
//	}
//
//	public void sendMail(Complaint complaint) {
//		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
//		msg.setTo(complaint.getEmailAddress());
//		msg.setSubject("complaint:"+complaint.getTypeWord());
//		msg.setText("Dear " + complaint.getStudent().getFirstName() + complaint.getStudent().getLastName()
//				+ ", your complaint about "+complaint.getSemesterCourseUnit().getCourseUnit().getName()
//				+" :"+complaint.getTypeWord()+" submitted on "+complaint.getSubmissionDate()
//				+" has been received. test email from complaints system ");
//		try {
//			this.mailSender.send(msg);
//		} catch (MailException ex) {
//			// simply log it and go on...
//			System.err.println(ex.getMessage());
//		}		
//	}
//
//	public void sendNotification(Comment comment) {
//		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
//		msg.setTo(comment.getComplaint().getEmailAddress());
//		msg.setSubject("New comment on your complaint:"+comment.getComplaint().getTypeWord());
//		msg.setText("Dear " + comment.getComplaint().getStudent().getFirstName() + comment.getComplaint().getStudent().getLastName()
//				+ ", your complaint about "+comment.getComplaint().getSemesterCourseUnit().getCourseUnit().getCode()+"-"
//				+comment.getComplaint().getSemesterCourseUnit().getCourseUnit().getName()
//				+" :"+comment.getComplaint().getTypeWord()+" submitted on "+comment.getComplaint().getSubmissionDate()
//				+" has received a new comment with the following content:\n\n"
//				+comment.getCommentText()+".\n test email from complaints system ");
//		
//		/*if(comment.getComplaint().getStatusWord()=="open")
//			msg.setText("Dear " + comment.getComplaint().getStudent().getFirstName() + comment.getComplaint().getStudent().getLastName()
//				+ ", your complaint about "+comment.getComplaint().getSemesterCourseUnit().getCourseUnit().getCode()+"-"
//				+comment.getComplaint().getSemesterCourseUnit().getCourseUnit().getName()
//				+" :"+comment.getComplaint().getTypeWord()+" submitted on "+comment.getComplaint().getSubmissionDate()
//				+" has received a new comment with the following content:\n\n"
//				+comment.getCommentText()+".\n test email from complaints system ");*/
//		try {
//			this.mailSender.send(msg);
//		} catch (MailException ex) {
//			// simply log it and go on...
//			System.err.println(ex.getMessage());
//		}		
//	}
//	
//
///*	@Override
//	public void sendNotification(Staff staff) {
//		// TODO Auto-generated method stub
//		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
//		msg.setTo("gankunda@cit.mak.ac.ug");
//		msg.setText("Dear " + staff.getFirstName() + staff.getLastName()
//				+ ", a complaint has been received from "
//				+ student.getFirstName() + student.getLastName()+student.getRegNo()
//				+"test email from complaints system ");
//		try {
//			this.mailSender.send(msg);
//		} catch (MailException ex) {
//			// simply log it and go on...
//			System.err.println(ex.getMessage());
//		}
//	}*/
//	
//	
//	
////	@Override
////	public void sendMail(Mail mail) {
////		// TODO Auto-generated method stub
////		SimpleMailMessage msg = new SimpleMailMessage(/*this.templateMessage*/);
////		msg.setTo("jaredkage@yahoo.com");
////		msg.setText("Dear " + student.getFirstName() + student.getLastName()
////				+ ", your complaint has been received. ");
////		try {
////			this.mailSender.send(msg);
////		} catch (MailException ex) {
////			// simply log it and go on...
////			System.err.println(ex.getMessage());
////		}
////	}
//}
