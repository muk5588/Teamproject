package shop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.Item;
import dto.ItemFile;
import shop.service.face.AdminShopService;
import util.Paging;

@Controller
@RequestMapping("/shop/admin")
public class AdminShopController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired AdminShopService adminShopService;
	
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
	
	@GetMapping("/insert")
	public void adminItemInsert() {}
	
	@PostMapping("/insert")
	public void adminItemInsertProc() {
		
		
		
	}//해야함. 페이징도 해야함.
	
}
