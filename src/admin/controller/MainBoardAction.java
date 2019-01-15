package admin.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mainboard.MainBoard;
import mainboard.MainBoardDBBean;

public class MainBoardAction extends Action {

	// ë¦¬ìŠ¤?Š¸
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

		MainBoardDBBean dbPro = MainBoardDBBean.getInstance();

		int count = 0;

		int number = 0;

		List mainBoardList = null;
		try {
			count = dbPro.getMainBoardCount();
			number = count - ((currentPage - 1) * pageSize);

			if (count > 0) {
				mainBoardList = dbPro.getMainBoardList(start, end);
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
		System.out.println("mainBoardList: " + mainBoardList);

		request.setAttribute("count", count);
		request.setAttribute("mainBoardList", mainBoardList);
		request.setAttribute("number", number);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "/mainboard/admin/mainBoardList.jsp";
	}

	// ?ž…? ¥
	public String writeGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pageNum = "";

		pageNum = request.getParameter("pageNum");

		return "/mainboard/admin/mainBoardForm.jsp";
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
//
			MainBoard mainboard = new MainBoard();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			// mainboard.setId(Long.parseLong(multi.getParameter("id")));
			mainboard.setProductName(multi.getParameter("productName"));
			mainboard.setProductCompany(multi.getParameter("productCompany"));
			mainboard.setCpuSocket(multi.getParameter("cpuSocket"));
			mainboard.setChipSet(multi.getParameter("chipSet"));
			mainboard.setFormFactor(multi.getParameter("formFactor"));
			mainboard.setMemoryType(multi.getParameter("memoryType"));
			mainboard.setProductSort(multi.getParameter("productSort"));
			mainboard.setMemorySlot(Integer.parseInt(multi.getParameter("memorySlot")));
			// mainboard.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			mainboard.setPrice(Integer.parseInt(multi.getParameter("price")));
			mainboard.setFilename(filename);
			if (file != null) {
				filesize = (int) file.length();
			}
			mainboard.setFilesize(filesize);

			MainBoardDBBean dbPro = MainBoardDBBean.getInstance();
			dbPro.insertMainBoard(mainboard);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/mainboard/admin/mainBoardWrite.jsp";
	}

	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String no = request.getParameter("id");
		Long id = Long.parseLong(no);

		MainBoardDBBean dbPro = MainBoardDBBean.getInstance();
		MainBoard mainboard = new MainBoard();

		try {
			mainboard = dbPro.getMainBoard(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("no", no);
		request.setAttribute("mainboard", mainboard);

		return "/mainboard/admin/mainBoardDetail.jsp";
	}

	public String updateGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		Long id = Long.parseLong(request.getParameter("id"));
		String no = String.valueOf(id);

		MainBoardDBBean dbPro = MainBoardDBBean.getInstance();
		MainBoard mainboard = new MainBoard();
		try {
			mainboard = dbPro.getUpdate(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + mainboard.getProductDate());
		Date productDate_date = mainboard.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("mainboard", mainboard);
		request.setAttribute("productDate", productDate);

		return "/mainboard/admin/mainBoardUpdateForm.jsp";
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
			String oldfilename = multi.getParameter("oldfilename");
			int oldfilesize = Integer.parseInt(multi.getParameter("oldfilesize"));

			Enumeration files = multi.getFileNames();
			String filename = "";
			int filesize = 0;
			File file = null;

			if (files.hasMoreElements()) {
				String name = (String) files.nextElement();
				filename = multi.getFilesystemName(name);
				file = multi.getFile(name);
			}

			MainBoard mainboard = new MainBoard();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			
			mainboard.setId(Long.parseLong(multi.getParameter("id")));
			mainboard.setProductName(multi.getParameter("productName"));
			mainboard.setProductCompany(multi.getParameter("productCompany"));
			mainboard.setCpuSocket(multi.getParameter("cpuSocket"));
			mainboard.setChipSet(multi.getParameter("chipSet"));
			mainboard.setFormFactor(multi.getParameter("formFactor"));
			mainboard.setMemoryType(multi.getParameter("memoryType"));
			mainboard.setProductSort(multi.getParameter("productSort"));
			mainboard.setMemorySlot(Integer.parseInt(multi.getParameter("memorySlot")));
			mainboard.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			mainboard.setPrice(Integer.parseInt(multi.getParameter("price")));
			if (file != null) {
				mainboard.setFilename(filename);
				filesize = (int) file.length();
				mainboard.setFilesize(filesize);
			} else {
				if (oldfilename != null) {
					mainboard.setFilename(oldfilename);
					mainboard.setFilesize(oldfilesize);
				}
			}
			
			MainBoardDBBean dbPro = MainBoardDBBean.getInstance();
			System.out.println("mainboard: " + mainboard);
			dbPro.updateMainBoard(mainboard);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/mainboard/admin/mainBoardUpdate.jsp";
	}

	public String deleteGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		Long id = Long.parseLong(request.getParameter("id"));

		MainBoardDBBean dbPro = MainBoardDBBean.getInstance();

		try {
			dbPro.deleteMainBoard(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/mainboard/admin/mainBoardDelete.jsp";
	}
}