package view.controller;

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
import board_opinion.BoardDBBeanMybatis;
import board_opinion.BoardDataBean;

@Controller
public class OpinionController extends HttpServlet {

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
		mv.setViewName("board/opinion/list");
		return mv;
	}
	
	@RequestMapping(value="write")
	public ModelAndView writeForm(BoardDataBean article) throws Exception { 
		
		mv.clear();
		
		mv.addObject("num", article.getNum());
		mv.addObject("ref", article.getRef());
		mv.addObject("re_step", article.getRe_step());
		mv.addObject("re_level", article.getRe_level());
		mv.setViewName("board/opinion/writeUploadForm");
		
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
		mv.setViewName("board/opinion/content");
		
		return mv;
	}
	
	@RequestMapping(value="update")
	public ModelAndView updateForm(int num) throws Exception {
		
		BoardDataBean article = dbPro.getArticle(num);
		
		mv.addObject("article", article);
		mv.setViewName("board/opinion/updateForm");
		
		return mv;
	}
	
	@RequestMapping(value="updatePro")
	public ModelAndView updatePro(BoardDataBean article) throws Exception {

		int x = dbPro.updateArticle(article);
		
		mv.addObject("x", x);
		mv.setViewName("board/opinion/updatePro");
		
		return mv;
	}
/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡdeleteㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
	@RequestMapping(value="delete")
	public ModelAndView deleteForm(int num) throws Exception {
		mv.clear();
		
		mv.addObject("num", num);
		mv.setViewName("board/opinion/deleteForm");
		
		return mv;
	}
	
	@RequestMapping(value="deletePro")
	public ModelAndView deletePro(int num, String passwd) throws Exception {
		mv.clear();
		
		int x = dbPro.deleteArticle(num, passwd);
		
		mv.addObject("x", x);
		mv.setViewName("board/opinion/deletePro");
		
		return mv;
	}
}
