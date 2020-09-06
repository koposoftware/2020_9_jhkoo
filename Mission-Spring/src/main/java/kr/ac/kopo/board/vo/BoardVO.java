package kr.ac.kopo.board.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class BoardVO {

	private int no;
	
	@Length(min = 2, max=100, message = "제목은 2글자 이상입력하여야합니다")	//NotEmpty 여기서 걸림 !
//	@NotEmpty(message="필수항목입니다.")
	private String title;
	@Pattern(regexp = "^[A-Za-z0-9]*$", message = "특수기호는 사용할 수 없습니다.")	//이렇게 하면 대문자, 소문자, 숫자만 입력가능. 특수기호 막을 수 있음!. 자바 정규식 찾아보기. 주민등록번호 등 이걸로 ~
	@NotEmpty(message="필수항목입니다.")
	private String writer;
	@NotEmpty(message="필수항목입니다.")
	private String content;
	
	private String viewCnt;
	private String regDate;
	
	
	
	
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(String viewCnt) {
		this.viewCnt = viewCnt;
	}
	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", writer=" + writer + ", content=" + content + ", viewCnt="
				+ viewCnt + ", regDate=" + regDate + "]";
	}
	
	
	
	
	
	
}
