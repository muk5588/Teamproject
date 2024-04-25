package board.service;

import org.springframework.web.multipart.MultipartFile;

import board.dto.Board;

public interface FileService {

	public void filesave(Board board, MultipartFile file);

}
