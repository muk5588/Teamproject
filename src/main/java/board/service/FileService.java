package board.service;

import java.util.List;

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

}
