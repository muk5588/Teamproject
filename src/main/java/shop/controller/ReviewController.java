package shop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import dto.Review;
import shop.service.face.ReviewService;
import user.dto.User;

@Controller
@RequestMapping("/shop/review")
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

	@RequestMapping("/writeReviewForm")
	public void writeReviewForm(@RequestParam("itemNo")int itemNo, Model model){
		model.addAttribute("itemNo", itemNo);
	}

	@RequestMapping("/writereview")
	@ResponseBody
	public int writeReview(@RequestParam("itemNo")int itemNo,  Review review, String title, String content, Model model
			, @SessionAttribute(value = "dto1", required = false) User login){
		int res = 0;
		//1. 내가 구매한 상품(주문 내역)이 아니면 리뷰 작성 버튼이 보이지 않아야 함.
		//주문한 내역에 해당 itemNo의 상품이 있으면 리뷰 작성 버튼이 보이고 없으면 안보여야함.

		//2. 리뷰 제목, 작성작 닉네임, 리뷰 내용, 리뷰 작성일
		review.setItemNo(itemNo);
		review.setUserNo(login.getUserno());
		review.setReviewTitle(title);
		review.setReviewContent(content);
		res = reviewService.writeReview(review);
		System.out.println(res);
		return res;
	}

	@PostMapping("/deletereview")
	public int deleteReview(@RequestParam("reviewNo")int reviewNo
		,@SessionAttribute(value = "dto1", required = false) User login
		, Review review){
		int res = 0;


		review.setReviewNo(reviewNo);
		review.setUserNo(login.getUserno());

		res = reviewService.deleteReview(review);


		return res;
	}

	@RequestMapping("/updateReviewForm")
	public void updateReviewForm(@RequestParam("reviewNo") int reviewNo,
	                             @RequestParam("reviewTitle") String reviewTitle,
	                             @RequestParam("reviewContent") String reviewContent,
	                             Model model) {
	    // 리뷰 번호, 제목, 내용을 모델에 추가하여 수정 페이지에 전달합니다.
	    model.addAttribute("reviewNo", reviewNo);
	    model.addAttribute("reviewTitle", reviewTitle);
	    model.addAttribute("reviewContent", reviewContent);
	}

	@RequestMapping("/updatereview")
	@ResponseBody
	public int updateReview(Review review, String title, String content) {
	    // 리뷰를 업데이트합니다. 리뷰의 내용을 변경하고 DB에 업데이트된 내용을 반영합니다.
		review.setReviewTitle(title);
		review.setReviewContent(content);
	    int res = reviewService.updateReview(review);
	    return res;
	}
	
}
