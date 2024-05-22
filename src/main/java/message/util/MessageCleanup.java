//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import dto.Message;
//import message.service.MessageService;
//
//@Component
//public class MessageCleanup {
//
//    @Autowired
//    private MessageService messageService;
//
//    // 매 자정에 실행되도록 설정
//    @Scheduled(cron = "0 0 0 * * *")
//    public void cleanupExpiredMessages() {
//        // 현재 날짜를 가져옵니다.
//        LocalDate currentDate = LocalDate.now();
//
//        // 30일 이전의 날짜를 계산합니다.
//        LocalDate expirationDate = currentDate.minusDays(30);
//
//        // 만료된 쪽지를 가져옵니다.
//        List<Message> expiredMessages = messageService.getExpiredMessages(expirationDate);
//
//        // 만료된 쪽지를 삭제합니다.
//        for (Message message : expiredMessages) {
//            messageService.deleteMessage(message.getId());
//        }
//    }
//}