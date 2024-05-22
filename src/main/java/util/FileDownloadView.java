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

public class FileDownloadView extends AbstractView {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ServletContext servletContext;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        logger.info("FileDownloadView");
        //-------------------------------------------------------------------------------

        //모델값 가져오기
        BoardFile downFile = (BoardFile) model.get("downFile");
        logger.info("downFile : {}", downFile);

        //저장된 파일의 폴더 (upload)
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "resources" + File.separator + "boardUpload" + File.separator;
        logger.info("path : {}", path);

        //실제 업로드된 파일의 이름
        String filename = downFile.getStoredName();

        //실제 파일 객체
        File src = new File(path, filename);

        logger.info("서버에 업로드된 파일 : {}", src);
        logger.info("존재 여부 : {}", src.exists());
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

        //응답 데이터의 크기 설정
        response.setContentLengthLong(src.length());

        //응답 데이터의 인코딩 설정
        response.setCharacterEncoding("UTF-8");

        //브라우저가 다운로드할 파일의 이름 설정
        //	Content-Disposition
        String outputName = URLEncoder.encode(downFile.getOriginName(), "UTF-8");
        outputName = outputName.replace("+", "%20"); // %20 -> 띄어쓰기

        response.setHeader("Content-Disposition", "attachment; filename=\"" + outputName + "\"");

        //-------------------------------------------------------------------------------

        //** 응답 메시지의 BODY영역 설정하기

        //	서버 File객체 -> FileInputStream 입력 -> HTTP Response OutputStream 출력

        //서버의 파일 입력 스트림 객체
        FileInputStream in = new FileInputStream(src);

        //클라이언트의 응답 출력 스트림 객체
        OutputStream out = response.getOutputStream();

        //서버->클라이언트의 파일 복사
        FileCopyUtils.copy(in, out);

        // 스트림을 닫아 자원을 해제합니다.
        in.close();
        out.close();
    }
}
