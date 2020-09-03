package kr.ac.kopo.cash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.cash.dao.CashDAO;
import kr.ac.kopo.cash.vo.CashVO;
import kr.ac.kopo.member.dao.MemberDAO;


@Service
public class CashServiceimpl implements CashService {

	@Autowired
	private CashDAO cashDAO;
	@Autowired
	private MemberDAO memberDAO;

	
	
	/**
	 *  내역 추가
	 *  1. 현금 내역에 추가
	 *  2. 사용자의 현금 상태 업데이트
	 */
	@Transactional
	@Override
	public void insertCashDetail(CashVO cashVO) {

		cashDAO.insert(cashVO);		
		memberDAO.cashUpdate(cashVO);	// 해당 아이디와 업데이트될 값 필요 
	}

	/**
	 *  내역 리스트 조회
	 */
	@Override
	public List<CashVO> selectAllById(String id) {
		List<CashVO> cashList = cashDAO.selectAll(id);
		return cashList;
	}

	/**
	 *  내역 지우기
	 *  삭제할 내역 주인의 id 
	 */
	@Override
	@Transactional
	public void removeCash(CashVO cashVO) {
		cashDAO.delete(cashVO.getNo());
		memberDAO.cashUpdate(cashVO);	// 해당 아이디와 업데이트될 값 필요 
		
	}
	
	
}
