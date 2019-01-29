package board_controller;

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
import board_notice.BoardDBBeanMybatis;
import board_notice.BoardDataBean;

public class Notice_action extends Action {

	public String listGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String boardid = "1";
		HttpSession session = request.getSession();
		String inform = request.getParameter("inform");

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
		request.setAttribute("inform", inform);

		return "/board/notice/list.jsp";
	}

	public String writeGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

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
		return "/board/notice/writeUploadForm.jsp";
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
			article.setInform(multi.getParameter("inform"));

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
		return "/board/notice/writePro.jsp";
	}

	public String contentGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int num = Integer.parseInt(request.getParameter("num"));
		String no = request.getParameter("no");
		String inform = request.getParameter("inform");

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
		request.setAttribute("inform", inform);

		return "/board/notice/content.jsp";
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

		return "/board/notice/updateForm.jsp";
	}

	public String updatePOST(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		BoardDataBean article = new BoardDataBean();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setEmail(request.getParameter("email"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setPasswd(request.getParameter("passwd"));
		article.setInform(request.getParameter("inform"));

		BoardDBBeanMybatis dbPro = BoardDBBeanMybatis.getInstance();
		int x = 0;
		try {
			x = dbPro.updateArticle(article);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("x", x);
		return "/board/notice/updatePro.jsp";
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
		return "/board/notice/deleteForm.jsp";
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
		return "/board/notice/deletePro.jsp";
	}

}
