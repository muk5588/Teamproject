package util;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/sendMail")
	public String sendMailTest() {
		
		String subject = "test 메일";
		String content = "test내용물";
        String from = "jxoow1531155@gmail.com";
        String to = "jxoow1531155@gmail.com";
		
        try {
        	MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true,"UTF-8");
			//true : multipart Message
			
			mailHelper.setFrom(from);
			
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content,true);
			
			//첨부파일 있을경우 추가
//			FileSystemResource file = new FileSystemResource(new File("경로")); 
//			mailHelper.addAttachment("경로", file);
			
			
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        return "redirect:/";
		
	}
	

	
	
}
