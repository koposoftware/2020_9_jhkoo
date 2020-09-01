package kr.ac.kopo.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.vo.BoardVO;

@Service
public class BoardServiceimpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;	//서비스가 DAO 호출함
	
	
	@Override
	public List<BoardVO> selectAllBoard() {
		
		List<BoardVO> boardList = boardDAO.selectAll();
		
		return boardList;
	}


	@Override
	public BoardVO selectBoardByNo(int no) {
		BoardVO board = boardDAO.selectByNo(no);
		return board;
	}


	@Override
	public void insertBoard(BoardVO board) {
		boardDAO.insert(board);
		
	}
	
	

}
