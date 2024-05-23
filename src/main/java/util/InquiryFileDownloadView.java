package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import board.dto.BoardFile;
import dto.InquiryFile;

public class InquiryFileDownloadView extends AbstractView {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private ServletContext servletContext;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("InquiryFileDownloadView");
		//-------------------------------------------------------------------------------

		//HTML 응답 테스트
//		response.setCharacterEncoding("text/html; charset=utf-8");
//		response.getWriter().append("<h1>하이!</h1>");
		//-------------------------------------------------------------------------------

		//모델값 가져오기
		InquiryFile downFile =  (InquiryFile) model.get("downFile");
		logger.info("downFile : {}",downFile);
		
		//저장된 파일의 폴더 (upload)
//		String path = servletContext.getRealPath("/resources/boardUpload");
		String path = servletContext.getRealPath("/resources/InquiryUpload");
//		String documentsPath = System.getProperty("user.home") + File.separator + "Documents";
//		String path = documentsPath + File.separator + "boardUpload" + File.separator;
		logger.info("path : {}",path);
		
		//실제 업로드된 파일의 이름
		String filename = downFile.getStoredName();

		//실제 파일 객체
		File src = new File(path, filename);

		logger.info("서버에 업로드된 파일 : {}",src);
		logger.info("존재 여부 : {}",src.exists());
		//-------------------------------------------------------------------------------

		//** HTTP 응답을 직접 구성하여 처리해야 한다

		//** 필수 HTTP Response HEADER 속성을 설정한 뒤
		// HTTP Response BODY의 내용을 src(파일)로 채운다

		//** 필수 응답 헤더 속성
		//	Content-Type
		//	Content-Length
		//	CharacterEncoding
		//	Content-Disposition
		//-------------------------------------------------------------------------------

		//** 응답 메시지의 HEADER영역 설정하기

		//응답 데이터의 콘텐트 타입(데이터 유형) 설정하기
		//	Content-Type
		//	-> application/octet-stream : 모든 파일의 형식을 대표하는 MIME
		//	-> 브라우저는 해당 형식의 데이터를 응답받으면 다운로드 받는다
		response.setContentType("application/octet-stream");
//		response.setContentType("application/octet-stream; charset=utf-8");

		//응답 데이터의 크기 설정
		//Body의 용량을 지정 ( close 대신 수행하는 역할 중요! )
		//	Content-Length
		//	-> 브라우저(클라이언트)가 응답 데이터의 끝(EOF)을 알 수 있도록 한다
//		response.setContentLength( (int)src.length() ); //(int) 2GB 이하는 문제 없음.
		response.setContentLengthLong( src.length() );
		//(int) -> byte 로 약 21억 까지 가능 = 2GB   => (long)은 더 큼.

		//응답 데이터의 인코딩 설정
		//	CharacterEncoding
		//	-> BODY에 대한 인코딩 설정
		response.setCharacterEncoding("UTF-8");

		//브라우저가 다운로드할 파일의 이름 설정
		//	Content-Disposition
		//	-> 파일 이름이 응답 헤더로 포함되어 응답될 때
		//	톰캣 서버의 인코딩(ISO-8859-1)이 적용되어 한글이
		//	깨지거나 인식되지 못하는 경우가 발생한다
		//	-> URL Encoding을 이용하여 원본(UTF-8)을 유지하도록 설정한다
//		String outputName = downFile.getOriginName();	//한글 처리 잘 안됨
		String outputName = URLEncoder.encode(downFile.getOriginName(), "UTF-8" );

		// URLEncoder 에서 띄어쓰기 + 처리됨 -> 다시 띄어쓰기 처리
		outputName = outputName.replace("+", "%20");	//%20 -> 띄어쓰기

		response.setHeader("Content-Disposition", "attachment; filename=\""+outputName+"\"");

		//-------------------------------------------------------------------------------

		//** 응답 메시지의 BODY영역 설정하기

		//	서버 File객체 -> FileInputStream 입력 -> HTTP Response OutputStream 출력

		//서버의 파일 입력 스트림 객체
		FileInputStream in = new FileInputStream(src);

		//클라이언트의 응답 출력 스트림 객체
		OutputStream out = response.getOutputStream();

		//서버->클라이언트의 파일 복사
		FileCopyUtils.copy(in, out);


	}


}
