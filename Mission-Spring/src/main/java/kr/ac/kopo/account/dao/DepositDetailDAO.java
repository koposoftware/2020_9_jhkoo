package kr.ac.kopo.account.dao;

import java.util.List;

import kr.ac.kopo.account.vo.DepositDetailVO;

public interface DepositDetailDAO {

	List<DepositDetailVO> detailList(String accountNumber);
	
	String month();
	
	
	List<DepositDetailVO> detailListMonthAgo(DepositDetailVO depositDetailVO);
	
	List<DepositDetailVO> depositDetailCategory(DepositDetailVO depositDetailVO);
	
	int lastMonthSumByCategory(DepositDetailVO depositDetailVO);	
	
	// 도전하기. 메인 계좌의 이번달 총지출(해당 카테고리)
	int nowBalanceByType(DepositDetailVO depositDetailVO);
	
	List<DepositDetailVO> infiniteScrollDown(DepositDetailVO depositDetailVO);
}
