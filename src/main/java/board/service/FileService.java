package board.service;

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

}
