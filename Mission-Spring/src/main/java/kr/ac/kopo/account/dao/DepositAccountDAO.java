package kr.ac.kopo.account.dao;

import java.util.List;

import kr.ac.kopo.account.vo.DepositAccountVO;

public interface DepositAccountDAO {

	/**
	 * 사용자의 입출금 계좌 조회
	 */
	public List<DepositAccountVO> selectById(String id);
	
	/**
	 * 사용자의 입출금 계좌 잔액 총합
	 */
	public int totalBalanceById(String id);
	
	/**
	 * 사용자의 입출금 계좌 번호 조회
	 */
	public List<String> accountNumListById(String id);
	
	/**
	 * 입출금 자유 예금 상품 가입
	 */
	public void insert(DepositAccountVO depositAccountVO);
	
	/**
	 * 계좌 정보 가져오기
	 */
	public DepositAccountVO getInfo(String accountNumber);
	
	/**
	 * 계좌 정보 수정
	 */
	public void updateInfo(DepositAccountVO depositAccountVO);
	
	/**
	 * 사용자의 모든 계좌 상태 sub으로 만들기 
	 */
	public void mainToSub(String id);
	
	/**
	 *  메인 계좌번호 가져오기
	 */
	public String getMainAccountNumber(String id);
			
	
	/**
	 *  계좌 삭제하기
	 */
	public void deleteDepositAccount(String accountNumber);
}
