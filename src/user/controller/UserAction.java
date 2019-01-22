package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		return "/user/loginPro.jsp";
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
		return "/user/registerPro.jsp";
	}

}
