package inquiry.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.dto.Board;
import board.service.FileService;
import inquiry.service.InquiryService;
import util.Paging;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private InquiryService inquiryService;
	@Autowired private FileService fileService;
	@Autowired private ServletContext servletContext;

	@GetMapping("/list")
	public String list() {
			
		
		return "/inquiry/list";
	}
}