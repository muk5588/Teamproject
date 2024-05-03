package board.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import board.dto.Board;
import board.dto.BoardFile;

public interface FileService {

	/**
	 * 파일 정보 DB에 삽입
	 * @param board - 게시글 번호
	 * @param file - MultipartFile 객체
	 */
	public void filesave(Board board, MultipartFile file);

	public int getFileCnt(int boardno);

	public BoardFile getFileByBoardNo(int boardno);

	/**
	 * 파일 번호를 이용하여 여러개의 파일 정보 조회
	 * @param boardno - 게시글 번호
	 * @return - 조회된 파일 정보 전체
	 */
	public List<BoardFile> getFilesByBoardNo(int boardno);

	/**
	 * Ajax 통신으로 받은 파일 임시 저장.
	 * @param request - 요청
	 * @param response - 응답
	 * @return 
	 */
	public BoardFile fileTempSave(HttpServletRequest request, HttpServletResponse response);

	public void listDeleteByBoardNo(ArrayList<Integer> boardno);

	/**
	 * content 안의 OriginName 정규식으로 검사
	 * @param content
	 * @return
	 */
	public List<String> extractOriginName(String content);
	
	/**
	 * content 안의 StoredName 정규식으로 검사
	 * @param content
	 * @return
	 */
	public List<String> extractStoredName(String content,  List<String> originNames);

	/**
	 * 파일 DB에 저장
	 * @param files - BoardFile 객체
	 */
	public void setFile(ArrayList<BoardFile> files);

	/**
	 * Ajax 통신을 통한 파일 정보 조회
	 * @param boardNo - 게시글 번호
	 * @return - 조회된 파일 행
	 */
	public List<BoardFile> getFileList(int boardNo);

	/**
	 * 파일 번호로 다운로드
	 * @param fileNo - 파일 번호
	 * @return - 조회된 파일 정보
	 */
	public BoardFile getFileByFileNo(int fileNo);

}
