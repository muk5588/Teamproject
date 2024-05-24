package com.test.www;

import board.dto.Board;
import board.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import report.dto.BoardReport;
import report.service.ReportService;
import user.dto.User;
import util.service.GeoCoding;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.*;

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
	@Autowired
	ReportService reportService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {

		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		List<Board> list = boardService.list();
		String x = null;
		String y = null;
		String address = null;
		User user = (User) session.getAttribute("dto1");
		if (user == null) {
			x = "126.98322015";
			y = "37.57023746";
			address = "서울시 중구 종로1가";
			model.addAttribute("x",x);
			model.addAttribute("y",y);
			model.addAttribute("address",address);
		}else {
			address = user.getAddress();
			HashMap<String, String> XYMap = geoCoding.getXYMapfromJson(geoCoding.getKakaoApiFromAddress(address));
			x = XYMap.get("x");   //위도
			y = XYMap.get("y");   //경도
			String[] addressParts = address.split(" ");
			address = (addressParts.length >= 3) ? addressParts[0] + " " + addressParts[1] + " " + addressParts[2]: address;
			model.addAttribute("x",x);
			model.addAttribute("y",y);
			model.addAttribute("address",address);
		}
		List<BoardReport> reportlist = reportService.reportboardlist();
		Iterator<Board> iterator = list.iterator();
		while (iterator.hasNext()) {
			Board board = iterator.next();
			for (BoardReport report : reportlist) {
				if (report.getBoardNo() == board.getBoardNo()) {
					iterator.remove();
					break; // 현재 보드가 삭제되었으므로 내부 루프 종료
				}
			}
		}
		model.addAttribute("list",list);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/error")
	public void error() {
		
	}
	
	@RequestMapping("/info/company")
	public void company() {
		
	}
	@RequestMapping("/info/policy")
	public void policy() {
		
	}
	@RequestMapping("/info/privacy")
	public void privacy() {
		
	}
	@RequestMapping("/info/service")
	public void service() {
		
	}
	@RequestMapping("/info/service2")
	public void service2() {
		
	}
	@RequestMapping("/info/customerService")
	public void customerService() {
		
	}
	
}
