package kr.ac.kopo.account.service;

import java.util.List;

import kr.ac.kopo.account.vo.DepositDetailVO;

public interface DepositDetailService {

	// 입출금 자유 예금 계좌와 연결된 카드 내역
	List<DepositDetailVO> depositDetailList(String accountNumber);
	
	// 이번 달
	String month();
	
	
	// 지난 달 내역
	List<DepositDetailVO> depositDetailListMonthAgo(DepositDetailVO depositDetailVO);
	
	
	// 카테고리별 내역
	List<DepositDetailVO> depositDetailCategory(DepositDetailVO depositDetailVO);
	
	
	
	

	// 스크롤 다운. 저번 달 내역 리스트
	List<DepositDetailVO> infiniteScrollDown(DepositDetailVO depositDetailVO);
}
