package kr.ac.kopo.friend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.friend.dao.FriendDAO;
import kr.ac.kopo.friend.vo.FriendVO;

@Service
public class FriendServiceimpl implements FriendService {

	@Autowired
	private FriendDAO friendDAO; 
	
	@Override
	public void insertRequest(FriendVO friendVO) {
		friendDAO.insertRequest(friendVO);
	}

	@Override
	public List<FriendVO> getRequestList(String id) {
		List<FriendVO> requestList = friendDAO.getRequestList(id);
		return requestList;
	}

	@Override
	public List<FriendVO> getRequestedList(String id) {
		List<FriendVO> requestedList = friendDAO.getRequestedList(id);
		return requestedList;
	}

	@Override
	public void agreeRequest(FriendVO friendVO) {
		friendDAO.agreeRequest(friendVO);
	}
	
	@Override
	public void disAgreeREquest(FriendVO friendVO) {
		friendDAO.disAgreeRequest(friendVO);
	}

	@Override
	public List<String> getFriendsIdList(String id) {
		// 내가 보낸 요청 중 승인된 친구 아이디
		List<String> friendsIdList1 = friendDAO.getFriendsIdList1(id);
		// 내가 받은 요청 중 승인한 친구 아이디
		List<String> friendsIdList2 = friendDAO.getFriendsIdList2(id);
		// 리스트 합치기
		List<String> friendsIdList = new ArrayList<>();
		friendsIdList.addAll(friendsIdList1);
		friendsIdList.addAll(friendsIdList2);

		return friendsIdList;
	}

	@Override
	public List<String> getFriendsNameList(String id) {
		// 내가 보낸 요청 중 승인된 친구 이름
		List<String> friendsNameList1 = friendDAO.getFriendsNameList1(id);
		// 내가 받은 요청 중 승인한 친구 이름
		List<String> friendsNameList2 = friendDAO.getFriendsNameList2(id);
		// 리스트 합치기
		List<String> friendsNameList = new ArrayList<>();
		friendsNameList.addAll(friendsNameList1);
		friendsNameList.addAll(friendsNameList2);

		return friendsNameList;
	}

	@Override
	public void resetChallenge() {
		friendDAO.resetChallenge();
	}
	
}
