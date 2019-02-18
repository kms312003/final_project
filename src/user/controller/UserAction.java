package user.controller;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import admin.controller.Action;
import user.User;
import user.User.Gender;
import user.User.Job;
import user.UserDBBean;

public class UserAction extends Action {

	public String loginGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/user/loginForm.jsp";
	}

	public String loginPOST(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		User user = new User();
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		UserDBBean dbPro = UserDBBean.getInstance();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int check = 0;
		try {
			check = dbPro.loginCheck(email, password);
			HttpSession session = request.getSession();

			if (email != null && check == 1) {
				session.setAttribute("email", email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("check", check);
		System.out.println("check:" + check);
		return "/user/login.jsp";
	}

	public String registerGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/user/registerForm.jsp";
	}

	public String registerPOST(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		User user = new User();

		user.setEmail(request.getParameter("email"));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setGender(Gender.valueOf(request.getParameter("gender")));
		user.setBirth(Integer.parseInt(request.getParameter("birth")));
		user.setJob(Job.valueOf(request.getParameter("job")));
		//주소입력 추가분 시작
		user.setPostcode(request.getParameter("postcode"));
		user.setRoadAddress(request.getParameter("roadAddress"));
		user.setJibunAddress(request.getParameter("jibunAddress"));
		user.setDetailAddress(request.getParameter("detailAddress"));
		user.setExtraAddress(request.getParameter("extraAddress"));
		//주소입력 추가분 끝
		user.setPhoneNum(request.getParameter("phoneNum"));
		UserDBBean dbPro = UserDBBean.getInstance();
		String email = request.getParameter("email");
		int check = 0;
		try {

			check = dbPro.emailCheck(email);

			HttpSession session = request.getSession();

			if (email != null && check == 1) {
				dbPro.insertUser(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("check", check);
		System.out.println("email check:" + check);
		return "/user/register.jsp";
	}

	public String userInfoGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		UserDBBean dbPro = UserDBBean.getInstance();
		HttpSession session = request.getSession();
		User user = dbPro.getUserE((String) session.getAttribute("email"));
		request.setAttribute("user", user);
		System.out.println("user:" + user);
		return "/user/userInfoForm.jsp";
	}

	public String updateGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		UserDBBean dbPro = UserDBBean.getInstance();
		HttpSession session = request.getSession();
		User user = dbPro.getUserE((String) session.getAttribute("email"));
		request.setAttribute("user", user);
		System.out.println("user:" + user);
		return "/user/updateForm.jsp";
	}

	public String updatePOST(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		UserDBBean dbPro = UserDBBean.getInstance();
		HttpSession session = request.getSession();
		User user = dbPro.getUserE((String) session.getAttribute("email"));
		System.out.println("update user:"+user);
		
		/*user.setEmail(request.getParameter("email"));*/
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setGender(Gender.valueOf(request.getParameter("gender")));
		user.setBirth(Integer.parseInt(request.getParameter("birth")));
		user.setJob(Job.valueOf(request.getParameter("job")));	
		//주소입력 추가분 시작
		user.setPostcode(request.getParameter("postcode"));
		user.setRoadAddress(request.getParameter("roadAddress"));
		user.setJibunAddress(request.getParameter("jibunAddress"));
		user.setDetailAddress(request.getParameter("detailAddress"));
		user.setExtraAddress(request.getParameter("extraAddress"));
		//주소입력 추가분 끝
		user.setPhoneNum(request.getParameter("phoneNum"));
		dbPro.updateUser(user);
		return "/user/update.jsp";
	}

	public String deleteGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		UserDBBean dbPro = UserDBBean.getInstance();
		HttpSession session = request.getSession();
		User user = dbPro.getUserE((String) session.getAttribute("email"));
		int id = user.getId();
		String dbPassword = user.getPassword();
		System.out.println("deleteUser:" + user);
		System.out.println("deleteId:" + id);
		System.out.println("deletePw:" + dbPassword);
		return "/user/deleteForm.jsp";
	}

	public String deletePOST(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		UserDBBean dbPro = UserDBBean.getInstance();
		HttpSession session = request.getSession();
		User user = dbPro.getUserE((String) session.getAttribute("email"));
		int id = user.getId();
		String dbpassword = user.getPassword();
		String password = request.getParameter("password");
		try {
			if (dbpassword.equals(password))
				dbPro.deleteUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/user/delete.jsp";
	}
}
