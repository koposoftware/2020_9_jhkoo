package kr.ac.kopo.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.board.vo.BoardVO;

@Repository		//데이터베이스를 관리하는 애들은 보통 Repository 어노테이션 많이 씀
public class BoardDAOimpl implements BoardDAO{

	@Autowired	// db 접속하려는 세션객체 객체 가져옴!
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<BoardVO> selectAll() {
		
		List<BoardVO> boardList = sqlSession.selectList("board.dao.BoardDAO.selectAll");
		
		return boardList;
	}

	@Override
	public void insert(BoardVO board) {
		
		sqlSession.insert("board.dao.BoardDAO.insert", board);
		
	}

	@Override
	public BoardVO selectByNo(int no) {
		
		BoardVO board = sqlSession.selectOne("board.dao.BoardDAO.selectByNo", no);

		return board;
	}

	@Override
	public void increamentReplyCnt(int no) {
		sqlSession.update("board.dao.BoardDAO.increamentReplyCnt", no);	
	}

	@Override
	public void reduceReplyCnt(int no) {
		sqlSession.update("board.dao.BoardDAO.reduceReplyCnt", no);	
	}

	
	
}
