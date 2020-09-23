package kr.ac.kopo.account.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.util.PagingVO;

@Repository
public class DepositDetailDAOimpl implements DepositDetailDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<DepositDetailVO> detailList(String accountNumber) {
		List<DepositDetailVO> depositDetailList = sqlSession.selectList("account.dao.DepositDetailDAO.detailList",accountNumber);
		return depositDetailList;
	}
	
	@Override
	public String month() {
		String month = sqlSession.selectOne("account.dao.DepositDetailDAO.month");
		return month;
	}

	@Override
	public List<DepositDetailVO> depositDetailCategory(DepositDetailVO depositDetailVO) {
		List<DepositDetailVO> detailListCategory = sqlSession.selectList("account.dao.DepositDetailDAO.category",depositDetailVO);
		return detailListCategory;
	}

	@Override
	public List<DepositDetailVO> detailListMonthAgo(DepositDetailVO depositDetailVO) {
		List<DepositDetailVO> detailListMonthAgo = sqlSession.selectList("account.dao.DepositDetailDAO.lastMonth", depositDetailVO);
		return detailListMonthAgo;
	}

	@Override
	public int lastMonthSumByCategory(DepositDetailVO depositDetailVO) {
		int lastMonthSumByCategory = sqlSession.selectOne("account.dao.DepositDetailDAO.lastMonthSumByCategory", depositDetailVO);
		return lastMonthSumByCategory;
	}

	@Override
	public int nowBalanceByType(DepositDetailVO depositDetailVO) {
		int nowBalanceByType = sqlSession.selectOne("account.dao.DepositDetailDAO.nowBalanceByType",depositDetailVO);
		return nowBalanceByType;
	}

	@Override
	public int expenditureThisMonth(String accountNumber) {
		int expenditureThisMonth = sqlSession.selectOne("account.dao.DepositDetailDAO.expenditureThisMonth", accountNumber);
		return expenditureThisMonth;
	}

	@Override
	public List<DepositDetailVO> frequentExpenditure(String accountNumber) {
		List<DepositDetailVO> frequentExpenditureList = sqlSession.selectList("account.dao.DepositDetailDAO.frequentExpenditure", accountNumber);
		return frequentExpenditureList;
	}

	@Override
	public List<DepositDetailVO> getFrequentDetail(DepositDetailVO depositDetailVO) {
		List<DepositDetailVO> frequentDetailList = sqlSession.selectList("account.dao.DepositDetailDAO.getFrequentDetail", depositDetailVO);
		return frequentDetailList;
	}

	@Override
	public List<DepositDetailVO> expenditureTop3(String accountNumber) {
		List<DepositDetailVO> expenditureTop3List = sqlSession.selectList("account.dao.DepositDetailDAO.expenditureTop3", accountNumber); 
		return expenditureTop3List;
	}

	@Override
	public List<DepositDetailVO> expenditureByWeekList(String accountNumber) {
		List<DepositDetailVO> expenditureByWeekList = sqlSession.selectList("account.dao.DepositDetailDAO.expenditureByWeek", accountNumber);
		return expenditureByWeekList;
	}

	@Override
	public int countBoard(String accountNumber) {
		int countBoard = sqlSession.selectOne("account.dao.DepositDetailDAO.countBoard", accountNumber);
		return countBoard;
	}

	@Override
	public List<DepositDetailVO> selectBoard(PagingVO vo) {
		List<DepositDetailVO> selectBoard = sqlSession.selectList("account.dao.DepositDetailDAO.selectBoard", vo);
		return selectBoard;
	}

	@Override
	public List<DepositDetailVO> selectBoardMonthChange(PagingVO vo) {
		List<DepositDetailVO> selectBoardMonthChange = sqlSession.selectList("account.dao.DepositDetailDAO.selectBoardMonthChange", vo);
		return selectBoardMonthChange;
	}

	
	
	

}
