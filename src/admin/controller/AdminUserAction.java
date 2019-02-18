package admin.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;

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

public class AdminUserAction extends Action {

	// 리스트
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
		int end = currentPage * pageSize;

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

		return "/adminPage/user/userList.jsp";
	}

	// 입력
	public String writeGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pageNum = "";

		pageNum = request.getParameter("pageNum");

		return "/adminPage/user/userWriteForm.jsp";
	}

	public String writePOST(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String realFolder = "";
		String encType = "utf-8";
		int maxSize = 5 * 1024 * 1024;
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath("fileSave");

		MultipartRequest multi = null;

		try {
			multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());

			Enumeration files = multi.getFileNames();
			String filename = "";
			int filesize = 0;
			File file = null;

			if (files.hasMoreElements()) {
				String name = (String) files.nextElement();
				filename = multi.getFilesystemName(name);
				file = multi.getFile(name);
			}

			User user = new User();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			user.setEmail(multi.getParameter("email"));
			user.setName(multi.getParameter("name"));
			user.setPassword(multi.getParameter("password"));
			user.setGender(Gender.valueOf(multi.getParameter("gender")));
			user.setBirth(Integer.parseInt(multi.getParameter("birth")));
			user.setJob(Job.valueOf(multi.getParameter("job")));
			
			//주소입력 추가분 시작
			user.setPostcode(multi.getParameter("postcode"));
			user.setRoadAddress(multi.getParameter("roadAddress"));
			user.setJibunAddress(multi.getParameter("jibunAddress"));
			user.setDetailAddress(multi.getParameter("detailAddress"));
			user.setExtraAddress(multi.getParameter("extraAddress"));
			//주소입력 추가분 끝
			user.setPhoneNum(multi.getParameter("phoneNum"));
			UserDBBean dbPro = UserDBBean.getInstance();
			dbPro.insertUser(user);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/adminPage/user/userWrite.jsp";
	}

	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String no = request.getParameter("id");
		int id = Integer.parseInt(no);

		UserDBBean dbPro = UserDBBean.getInstance();
		User user = new User();

		try {
			user = dbPro.getUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("no", no);
		request.setAttribute("user", user);

		return "/adminPage/user/userDetail.jsp";
	}

	// 수정
	public String updateGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
/*		System.out.println("updateGet id check : " + id);
		String no = String.valueOf(id);*/
		System.out.println("updateGet no check : " + id);

		UserDBBean dbPro = UserDBBean.getInstance();
		User user = new User();
		try {
			user = dbPro.getUpdate(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		request.setAttribute("id", id);
		request.setAttribute("user", user);

		return "/adminPage/user/userUpdateForm.jsp";
	}

	public String updatePOST(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String realFolder = "";
		String encType = "utf-8";
		int maxSize = 5 * 1024 * 1024;
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath("fileSave");

		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
			/*String oldfilename = multi.getParameter("oldfilename");
			int oldfilesize = Integer.parseInt(multi.getParameter("oldfilesize"));*/

			Enumeration files = multi.getFileNames();
			String filename = "";
			int filesize = 0;
			File file = null;

			if (files.hasMoreElements()) {
				String name = (String) files.nextElement();
				filename = multi.getFilesystemName(name);
				file = multi.getFile(name);
			}
			
			User user = new User();

			user.setId(Integer.parseInt(multi.getParameter("id")));
			user.setEmail(multi.getParameter("email"));
			user.setName(multi.getParameter("name"));
			user.setPassword(multi.getParameter("password"));
			user.setGender(Gender.valueOf(multi.getParameter("gender")));
			user.setBirth(Integer.parseInt(multi.getParameter("birth")));
			user.setJob(Job.valueOf(multi.getParameter("job")));	
			
			//주소입력 추가분 시작
			user.setPostcode(multi.getParameter("postcode"));
			user.setRoadAddress(multi.getParameter("roadAddress"));
			user.setJibunAddress(multi.getParameter("jibunAddress"));
			user.setDetailAddress(multi.getParameter("detailAddress"));
			user.setExtraAddress(multi.getParameter("extraAddress"));
			//주소입력 추가분 끝
			user.setPhoneNum(multi.getParameter("phoneNum"));
			
			UserDBBean dbPro = UserDBBean.getInstance();
			dbPro.updateUser(user);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/adminPage/user/userUpdate.jsp";
	}

	// 삭제
	public String deleteGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));

		UserDBBean dbPro = UserDBBean.getInstance();

		try {
			dbPro.deleteUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/adminPage/user/userDelete.jsp";
	}
}
