package QandA.service.face;

import QandA.dto.QandA;
import java.util.List;

public interface QandAService {
   public List<QandA> getAllQandAs();
   public QandA getQandAById(int id);
   public void addQandA(QandA qanda);
   public void updateQandA(QandA qanda);
    public void deleteQandA(int id);
}
