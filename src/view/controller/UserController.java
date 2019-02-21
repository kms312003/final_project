package view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import user.User;
import user.UserDBBean;
@Controller
public class UserController {
	
	HttpSession session;
	ModelAndView mv = new ModelAndView();
	
	@Autowired
	UserDBBean dbPro;
	
	@RequestMapping(value = "index")
	public String index() {

		return "user/index";
	}
	//1.회원가입 화면
	@RequestMapping(value="register")
	public String registerForm() throws Exception{
		return "mainView/user/registerForm";
	}
	//2. 회원가입 처리
	@RequestMapping(value="registerPro")
	public ModelAndView registerPro(User user) throws Exception{
		String email = user.getEmail();
		int check= 0;
		check = dbPro.emailCheck(email);
		if(email!=null&&check==1){
			dbPro.insertUser(user);
		}
		mv.addObject("check", check);
		mv.setViewName("mainView/user/register");
		return mv;
	}
	//3.로그인 화면
	@RequestMapping(value="login")
	public String loginForm() throws Exception{
		return "mainView/user/loginForm";
	}
	//4.로그인 처리
	@RequestMapping(value="loginPro")
	public ModelAndView loginPro(User user, HttpServletRequest request) throws Exception{
		String email = user.getEmail();
		String password = user.getPassword();
		int check =0;
		check=dbPro.loginCheck(email, password);
		HttpSession session = request.getSession();
		if(email!=null&&check==1){
			session.setAttribute("email", email);
		}
		mv.addObject("check", check);
		mv.setViewName("mainView/user/login");
		return mv;
		
	}
	//5.유저 정보 화면
	@RequestMapping(value="userInfo")
	public String userInfoForm(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		User user = dbPro.getUserE((String)session.getAttribute("email"));
		request.setAttribute("user", user);
		return "mainView/user/userInfoForm";
	}
	//6.유저 정보 수정 화면
	@RequestMapping(value="update")
	public ModelAndView updateForm(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		User user = dbPro.getUserE((String) session.getAttribute("email"));
		request.setAttribute("user", user);
		mv.addObject("user",user);
		mv.setViewName("mainView/user/updateForm");
		return mv;
		
	}
	//7.유저 정보 수정 처리
	@RequestMapping(value="updatePro")
	public ModelAndView updatePro(User user, HttpServletRequest request) throws Exception{
		UserDBBean dpPro = UserDBBean.getInstance();
		HttpSession session = request.getSession();
		User user2 = dbPro.getUserE((String) session.getAttribute("email"));
		user.setId(user2.getId());
		user.setEmail(user2.getEmail());
		dbPro.updateUser(user);
		mv.addObject("user", user);
		mv.setViewName("mainView/user/userInfoForm");
		return mv;
	}
	
	//8.유저 정보 삭제 화면
	@RequestMapping(value="delete")
	public String deleteForm() {
		return "mainView/user/deleteForm";
		
	}
	//9.유저 정보 삭제 처리
	@RequestMapping(value="deletePro")
	public String deletePro(String password, HttpServletRequest request){
		UserDBBean dbPro = UserDBBean.getInstance();
		HttpSession session = request.getSession();
		User user = dbPro.getUserE((String) session.getAttribute("email"));
		user.setId(user.getId());
		int id = user.getId();
		String dbPassword = user.getPassword();
		if(dbPassword.equals(password)){
			dbPro.deleteUser(id);
		}
		return "mainView/user/delete";
	}

}
