package inquiry.service;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.dao.FileDao;

@Service
public class inquiryFileServiceImpl implements inquiryFileService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private FileDao fileDao;
	
	@Autowired private ServletContext servletContext;
	
	
}
