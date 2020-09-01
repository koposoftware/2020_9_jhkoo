package kr.ac.kopo.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.cash.vo.CashVO;
import kr.ac.kopo.member.vo.MemberVO;

@Repository
public class MemberDAOimpl implements MemberDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public MemberVO login(MemberVO member) {
		MemberVO loginVO = sqlSession.selectOne("member.dao.MemberDAO.login", member);
		return loginVO;
	}

	@Override
	public void join(MemberVO member) {
		sqlSession.insert("member.dao.MemberDAO.join", member);
	}

	@Override
	public String idCheck(String id) {
		String idCheck = sqlSession.selectOne("member.dao.MemberDAO.idCheck", id);
		return idCheck;
	}

	@Override
	public void cashUpdate(CashVO cashVO) {
		sqlSession.update("member.dao.MemberDAO.cashUpdate", cashVO);
	}

	

	
}
