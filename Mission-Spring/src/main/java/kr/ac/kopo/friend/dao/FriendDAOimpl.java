package kr.ac.kopo.friend.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.friend.vo.FriendVO;

@Repository
public class FriendDAOimpl implements FriendDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void insertRequest(FriendVO friendVO) {
		sqlSession.insert("friend.dao.friendDAO.insertRequest", friendVO);
	}

	@Override
	public List<FriendVO> getRequestList(String id) {
		List<FriendVO> requestList = sqlSession.selectList("friend.dao.friendDAO.getRequestList", id);
		return requestList;
	}

	@Override
	public List<FriendVO> getRequestedList(String id) {
		List<FriendVO> requestedList = sqlSession.selectList("friend.dao.friendDAO.getRequestedList", id);
		return requestedList;
	}

	@Override
	public void agreeRequest(FriendVO friendVO) {
		sqlSession.update("friend.dao.friendDAO.agreeRequest", friendVO);	
	}
	
	@Override
	public void disAgreeRequest(FriendVO friendVO) {
		sqlSession.delete("friend.dao.friendDAO.disAgreeRequest", friendVO);	
	}

	@Override
	public List<String> getFriendsIdList1(String id) {
		List<String> friendsIdList1 = sqlSession.selectList("friend.dao.friendDAO.getFriendsIdList1",id);
		return friendsIdList1;
	}

	@Override
	public List<String> getFriendsIdList2(String id) {
		List<String> friendsIdList2 = sqlSession.selectList("friend.dao.friendDAO.getFriendsIdList2",id);
		return friendsIdList2;
	}

	@Override
	public List<String> getFriendsNameList1(String id) {
		List<String> friendsNameList1 = sqlSession.selectList("friend.dao.friendDAO.getFriendsNameList1",id);
		return friendsNameList1;
	}

	@Override
	public List<String> getFriendsNameList2(String id) {
		List<String> friendsNameList2 = sqlSession.selectList("friend.dao.friendDAO.getFriendsNameList2",id);
		return friendsNameList2;
	}

	@Override
	public void resetChallenge() {
		sqlSession.delete("friend.dao.friendDAO.resetChallenge");
	}

}
