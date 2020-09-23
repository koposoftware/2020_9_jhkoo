package kr.ac.kopo.account.service;

import java.util.List;

import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.util.PagingVO;

public interface DepositDetailService {

	// 입출금 자유 예금 계좌 내역 (카드테이터와 연동되어 카테 고리 컬럼 존재한다고 가정)
	List<DepositDetailVO> depositDetailList(String accountNumber);
	
	// 이번 달
	String month();
	
	
	// 지난 달 내역
	List<DepositDetailVO> depositDetailListMonthAgo(DepositDetailVO depositDetailVO);
	
	
	// 카테고리별 내역
	List<DepositDetailVO> depositDetailCategory(DepositDetailVO depositDetailVO);
	
	
	// 이번 달 지출액     1. id에 해당하는 계좌번호들 가져오기     2. 계좌번호에 해당하는 지출액(입금제외) 가져오기 
	int getExpenditureThisMonth(String id);
	
	
	// 내 모든 입출금 계좌의 잦은 지출가져오기(돈 쓴 곳, 횟수)  1. id로 내 계좌번호들 가져오기   2. 계좌번호에 해당하는 지출 list 가져와 합치기
	List<DepositDetailVO> frequentExpenditureList(String id);
	
	
	// 선택된 이번달 잦은 지출 정보(날짜, 금액)
	List<DepositDetailVO> getFrequentDetail(DepositDetailVO depositDetailVO);
	
	// 이번달 지출 Top3
	List<DepositDetailVO> expenditureTop3List(String id);
	
	// 이번달 주별 지출액
	List<DepositDetailVO> expenditureByWeekList(String accountNumber);

	
	// 페이징. 게시물 총 갯수, 페이징 처리 게시글 조회
	int countBoard(String accountNumber);
	List<DepositDetailVO> selectBoard(PagingVO vo);
	
	// 페이징. 페이징 처리 게시글 조회 월 변화
	List<DepositDetailVO> selectBoardMonthChange(PagingVO vo);
}
