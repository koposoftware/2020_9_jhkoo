package kr.ac.kopo.account.dao;

import java.util.List;

import kr.ac.kopo.account.vo.DepositDetailVO;

public interface DepositDetailDAO {

	List<DepositDetailVO> detailList(String accountNumber);
	
	String month();
	
	
	List<DepositDetailVO> detailListMonthAgo(DepositDetailVO depositDetailVO);
	
	List<DepositDetailVO> depositDetailCategory(DepositDetailVO depositDetailVO);
	
	
	
	
	
	List<DepositDetailVO> infiniteScrollDown(DepositDetailVO depositDetailVO);
}
