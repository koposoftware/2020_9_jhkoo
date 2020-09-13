package kr.ac.kopo.eda.dao;

import java.util.List;

import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.eda.vo.EdaVO;

public interface EdaDAO {

	// 이번달 카테고리별 지출액 리스트
	public List<EdaVO> amountByType(String accountNumber);
	
	// 해당 카테고리의 이번달 내역
	public List<DepositDetailVO> detailListByCotegory(DepositDetailVO depositDetailVO);
	
	// 해당 카테고리의 이번달 + 저번달 내역
		public List<DepositDetailVO> detailListByCotegory2(DepositDetailVO depositDetailVO);
		
	
	// 이번 달 카테고리별 지출 합
	public List<DepositDetailVO> categorySumList(String accountNumber);
}
