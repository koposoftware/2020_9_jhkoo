package kr.ac.kopo.board.dao;

import java.util.List;

import kr.ac.kopo.board.vo.BoardVO;

public interface BoardDAO {

	/**
	 *  전체게시글 조회 서비스
	 */
	public List<BoardVO> selectAll();	//throws Exception 예외처리 잡아주는게 정석적인 방법
	
	
	/**
	 *  새 글 등록 서비스
	 */
	public void insert(BoardVO board);
	
	
	/**
	 *  게시글 상세 조회 서비스
	 	@param no 게시물 번호
	 */
	public BoardVO selectByNo(int no);
	
	
	/**
	 * 해당 게시물의 댓글 카운트 증가
	 * @param no 증가시킬 게시물 번호
	 */
	public void increamentReplyCnt(int no);
	
	public void reduceReplyCnt(int no);
	
}
