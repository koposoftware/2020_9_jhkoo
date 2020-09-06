package kr.ac.kopo.cash.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.cash.service.CashService;
import kr.ac.kopo.cash.vo.CashVO;
import kr.ac.kopo.member.vo.MemberVO;

@Controller
public class CashController {

	@Autowired
	private CashService cashService;
	
	@RequestMapping("/cashManage")
	public ModelAndView goCashManage(HttpSession session) {

		
		ModelAndView mav = new ModelAndView("cash/cash");
		return mav;
	}
	
	//내역 입력
	@ResponseBody
	@PostMapping("/cash")
	public void addCashDetail(CashVO cashVO, HttpSession session) {
		
		cashService.insertCashDetail(cashVO);

		//입력한 값으로 session에 등록된 loginVO의 cash, 총자산,저번달 대비 변동자산 업데이트 
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		int pastCash = loginVO.getCash();
		// 총자산 업데이트
		loginVO.setTotalBalance(loginVO.getTotalBalance() - pastCash + cashVO.getCash() );
		// 저번 달 대비 변동 자산
		loginVO.setTotalBalanceChange(loginVO.getTotalBalanceChange() - pastCash + cashVO.getCash() );
		// cash 정보 업데이트
		loginVO.setCash(cashVO.getCash());
	}
	
	//내역 리스트 조회
	@ResponseBody
	@GetMapping("/cash")
	public List<CashVO> getCashList(HttpSession session){
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		
		List<CashVO> cashList = cashService.selectAllById(id);
		return cashList;
	}
	
	//내역 삭제
	@ResponseBody
	@DeleteMapping("/cash/{cashNo}/{id}")
	public void deleteCash(@PathVariable("cashNo") int cashNo,
							@PathVariable("id") String id) {
		CashVO cashVO = new CashVO();
		cashVO.setNo(cashNo);
		cashVO.setId(id);
		
		cashService.removeCash(cashVO);
	}
}
