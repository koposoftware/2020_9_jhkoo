package kr.ac.kopo.member.dao;

import kr.ac.kopo.cash.vo.CashVO;
import kr.ac.kopo.member.vo.MemberVO;

public interface MemberDAO {

	MemberVO login(MemberVO member);
	
	void join(MemberVO member);
	
	String idCheck(String id);
	
	void cashUpdate(CashVO cashVO);
	
	//이번달 입출금 계좌'입금'총액 
	int depositBalanceThisMonth(String id);
	
	//이번달 적금 계좌 '입금' 총액
	int savingsBalanceThisMonth(String id);
	
	//이번달 입출금 계좌 '출금' 총액
	int withdralBalanceThisMonth(String id);
}
