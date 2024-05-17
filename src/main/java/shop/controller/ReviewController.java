package shop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.Review;
import shop.service.face.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private ReviewService reviewService;
	
//	@RequestMapping("/")
//	public void defaultPage() {
		//전체 조회 관리자에서만 확인 
		//사용자는 상품당 List 
//	}
	
	@RequestMapping("/view")
	public void viewReview(Model model, @RequestParam("itemNo")int itemNo) {
		logger.debug("리뷰");
//        List<Review> reviews = reviewService.getReviewsByItemNo(itemNo);
//        model.addAttribute("reviews", reviews);
//        return "review/reviewList";
	}
	
	@RequestMapping("/loadreview")
	@ResponseBody
	public List<Review> loadreview(
		@RequestParam("itemNo")int itemNo) {
		logger.debug("리뷰 가져오기");
		List<Review> reviews = null;
		//리뷰 가져오기
		reviews = reviewService.selectByItemNo(itemNo);
		logger.debug("리뷰 가져오기 reviews : {} ",reviews);
		return reviews;
	}
	
	
	
}
