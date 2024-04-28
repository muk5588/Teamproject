package board.service;

import board.dao.FileDao;
import board.dto.Board;
import board.dto.BoardFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private FileDao fileDao;
	
	@Autowired private ServletContext servletContext;
	
	@Override
	public void filesave(Board board, MultipartFile file) {
		String storedPath = "/resources/img/boardupload";
		logger.info("storedPath : {}", storedPath);
		
		File storedFolder = new File(storedPath);
		storedFolder.mkdir();
		
		String storedName = null;
		File dest = null;
		
		do {
			storedName = file.getOriginalFilename(); //원본파일 명
			storedName += UUID.randomUUID().toString().split("-")[4]; //UUID 추가
			logger.debug("storedName : {} ", storedName);
			
			dest = new File(storedFolder, storedName);
		}while( dest.exists() );
		
		try {
			//업로드된 임시 파일을 upload 폴더로 옮기기
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BoardFile filetest = new BoardFile();
		
		filetest.setBoardNo(board.getBoardNo());
		filetest.setOriginName(file.getOriginalFilename());
		filetest.setStoredName(storedName);
		logger.info("fileTest : {}", filetest);
		
		fileDao.insert(filetest);
	}

	@Override
	public int getFileCnt(int boardno) {
		return fileDao.getFileCnt(boardno);
	}

	@Override
	public BoardFile getFileByBoardNo(int boardno) {
		return fileDao.getFileByBoardNo(boardno);
	}
	
}
