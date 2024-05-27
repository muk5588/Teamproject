package inquiry.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import dto.InquiryFile;

@Repository("InquiryFileDao")
public interface inquiryFileDao {

	public int insertInquiryFiles(@Param("inquiryFiles")List<InquiryFile> inquiryFiles);

	public List<InquiryFile> getFilesByinquiryNo(int inquiryNo);

	public InquiryFile getFileByFileNo(int fileNo);

	public int deleteByFileNo(int no);


}
