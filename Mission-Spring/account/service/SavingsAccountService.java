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
	
	// 적금 계좌 정보 가져오기
	SavingsAccountVO getSavingsAccountInfo(String accountNumber);
	
	// 적금 계좌 정보 업데이트
	void changeSavingsManage(SavingsAccountVO savingsAccountVO);

	// 자동이체 on으로 업데이트
	void changeBool(SavingsAccountVO savingsAccountVO);

	// 새로운 계좌 정보
	String getNewAccountNumber();
	
	// 최초 자동이체를 위해 등록 계좌 정보 가져오기
	SavingsAccountVO getSavingsAccountInfoForTrans(String accountNumber);
}
