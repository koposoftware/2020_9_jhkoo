package kr.ac.kopo.cash.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.cash.vo.CashVO;

@Repository
public class CashDAOimpl implements CashDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void insert(CashVO cashVO) {

		sqlSession.insert("cash.dao.CashDAO.insert", cashVO);
	}

	@Override
	public List<CashVO> selectAll(String id) {
		List<CashVO> cashList = sqlSession.selectList("cash.dao.CashDAO.selectAll", id);
		return cashList;
	}

	@Override
	public void delete(int cashNo) {
		sqlSession.delete("cash.dao.CashDAO.delete", cashNo);
	}
	
	
}
