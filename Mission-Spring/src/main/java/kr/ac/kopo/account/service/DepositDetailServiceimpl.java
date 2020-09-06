package kr.ac.kopo.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.account.dao.DepositDetailDAO;
import kr.ac.kopo.account.vo.DepositDetailVO;

@Service
public class DepositDetailServiceimpl implements DepositDetailService {

	@Autowired
	private DepositDetailDAO depositDetailDAO;
	
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
	public List<DepositDetailVO> infiniteScrollDown(DepositDetailVO depositDetailVO) {
		List<DepositDetailVO> infiniteScrollDown = depositDetailDAO.infiniteScrollDown(depositDetailVO);	
		return infiniteScrollDown;
	}
	
	

}
