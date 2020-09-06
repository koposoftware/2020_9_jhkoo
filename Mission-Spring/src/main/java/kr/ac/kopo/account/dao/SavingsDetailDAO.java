package kr.ac.kopo.account.dao;

import java.util.List;

import kr.ac.kopo.account.vo.SavingsDetailVO;

public interface SavingsDetailDAO {

	List<SavingsDetailVO> detailList(String accountNumber);
}
