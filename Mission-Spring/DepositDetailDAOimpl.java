package kr.ac.kopo.account.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.account.vo.DepositDetailVO;

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
	public List<DepositDetailVO> infiniteScrollDown(DepositDetailVO depositDetailVO) {
		
		List<DepositDetailVO> infiniteScrollDown = sqlSession.selectList("account.dao.DepositDetailDAO.lastMonth", depositDetailVO);
		
		return infiniteScrollDown;
	}
	
	

}
