package inquiry.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dto.InquiryFile;
import inquiry.dao.inquiryFileDao;

@Service
public class inquiryFileServiceImpl implements inquiryFileService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private inquiryFileDao inquiryFileDao;
	
	@Autowired private ServletContext servletContext;

	@Override
	public List<InquiryFile> extractInquiryFiles(MultipartFile[] files, int inquiryNo) {
		 List<InquiryFile> inquiryFiles = new ArrayList<>();

	        if (files != null && files.length > 0) {
	            for (MultipartFile file : files) {
	                if (!file.isEmpty()) {
	                    try {
	                        // 파일 업로드 및 저장 로직 구현
	                        // 원본 파일 이름과 저장된 파일 이름을 추출하여 InquiryFile 객체 생성
	                        String originName = file.getOriginalFilename();
	                        String storedName = saveFileAndGetStoredName(file);
	                        
	                        // InquiryFile 객체 생성 및 리스트에 추가
	                        InquiryFile inquiryFile = new InquiryFile();
	                        inquiryFile.setInquiryNo(inquiryNo);
	                        inquiryFile.setOriginName(originName);
	                        inquiryFile.setStoredName(storedName);
	                        inquiryFiles.add(inquiryFile);
	                    } catch (IOException e) {
	                        // 파일 처리 중 예외 발생 시 예외 처리
	                        e.printStackTrace();
	                        // 예외 처리를 원하는 대로 수행
	                    }
	                }
	            }
	        }

	        return inquiryFiles;
	    }

	    // 파일을 저장하고 저장된 파일 이름을 반환하는 메서드
	    private String saveFileAndGetStoredName(MultipartFile file) throws IOException {
	        // 파일 저장 로직을 여기에 구현
	        // 저장된 파일 이름을 반환
	    	//파일 업로드 경로 지정
//			String storedPath = "/resources/boardupload";
			String storedPath = servletContext.getRealPath("/resources/InquiryUpload");
//			String documentsPath = System.getProperty("user.home") + File.separator + "Documents";
//			String storedPath = documentsPath + File.separator + "boardUpload" + File.separator;
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
	        return storedName; // 예시로 저장된 파일 이름을 반환하도록 설정
	    }

		@Override
		public int insertInquiryFiles(List<InquiryFile> inquiryFiles) {
			return inquiryFileDao.insertInquiryFiles(inquiryFiles);
		}

		@Override
		public List<InquiryFile> getFilesByinquiryNo(int inquiryNo) {
			return inquiryFileDao.getFilesByinquiryNo(inquiryNo);
		}

		@Override
		public InquiryFile getFileByFileNo(int fileNo) {
			return inquiryFileDao.getFileByFileNo(fileNo);
		}

		@Override
		public int deleteByFileNo(int no) {
			return inquiryFileDao.deleteByFileNo(no);
		}
	
	
}
