package kr.ac.kopo.account.service;

import java.util.List;

import kr.ac.kopo.account.vo.SavingsAccountVO;

public interface SavingsAccountService {

	// 보유 적금 계좌 리스트
	List<SavingsAccountVO> selectSavingsAccountById(String id);
	
	// 보유 적금 계좌 총 합
	int savingsTotalBalanceById(String id);
	
	// 적금 계좌 가입
	void insertSavingsAccount(SavingsAccountVO savingsAccountVO);
}
