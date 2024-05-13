package com.test.www;

import board.dto.MainBoard;
import board.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import user.dto.User;
import util.service.GeoCoding;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	GeoCoding geoCoding;
	@Autowired
	BoardService boardService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {

		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		List<MainBoard> list = boardService.list();
		for (int i = 0; i < list.size(); i++) {
			String cate = list.get(i).getCategoryName();
			String category = cate.split("-")[1];
			list.get(i).setCategoryName(category);
		}
		String formattedDate = dateFormat.format(date);
		String x = null;
		String y = null;
		String address = null;
		User user = (User) session.getAttribute("dto1");
		if (user == null) {
			x = "126.98322015";
			y = "37.57023746";
			address = "서울특별시 종로1가";
			model.addAttribute("x",x);
			model.addAttribute("y",y);
			model.addAttribute("address",address);
		}else {
			address = user.getAddress();
			HashMap<String, String> XYMap = geoCoding.getXYMapfromJson(geoCoding.getKakaoApiFromAddress(address));
			x = XYMap.get("x");   //위도
			y = XYMap.get("y");   //경도
			model.addAttribute("x",x);
			model.addAttribute("y",y);
			model.addAttribute("address",address);
		}
		model.addAttribute("list",list);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/error")
	public void error() {
		
	}
	
}
