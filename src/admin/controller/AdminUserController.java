package admin.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import user.User;
import user.UserDBBean;

@Controller
public class AdminUserController {
	HttpSession session;
	ModelAndView mv = new ModelAndView();

	@Autowired
	UserDBBean dbPro;

	@ModelAttribute
	public void addAttributes(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			HttpServletRequest req) {
		this.session = req.getSession();

		if (pageNum != 0)
			session.setAttribute("pageNum", pageNum);

		if (session.getAttribute("pageNum") == null)
			session.setAttribute("pageNum", 1);
	}

	// 리스트
	@RequestMapping("/list")
	public ModelAndView list() throws Exception {

		int pageNum = (int) session.getAttribute("pageNum");
		int currentPage = pageNum;
		int pageSize = 5;
		int start = (currentPage - 1) * pageSize;
		int end = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List userList = null;

		count = dbPro.getUserCount();
		number = count - (currentPage - 1) * pageSize;

		if (count > 0) {
			userList = dbPro.getUserList(start, end);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		mv.addObject("count", count);
		mv.addObject("userList", userList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("pageCount", pageCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("adminPage/user/userList");

		return mv;
	}

	// 입력
	@RequestMapping("/write")
	public ModelAndView write(@RequestParam(value = "pageNum", defaultValue = "") String pageNum) throws Exception {

		mv.clear();
		mv.setViewName("adminPage/user/userWriteForm");

		return mv;
	}
	
	@RequestMapping("/writePro")
	public ModelAndView write(User user) throws Exception{
		dbPro.insertUser(user);
		mv.setViewName("adminPage/user/userWrite");
		return mv;
		
	}
	
	//수정
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam(value="id", defaultValue="0") int id) throws Exception{
		String no = String.valueOf(id);
		User user = dbPro.getUpdate(id);
		mv.addObject("no", no);
		mv.addObject("user", user);
		mv.setViewName("adminPage/user/userUpdateForm");
		return mv;
	}
	@RequestMapping("/updatePro")
	public ModelAndView updatePro(@RequestParam(value="id", defaultValue="0") int id, User user) throws Exception{
		String no = String.valueOf(id);
		User user2 = dbPro.getUpdate(id);
		user.setId(user2.getId());
		dbPro.updateUser(user);
		mv.addObject("no", no);
		mv.addObject("user", user);
		mv.setViewName("adminPage/user/userUpdate");
		return mv;
	}
	//삭제
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value="id", defaultValue="0") int id) throws Exception{
		mv.clear();
		dbPro.deleteUser(id);
		mv.setViewName("adminPage/user/userDelete");
		return mv;
	}
}
