package shop.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import dto.Item;
import dto.ItemFile;
import shop.dao.AdminShopDao;
import shop.service.face.AdminShopService;
import util.Paging;
import util.ShopPaging;

@Service
public class AdminShopSerivceImpl implements AdminShopService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired AdminShopDao adminShopDao;
	@Autowired private HttpServletRequest request;
	@Autowired private HttpServletResponse response;
	@Autowired private ServletContext servletContext;
	
	@Override
	public ShopPaging getPaging(int curPage, ShopPaging paging) {
		
		int totalCount = adminShopDao.selectCnt(paging);
		logger.info("totalCount : {}",totalCount);
		ShopPaging pagingres = new ShopPaging(totalCount, curPage);
		
		return pagingres;
	}//getPaging(int curPage, Paging paging)

	@Override
	public List<Item> selectItems(ShopPaging paging) {
		return adminShopDao.selectItems(paging);
	}//selectItems(Paging paging)

	@Override
	public List<ItemFile> selectTitleImgFile(List<Item> items) {
		return adminShopDao.selectTitleImgFile(items);
	}//selectTitleImgFile(List<Item> items)

	@Override
	public ItemFile fileTempSave() {
		ItemFile TempFile = new ItemFile();
		
		String sFileInfo  = "";
		//파일명을 받는다 - 일반 원본파일명
		String sFilename = request.getHeader("file-name");
		logger.debug("sFilename : {}", sFilename);
		//파일 확장자
		String sFilenameExt = sFilename.substring(sFilename.lastIndexOf(".")+1);
		logger.debug("sFilenameExt : {}", sFilenameExt);
		//확장자를소문자로 변경
		sFilenameExt = sFilenameExt.toLowerCase();
		logger.debug("sFilenameExt : {}", sFilenameExt);
		try {
			//확장자 체크를 위한 
			String[] allowFileArr = {"jpg","png","gif"};
			
			//확장자 체크
			int nCnt = 0;
			for(int i=0; i<allowFileArr.length; i++) {
				if(sFilenameExt.equals(allowFileArr[i])){
					nCnt++;
				}
			}

			if(nCnt == 0) {
				//이미지가 아니라면 중단
				return null;
			} else {
				
				//파일경로
				String filePath = servletContext.getRealPath("/resources/itemUpload/");
				logger.debug("filePath : {}", filePath);
				File file = new File(filePath);
				
				if(!file.exists()) {
					file.mkdirs();
				}
				
				String sRealFileNm = "";
//				sRealFileNm = UUID.randomUUID().toString() + sFilename.substring(sFilename.lastIndexOf("."));
				sRealFileNm = UUID.randomUUID().toString() + sFilename;
				String rlFileNm = filePath + sRealFileNm;
				
				///////////////// 서버에 파일쓰기 ///////////////// 
				InputStream inputStream = request.getInputStream();
				OutputStream outputStream=new FileOutputStream(rlFileNm);
				int numRead;
				byte bytes[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
				while((numRead = inputStream.read(bytes,0,bytes.length)) != -1){
					outputStream.write(bytes,0,numRead);
				}
				if(inputStream != null) {
					inputStream.close();
				}
				outputStream.flush();
				outputStream.close();
				
				///////////////// 이미지 /////////////////
				// 정보 출력
				sFileInfo += "&bNewLine=true";
				// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
				sFileInfo += "&sFileName="+ sFilename;
				sFileInfo += "&sFileURL="+"\\resources\\itemUpload\\"+sRealFileNm;
				PrintWriter printWriter = response.getWriter();
				printWriter.print(sFileInfo);
				printWriter.flush();
				printWriter.close();
				logger.debug("sFileInfo : {}", sFileInfo);
				TempFile.setOriginName(sFilename);
				TempFile.setStoredName(sRealFileNm);
				logger.debug("TempFile : {}", TempFile);
				
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("TempFile : {}", TempFile);
		
		return TempFile;
	}// fileTempSave()

	@Override
	public int insertItem(Item item) {
		return adminShopDao.insertItem(item);
	}

	@Override
	public int fileSave(Item item) {
		String itemComm = item.getItemComm();
		List<String> originNames = new ArrayList<>();
        // originName 추출을 위한 정규식 패턴
        Pattern pattern = Pattern.compile("title=\"([^\"\\\\]+\\.(?:png|jpg|gif|PNG|JPG|GIF))\"");
        logger.info("pattern : {}",pattern);
        Matcher matcher = pattern.matcher(itemComm);
        while (matcher.find()) {
            originNames.add(matcher.group(1));
        }
        logger.debug("originNames : {} ",originNames);
        if(originNames == null || originNames.isEmpty()) {
        	return 0;
        }
		List<String> storedNames = new ArrayList<>();
		String temp = "";
        // storedName 추출을 위한 정규식 패턴
        for (String originName : originNames) {
        	Pattern pattern1 = Pattern.compile("img src=\"\\\\resources\\\\itemUpload\\\\([^\"\\\\]+)\\.(png|jpg|gif|PNG|JPG|GIF)\"");
        	logger.info("pattern : {}",pattern);
            Matcher matcherStored = pattern1.matcher(itemComm);
            logger.info("matcherStored : {}",matcherStored);
            if (matcherStored.find()) {
            	temp = "";
            	temp += matcherStored.group(1);
            	temp += ".";
            	temp += matcherStored.group(2);
                storedNames.add(temp);
                logger.info("matcher.group(1) : {}",matcherStored.group(1));
                logger.info("matcher.group(2) : {}",matcherStored.group(2));
            }
            logger.debug("storedNames : {} ",storedNames);
        }
        // originName과 storedName을 함께 설정하여 ItemFile 객체 생성
        List<ItemFile> itemFiles = new ArrayList<>();
        for (int i = 0; i < originNames.size(); i++) {
            ItemFile itemFile = new ItemFile();
            itemFile.setOriginName(originNames.get(i));
            itemFile.setStoredName(storedNames.get(i));
            itemFile.setItemNo(item.getItemNo());
            itemFiles.add(itemFile);
        }
        logger.debug("itemFiles : {}", itemFiles);
        int res = adminShopDao.fileSave(itemFiles);
        
        return res;
	}

	@Override
	public int updatetitleImg(Item item, MultipartFile file) {
		//파일 업로드 경로 지정
		String storedPath = servletContext.getRealPath("/resources/itemUpload/");
		logger.info("storedPath : {}", storedPath);
		
		File storedFolder = new File(storedPath);
		storedFolder.mkdir();
		logger.debug("폴더 확인 : {}", storedFolder.toPath());
		
		String storedName = null;
		File dest = null;
		
		do {
			storedName = UUID.randomUUID().toString().split("-")[4]; //UUID 추가 
			logger.debug("originName : {} ", storedName);
			logger.debug("UUID.randomUUID().toString().split(\"-\")[4] : {} ", storedName);
			storedName += file.getOriginalFilename(); //원본파일 명 
			logger.debug("storedName : {} ", storedName);
			
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
		
		ItemFile filetest = new ItemFile();
		
		filetest.setItemNo(item.getItemNo());
		filetest.setOriginName(file.getOriginalFilename());
		filetest.setStoredName(storedName);
		logger.info("fileTest : {}", filetest);
		
		int res = adminShopDao.inserttitleImgFile(filetest);
		int updateRes = 0;
		if(res > 0 ) {
			updateRes= adminShopDao.updatetitleImgFile(filetest);
		}
		
		return filetest.getFileNo(); 
	}

	@Override
	public Item selectItemByItemNo(int itemNo) {
		return adminShopDao.selectItemByItemNo(itemNo);
	}

	@Override
	public List<ItemFile> selectItemFileByItemNo(int itemNo) {
		return adminShopDao.selectItemFileByItemNo(itemNo);
	}

	@Override
	public int updateIByItem(Item item) {
		adminShopDao.updateIByItem(item);
		adminShopDao.deleteByItemOldFile(item);
		String itemComm = item.getItemComm();
		List<String> originNames = new ArrayList<>();
		// originName 추출을 위한 정규식 패턴
		Pattern pattern = Pattern.compile("title=\"([^\"\\\\]+\\.(?:png|jpg|gif|PNG|JPG|GIF))\"");
		logger.info("pattern : {}",pattern);
		Matcher matcher = pattern.matcher(itemComm);
		while (matcher.find()) {
			originNames.add(matcher.group(1));
		}
		logger.debug("originNames : {} ",originNames);
		if(originNames == null || originNames.isEmpty()) {
			return 0;
		}
		List<String> storedNames = new ArrayList<>();
		String temp = "";
		// storedName 추출을 위한 정규식 패턴
		for (String originName : originNames) {
			Pattern pattern1 = Pattern.compile("img src=\"\\\\resources\\\\itemUpload\\\\([^\"\\\\]+)\\.(png|jpg|gif|PNG|JPG|GIF)\"");
			logger.info("pattern : {}",pattern1);
			Matcher matcherStored = pattern1.matcher(itemComm);
			logger.info("matcherStored : {}",matcherStored);
			if (matcherStored.find()) {
				temp = "";
				temp += matcherStored.group(1);
				temp += ".";
				temp += matcherStored.group(2);
				storedNames.add(temp);
				logger.info("matcher.group(1) : {}",matcherStored.group(1));
				logger.info("matcher.group(2) : {}",matcherStored.group(2));
			}
			logger.debug("storedNames : {} ",storedNames);
		}
		// originName과 storedName을 함께 설정하여 ItemFile 객체 생성
		List<ItemFile> itemFiles = new ArrayList<>();
		for (int i = 0; i < originNames.size(); i++) {
			ItemFile itemFile = new ItemFile();
			itemFile.setOriginName(originNames.get(i));
			itemFile.setStoredName(storedNames.get(i));
			itemFile.setItemNo(item.getItemNo());
			itemFiles.add(itemFile);
		}
		int res = adminShopDao.fileSave(itemFiles);
		return res;
	}

	@Override
	public int deleteByItemNo(int itemNo) {
		int res = 0;
		int res1, res2, res3;
		res1=res2=res3=0;
		res1=adminShopDao.deleteItemFK(itemNo);
		res2=adminShopDao.deleteitemFileByItemNo(itemNo);
		res3= adminShopDao.deleteitemByItemNo(itemNo);
		res = res1+res2+res3;
		return res;
	}

	
	
	
}
