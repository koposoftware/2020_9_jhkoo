package kr.ac.kopo.eda.service;

import java.util.List;

import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.eda.vo.EdaVO;

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
}
