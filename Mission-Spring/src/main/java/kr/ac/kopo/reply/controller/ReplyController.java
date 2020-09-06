package kr.ac.kopo.reply.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.reply.service.ReplyService;
import kr.ac.kopo.reply.vo.ReplyVO;

@CrossOrigin		// 뭐냐??
@RestController		// 이게 붙은 모든 클래스의 메서드들은 포워드하지 않고, 여기 컨트롤러에서 직접 모든 응답을 줄거야!
public class ReplyController {
// ReponseBoby와 같지만, RestController는 클래스 자체에 적용하는것. ResponseBody는 메서드만.

	@Autowired
	private ReplyService replyService;
	
	//댓글 입력
	@PostMapping("/reply")
	public void addReply(ReplyVO replyVO) {
		replyService.insertReply(replyVO);
	}
	//   /WEB-INF/jsp/reply.jsp 찾는데 없어서 fail뜸..
	
	
	@GetMapping("/reply/{boardNo}")
	   public List<ReplyVO> getList(@PathVariable("boardNo") int boardNo){
	      
		List<ReplyVO> replyList = replyService.selectAllByBoardNo(boardNo);
	      return replyList;
	   }
	
	@DeleteMapping("/reply/{replyNo}")	//지울 ㄹ댓글 번호는 알지만, 그것이 어떤 게시글 번호에 것인지 모름...
	public void deleteReply(@PathVariable("replyNo") int replyNo) { 
		replyService.removeReply(replyNo); // 1번 방법. db에서 직접 boardNo 가져오기
	}
	
	@DeleteMapping("/reply/{replyNo}/{boardNo}")
	public void deleteReply(@PathVariable("replyNo") int replyNo, 
			                @PathVariable("boardNo") int boardNo) {
		ReplyVO replyVO = new ReplyVO();
		replyVO.setNo(replyNo);
		replyVO.setBoardNo(boardNo);
		
		
		replyService.removeReply(replyVO);
	}
}
