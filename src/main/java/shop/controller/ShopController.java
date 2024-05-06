package shop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.Item;
import dto.ItemFile;
import shop.service.face.ShopFileService;
import shop.service.face.ShopService;
import util.Paging;

@Controller
@RequestMapping("/shop")
public class ShopController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private ShopService shopService;
	@Autowired private ShopFileService shopFileService;
	
	@RequestMapping("/main")
	public String shopMain() { return "redirect:/shop/"; }
	
	@RequestMapping("/")
	public String list(Model model , Paging shopPaging
			, @RequestParam(value="curPage", required = false, defaultValue = "0") int curPage
			, @RequestParam(required = false)String search
	) {
		//페이징
		shopPaging.setCurPage(curPage);
		if( null == search || "".equals(search)) {
			shopPaging = shopService.getPagging(shopPaging);
		}else if( search != null && !"".equals(search)) {
			shopPaging.setSearch(search);
			shopPaging = shopService.getPagging(shopPaging);
			shopPaging.setSearch(search);
		}
		logger.debug("Paging : {}", shopPaging);
		model.addAttribute("paging", shopPaging);
		model.addAttribute("curPage", curPage);
		
		//ITEM 객체 List + 대표 이미지 파일 정보 조회 및 view로 전달
		List<Item> item = shopService.list();
		List<ItemFile> files = shopFileService.getTitleImgs();
		logger.debug("title IMG files : {}", files);
		logger.debug("item Chk: {}", item);
		model.addAttribute("files", files);
		model.addAttribute("item", item);
		
		return "/shop/main";
	}
	
	@RequestMapping("/detail")
	private void detail(
			@RequestParam("itemNo") int itemNo
			, Model model
			) {
		logger.debug("detail itemNo : {}", itemNo);
		//상품 정보
		Item item = shopService.getItemByItemNo(itemNo);
		logger.debug("detail item : {}", item);
		//상품 정보 파일
		List<ItemFile> files = shopFileService.getItemFilesByItemNo(itemNo);
		logger.debug("detail item files : {}", files);
		
		model.addAttribute("item", item);
		model.addAttribute("files", files);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
