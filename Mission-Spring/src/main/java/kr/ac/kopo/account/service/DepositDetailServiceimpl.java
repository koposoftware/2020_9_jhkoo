package kr.ac.kopo.account.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.account.dao.DepositAccountDAO;
import kr.ac.kopo.account.dao.DepositDetailDAO;
import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.util.PagingVO;

@Service
public class DepositDetailServiceimpl implements DepositDetailService {

	@Autowired
	private DepositDetailDAO depositDetailDAO;
	@Autowired
	private DepositAccountDAO depositAccountDAO;
	
	
	@Override
	public List<DepositDetailVO> depositDetailList(String accountNumber) {	
		List<DepositDetailVO> depositDetailList = depositDetailDAO.detailList(accountNumber);
		return depositDetailList;
	}
	
	@Override
	public String month() {
		String month = depositDetailDAO.month();
		return month;
	}

	@Override
	public List<DepositDetailVO> depositDetailListMonthAgo(DepositDetailVO depositDetailVO) {
		List<DepositDetailVO> depositDetailListMonthAgo = depositDetailDAO.detailListMonthAgo(depositDetailVO);	
		return depositDetailListMonthAgo;
	}
	
	@Override
	public List<DepositDetailVO> depositDetailCategory(DepositDetailVO depositDetailVO) {
		List<DepositDetailVO> depositDetailCategoryList = depositDetailDAO.depositDetailCategory(depositDetailVO);
		return depositDetailCategoryList;
	}


	@Override
	public int getExpenditureThisMonth(String id) {
		// 1. id에 해당하는 계좌번호들 가져오기   
		List<String> accountList = depositAccountDAO.accountNumListById(id);
		
		//  2. 계좌번호에 해당하는 지출액(입금제외) 가져와 합치기
		int totalExpenditureThisMonth = 0;
		for(String accountNumber:accountList) {
			int expenditureThisMonth = depositDetailDAO.expenditureThisMonth(accountNumber);
			totalExpenditureThisMonth += expenditureThisMonth;
		}
		
		return totalExpenditureThisMonth;
	}
	

	@Override
	public List<DepositDetailVO> frequentExpenditureList(String id) {
		// 1. id에 해당하는 계좌번호들 가져오기
		List<String> accountList = depositAccountDAO.accountNumListById(id);

		// 2. 계좌별 잦은 지출 list 가져와 합치기
		List<DepositDetailVO> myFrequentExpenditureList = new ArrayList<>();
		
		for(String accountNumber:accountList) {
			List<DepositDetailVO> frequentExpenditureList = depositDetailDAO.frequentExpenditure(accountNumber);
			myFrequentExpenditureList.addAll(frequentExpenditureList);
		}
		
		
		return myFrequentExpenditureList;
	}

	@Override
	public List<DepositDetailVO> getFrequentDetail(DepositDetailVO depositDetailVO) {
		List<DepositDetailVO> frequentDetailList = depositDetailDAO.getFrequentDetail(depositDetailVO);
		return frequentDetailList;
	}

	@Override
	public List<DepositDetailVO> expenditureTop3List(String id) {
	//  1. id에 해당하는 계좌번호들 가져오기
	//	List<String> accountList = depositAccountDAO.accountNumListById(id);
		
		// 메인계좌만 가져오기
		String accountNumber = depositAccountDAO.getMainAccountNumber(id);
		
		List<DepositDetailVO> expenditureTop3List = depositDetailDAO.expenditureTop3(accountNumber);
		
		return expenditureTop3List;
	}

	@Override
	public List<DepositDetailVO> expenditureByWeekList(String accountNumber) {
		List<DepositDetailVO> expenditureByWeekList = depositDetailDAO.expenditureByWeekList(accountNumber);
		return expenditureByWeekList;
	}

	
	@Override
	public int countBoard(String accountNumber) {
		int countBoard = depositDetailDAO.countBoard(accountNumber);
		return countBoard;
	}

	@Override
	public List<DepositDetailVO> selectBoard(PagingVO vo) {
		List<DepositDetailVO> selectBoard = depositDetailDAO.selectBoard(vo);
		return selectBoard;
	}

	@Override
	public List<DepositDetailVO> selectBoardMonthChange(PagingVO vo) {
		List<DepositDetailVO> selectBoardMonthChange = depositDetailDAO.selectBoardMonthChange(vo);
		return selectBoardMonthChange;
	}

	
	

	
	

}
