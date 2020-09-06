package kr.ac.kopo.account.service;

import java.util.List;

import kr.ac.kopo.account.vo.SavingsDetailVO;

public interface SavingsDetailService {

	/**
	 *  적금 내역 리스트
	 */
	List<SavingsDetailVO> savingsDetailList(String accountNumber);
	
}
