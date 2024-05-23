package util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import grade.service.GradeService;
import user.dto.User;

@Component
public class AutoGradeUpdater {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private GradeService gradeService;
	
//    @Scheduled(cron = "*/10 * * * * *") //테스팅용 10초에 실행되게
	// 매 자정에 실행되도록 설정
//	@Scheduled(cron = "0 0 0 * * *")
//    @Scheduled(cron = "0 0 12 * * *") //정오 실행.
    @Scheduled(cron = "0 0 * * * *") //매 시각 0분 마다 실행
    public void GradeUpdater() {
    	
    	List<Map<String, Object>> users = gradeService.getUserWriteCountAndaccessCount();
    	logger.debug("userS : {}",users);
    	List<User> user2 = new ArrayList<>();
    	List<User> user3 = new ArrayList<>();
    	int gradeNo,accessCount,writeCount,userNo;
    	for(Map<String, Object> m : users) {
    		logger.debug("m : {}",m);
    		userNo = ((BigDecimal) m.get("USERNO")).intValue();
    		gradeNo = ((BigDecimal) m.get("GRADENO")).intValue();
    		accessCount = ((BigDecimal) m.get("ACCESSCOUNT")).intValue();
    		writeCount = ((BigDecimal) m.get("WRITECOUNT")).intValue();
    		if( gradeNo == 1 && writeCount >= 3 ) {
    			User temp = new User();
    			temp.setUserno(userNo);
    			temp.setgradeno(2);
    			user2.add(temp);
    		}
    		if(gradeNo == 2 && writeCount >= 10 && accessCount >= 14) {
    			User temp = new User();
    			temp.setUserno(userNo);
    			temp.setgradeno(3);
    			user3.add(temp);
			}
    		
    	}
    	logger.debug("user1 : {}",user2);
    	logger.debug("user2 : {}",user3);
    	//자동 등급업 실행
    	if( user2 != null && !user2.isEmpty()) {
    		int res1 = gradeService.autoGradeUpdater2(user2);
    		logger.debug("res1 : {}",res1);
    	}
    	if( user3 != null && !user3.isEmpty()) {
    		int res2 = gradeService.autoGradeUpdater3(user3);
    		logger.debug("res2 : {}",res2);
    	}
    	 
    }
    
    
    
}
