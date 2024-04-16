package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import user.dao.UserDao;
import user.dto.UserDTO;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    
	@Autowired
	private JavaMailSender mailSender;	//메일
    
    @Override
    public List<UserDTO> userList() {
        return userDao.userList();
    }

    @Override
    public UserDTO userDetail(UserDTO userno) {
        return userDao.userDetail(userno);
    }

    public void userInsert(UserDTO dto) {
        userDao.userInsert(dto);
    }

    @Override
    public void userUpdate(UserDTO dto) {
        userDao.userUpdate(dto);
    }

    @Override
    public void userDelete(UserDTO dto) {
        userDao.userDelete(dto);
    }

    @Override
    public int passChk(UserDTO dto) {
        int result = userDao.passChk(dto);
        return result;
    }

    @Override
    public int idChk(UserDTO dto) {
        int result = userDao.idChk(dto);
        return result;
    }

	@Override
	public int checkEmail(String email) {
		
		Random random =new Random();
		int checkNum = random.nextInt(888888)+111111;
		String subject = "회원가입 인증 메일입니다";
		String content = "방문해 주셔서 감사합니다"+
				"인증번호는" + checkNum + " 입니다." + "\r\n" + "해당 인증번호를 인증번호 확인란에 기입라여 주세요";
		String from = "jxoow1531155@gmail.com";

		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true,"UTF-8");
			//true : multipart Message

			mailHelper.setFrom(from,"관리자");

			mailHelper.setTo(email);
			mailHelper.setSubject(subject);

			mailHelper.setText(content,true);

			//첨부파일 있을경우 추가
//         FileSystemResource file = new FileSystemResource(new File("경로"));
//         mailHelper.addAttachment("경로", file);


			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return checkNum;
	}

    @Override
    public UserDTO findUserId(UserDTO dto) {
        return userDao.findUserid(dto);
    }

    @Override
    public void updateUserpw(UserDTO dto) {
        userDao.updateUserpw(dto);
    }

    @Override
    public int findUserpw(UserDTO dto) {
        return userDao.findUserpw(dto);
    }


}



