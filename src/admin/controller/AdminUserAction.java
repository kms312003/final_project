package admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.controller.Action;
import cpu.CpuDBBean;
import user.User;
import user.User.Gender;
import user.User.Job;
import user.UserDBBean;

public class AdminUserAction extends Action {

	public String listGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		HttpSession session = request.getSession();

		String pageNum = request.getParameter("pageNum");
		if (pageNum != null) {
			session.setAttribute("pageNum", pageNum);
		}

		pageNum = (String) session.getAttribute("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 5;
		int start = (currentPage - 1) * pageSize;
		int end = currentPage * pageSize - 1;

		System.out.println("start: " + start);
		System.out.println("end: " + end);

		UserDBBean dbPro = UserDBBean.getInstance();

		int count = 0;

		int number = 0;

		List userList = null;
		try {
			count = dbPro.getUserCount();
			number = count - ((currentPage - 1) * pageSize);

			if (count > 0) {
				userList = dbPro.getUserList(start, end);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		System.out.println("count: " + count);
		System.out.println("number: " + number);
		System.out.println("userList: " + userList);

		request.setAttribute("count", count);
		request.setAttribute("userList", userList);
		request.setAttribute("number", number);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "/user/admin/userList.jsp";
	}

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
