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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dto.Item;
import dto.ItemFile;
import shop.service.face.AdminShopService;
import user.dto.User;
import util.Paging;
import util.ShopPaging;

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
	    ShopPaging paging = new ShopPaging();
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
			if(!file.isEmpty()) {
				int fileSave = adminShopService.fileSave(item);
				logger.debug("post create fileSave res : {}", fileSave);
			}
		}//if( res > 0  )
		
		//상품 대표 이미지 파일 update
		if( res > 0  && null != file && file.getSize() > 0) {
			if(!file.isEmpty()) {
				int titleImgRes = adminShopService.updatetitleImg(item,file);
				logger.debug("post create fileSave titleImgRes : {}", titleImgRes);
			}
		}else {
			logger.debug("post create file  : {}", file);
		}
		return "redirect:/shop/admin/list";
	}

	@ResponseBody
	@RequestMapping("/fileupload")
	public void uploadItemFile() {
		logger.debug("파일 업로드 처리");
		ItemFile file = adminShopService.fileTempSave();
		logger.debug("파일 업로드 처리 결과 : {}", file);
	}//파일 경로 /resources/itemUpload/
	
	@RequestMapping("/detail")
	public void detail(int itemNo, Model model) {
		Item item = adminShopService.selectItemByItemNo(itemNo);
		List<ItemFile> files = adminShopService.selectItemFileByItemNo(itemNo);
		logger.debug("item : {}", item);
		logger.debug("files : {}", files);
		model.addAttribute("item", item);
		model.addAttribute("itemFiles", files);
	}
	
	@GetMapping("/update")
	public void update(Model model, @RequestParam(value="itemNo")int itemNo) {
		logger.debug("get update");
		logger.debug("get update itemNo  : {} ",itemNo);
		Item item = adminShopService.selectItemByItemNo(itemNo);
		List<ItemFile> files = adminShopService.selectItemFileByItemNo(itemNo);
		logger.debug("item : {}", item);
		logger.debug("files : {}", files);
		model.addAttribute("item", item);
		model.addAttribute("files", files);
		
	}
	
	@PostMapping("/update")
	public String updateProc(Model model,@RequestAttribute(required = false)MultipartFile file
			,Item item) {
		logger.debug("post update");
		logger.debug("post update item : {}", item);
		logger.debug("post update file : {}", file);
		if(  null != file && file.getSize() > 0 && !file.isEmpty()) {
			int titleImgFileNo = adminShopService.updatetitleImg(item,file);
			item.setTitleImg(titleImgFileNo);
			logger.debug("post create fileSave titleImgFileNo : {}", titleImgFileNo);
		}else {
			logger.debug("post create file  : {}", file);
		}
		int res = adminShopService.updateIByItem(item);
		
		return "redirect:./detail?itemNo=" +item.getItemNo();
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="itemNo",required = false)int itemNo) {
		logger.debug("삭제 처리");
		int res = adminShopService.deleteByItemNo(itemNo);
		return "redirect:./list";
	}
	
	
	
	
	
}
