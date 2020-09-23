package kr.ac.kopo.account.dao;

import java.util.List;

import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.util.PagingVO;

public interface DepositDetailDAO {

	List<DepositDetailVO> detailList(String accountNumber);
	
	String month();
	
	
	List<DepositDetailVO> detailListMonthAgo(DepositDetailVO depositDetailVO);
	
	List<DepositDetailVO> depositDetailCategory(DepositDetailVO depositDetailVO);
	
	int lastMonthSumByCategory(DepositDetailVO depositDetailVO);	
	
	// 도전하기. 메인 계좌의 이번달 총지출(해당 카테고리)
	int nowBalanceByType(DepositDetailVO depositDetailVO);
	
	// 해당 계좌 번호의 이번 달 지출액(입금제외)
	int expenditureThisMonth(String accountNumber);
	
	// 계좌 내의 잦은 지출 순 지출명, 카운트
	List<DepositDetailVO> frequentExpenditure(String accountNumber);
	
	// 선택된 이번달 잦은 지출 정보(날짜, 금액)
	List<DepositDetailVO> getFrequentDetail(DepositDetailVO depositDetailVO);
	
	// 이번 지출 Top3
	List<DepositDetailVO> expenditureTop3(String accountNumber);
	
	// 이번달 주별 지출
	List<DepositDetailVO> expenditureByWeekList(String accountNumber);
	
	
	// 페이징. 게시물 총 갯수
	int countBoard(String accountNumber);
	
	// 페이징. 페이징 처리 게시글 조회
	List<DepositDetailVO> selectBoard(PagingVO vo);
	
	// 페이징. 페이징 처리 게시글 조회 월 변화
	List<DepositDetailVO> selectBoardMonthChange(PagingVO vo);
}
