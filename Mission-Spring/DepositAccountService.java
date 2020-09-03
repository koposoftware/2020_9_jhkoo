package kr.ac.kopo.account.service;

import java.util.List;

import kr.ac.kopo.account.vo.DepositAccountVO;

public interface DepositAccountService {

	// 보유 입출금 계좌 리스트
	List<DepositAccountVO> selectDepositAccountById(String id);
	
	// 보유 입출금 계좌 총 합
	int depositTotalBalanceById(String id);
	
	// 보유 입출금 계좌 번호 리스트
	List<String> depositAccountNumListById(String id);
	
	// 입출금 자유 계좌 가입
	void insertDepositAccount(DepositAccountVO depositAccountVO);
	
	// 해당 계좌 정보 가져오기
	DepositAccountVO getDepositAccountInfo(String accountNumber);
	
	// 입출금 자유 계좌 정보 수정
	void changeDepositManage(DepositAccountVO depositAccountVO);
	

}
