package board_controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import admin.controller.Action;
import board_information.BoardDBBeanMybatis;
import board_information.BoardDataBean;


@Controller
public class InformationController extends HttpServlet{
	
	HttpSession session;
	ModelAndView mv = new ModelAndView();
	
	@Autowired
	BoardDBBeanMybatis dbPro;
	
	@ModelAttribute
	public void addAttributes(
			@RequestParam(value="pageNum", defaultValue="0") int pageNum,
			@RequestParam(value="boardid", defaultValue="") String boardid,
			HttpServletRequest req){
		this.session = req.getSession();
		
		if(pageNum!=0)session.setAttribute("pageNum", pageNum);
		if(!boardid.equals(""))session.setAttribute("boardid", boardid);
		
		if(session.getAttribute("pageNum")==null)session.setAttribute("pageNum", 1);	
		if(session.getAttribute("boardid")==null)session.setAttribute("boardid", "1");
	}

/*	public String listGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String boardid = "1";
		HttpSession session = request.getSession();

		String pageNum = request.getParameter("pageNum");
		if (pageNum != null) {
			session.setAttribute(boardid + "pageNum", pageNum);
		}
		pageNum = (String) session.getAttribute(boardid + "pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;
		BoardDBBeanMybatis dbPro = BoardDBBeanMybatis.getInstance();

		int count = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		int number = 0;
		List articleList = null;

		try {
			count = dbPro.getArticleCount(boardid);
			number = count - ((currentPage - 1) * pageSize);

			if (count > 0) {
				articleList = dbPro.getArticleList(start, end, boardid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int bottomLine = 3;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		request.setAttribute("count", count);
		request.setAttribute("articleList", articleList);
		request.setAttribute("number", number);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageCount", pageCount);

		return "/view/board/information/list.jsp";
	}*/
	@RequestMapping(value="index")
	public String index() throws Exception {
		return "/index";
		
	}
	@RequestMapping(value="list")
	public ModelAndView list() throws Exception {
		
		int pageNum = (int) session.getAttribute("pageNum");
		String boardid = (String) session.getAttribute("boardid");
		int currentPage = pageNum;
		int pageSize = 10;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List articleList = null;
		
		BoardDBBeanMybatis dbPro = BoardDBBeanMybatis.getInstance();
		
		count = dbPro.getArticleCount(boardid);
		
		if (count > 0) { articleList = dbPro.getArticleList(startRow, endRow, boardid); }
		
		number = count - (currentPage - 1) * pageSize;
		
		int bottomLine = 3;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		
		if (endPage > pageCount) { endPage = pageCount; }
		
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("pageCount", pageCount);
		mv.addObject("bottomLine", bottomLine);	
		mv.addObject("count", count);
		mv.addObject("currentPage", currentPage);
		mv.addObject("pageSize", pageSize);
		mv.addObject("number", number);
		mv.addObject("articleList", articleList);
		mv.setViewName("information/list");
		return mv;
	}
	
	@RequestMapping(value="write")
	public ModelAndView writeForm(BoardDataBean article) throws Exception { 
		
		mv.clear();
		
		mv.addObject("num", article.getNum());
		mv.addObject("ref", article.getRef());
		mv.addObject("re_step", article.getRe_step());
		mv.addObject("re_level", article.getRe_level());
		mv.setViewName("information/writeUploadForm");
		
		return mv;
	}
	
	@RequestMapping(value="writePro")
	public String writePro(MultipartHttpServletRequest multipart, BoardDataBean article) throws Exception {
		
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");
		
		MultipartFile multi = multipart.getFile("uploadfile");
		String boardid = (String) session.getAttribute("boardid");
		String filename = multi.getOriginalFilename();
		
		if(filename != null && !filename.equals("")){
			FileCopyUtils.copy(multi.getInputStream(), new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			article.setFilename(filename);
			article.setFilesize((int)multi.getSize());
		} else {
			article.setFilename("");
			article.setFilesize(0);
		}
		
		article.setIp(multipart.getRemoteAddr());
		article.setBoardid(boardid);
		System.out.println(article);

		BoardDBBeanMybatis dbPro = BoardDBBeanMybatis.getInstance();
		dbPro.insertArticle(article, boardid);
		
		return "redirect:list";
	}

	
	@RequestMapping(value="content")
	public ModelAndView content(int num) throws Exception {

		BoardDataBean article = dbPro.getArticle(num);
		
		mv.addObject("article", article); 
		mv.setViewName("information/content");
		
		return mv;
	}
	
	@RequestMapping(value="update")
	public ModelAndView updateForm(int num) throws Exception {
		
		BoardDataBean article = dbPro.getArticle(num);
		
		mv.addObject("article", article);
		mv.setViewName("information/updateForm");
		
		return mv;
	}
	
	@RequestMapping(value="updatePro")
	public ModelAndView updatePro(BoardDataBean article) throws Exception {

		int x = dbPro.updateArticle(article);
		
		mv.addObject("x", x);
		mv.setViewName("information/updatePro");
		
		return mv;
	}
/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡdeleteㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
	@RequestMapping(value="delete")
	public ModelAndView deleteForm(int num) throws Exception {
		mv.clear();
		
		mv.addObject("num", num);
		mv.setViewName("information/deleteForm");
		
		return mv;
	}
	
	@RequestMapping(value="deletePro")
	public ModelAndView deletePro(int num, String passwd) throws Exception {
		mv.clear();
		
		int x = dbPro.deleteArticle(num, passwd);
		
		mv.addObject("x", x);
		mv.setViewName("information/deletePro");
		
		return mv;
	}
	
	

	/*public String writeGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int num = 0, ref = 1, re_step = 0, re_level = 0;
		String pageNum = "";
		if (request.getParameter("num") != null) {
			num = Integer.parseInt(request.getParameter("num"));
			pageNum = request.getParameter("pageNum");
			ref = Integer.parseInt(request.getParameter("ref"));
			re_step = Integer.parseInt(request.getParameter("re_step"));
			re_level = Integer.parseInt(request.getParameter("re_level"));
		}

		request.setAttribute("num", num);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);
		return "/view/board/information/writeUploadForm.jsp";
	}

	public String writePOST(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String realFolder = "";
		String encType = "euc-kr";
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

			BoardDataBean article = new BoardDataBean();

			article.setNum(Integer.parseInt(multi.getParameter("num")));
			article.setRef(Integer.parseInt(multi.getParameter("ref")));
			article.setRe_step(Integer.parseInt(multi.getParameter("re_step")));
			article.setRe_level(Integer.parseInt(multi.getParameter("re_level")));
			article.setWriter(multi.getParameter("writer"));
			article.setEmail(multi.getParameter("email"));
			article.setSubject(multi.getParameter("subject"));
			article.setPasswd(multi.getParameter("passwd"));
			article.setContent(multi.getParameter("content"));
			article.setBoardid("1");
			article.setIp(request.getRemoteAddr());

			if (file != null) {
				filesize = (int) file.length();
			} else {
				filesize = 0;
				filename = "";
			}

			article.setFilename(filename);
			article.setFilesize(filesize);
			System.out.println(article);
			article.setIp(request.getRemoteAddr());

			BoardDBBeanMybatis dbPro = BoardDBBeanMybatis.getInstance();
			dbPro.insertArticle(article, "1");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/view/board/information/writePro.jsp";
	}

	public String contentGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int num = Integer.parseInt(request.getParameter("num"));
		String no = request.getParameter("no");

		BoardDBBeanMybatis dbPro = BoardDBBeanMybatis.getInstance();
		BoardDataBean article = new BoardDataBean();
		try {
			article = dbPro.getArticle(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("article", article);
		request.setAttribute("no", no);
		return "/view/board/information/content.jsp";
	}

	public String updateGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));

		BoardDBBeanMybatis dbPro = BoardDBBeanMybatis.getInstance();
		BoardDataBean article = new BoardDataBean();
		try {
			article = dbPro.getUpdate(num);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("article", article);

		return "/view/board/information/updateForm.jsp";
	}

	public String updatePOST(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		BoardDataBean article = new BoardDataBean();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setEmail(request.getParameter("email"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setPasswd(request.getParameter("passwd"));

		BoardDBBeanMybatis dbPro = BoardDBBeanMybatis.getInstance();
		int x = 0;
		try {
			x = dbPro.updateArticle(article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("x", x);
		return "/view/board/information/updatePro.jsp";
	}

	public String deleteGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int num = Integer.parseInt(request.getParameter("num"));

		BoardDBBeanMybatis dbPro = BoardDBBeanMybatis.getInstance();
		BoardDataBean article = new BoardDataBean();
		try {
			article = dbPro.getUpdate(num);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("article", article);
		return "/view/board/information/deleteForm.jsp";
	}

	public String deletePOST(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		BoardDataBean article = new BoardDataBean();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setEmail(request.getParameter("email"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setPasswd(request.getParameter("passwd"));

		BoardDBBeanMybatis dbPro = BoardDBBeanMybatis.getInstance();

		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");

		int x = 0;
		try {
			x = dbPro.deleteArticle(num, passwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("x", x);
		return "/view/board/information/deletePro.jsp";
	}*/

}
