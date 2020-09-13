package kr.ac.kopo.favorite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.favorite.service.FavoriteService;
import kr.ac.kopo.favorite.vo.FavoriteVO;
import kr.ac.kopo.member.vo.MemberVO;

@Controller
public class FavoriteController {

	@Autowired
	private FavoriteService favoriteService;
	
	
	/**
	 *   즐겨찾기 페이지 가기. 최근 이체 목록을 공유영역에 올려 사용자에게 보여줍니다.
	 */
	@RequestMapping("/favoriteList")
	public ModelAndView recentList(HttpSession session) {
		ModelAndView mav = new ModelAndView("favorite/favorite");
		
		// 아이디
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		
		// 최근 거래 목록
		List<FavoriteVO> recentList = favoriteService.getRecentList(id);
		mav.addObject("recentList", recentList);
		
		return mav;
	}
	
	/**
	 *   즐겨찾기 등록 버튼 클릭 시 <즐겨찾기 등록하기>에서 <즐겨찾기 등록 완료> 로 변한 뒤, diabled 처리하는 ajax를 위한 ResponseBody!
	 *   최근 이체 테이블의 Flag 컬럼은 0에서 1로 바꿉니다.
	 */
	@ResponseBody
	@GetMapping("/favorite/updateFavorite")
	public void addFavorite(FavoriteVO favoriteVO) {
		// favorite Flag 0 -> 1
		favoriteVO.setFavoriteFlag("1");
		favoriteService.updateFavorite(favoriteVO);
	}
	
	/**
	 *   즐겨찾기 해지 ajax를 위한 ResponseBody
	 *   
	 */
	@ResponseBody
	@GetMapping("/favorite/deleteFavorite")
	public void deleteFavorite(FavoriteVO favoriteVO) {
		// favorite Flag 1 -> 0
		favoriteVO.setFavoriteFlag("0");
		favoriteService.updateFavorite(favoriteVO);
	}
}
