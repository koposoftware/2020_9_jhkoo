package kr.ac.kopo.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.account.dao.SavingsDetailDAO;
import kr.ac.kopo.account.vo.SavingsDetailVO;

@Service
public class SavingsDetailServiceimpl implements SavingsDetailService {

	@Autowired
	private SavingsDetailDAO savingsDetailDAO;
	
	@Override
	public List<SavingsDetailVO> savingsDetailList(String accountNumber) {
		List<SavingsDetailVO> savingsDetailList = savingsDetailDAO.detailList(accountNumber);
		return savingsDetailList;
	}

	
}
