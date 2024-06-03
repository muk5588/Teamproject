package shop.controller;

import dto.Item;
import dto.ItemFile;
import dto.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import report.service.ReportService;
import shop.service.face.ShopFileService;
import shop.service.face.ShopService;
import user.dto.User;
import util.ShopPaging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop")
public class ShopController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private ShopService shopService;
	@Autowired private ShopFileService shopFileService;
	@Autowired private ReportService reportService;
	
	@RequestMapping("")
	public String shopMain() { return "redirect:/shop/"; }
	
	@RequestMapping("/")
	public String list(Model model , ShopPaging shopPaging
			, @RequestParam(value="curPage", required = false, defaultValue = "0") int curPage
			, @RequestParam(required = false)String search
	) {
		String URL ="/shop/";
		model.addAttribute("URL", URL);
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
		List<Item> item = shopService.list(shopPaging);
		List<ItemFile> files = shopFileService.getTitleImgs(item);
//		List<ItemReport> reportlist = reportService.reportitemlist();
//		Iterator<Item> itemIterator = item.iterator();

//		while (itemIterator.hasNext()) {
//			Item item1 = itemIterator.next();
//			boolean itemRemoved = false; // Item이 제거되었는지 추적
//
//			for (ItemReport report : reportlist) {
//				if (report.getItemNo() == item1.getItemNo()) {
//					itemIterator.remove();
//					itemRemoved = true;
//					break;
//				}
//			}
//			if (itemRemoved) {
//				Iterator<ItemFile> fileIterator = files.iterator();
//				while (fileIterator.hasNext()) {
//					ItemFile file = fileIterator.next();
//					if (file.getItemNo() == item1.getItemNo()) {
//						fileIterator.remove();
//						break; // 해당 itemNo와 일치하는 첫 번째 파일을 제거하고 루프 종료
//					}
//				}
//			}
//		}
		logger.debug("title IMG files : {}", files);
		logger.debug("item Chk: {}", item);
		model.addAttribute("files", files);
		model.addAttribute("item", item);
		
		return "/shop/main";
	}
	
	@RequestMapping("/detail")
	public void detail(
			@RequestParam("itemNo") int itemNo
			, Model model, UserOrder userOrder
			, @SessionAttribute(value = "dto1", required = false) User login
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
		if( login != null) {
			int userno = login.getUserno();
			Map<Object,String> map = new HashMap<Object,String>();
			map.put("itemNo", String.valueOf(itemNo));
			map.put("userno", String.valueOf(userno));
			//상품 구매 내역
			int countMyOrder = shopService.countMyOrderByItemNo(map);
	
	
			System.out.println("countMyOrder" + countMyOrder);
			model.addAttribute("countMyOrder", countMyOrder);
		}
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
