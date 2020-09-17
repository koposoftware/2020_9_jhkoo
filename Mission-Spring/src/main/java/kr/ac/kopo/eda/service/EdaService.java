package kr.ac.kopo.eda.service;

import java.util.List;

import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.eda.vo.EdaVO;
import kr.ac.kopo.eda.vo.EmailVO;

public interface EdaService {

	/**
	 *  이번 달 카테고리별 지출 퍼센트
	 *  1. 아이디로 메인 계좌번호 가져오기
	 *  2. 가져온 계좌번호로 카테고리별 지출액 list 담기
	 */
	public List<EdaVO> amountByType(String id);
	
	
	/**
	 *  해당 카테고리의 이번 달 내역
	 */
	public List<DepositDetailVO> detailListByCotegory(DepositDetailVO depositDetailVO);
	
	/**
	 *  해당 카테고리의 이번 달 + 저번 달 내역
	 */
	public List<DepositDetailVO> detailListByCotegory2(DepositDetailVO depositDetailVO);
	
	/**
	 *  이번 달 카테고리별 지출 합 리스트 가져와
	 *  가장 지출 많이한 카테고리 가져오기
	 */
	public String biggestCategory(String accountNumber);
	
	/**
	 *  정기 이메일 서비스 테이블에 insert
	 */
	public void addMailService(EmailVO emailVO);
	
	/**
	 *  정기 이메일 서비스 취소
	 */
	public void deleteMailService(String id);
	
	/**
	 *  정기 이메일 리스트 가져오기
	 */
	public List<EmailVO> getMailList();
	
	/**
	 *  정기 이메일 서비스 받는지 안받는지
	 */
	public int mailServiceBool(String id);
	
	/**
	 *  이번달, 저번달, 저저번달 수입액 리스트
	 */
	public List<Integer> depositByLast3Month(String accountNumber);
	/**
	 *  이번달, 저번달, 저저번달 지출액 리스트
	 */
	public List<Integer> withdrawByLast3Month(String accountNumber);	
	
}
