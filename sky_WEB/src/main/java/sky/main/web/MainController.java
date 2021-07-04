package sky.main.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sky.main.service.MainService;

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@Resource(name="MainService")MainService mainService;
	
	@RequestMapping(value="/main.do")
	public String main(HttpServletRequest request, Model model) throws Exception{
		System.out.println("Hello World!");

		return "main/main";
	}
	
	@RequestMapping(value="/main2.do")
	public String main2(HttpServletRequest request, Model model) throws Exception{
		return "main/main2";
	}
	
	@RequestMapping(value="/main3.do")
	public String main3(@RequestParam("pw")String pw, HttpServletRequest request, ModelMap model) throws Exception{
		//RequestParam : 사용자가 입력한 name의 값을 파라미터로 받음 
		//hidden name
		int userNo = Integer.parseInt(request.getParameter("userNo").toString());
		String id = request.getParameter("id").toString();
		if(id.equals("sky")){
			//model.addAttribute("", arg1);//ModelMap(key, value)
			model.addAttribute("id", id);
		}else{
			model.addAttribute("id", id);
		}
		
		userNo = userNo + 5;
		model.addAttribute("userNo", userNo);
		
		return "main/main3";
	}
	
	/*@RequestMapping(value="/main4/{userNo}.do")
	public String main4(@PathVariable String userNo, HttpServletRequest request, ModelMap model) throws Exception{
		//일반적으로 DB와 연결하여 값을 처리함 
		model.addAttribute("userNo", userNo);
		return "main/main3";
	}*/
	
	/*다시 하기(sql로 안넘어가는 문제)*/
	/*@RequestMapping(value="/main5.do")
	public String main5(HttpServletRequest request, ModelMap model) throws Exception{
		String userid = "";
		//db요청, 결과 받는 각 해쉬맵
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = mainService.selectMain();
		
		System.out.println("resultMap : " + resultMap);
		userid = resultMap.get("USER_ID").toString();
		
		System.out.println("serverid : "+userid);
		model.addAttribute("serverid", userid);
		
		return "main/main3";
	}*/
	
	@RequestMapping(value="/login.do")
	public String login(HttpServletRequest request, ModelMap model) throws Exception{
		
		return "login/login";
	}
	
	//sql까지 왜 안가는 거지....설정이 잘 못 되었나...확인해보자 
	/*@RequestMapping(value="/loginSubmission.do")
	public String main6(HttpServletRequest request, ModelMap model) throws Exception{
		
		String userId = request.getParameter("id").toString();
		//1차 체크.javaScript로 유효성 2차체크 
		if(userId.length()>10){
			return "redirect:/login.do";
		}
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		paraMap.put("userId", userId);
		resultMap = mainService.selectLogin(paraMap);
		if(null == resultMap){
			return "redirect:/login.do";
		}
		request.getSession().setAttribute("USER_ID", userId);
		
		return "main/main4";
	}*/
	
	/*@RequestMapping(value="/loginSubmission.do")
	public String main6(HttpServletRequest request, ModelMap model) throws Exception{
		
		String userId = request.getParameter("id").toString();
		//1차 체크.javaScript로 유효성 2차체크 
		if(userId.length()>10){
			return "redirect:/login.do";
		}
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		paraMap.put("userId", userId);
		System.out.println("selectLogin 진입");
		resultMap = mainService.selectLogin(paraMap);
		System.out.println("selectLogin 결과?");
		if(null == resultMap){
			System.out.println("일치하는 아이디가 없습니다.");
			return "redirect:/login.do";
		}
		request.getSession().setAttribute("USER_ID", userId);
		
		return "main/main4";
	}*/

	
	@RequestMapping(value="/loginSubmission.do")
	public String main6(HttpServletRequest request, ModelMap model) throws Exception{
		
		String userId = request.getParameter("id").toString();
		//1차 체크.javaScript로 유효성 2차체크 
		if(userId.length()>10){
			return "redirect:/login.do";
		}
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
//		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		paraMap.put("userId", userId);
		paraMap.put("ref_cursor", null);
		
		mainService.selectLogin2(paraMap);
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		list = (ArrayList<HashMap<String, Object>>)paraMap.get("ref_cursor");
		
		if(list.size() == 0){
			return "redierct:/login.do";
		}else {
			userId = list.get(0).get("USER_ID").toString();
		}
		request.getSession().setAttribute("USER_ID", userId);
		logger.info("ST접속정보 기록===============================");
		logger.info("유저아이디"+userId+", 접속날짜");
		logger.info("ED접속정보 기록===============================");
		return "main/main4";
	}
	
	@RequestMapping(value="/main4.do")
	public String main4(HttpServletRequest request, ModelMap model) throws Exception {
		return "main/main4";
	}
	
	@RequestMapping(value="/exception.do")
	public String exception(HttpServletRequest request, ModelMap model) throws Exception {
		System.out.println("이건 테스트");
		throw new Exception("사용자임의의 에러발생");
	}
	
	
}
