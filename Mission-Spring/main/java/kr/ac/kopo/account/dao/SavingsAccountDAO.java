package kr.ac.kopo.account.dao;

import java.util.List;

import kr.ac.kopo.account.vo.SavingsAccountVO;

public interface SavingsAccountDAO {

	/**
	 *  사용자의 적금 계좌 조회
	 */
	public List<SavingsAccountVO> selectById(String id);
	
	/**
	 * 사용자의 입출금 계좌 잔액 총합
	 */
	public int totalBalanceById(String id);
	
	/**
	 * 적금 상품 가입
	 */
	public void insert(SavingsAccountVO savingsAccountVO);
}
