package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.member.service.MemberService;
import kr.ac.kopo.member.vo.MemberVO;

/* @SessionAttributes({"loginVO"})
 * 
 * ModelAndView객체에 addObject하면 기본적으로 request영역에 저장됨
 * 근데 이렇게 지정해주면 ModelAndView영역에 저장되는 것들중 이름이 loginVO인 경우 session에 등록시켜라 라고 지정 가능.
 * 배열형태기 때문에 여러개 선언 가능
 * 
 * 대신 이렇게 하면 invalidate를 이용해서 session영역 삭제 불가능
 * SessionStatus status를 이용 -> false : session영역에 등록된 것이 있음
 * setComplete()로 session지우기가능
 * */
@SessionAttributes({"loginVO"})   
@Controller
public class MemberController {
   
   @Autowired
   private MemberService memberService;

   @GetMapping("/login")
   public String loginForm() {
      return "/login/login";
   }
   

   @PostMapping("/login")
   public ModelAndView login(MemberVO member, HttpSession session) {
      
      MemberVO loginVO = memberService.login(member);
      ModelAndView mav = new ModelAndView();
      
      // 로그인 실패
      if(loginVO == null) {
         mav.setViewName("redirect:/login");
      } 
      else {
         // 로그인 성공
         
         String dest = (String)session.getAttribute("dest");
         
         if(dest == null) { // 로그인 인터셉트의 preHandler를 거치지 않은 것. 로그인을 한 뒤에, 조건 걸어둔것을에 안걸리면(상세보기 하면), preHandler안거치니까 dest안생겨서 
            mav.setViewName("redirect:/");	//메인페이지로 가라
         } else {   // 로그인 인터셉트의 preHandler를 거친 것
            mav.setViewName("redirect:/" + dest);
            session.removeAttribute(dest);
         }

         mav.addObject("loginVO", loginVO);
      }
      return mav;
   }
   
   @RequestMapping("/logout")
   public String logout(SessionStatus status) {
      
      status.setComplete();
      
      return "redirect:/";
   }
   
   
   
   //회원가입 get, post mapping
 	@GetMapping("/join")
 	public String joinForm(Model model) {
 			
 		model.addAttribute("memberVO", new MemberVO());
 		
 		return "login/join";
 	}
 	
 	@PostMapping("/join")
 	public String join(@Valid MemberVO memberVO, BindingResult result) {
 		
 		if(result.hasErrors()) {
 			return "login/join";
 		}
 		memberService.join(memberVO);
 		return "redirect:/login";
 	}
 	
 	@ResponseBody
 	@GetMapping("/idCheck/{id}")
 	public boolean idCheck(@PathVariable("id") String id) {
 		
 		String idCheck = memberService.idCheck(id);
 		boolean idCheckBool;
 		if(idCheck == null) {
 			idCheckBool = true;
 		} else {
 			idCheckBool = false;
 		}

 		return idCheckBool;
 	}
 	
}
