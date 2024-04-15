package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Random;

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
//	@PostMapping("/user/checkEmail")
//	public HashMap<String,Object> checkEmail (String email){
//		HashMap<String,Object> data = new HashMap<>();
//
//		Random random =new Random();
//		int checkNum = random.nextInt(888888)+111111;
//		String subject = "회원가입 인증 메일입니다";
//		String content = "방문해 주셔서 감사합니다"+
//				"인증번호는" + checkNum + " 입니다." + "\r\n" + "해당 인증번호를 인증번호 확인란에 기입라여 주세요";
//		String from = "jxoow1531155@gmail.com";
//
//		try {
//			MimeMessage mail = mailSender.createMimeMessage();
//			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true,"UTF-8");
//			//true : multipart Message
//
//			mailHelper.setFrom(from,"관리자");
//
//			mailHelper.setTo(email);
//			mailHelper.setSubject(subject);
//
//			mailHelper.setText(content,true);
//
//			//첨부파일 있을경우 추가
////         FileSystemResource file = new FileSystemResource(new File("경로"));
////         mailHelper.addAttachment("경로", file);
//
//
//			mailSender.send(mail);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		data.put("code",checkNum);
//		return data;
//	}



	
	
}
