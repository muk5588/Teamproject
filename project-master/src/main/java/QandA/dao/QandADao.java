package QandA.dao;

import QandA.dto.QandA;
import java.util.List;

public interface QandADao {
   
	public List<QandA> findAll();
   
	public QandA findById(int id);
   
	public void save(QandA qanda);
   
	public void update(QandA qanda);
   
	public void deleteById(int id);
}
