package kr.ac.kopo.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.reply.dao.ReplyDAO;
import kr.ac.kopo.reply.vo.ReplyVO;

@Service
public class ReplyServiceimpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDao;
	@Autowired
	private BoardDAO boardDao;
	
	/**
	 * 댓글 추가
	 * 1. DB에 a_board에 해당 레코드의 댓글 카운트 컬럼값을 1증가
	 * 2. DB에 a_reply에 새로운 댓글 추가
	 */
	@Transactional	//이것만 붙이면 트랜잭션 처리 할 수 있음. 
	@Override
	public void insertReply(ReplyVO replyVO) {
		boardDao.increamentReplyCnt(replyVO.getBoardNo());//jsp에서 받아온 replyVO 객체의 boardNo를 가져옴
		replyDao.insert(replyVO);
	}

	@Override
	public List<ReplyVO> selectAllByBoardNo(int boardNo) {
		
		List<ReplyVO> replyList = replyDao.selectAll(boardNo);
		return replyList;
	}

	/**
	 * 0. DA에서 삭제할 댓글의 게시물 번호 조회(a_reply)
	 * 1. DA에서 해당 댓글 삭제(a_reply)
	 * 2. DA에서 해당 게시물 댓글 카운트 감소(a_board)
	 */
	@Transactional
	@Override
	public void removeReply(int replyNo) {
		int boardNo = replyDao.selectBoardNo(replyNo);
		replyDao.delete(replyNo);
		boardDao.reduceReplyCnt(boardNo);
		
	}

	/* 2번째 방법.
	 * 1. DA에서 해당 댓글 삭제(a_reply)
	 * 2. DA에서 해당 게시물 댓글 카운트 감소(a_board)
	 */
	@Transactional
	@Override
	public void removeReply(ReplyVO replyVO) {
		replyDao.delete(replyVO.getNo());
		boardDao.reduceReplyCnt(replyVO.getBoardNo());
	}

	
	
	
}
