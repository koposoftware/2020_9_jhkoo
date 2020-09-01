package kr.ac.kopo.member.dao;

import kr.ac.kopo.cash.vo.CashVO;
import kr.ac.kopo.member.vo.MemberVO;

public interface MemberDAO {

	MemberVO login(MemberVO member);
	
	void join(MemberVO member);
	
	String idCheck(String id);
	
	void cashUpdate(CashVO cashVO);
	
}
