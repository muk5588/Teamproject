package board.dao;

import board.dto.BoardFile;

public interface FileDao {

	public void insert(BoardFile filetest);

	public int getFileCnt(int boardno);

	public BoardFile getFileByBoardNo(int boardno);

}
