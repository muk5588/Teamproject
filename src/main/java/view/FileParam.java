package view;

import org.springframework.web.multipart.MultipartFile;

public class FileParam {
	private String title;
	private MultipartFile file;
	public FileParam() {
	}
	public FileParam(String title, MultipartFile file) {
		super();
		this.title = title;
		this.file = file;
	}
	@Override
	public String toString() {
		return "FileParam [title=" + title + ", file=" + file + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
