package kr.ac.kopo.cash.dao;

import java.util.List;

import kr.ac.kopo.cash.vo.CashVO;

public interface CashDAO {

	/*
	 *  내역 등록
	 */
	void insert(CashVO cashVO);
	
	/*
	 *  해당 이용자의 현금 내역 조회
	 */
	List<CashVO> selectAll(String id);
	
	/*
	 *  내역 삭제
	 */
	void delete(int cashNo);
}
