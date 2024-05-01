package board.dao;

import board.dto.BoardFile;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("FileDao")
public interface FileDao {

	public void insert(BoardFile filetest);

	public int getFileCnt(int boardno);

	public BoardFile getFileByBoardNo(int boardno);

	public List<BoardFile> getFilesByBoardNo(int boardno);

}
