package QandA.dao;

import QandA.dto.QandA;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("QandADao")
public interface QandADao {
   
	public List<QandA> findAll();
   
	public QandA findById(int id);
   
	public void save(QandA qanda);
   
	public void update(QandA qanda);
   
	public void deleteById(int id);
}
