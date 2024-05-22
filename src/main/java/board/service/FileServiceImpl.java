package board.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import board.dao.FileDao;
import board.dto.Board;
import board.dto.BoardFile;

@Service
public class FileServiceImpl implements FileService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private FileDao fileDao;
	
	@Autowired private ServletContext servletContext;
	
	@Override
	public void filesave(Board board, MultipartFile file) {
		//파일 업로드 경로 지정
//		String storedPath = "/resources/boardupload";
		String storedPath = servletContext.getRealPath("/resources/boardUpload");
		logger.info("storedPath : {}", storedPath);
		
		File storedFolder = new File(storedPath);
		storedFolder.mkdir();
		logger.debug("폴더 확인 : {}", storedFolder.toPath());
		
		String storedName = null;
		File dest = null;
		
		do {
			storedName = file.getOriginalFilename(); //원본파일 명
			logger.debug("originName : {} ", storedName);
			storedName += UUID.randomUUID().toString().split("-")[4]; //UUID 추가
			logger.debug("storedName : {} ", storedName);
			logger.debug("UUID.randomUUID().toString().split(\"-\")[4] : {} ", UUID.randomUUID().toString().split("-")[4]);
			
			dest = new File(storedFolder, storedName);
		}while( dest.exists() );
		
		try {
			//업로드된 임시 파일을 upload 폴더로 옮기기
			file.transferTo(dest);
			logger.debug("파일 옮기기 완료 : {}", dest);
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

	@Override
	public List<BoardFile> getFilesByBoardNo(int boardno) {
		return fileDao.getFilesByBoardNo(boardno);
	}

	@Override
	public BoardFile fileTempSave(HttpServletRequest request, HttpServletResponse response) {
	    
	    BoardFile TempFile = new BoardFile();
	    
	    String sFileInfo = "";
	    String sFilename = request.getHeader("file-name");
	    logger.debug("sFilename: {}", sFilename);

	    String sFilenameExt = sFilename.substring(sFilename.lastIndexOf(".") + 1).toLowerCase();
	    logger.debug("sFilenameExt: {}", sFilenameExt);

	    String[] allowFileArr = {"jpg", "png", "gif"};
	    boolean isAllowed = Arrays.asList(allowFileArr).contains(sFilenameExt);
	    
	    if (!isAllowed) {
	        try (PrintWriter print = response.getWriter()) {
	            print.print("NOTALLOW_" + sFilename);
	            print.flush();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    String projectPath = System.getProperty("user.dir");
	    String filePath = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "resources" + File.separator + "boardUpload" + File.separator;
	    logger.debug("filePath: {}", filePath);

	    File file = new File(filePath);
	    if (!file.exists()) {
	        file.mkdirs();
	    }

	    String sRealFileNm = UUID.randomUUID().toString() + sFilename.substring(sFilename.lastIndexOf("."));
	    String rlFileNm = filePath + sRealFileNm;

	    try (InputStream inputStream = request.getInputStream();
	         OutputStream outputStream = new FileOutputStream(rlFileNm)) {

	        byte[] bytes = new byte[Integer.parseInt(request.getHeader("file-size"))];
	        int numRead;
	        while ((numRead = inputStream.read(bytes, 0, bytes.length)) != -1) {
	            outputStream.write(bytes, 0, numRead);
	        }
	        outputStream.flush();
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    sFileInfo += "&bNewLine=true";
	    sFileInfo += "&sFileName=" + sFilename;
	    sFileInfo += "&sFileURL=" + "/resources/boardUpload/" + sRealFileNm;

	    try (PrintWriter printWriter = response.getWriter()) {
	        printWriter.print(sFileInfo);
	        printWriter.flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    TempFile.setOriginName(sFilename);
	    TempFile.setStoredName(sRealFileNm);
	    logger.debug("TempFile: {}", TempFile);

	    return TempFile;
	}


	@Override
	public void listDeleteByBoardNo(ArrayList<Integer> boardno) {
		fileDao.listDeleteByBoardNo(boardno);
	}

	@Override
	public List<String> extractOriginName(String content) {
		List<String> originNames = new ArrayList<>();
        // originName 추출을 위한 정규식 패턴
        Pattern pattern = Pattern.compile("title=\"([^\"\\\\]+\\.(?:png|jpg|gif|PNG|JPG|GIF))\"");
        logger.info("pattern : {}",pattern);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            originNames.add(matcher.group(1));
        }
        return originNames;
	}

	@Override
	public List<String> extractStoredName(String content,  List<String> originNames) {
		List<String> storedNames = new ArrayList<>();
		String temp = "";
        // storedName 추출을 위한 정규식 패턴
        for (String originName : originNames) {
//        	Pattern pattern = Pattern.compile("img src=\"\\\\resources\\\\boardUpload\\\\([^\"\\\\]+)\\.(?:png|jpg|gif|PNG|JPG|GIF)\"");
        	Pattern pattern = Pattern.compile("img src=\"\\\\resources\\\\boardUpload\\\\([^\"\\\\]+)\\.(png|jpg|gif|PNG|JPG|GIF)\"");
        	logger.info("pattern : {}",pattern);
            Matcher matcher = pattern.matcher(content);
            logger.info("matcher : {}",matcher);
            logger.info("matcher.find() : {}",matcher.find());
            if (matcher.find()) {
            	temp = "";
            	temp += matcher.group(1);
            	temp += ".";
            	temp += matcher.group(2);
                storedNames.add(temp);
                logger.info("matcher.group(1) : {}",matcher.group(1));
            }
        }
        return storedNames;
	}

	@Override
	public void setFile(ArrayList<BoardFile> files) {
		fileDao.setFile(files);
	}

	@Override
	public List<BoardFile> getFileList(int boardNo) {
		return fileDao.getFileList(boardNo);
	}

	@Override
	public BoardFile getFileByFileNo(int fileNo) {
		return fileDao.getFileByFileNo(fileNo);
	}	
	
}
