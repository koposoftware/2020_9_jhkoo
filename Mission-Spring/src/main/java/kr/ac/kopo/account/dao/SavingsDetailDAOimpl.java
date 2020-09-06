package kr.ac.kopo.account.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.account.vo.SavingsDetailVO;

@Repository
public class SavingsDetailDAOimpl implements SavingsDetailDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<SavingsDetailVO> detailList(String accountNumber) {
		List<SavingsDetailVO> detailList = sqlSession.selectList("account.dao.SavingsDetailDAO.savingsDetailList", accountNumber);
		
		return detailList;
	}
	
	
}
