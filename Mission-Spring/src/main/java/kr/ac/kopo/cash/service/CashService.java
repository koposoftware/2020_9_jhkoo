package kr.ac.kopo.cash.service;

import java.util.List;

import kr.ac.kopo.cash.vo.CashVO;

public interface CashService {
	
	// 내역 입력
	void insertCashDetail(CashVO cashVO);
	
	// 내역 리스트 조회
	List<CashVO> selectAllById(String id);
	
	// 내역 삭제
	void removeCash(CashVO vashVO);
	

}
