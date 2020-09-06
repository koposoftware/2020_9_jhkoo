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
	
	/**
	 *  적금 계좌 정보 가져오기(manage)
	 */
	public SavingsAccountVO getInfo(String accountNumber);
	
	/**
	 *  최초 자동이체를 위해 계좌 정보 가져오기
	 */
	public SavingsAccountVO getInfoForTrans(String accountNumber);
	
	/**
	 *  적금 계좌 정보 업데이트
	 */
	public void updateSavingsInfo(SavingsAccountVO savingsAccountVO);

	/**
	 *  남은 개월 수
	 */
	public int getSavingMonth(String accountNumber);

	/**
	 *  평균 입금액
	 */
	public int getAvgAmount(String accountNumber);
	
	/**
	 * 자동이체 on
	 */
	public void changeBool(SavingsAccountVO savingsAccountVO);
	
	/**
	 * 새로운 계좌번호 가져오기
	 */
	public String getNewAccountNumber();
	
	/**
	 * 예약 이체 해지 시 상태 Y-> N
	 */
	public void changeAutoTransferBool(String accountNumber);
}
