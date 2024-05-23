package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import message.service.MessageService;

@Component
public class MessageCleanup {
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MessageService messageService;

    // 매 자정에 실행
//    @Scheduled(cron = "0 0 0 * * *")
//    @Scheduled(cron = "0 0 12 * * *") //정오 실행.
    @Scheduled(cron = "0 0 * * * *") //매 시각 0분 마다 실행
    public void cleanupExpiredMessages() {
    	//전송한지 1달 지난 저장하지 않은 메시지 삭제
    	int res = messageService.cleanupExpiredMessages();
    	logger.debug("1달 지난 저장되지 않은 메시지 {} 개를 삭제 하였습니다",res);
    }
}