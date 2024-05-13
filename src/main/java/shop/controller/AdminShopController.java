package shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dto.Item;
import dto.ItemFile;
import shop.service.face.AdminShopService;
import user.dto.User;
import util.Paging;

@Controller
@RequestMapping("/shop/admin")
public class AdminShopController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired AdminShopService adminShopService;
	@Autowired HttpSession session;
	
	@RequestMapping("/list")
	public void adminList(
		    @RequestParam(defaultValue ="0") int curPage
		    ,@RequestParam(value="search",required = false) String search
			,Model model
			) {
	    Paging paging = new Paging();
	    if( null != search && !"".equals(search)) {
	    	paging.setSearch(search);
	    }
	    
	    paging = adminShopService.getPaging(curPage,paging);
	    
	    if( null != search && !"".equals(search)) {
	    	paging.setSearch(search);
	    }
	    
		List<Item> items = adminShopService.selectItems(paging);
		List<ItemFile> itemFiles = adminShopService.selectTitleImgFile(items);
		logger.debug("items : {}", items);
		logger.debug("itemFiles : {}", itemFiles);
	    model.addAttribute("curPage", curPage);
	    model.addAttribute("paging", paging);
	    model.addAttribute("items", items);
	    model.addAttribute("itemFiles", itemFiles);
	}
	
	@GetMapping("/create")
	public void adminItemInsert() {}
	
	@PostMapping("/create")
	public String adminItemInsertProc(
			Model model,@RequestAttribute(required = false)MultipartFile file
			,Item item) {
		logger.debug("post create 실행");
		logger.debug("post create item : {}", item);
		User user = (User) session.getAttribute("dto1");
		if( null == user) { 
			return "/login";
		}
		item.setUserNo(user.getUserno());
		
		int res = adminShopService.insertItem(item);
		logger.debug("post create adminShopService : {}", res);
		//상품 설명 IMG파일 DB에 insert
		if( res > 0  ) {
			int fileSave = adminShopService.fileSave(item);
			logger.debug("post create fileSave res : {}", fileSave);
		}//if( res > 0  )
		
		//상품 대표 이미지 파일 update
		if( res > 0  && null != file && file.getSize() > 0) {
			int titleImgRes = adminShopService.updatetitleImg(item,file);
			logger.debug("post create fileSave titleImgRes : {}", titleImgRes);
		}else {
			logger.debug("post create file  : {}", file);
		}
		return "redirect:/shop/admin/list";
	}

	@RequestMapping("/uploaditemfile")
	public void uploadItemFile() {
		logger.debug("파일 업로드 처리");
		ItemFile file = adminShopService.fileTempSave();
		logger.debug("파일 업로드 처리 결과 : {}", file);
	}//파일 경로 /resources/itemUpload/
	
	
	
	
}
