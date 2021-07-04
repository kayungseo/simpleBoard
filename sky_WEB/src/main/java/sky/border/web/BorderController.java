package sky.border.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sky.border.service.BorderService;
import sky.main.web.MainController;

@Controller
public class BorderController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@Resource(name="BorderService")BorderService borderService;
	
	@RequestMapping(value="/borderWrite.do")
	public String borderWrite(HttpServletRequest request, Model model) throws Exception {
		String userId = "";
				
		if(request.getSession().getAttribute("USER_ID") == null){//사용자가 로그인을 하지 않았다면 ><
			request.getSession().invalidate();//session 무효화(비로그인 상태)
			return "redirect:/login.do";//그후 사용자를 로그인 페이지로 보냄 
		}else{//세션에 아이디가 있다면 
			userId = request.getSession().getAttribute("USER_ID").toString();
		}
		model.addAttribute("userId", userId);
		return "border/borderWrite";
	}
	
	@RequestMapping(value="/borderInsert.do")
	public String borderInsert(HttpServletRequest request, Model model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String title = request.getParameter("title").toString();//name값과 동일하게 
		String mytextarea = request.getParameter("mytextarea").toString();
		String userId = "";
		
		//javascript 유효성 검사앞서서 진행한 이후 (이는 해커 등으로 부터 유효화 됨)
		//서버단에서 재검증 
		if(title.length()>15){
			return "redirect:/borderWrite.do";
		}else if(mytextarea.length()>2000){
			return "redirect:/borderWrite.do";
		}else if(request.getSession().getAttribute("USER_ID") == null){//이외 클래스로 sesstion 에 데이터 담을 수 있음 
			request.getSession().invalidate();
			return "redirect:/login.do";
		}else{
			userId = request.getSession().getAttribute("USER_ID").toString();
			paramMap.put("userId", userId);
			paramMap.put("userIp", request.getRemoteAddr());
			paramMap.put("title", title);
			paramMap.put("mytextarea", mytextarea);
		}
		borderService.insertBorder(paramMap);//service에 요청 

		return "redirect:/borderList.do";

	}

}
