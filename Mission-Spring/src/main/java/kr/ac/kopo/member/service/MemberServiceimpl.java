package kr.ac.kopo.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;

@Service
public class MemberServiceimpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public MemberVO login(MemberVO member) {
		
		return memberDAO.login(member);
	}

	@Override
	public void join(MemberVO member) {
		memberDAO.join(member);
		
	}

	@Override
	public String idCheck(String id) {
		String idCheckBool = memberDAO.idCheck(id);
		return idCheckBool;
	}

	@Override
	public int totalBalanceChange(String id) {
		// 이번달 입출금 계좌'입금'총액 + 이번달 적금 계좌 '입금' 총액 - 이번달 입출금 계좌 '출금' 총액 
		int depositBalanceThisMonth = memberDAO.depositBalanceThisMonth(id);
		int savingsBalanceThisMonth = memberDAO.savingsBalanceThisMonth(id);
		int withdralBalanceThisMonth = memberDAO.withdralBalanceThisMonth(id);
		return (depositBalanceThisMonth + savingsBalanceThisMonth - withdralBalanceThisMonth);
	}


	
}
