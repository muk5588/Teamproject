package inquiry.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import dto.InquiryFile;

public interface inquiryFileService {

	/**
	 * MultipartFile배열에서 file 추출 후 List<InquiryFile> 로 반환
	 * @param files - MultipartFile[]
	 * @param inquiryNo - 문의 번호 
	 * @return - List<InquiryFile>
	 */
	public List<InquiryFile> extractInquiryFiles(MultipartFile[] files, int inquiryNo);

	/**
	 * List<InquiryFile> INSERT
	 * @param inquiryFiles - InquiryFile List
	 * @return - 삽입된 데이터 행
	 */
	public int insertInquiryFiles(List<InquiryFile> inquiryFiles);

	/**
	 * 문의 번호로 고객 문의 파일 조회
	 * @param inquiryNo - 문의 번호
	 * @return - 조회된 전체 행
	 */
	public List<InquiryFile> getFilesByinquiryNo(int inquiryNo);

	/**
	 * 파일 번호로 문의 파일 조회
	 * @param fileNo - 파일 번호
	 * @return - 조회된 행
	 */
	public InquiryFile getFileByFileNo(int fileNo);

	public int deleteByFileNo(int no);

	


}
