package kr.ac.kopo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.ac.kopo.member.vo.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)	//handler 어떤 클래스의 어떤 메소드를 실행했는지 알고있는 객체
			throws Exception {
		
		if (handler instanceof HandlerMethod) {
	         HandlerMethod method = (HandlerMethod)handler;
	         
	      }
		
		//이런 것들로 로그파일 만들 수 있음. 몇시 몇분에 접근 했음.. 
	//	HandlerMethod method = (HandlerMethod)handler;	
	//	System.out.println("메소드 :" + method);
	//	System.out.println("controller :" + method.getBean());//이 메소드가 어느것을 호출하느냐 알려줌
		
		
		// board controller 가기 전에 실행됨. post는 controller에 있는 list메서드 return되면 실행됨. after는 응답이 완료된 후 호출되는 녀석!
		// 이거에 맞춰서 코드주입 해주는 것임!!

		
		//로그인 여부 체크
		HttpSession session = request.getSession();
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		
		if(loginVO == null) {
			
			String uri = request.getRequestURI();
			uri = uri.substring(request.getContextPath().length());
//			System.out.println(uri);
			
			String query = request.getQueryString();	//원래 ~/board/번호?no=번호 이렇게 오는데, 여기서 query ?뒤의 bonarNO=24
//				System.out.println(query);	//detail 뒤에것 ...
			
			if(query != null && query.length() != 0) {
				uri = uri + "?" + query;
			}
			
			session.setAttribute("dest", uri);
			
			
			response.sendRedirect(request.getContextPath() + "/login");
			
			return false; //false 리턴되면, write 컨트롤러로 안감?
		}
		
		return true;	
	}

	

	
	
}
