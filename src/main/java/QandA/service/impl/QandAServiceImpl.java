package QandA.service.impl;

import QandA.dao.QandADao;
import QandA.dto.QandA;
import QandA.service.face.QandAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QandAServiceImpl implements QandAService {

    @Autowired
    private QandADao qandaDao;

    @Override
    public List<QandA> getAllQandAs() {
        return qandaDao.findAll();
    }

    @Override
    public QandA getQandAById(int id) {
        return qandaDao.findById(id);
    }

    @Override
    public void addQandA(QandA qanda) {
        qandaDao.save(qanda);
    }

    @Override
    public void updateQandA(QandA qanda) {
        qandaDao.update(qanda);
    }

    @Override
    public void deleteQandA(int id) {
        qandaDao.deleteById(id);
    }
}
