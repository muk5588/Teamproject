package board.dao;

import board.dto.BoardFile;
import org.springframework.stereotype.Repository;

@Repository("FileDao")
public interface FileDao {

	public void insert(BoardFile filetest);

	public int getFileCnt(int boardno);

	public BoardFile getFileByBoardNo(int boardno);

}
